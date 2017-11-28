/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.rest;

import cgc.rhh.contratos.util.Constants;
import cgc.rhh.contratos.util.ResponseData;
import cgc.rrhh.contratos.model.RrhhActividadContrato;
import cgc.rrhh.contratos.model.RrhhContrato;
import cgc.rrhh.contratos.model.RrhhContratoEstado;
import cgc.rrhh.contratos.model.RrhhControlPresupuesto;
import cgc.rrhh.contratos.model.RrhhHistoricoLaboral;
import cgc.rrhh.contratos.model.RrhhLaboral;
import cgc.rrhh.contratos.model.RrhhMovimientosPresupuesto;
import cgc.rrhh.contratos.pojo.ResultsActividad;
import cgc.rrhh.contratos.pojo.ResultsFuncionario;
import cgc.rrhh.contratos.service.ActividadPerfilService;
import cgc.rrhh.contratos.service.AddendumService;
import cgc.rrhh.contratos.service.AsesorService;
import cgc.rrhh.contratos.service.ContratoService;
import cgc.rrhh.contratos.service.GeneralService;
import cgc.rrhh.contratos.service.GenerarContrato;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import org.apache.log4j.Logger;
import rrhh.calculos.contrato.Contrato;

/**
 *
 * @author jrroquel
 */
@Stateless
@Path(Constants.ADDENDUM)
public class AddendumREST {
    
    @EJB
    private ActividadPerfilService actividadPerfilService;
    
    @EJB
    private AddendumService addendumService;
    
    @EJB
    private ContratoService contratoService;
    
    @EJB
    private GeneralService generalService;
    
    @EJB
    private AsesorService asesorService;
    
    //private Logger logger = Logger.getLogger("");
    private static final Logger log = Logger.getLogger(AddendumREST.class);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData<ResultsFuncionario> findContrato(@QueryParam("idContrato") BigDecimal idContrato){
        ResponseData response = new ResponseData();
        System.out.println(idContrato);
        try {
            System.out.println(idContrato);
            ResultsFuncionario funcionarioRue = addendumService.findContratoByid(idContrato);
            List<ResultsActividad> actividades = actividadPerfilService.findAllActividadesByContrato(funcionarioRue.getIdPerfil(), idContrato);
            funcionarioRue.setActividades(actividades);
            response.setCode(200);
            response.setData(funcionarioRue);
            response.setMessage("Response status OK");                
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage("Error interno del servidor..");
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
        }
        return response;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData createAddendum(ResultsFuncionario funcionario,
            @Context SecurityContext sc){
        ResponseData data = new ResponseData();
        data.setCode(403);
        data.setMessage("Error al cargar la Información");
        try {
            if(funcionario != null && this.validate(funcionario)){
                RrhhLaboral laboral = contratoService.findLaboralByContrato(funcionario.getIdContrato());
                if(laboral  != null){
                    if(laboral.getEstado().equalsIgnoreCase(Constants.ACTIVO)){
                        
                    
                    
                    String usuario = "S/U";
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    RrhhHistoricoLaboral historico = this.setHistoricoLaboral(usuario,laboral);
                    RrhhContrato contratoAddendum = this.setContrato(usuario, laboral);                    
                    RrhhMovimientosPresupuesto diferencia = new RrhhMovimientosPresupuesto();                    
                    RrhhMovimientosPresupuesto nuevo = new RrhhMovimientosPresupuesto();
                    List<RrhhActividadContrato> actividades =this.setActividades(usuario, funcionario);
                    
                    if(laboral.getHonorario().compareTo(BigDecimal.valueOf(funcionario.getHonorario())) != 0){
                        
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(format.parse(funcionario.getFechaCambioTipoMovimiento()));
                        cal.add(Calendar.DATE, -1);
                        log.info(format.format(cal.getTime()));
                        RrhhMovimientosPresupuesto movimiento = contratoService.findMovimientoByContrato(laboral.getIdContrato().getIdContrato());
                        Contrato diff = new Contrato(laboral.getIdContrato().getCorrelativoContrato().intValue(),
                                laboral.getHonorario().doubleValue(),
                                format.format(laboral.getFechaDel()), 
                                format.format(cal.getTime()),
                                format.format(laboral.getFechaCambioTipoMovimiento()));
                        log.info("fecha DEL: "+laboral.getFechaDel());
                        log.info("fechaMovimiento: "+funcionario.getFechaCambioTipoMovimiento());
                        log.info("DIFF: "+diff.getMontoTotal());
                        
                        Contrato sum = new Contrato(laboral.getIdContrato().getCorrelativoContrato().intValue(),
                                funcionario.getHonorario(),
                                funcionario.getFechaCambioTipoMovimiento(), 
                                format.format(laboral.getFechaAl()),
                                format.format(laboral.getFechaCambioTipoMovimiento()));
                        
                        BigDecimal sumLaboral = addendumService.findMontoByLaboral(laboral.getLaboral());
                        log.info("SUMLABORAL: "+sumLaboral);
                        BigDecimal diferenciaAnterior = sumLaboral.add(BigDecimal.valueOf(diff.getMontoTotal()));
                        log.info("DIFERENCIA SUMLABORAL - DIFF: "+diferenciaAnterior);
                        BigDecimal montoTotal = contratoService.findMontoTotal(movimiento.getIdControlPresupuesto().getIdControlPresupuesto());
                        log.info("MONTO TOTAL PRESUPUESTO: "+montoTotal);
                        BigDecimal montoReal = montoTotal.add(diferenciaAnterior.abs());
                        log.info("MONTO REAL: "+montoReal);
                        
                        BigDecimal montoContrato = BigDecimal.valueOf(sum.getMontoTotal());
                        log.info("MONTO CONTRATO TIEMPO RESTANTE: "+montoContrato);
                        
                            diferencia.setFechaInsert(new Date());
                            diferencia.setIdContrato(movimiento.getIdContrato());
                            diferencia.setIdControlPresupuesto(movimiento.getIdControlPresupuesto());
                            diferencia.setMonto(diferenciaAnterior.abs());
                            diferencia.setUsuarioInsert(usuario);
                        
                        if(montoContrato.compareTo(montoReal) != 1){
                            laboral.setHonorario(BigDecimal.valueOf(funcionario.getHonorario()));
                            
                            nuevo.setFechaInsert(new Date());
                            nuevo.setIdControlPresupuesto(movimiento.getIdControlPresupuesto());                            
                            nuevo.setMonto(montoContrato.negate());
                            nuevo.setUsuarioInsert(usuario);
                            
                        }else{
                            Calendar now = Calendar.getInstance();
                               List<RrhhControlPresupuesto> presupuestos = contratoService
                                  .getPresupuestoByAnioRenglon(String.valueOf(now.get(Calendar.YEAR)), funcionario.getRenglon());
                               
                               Iterator it = presupuestos.iterator();
                                boolean isPresupuesto = false;
                                RrhhControlPresupuesto fuente = new RrhhControlPresupuesto();
                                while(it.hasNext()){
                                    RrhhControlPresupuesto presupuesto = (RrhhControlPresupuesto)it.next();
                                    BigDecimal montoFinal = contratoService.findMontoTotal(presupuesto.getIdControlPresupuesto());
                                    if(montoContrato.compareTo(montoFinal) != 1){
                                        isPresupuesto = true;
                                        fuente = presupuesto;
                                        break;
                                    }
                                }
                                
                                if(isPresupuesto){
                                        laboral.setFuente(fuente.getIdFuenteFinanciamiento());
                                        laboral.setHonorario(BigDecimal.valueOf(funcionario.getHonorario()));
                                      
                                        nuevo.setFechaInsert(new Date());
                                        nuevo.setIdControlPresupuesto(fuente);                            
                                        nuevo.setMonto(montoContrato.negate());
                                        nuevo.setUsuarioInsert(usuario);
                                  }else{
                                        data.setCode(403);
                                        data.setMessage("Presupuesto insuficiente");
                                    }
                        }
                    }
                    
                    if(!laboral.getUbicacionFuncional().getUbicacionFuncional().equals(funcionario.getUbicacionFuncional() )){
                        laboral.setTipoMovimiento(generalService.getTipoMovimientoById(BigDecimal.valueOf(48)));
                        laboral.setUbicacionFuncional(generalService.getUbicacionFuncional(funcionario.getUbicacionFuncional()));                                                
                    }
                    
                        laboral.setFechaCambioTipoMovimiento(format.parse(funcionario.getFechaCambioTipoMovimiento()));
                        laboral.setObservacion("ADDENDUM AL CONTRATO +"+laboral.getNumeroContrato()+" SURTE EFECTO A PARTIR DEL "+funcionario.getFechaCambioTipoMovimiento());
                        laboral.setFechaUpdate(new Date());
                        laboral.setUsuarioUpdate(usuario);
                        
                        RrhhContratoEstado estadoAnterior = asesorService.findEstadoByContrato(laboral.getIdContrato().getIdContrato(),BigDecimal.valueOf(5));
                                   
                    addendumService.crearAddendum(usuario,laboral, contratoAddendum, diferencia, nuevo, actividades, historico,estadoAnterior);
                    data.setCode(200);
                    data.setMessage("ADDENDUM AL CONTRATO +"+laboral.getNumeroContrato()+" SURTE EFECTO A PARTIR DEL "+funcionario.getFechaCambioTipoMovimiento());
                    }else{
                        data.setMessage("El contrato debe estar de Alta para realizar un addendum");
                    }
                }
            }
        } catch (Exception e) {
            data.setCode(500);
            data.setMessage("Error Interno del Servidor");
        }
        return data;
    }
    
    private List<RrhhActividadContrato> setActividades(String usuario, ResultsFuncionario funcionario){
        List<RrhhActividadContrato> actividades = new ArrayList<RrhhActividadContrato>();
        try {
            for(ResultsActividad actividad: funcionario.getActividades()){
                if(actividad.isSeleccionado()){
                    RrhhActividadContrato actContrato = new RrhhActividadContrato();
                    actContrato.setEstado(Constants.ACTIVO);
                    actContrato.setFechaInsert(new Date());
                    actContrato.setIdActividad(actividadPerfilService.findActividadById(actividad.getIdActividad()));
                    actContrato.setIdPerfil(actividadPerfilService.findPerfilById(funcionario.getIdPerfil()));
                    actContrato.setUsuarioInsert(usuario);                    
                    
                    actividades.add(actContrato);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            actividades = null;
        }
        return actividades;
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
    
    private RrhhContrato setContrato(String usuario,RrhhLaboral laboral){
        RrhhContrato contrato = new RrhhContrato();
        try {
            Calendar now = Calendar.getInstance();
            
            contrato.setAnio(laboral.getIdContrato().getAnio());
            contrato.setAcademico(laboral.getIdContrato().getAcademico());
            contrato.setCorrelativoContrato(laboral.getIdContrato().getCorrelativoContrato());
            log.info(String.valueOf(now.get(Calendar.YEAR)));
            System.out.println(now.get(Calendar.YEAR));
            log.info(laboral.getRenglon().getRenglon());
            System.out.println(laboral.getRenglon().getRenglon());
            log.info(laboral.getTipoServicios());
            System.out.println(laboral.getTipoServicios());
            contrato.setIdPlantilla(generalService
                    .getPlantillaByRenglonAnio(String.valueOf(now.get(Calendar.YEAR)),
                            laboral.getRenglon().getRenglon(),
                            laboral.getTipoServicios(),
                            BigDecimal.valueOf(2)));
            
            
            //contrato.setIdPlantilla(generalService.getPlantillaByRenglonAnio("2017", "6", "T", BigDecimal.valueOf(2)));
            if(laboral.getIdContrato().getObservaciones() != null 
                    && !laboral.getIdContrato().getObservaciones().isEmpty()){
                contrato.setObservaciones(laboral.getIdContrato().getObservaciones());
            }
            contrato.setFechaInsert(new Date());
            contrato.setUsuarioInsert(usuario);
        } catch (Exception e) {
            contrato = null;
        }
        return contrato;
    }
    
    private boolean validate(ResultsFuncionario funcionario) throws Exception{
        boolean eval = true;
        try {
            if(funcionario.getActividades() == null){
                eval = false;
            }else if(funcionario.getActividades().isEmpty()){
                eval = false;
            }
            
            if(funcionario.getHonorario() == null){
                eval = false;
            }
            
            if(funcionario.getUbicacionFuncional() == null){
                eval = false;
            }
            
            if(funcionario.getIdPerfil() == null){
                eval = false;
            }
        } catch (Exception e) {
            throw new Exception();
        }
        return eval;
    }
    
}
