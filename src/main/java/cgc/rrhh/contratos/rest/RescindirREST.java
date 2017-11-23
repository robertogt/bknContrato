/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.rest;

import cgc.rhh.contratos.util.Constants;
import cgc.rhh.contratos.util.ResponseData;
import cgc.rrhh.contratos.model.RrhhHistoricoLaboral;
import cgc.rrhh.contratos.model.RrhhLaboral;
import cgc.rrhh.contratos.model.RrhhMovimientosPresupuesto;
import cgc.rrhh.contratos.service.ContratoService;
import cgc.rrhh.contratos.service.GeneralService;
import cgc.rrhh.contratos.service.RescindirService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import rrhh.calculos.contrato.Contrato;

/**
 *
 * @author ejmorales
 */
@Stateless
@Path(Constants.RESCINDIR)
public class RescindirREST {
    
    @EJB
    private ContratoService contratoService;
    
    @EJB
    private RescindirService rescindirService;
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData rescindirContrato(@FormParam("contrato") BigDecimal Contrato,
            @FormParam("fechaFin") String fechaFin){
        ResponseData response = new ResponseData();
        response.setCode(403);
        response.setMessage("Error al obtener la información");
        try {                        
            if(Contrato != null && fechaFin != null && !fechaFin.isEmpty()){
                RrhhLaboral laboral = contratoService.findLaboralByContrato(Contrato);
                RrhhMovimientosPresupuesto movimiento = contratoService.findMovimientoByContrato(laboral.getIdContrato().getIdContrato());
                if(laboral != null){
                    String usuario = "S/U";
                    SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy");
                    RrhhMovimientosPresupuesto diferencia = new RrhhMovimientosPresupuesto();
                    RrhhHistoricoLaboral historico = this.setHistoricoLaboral(usuario,laboral);
                    
                    Contrato diff = new Contrato(laboral.getIdContrato().getCorrelativoContrato().intValue(),
                            laboral.getHonorario().doubleValue(),
                            format.format(laboral.getFechaInicio()),
                            fechaFin,
                            format.format(laboral.getFechaCambioTipoMovimiento()));
                    BigDecimal montoRestante = movimiento.getMonto().add(BigDecimal.valueOf(diff.getMontoTotal()));
                    
                    laboral.setEstado("B");
                    laboral.setUsuarioUpdate(usuario);
                    laboral.setFechaUpdate(new Date());
                    
                    diferencia.setFechaInsert(new Date());
                    diferencia.setIdContrato(laboral.getIdContrato());
                    diferencia.setIdControlPresupuesto(movimiento.getIdControlPresupuesto());
                    diferencia.setMonto(montoRestante.abs());
                    diferencia.setUsuarioInsert(usuario);
                    
                    
                    rescindirService.rescindir(laboral, historico, diferencia);
                }
            }
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage("Internal Server Error");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
    
    private RrhhHistoricoLaboral setHistoricoLaboral(String usuario,RrhhLaboral laboral){
        RrhhHistoricoLaboral historico = new RrhhHistoricoLaboral();
        try {
            historico.setBonoProfesional(laboral.getBonoProfesional());
            historico.setDocumentoMovimiento(laboral.getDocumentoMovimiento());
            historico.setEdificio(laboral.getEdificio());
            historico.setEstado(laboral.getEstado());
            historico.setFechaAl(laboral.getFechaAl());
            historico.setFechaCambioTipoMovimiento(laboral.getFechaCambioTipoMovimiento());
            historico.setFechaDel(laboral.getFechaDel());
            historico.setFechaInicio(laboral.getFechaInicio());
            historico.setFechaInsert(laboral.getFechaInsert());
            historico.setFechaInsertHistorico(new Date());
            historico.setEstadoHistorico(Constants.ACTIVO);
            if(laboral.getFechaUpdate() != null){
                historico.setFechaUpdate(laboral.getFechaUpdate());
            }
            if(laboral.getFechaFin() != null){
                historico.setFechaFin(laboral.getFechaFin());
            }
            historico.setFuente(laboral.getFuente());
            historico.setHonorario(laboral.getHonorario());
            historico.setIdContrato(laboral.getIdContrato());
            historico.setIdRue(laboral.getIdRue());
            historico.setLaboral(laboral);
            historico.setNumeroContrato(laboral.getNumeroContrato());
            historico.setNumeroDocumento(laboral.getNumeroDocumento());
            if(laboral.getNumeroFianza() != null && !laboral.getNumeroFianza().isEmpty()){
                historico.setNumeroFianza(laboral.getNumeroFianza());
            }
            if(laboral.getObservacion() != null && !laboral.getObservacion().isEmpty()){
                historico.setObservacion(laboral.getObservacion());
            }
            historico.setPlazoContrato(laboral.getPlazoContrato());
            historico.setPuestoFuncional(laboral.getPuestoFuncional());
            historico.setPuestoNominal(laboral.getPuestoNominal());
            historico.setRenglon(laboral.getRenglon());
            historico.setTipoMovimiento(laboral.getTipoMovimiento());
            historico.setTipoServicios(laboral.getTipoServicios());
            historico.setUbicacionFuncional(laboral.getUbicacionFuncional());
            historico.setUsuarioInsert(laboral.getUsuarioInsert());
            historico.setUsuarioInsertHistorico(usuario);
            if(laboral.getUsuarioUpdate() != null && !laboral.getUsuarioUpdate().isEmpty()){
                historico.setUsuarioUpdate(laboral.getUsuarioUpdate());
            }
            
        } catch (Exception e) {
            historico = null;
        }
        return historico;
    }
}