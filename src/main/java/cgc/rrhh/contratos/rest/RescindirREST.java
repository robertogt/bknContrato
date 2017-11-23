/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.rest;

import cgc.rhh.contratos.util.Constants;
import cgc.rhh.contratos.util.ResponseData;
import cgc.rrhh.contratos.model.RrhhLaboral;
import cgc.rrhh.contratos.model.RrhhMovimientosPresupuesto;
import cgc.rrhh.contratos.service.ContratoService;
import cgc.rrhh.contratos.service.GeneralService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
    private GeneralService generalService;
    
    
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
                    SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy");
                    RrhhMovimientosPresupuesto diferencia = new RrhhMovimientosPresupuesto();
                    Contrato diff = new Contrato(laboral.getIdContrato().getCorrelativoContrato().intValue(),
                            laboral.getHonorario().doubleValue(),
                            format.format(laboral.getFechaInicio()),
                            fechaFin,
                            format.format(laboral.getFechaCambioTipoMovimiento()));
                    
                    
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
}
