/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.rest;

import cgc.rhh.contratos.util.Constants;
import cgc.rhh.contratos.util.ResponseData;
import cgc.rrhh.contratos.model.RrhhAcuerdoAprobacion;
import cgc.rrhh.contratos.model.RrhhAcuerdoContrato;
import cgc.rrhh.contratos.model.RrhhContratoEstado;
import cgc.rrhh.contratos.model.RrhhLaboral;
import cgc.rrhh.contratos.pojo.PersistAcuerdoAprobacion;
import cgc.rrhh.contratos.pojo.ResultAcuerdoAprobacion;
import cgc.rrhh.contratos.pojo.ResultsContrato;
import cgc.rrhh.contratos.service.AcuerdoAprobacionService;
import cgc.rrhh.contratos.service.ContratoService;
import cgc.rrhh.contratos.service.GeneralService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import org.apache.log4j.Logger;

/**
 *
 * @author jrroquel
 */
@Stateless
@Path(Constants.ACUERDO_APROBACION)
public class AcuerdoAprobacionREST {
    
    @EJB
    private AcuerdoAprobacionService acuerdoAprobacionService;
    
    @EJB
    private GeneralService generalService;
    
    @EJB
    private ContratoService contratoService;
    
    private static final Logger log = Logger.getLogger(AcuerdoAprobacionREST.class);
    
    @POST
    @RolesAllowed("rrhh_contrato")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData<RrhhAcuerdoAprobacion> crearAcuerdoAprobacion(ResultAcuerdoAprobacion acuerdoAprobacion,
            @Context SecurityContext sc){
        Calendar now = Calendar.getInstance();
        ResponseData response = new ResponseData();
        response.setCode(403);
        response.setMessage("Error al obtener la información");
        
        try {
            if(acuerdoAprobacion != null && this.validate(acuerdoAprobacion)){
                //String usuario = "S/U";
                String usuario = sc.getUserPrincipal().getName().toUpperCase();
                BigDecimal correlativo = acuerdoAprobacionService
                            .findCorrelativo(acuerdoAprobacion.getRenglon(), acuerdoAprobacion.getTipoServicios(),
                                             String.valueOf(now.get(Calendar.YEAR)));
        
                String identificadorAcuerdo = generarIdentificadorAcuerdo(acuerdoAprobacion.getRenglon(), acuerdoAprobacion.getTipoServicios(), correlativo.toString());
             
                RrhhAcuerdoAprobacion newAcuerdoAprobacion = new RrhhAcuerdoAprobacion();
                newAcuerdoAprobacion.setAnio(BigInteger.valueOf(now.get(Calendar.YEAR)));
                newAcuerdoAprobacion.setEstado(Constants.ACTIVO);
                newAcuerdoAprobacion.setFechaInsert(new Date());
                newAcuerdoAprobacion.setIdentificadorAcuerdo(identificadorAcuerdo);
                newAcuerdoAprobacion.setNumeroAcuerdo(BigInteger.valueOf(correlativo.intValue()));
                if(acuerdoAprobacion.getObservaciones() != null && !acuerdoAprobacion.getObservaciones().isEmpty()){
                    newAcuerdoAprobacion.setObservaciones(URLDecoder.decode(acuerdoAprobacion.getObservaciones(),"UTF-8"));
                }
                newAcuerdoAprobacion.setRenglon(generalService.getRenglonById(acuerdoAprobacion.getRenglon()));
                newAcuerdoAprobacion.setTipoServicios(acuerdoAprobacion.getTipoServicios());
                newAcuerdoAprobacion.setUsuarioInsert(usuario);
                
                PersistAcuerdoAprobacion newAcuerdosContrato = 
                        this.generarAcuerdosContrato(usuario,
                                acuerdoAprobacion.getContratos());
                
                
                
                RrhhAcuerdoAprobacion generado = acuerdoAprobacionService.crearAcuerdoAprobacion(newAcuerdoAprobacion, newAcuerdosContrato);
                
                if(generado != null && generado.getIdAcuerdoAprobacion() != null){
                    response.setCode(200);
                    response.setData(generado);
                    response.setMessage("Acuerdo "+generado.getIdentificadorAcuerdo()+" generado con éxito.");
                }
            }
             
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage("Error interno del servidor");            
            log.error("crearAcuerdoAprobacion: ",e);
        }
        
        return response;
    }
    
    private List<RrhhContratoEstado> updateContratoEstado(){
        List<RrhhContratoEstado> estados = new ArrayList<RrhhContratoEstado>();
        try {
            
        } catch (Exception e) {
            log.info(estados);
        }
        return estados;
    }
    
    private PersistAcuerdoAprobacion generarAcuerdosContrato(String usuario, List<ResultsContrato> acuerdoAprobacion){
        PersistAcuerdoAprobacion persist = new PersistAcuerdoAprobacion();
        
        List<RrhhLaboral> laboral = new ArrayList<RrhhLaboral>();
        List<RrhhContratoEstado> update = new ArrayList<RrhhContratoEstado>();
        List<RrhhContratoEstado> create = new ArrayList<RrhhContratoEstado>();
        List<RrhhAcuerdoContrato> acuerdos = new ArrayList<RrhhAcuerdoContrato>();
        try {
            for(ResultsContrato contrato:acuerdoAprobacion){
                RrhhAcuerdoContrato newAcuerdo = new RrhhAcuerdoContrato();
                newAcuerdo.setEstado(Constants.ACTIVO);
                newAcuerdo.setFechaInsert(new Date());
                newAcuerdo.setIdContrato(generalService.findContratoById(contrato.getIdContrato()));
                newAcuerdo.setUsuarioInsert(usuario);
                acuerdos.add(newAcuerdo);
                
                RrhhLaboral find = contratoService.findLaboralByContrato(contrato.getIdContrato());
                if(find != null){
                    find.setEstado(Constants.ACTIVO);
                    find.setUsuarioUpdate(usuario);
                    find.setFechaUpdate(new Date());
                    laboral.add(find);
                }else{                    
                    throw new Exception("Error al consultar el contrato: "+contrato.getIdContrato());
                }
                
                RrhhContratoEstado estado = generalService.findActiveByContrato(contrato.getIdContrato());
                if(estado != null){
                    estado.setEstado("F");
                    estado.setUsuarioUpdate(usuario);
                    estado.setFechaUpdate(new Date());
                    update.add(estado);
                    
                    RrhhContratoEstado newEstado = new RrhhContratoEstado();
                    newEstado.setDocumento(estado.getDocumento());
                    newEstado.setEstado(Constants.ACTIVO);
                    newEstado.setFechaInsert(new Date());
                    newEstado.setIdCatalogoEstado(generalService.findEstadoById(BigDecimal.valueOf(5)));
                    newEstado.setIdContrato(estado.getIdContrato());
                    newEstado.setUsuarioInsert(usuario);
                    create.add(newEstado);
                }else{
                    throw new Exception("Error al consultar estado del contrato: "+contrato.getIdContrato());                    
                }
                
            }
            
            persist.setAcuerdo(acuerdos);
            persist.setCreate(create);
            persist.setLaboral(laboral);
            persist.setUpdate(update);
        }catch(Exception ex){
            log.error("generarAcuerdosContrato",ex);
            persist = null;
        }
        
        return persist;
                
    }
    
    private String generarIdentificadorAcuerdo(String renglon, String tipoServicios, String correlativo){
        try {
            Calendar now = Calendar.getInstance();
            
            if(correlativo == null)
                throw new Exception("Ocurrio un error con el correlativo. ");
            
            
            StringBuilder builder = new StringBuilder();
            builder.append("AA-RRHH");
            builder.append("-");
            if(tipoServicios.equalsIgnoreCase("T")){
                builder.append("ST");
            }else{
                builder.append("SP");
            }
            builder.append("-");
            builder.append(generalService.getRenglonById(renglon).getNombre());
            builder.append("-");
            builder.append(correlativo);
            builder.append("-");
            builder.append(now.get(Calendar.YEAR));
            
            System.out.println(builder.toString());
            return builder.toString();
        } catch (Exception e) {
            log.error("generarIdentificadorAcuerdo",e);
            return null;
        }
    }
    
    private boolean validate(ResultAcuerdoAprobacion acuerdoAprobacion){
        boolean eval = true;
        try {
            
            if(acuerdoAprobacion.getRenglon() == null){
                eval = false;
            }else if(acuerdoAprobacion.getRenglon().isEmpty()){
                eval = false;
            }
            
            if(acuerdoAprobacion.getTipoServicios() == null){
                eval = false;
            }else if(acuerdoAprobacion.getTipoServicios().isEmpty()){
                eval = false;
            }
            
            if(acuerdoAprobacion.getContratos() == null){
                eval = false;
            }else if(acuerdoAprobacion.getContratos().isEmpty()){
                eval = false;
            }
            
            
        } catch (Exception e) {
            log.error("error al validar: ",e);
        }
        return eval;
    }
    
            
}
