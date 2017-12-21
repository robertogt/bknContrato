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
import cgc.rrhh.contratos.model.RrhhContrato;
import cgc.rrhh.contratos.model.RrhhContratoEstado;
import cgc.rrhh.contratos.model.RrhhLaboral;
import cgc.rrhh.contratos.pojo.PersistAcuerdoAprobacion;
import cgc.rrhh.contratos.pojo.PersistAcuerdoContrato;
import cgc.rrhh.contratos.pojo.ResponseAcuerdoContrato;
import cgc.rrhh.contratos.pojo.ResultAcuerdoAprobacion;
import cgc.rrhh.contratos.pojo.ResultsContrato;
import cgc.rrhh.contratos.service.AcuerdoAprobacionService;
import cgc.rrhh.contratos.service.AsesorService;
import cgc.rrhh.contratos.service.ContratoService;
import cgc.rrhh.contratos.service.GeneralService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

/**
 *
 * @author ejmorales
 */
@Stateless
@Path(Constants.MANTENIMIENTO)
public class MantenimientoREST {
    
    @EJB
    private AcuerdoAprobacionService acuerdoAprobacionService;
    
    @EJB
    private ContratoService contratoService;
    
    @EJB
    private GeneralService generalService;
    
    private static final Logger log = Logger.getLogger(MantenimientoREST.class);
    
    @GET
    @Path(Constants.ACUERDOS)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RrhhAcuerdoAprobacion> listAcuerdoAprobacion(){
        List<RrhhAcuerdoAprobacion> acuerdos = new ArrayList<RrhhAcuerdoAprobacion>();
        try {
            Calendar now = Calendar.getInstance();
            
            return acuerdoAprobacionService.listAllAcuerdos(BigInteger.valueOf(now.get(Calendar.YEAR)));
        } catch (Exception e) {
            log.error("listAcuerdoAprobacion: ",e);
        }
        return acuerdos;
    }
    
    @GET
    @Path(Constants.CONTRATOS)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseAcuerdoContrato listAcuerdoAprobacion(@QueryParam("acuerdo") BigDecimal acuerdo){
      //  List<RrhhAcuerdoContrato> acuerdos = new ArrayList<RrhhAcuerdoContrato>();
        ResponseAcuerdoContrato acuerdos = new ResponseAcuerdoContrato();
        try {
            RrhhAcuerdoAprobacion acuerdoAprobacion = acuerdoAprobacionService.findAcuerdoById(acuerdo);
            
            if(acuerdoAprobacion == null)
                throw new Exception("acuerdoAprobacion es nulo");
            
            acuerdos.setTarget(acuerdoAprobacionService.listAllContratosAcuerdo(acuerdoAprobacion.getIdAcuerdoAprobacion()));            
            acuerdos.setSource(acuerdoAprobacionService.findAcuerdosByRenglonTipoAnio(                    
                    acuerdoAprobacion.getRenglon().getRenglon(),
                    acuerdoAprobacion.getTipoServicios(), acuerdoAprobacion.getAnio().toString(),
                    acuerdoAprobacion.getIdAcuerdoAprobacion()));
        } catch (Exception e) {
            log.error("listAcuerdoAprobacion: ",e);
        }
        return acuerdos;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData modificarAcuerdoContrato(ResultAcuerdoAprobacion acuerdoAprobacion){
        ResponseData response = new ResponseData();        
        response.setCode(403);
        response.setMessage("Error al modificar la informacion");
        try {
            String usuario = "S/U";
            if(acuerdoAprobacion.getContratos() != null && !acuerdoAprobacion.getContratos().isEmpty()
                    && acuerdoAprobacion.getIdAcuerdoAprobacion() != null){
                List<RrhhAcuerdoContrato> rrhhAcuerdoContratos = acuerdoAprobacionService
                        .listAllContratosByAcuerdo(acuerdoAprobacion.getIdAcuerdoAprobacion());
                
                if(rrhhAcuerdoContratos == null)
                    throw new Exception("error al consultar contratos del acuerdo: "+acuerdoAprobacion.getIdAcuerdoAprobacion());                
                
                PersistAcuerdoContrato contrato = this.setPersistAcuerdoContrato(usuario,
                        rrhhAcuerdoContratos, acuerdoAprobacion);
                acuerdoAprobacionService.updateAcuerdo(contrato);
                response.setCode(200);
                response.setMessage("Acuerdo "+acuerdoAprobacion.getIdAcuerdoAprobacion()+" modificado correctamente");
            }   
            
        } catch (Exception e) {
            log.error("modificarAcuerdoContrato: ",e);
            response.setCode(500);
            response.setMessage("Error interno del servidor");
        }
        return response;
    }
    
    @POST
    @Path(Constants.ANULAR)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData anularAcuerdoAprobacion(@FormParam("acuerdo") BigDecimal acuerdo){
        ResponseData response = new ResponseData();
        response.setCode(403);
        response.setMessage("Error Al obtener la información");
        try {
            if(acuerdo != null){
                String usuario =  "S/U";
                RrhhAcuerdoAprobacion acuerdoAprobacion = acuerdoAprobacionService.findAcuerdoById(acuerdo);
                
                if(acuerdoAprobacion == null)
                    throw new Exception("No se encontro el acuerdoAprobacion");
                
                List<RrhhAcuerdoContrato> rrhhAcuerdoContratos = acuerdoAprobacionService
                        .listAllContratosByAcuerdo(acuerdoAprobacion.getIdAcuerdoAprobacion());
                
                if(rrhhAcuerdoContratos == null)
                    throw new Exception("Ocurrio un error al cargar los contratos. ");
                
                if(rrhhAcuerdoContratos.isEmpty())
                    throw new Exception("Ocurrio un error al cargar los contratos. ");
                
                PersistAcuerdoAprobacion persistir = this.setPersistAcuerdoAprobacion(acuerdoAprobacion,
                        rrhhAcuerdoContratos, usuario);
                
                acuerdoAprobacionService.anularAcuerdo(persistir);
                response.setCode(200);
                response.setMessage("Acuerdo: "+acuerdoAprobacion.getIdentificadorAcuerdo()+" anulado correctamente.");
                
            }
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage("Error interno del servidor");
            log.error("anularAcuerdoAprobacion",e);
        }
        return response;
    }
    
    private PersistAcuerdoContrato setPersistAcuerdoContrato(String usuario,
            List<RrhhAcuerdoContrato> rrhhAcuerdoContratos,
            ResultAcuerdoAprobacion acuerdoAprobacion){
        PersistAcuerdoContrato persist = new PersistAcuerdoContrato();
        List<RrhhAcuerdoContrato> update = new ArrayList<RrhhAcuerdoContrato>();
        List<RrhhAcuerdoContrato> create = new ArrayList<RrhhAcuerdoContrato>();
        List<RrhhLaboral> laboral = new ArrayList<RrhhLaboral>();
        List<RrhhContratoEstado> crearEstado = new ArrayList<RrhhContratoEstado>();
        List<RrhhContratoEstado> modifEstado = new ArrayList<RrhhContratoEstado>();
        try {
            for(RrhhAcuerdoContrato contrato: rrhhAcuerdoContratos){
                boolean eval = false;
                boolean up = false;
                for(ResultsContrato tmp: acuerdoAprobacion.getContratos()){
                    if(contrato.getIdContrato().getIdContrato().equals(tmp.getIdContrato())){
                        if(!contrato.getEstado().equalsIgnoreCase(Constants.ACTIVO)){
                            up = true;
                        }
                        eval = true;
                    }
                }
               
                RrhhLaboral tmpLaboral = contratoService.findLaboralByContrato(contrato.getIdContrato().getIdContrato());
                RrhhContratoEstado modifEst = generalService.findActiveByContrato(contrato.getIdContrato().getIdContrato());                                    
                
                if(eval){
                    if(up){
                            RrhhAcuerdoContrato newAcuerdo = new RrhhAcuerdoContrato();
                                newAcuerdo.setIdAcuerdoAprobacion(contrato.getIdAcuerdoAprobacion());
                                newAcuerdo.setIdContrato(contrato.getIdContrato());
                                newAcuerdo.setEstado(Constants.ACTIVO);
                                newAcuerdo.setUsuarioInsert(usuario);
                                newAcuerdo.setFechaInsert(new Date());
                                create.add(newAcuerdo);
                                
                             tmpLaboral.setEstado(Constants.ACTIVO);
                             tmpLaboral.setUsuarioUpdate(usuario);
                             tmpLaboral.setFechaUpdate(new Date());
                             
                             laboral.add(tmpLaboral);
                             
                             modifEst.setEstado("F");
                             modifEst.setUsuarioUpdate(usuario);
                             modifEst.setFechaUpdate(new Date());
                             
                             modifEstado.add(modifEst);
                             
                             RrhhContratoEstado newEst = new RrhhContratoEstado();
                             newEst.setDocumento(modifEst.getDocumento());
                             newEst.setEstado("A");
                             newEst.setFechaInsert(new Date());
                             newEst.setIdCatalogoEstado(generalService.findEstadoById(BigDecimal.valueOf(5)));
                             newEst.setIdContrato(contrato.getIdContrato());                             
                             if(modifEst.getObservacion() != null && !modifEst.getObservacion().isEmpty()){
                                 newEst.setObservacion(modifEst.getObservacion());
                             }
                             newEst.setUsuarioInsert(usuario);
                             crearEstado.add(newEst);
                    }
                }else{
                         contrato.setEstado(Constants.ANULADO);
                         contrato.setUsuarioUpdate(usuario);
                         contrato.setFechaUpdate(new Date());
                         update.add(contrato);
                         
                        tmpLaboral.setEstado(Constants.INACTIVO);
                        tmpLaboral.setUsuarioUpdate(usuario);
                        tmpLaboral.setFechaUpdate(new Date());
                        
                        laboral.add(tmpLaboral);
                        
                        modifEst.setEstado("F");
                        modifEst.setUsuarioUpdate(usuario);
                        modifEst.setFechaUpdate(new Date());
                          
                        modifEstado.add(modifEst);
                        
                        RrhhContratoEstado newEst = new RrhhContratoEstado();
                             newEst.setDocumento(modifEst.getDocumento());
                             newEst.setEstado("A");
                             newEst.setFechaInsert(new Date());
                             newEst.setIdCatalogoEstado(generalService.findEstadoById(BigDecimal.valueOf(4)));
                             newEst.setIdContrato(contrato.getIdContrato());                             
                             if(modifEst.getObservacion() != null && !modifEst.getObservacion().isEmpty()){
                                 newEst.setObservacion(modifEst.getObservacion());
                             }
                             newEst.setUsuarioInsert(usuario);
                             crearEstado.add(newEst);
                }
                
                
                
               
                
            }
            
            
            
            for(ResultsContrato tmp: acuerdoAprobacion.getContratos()){
                boolean eval = false;                
                    for(RrhhAcuerdoContrato contrato:rrhhAcuerdoContratos){
                        if(contrato.getIdContrato().getIdContrato().equals(tmp.getIdContrato())){
                            eval = true;
                        }
                    }
                    
                     if(!eval){
                         RrhhContratoEstado modifEst = generalService.findActiveByContrato(tmp.getIdContrato());   
                         RrhhLaboral tmpLaboral = contratoService.findLaboralByContrato(tmp.getIdContrato());
                         
                            RrhhAcuerdoContrato newAcuerdo = new RrhhAcuerdoContrato();
                                newAcuerdo.setIdAcuerdoAprobacion(acuerdoAprobacionService
                                        .findAcuerdoById(acuerdoAprobacion.getIdAcuerdoAprobacion()));
                                newAcuerdo.setIdContrato(contratoService.find(tmp.getIdContrato()));
                                newAcuerdo.setEstado(Constants.ACTIVO);
                                newAcuerdo.setUsuarioInsert(usuario);
                                newAcuerdo.setFechaInsert(new Date());
                                create.add(newAcuerdo);                           
                                
                            tmpLaboral.setEstado(Constants.ACTIVO);
                            tmpLaboral.setFechaUpdate(new Date());
                            tmpLaboral.setUsuarioUpdate(usuario);
                            laboral.add(tmpLaboral);
                            
                            
                        modifEst.setEstado("F");
                        modifEst.setUsuarioUpdate(usuario);
                        modifEst.setFechaUpdate(new Date());
                          
                        modifEstado.add(modifEst);
                        
                        RrhhContratoEstado newEst = new RrhhContratoEstado();
                             newEst.setDocumento(modifEst.getDocumento());
                             newEst.setEstado("A");
                             newEst.setFechaInsert(new Date());
                             newEst.setIdCatalogoEstado(generalService.findEstadoById(BigDecimal.valueOf(5)));
                             newEst.setIdContrato(generalService.findContratoById(tmp.getIdContrato()));                             
                             if(modifEst.getObservacion() != null && !modifEst.getObservacion().isEmpty()){
                                 newEst.setObservacion(modifEst.getObservacion());
                             }
                             newEst.setUsuarioInsert(usuario);
                             crearEstado.add(newEst);
                     }                              
            }
            persist.setCreate(create);
            persist.setUpdate(update);
            persist.setLaboral(laboral);
            persist.setCrearEstado(crearEstado);
            persist.setModificarEstado(modifEstado);
             
        } catch (Exception e) {
            persist = null;
            log.error("setPersistAcuerdoContrato",e);
        }
        return persist;
    }
    
    
    public PersistAcuerdoAprobacion setPersistAcuerdoAprobacion(RrhhAcuerdoAprobacion acuerdo,
            List<RrhhAcuerdoContrato> contratos, String usuario){
        PersistAcuerdoAprobacion persist = new PersistAcuerdoAprobacion();
        
           List<RrhhAcuerdoContrato> updateContrato = new ArrayList<RrhhAcuerdoContrato>();
           List<RrhhLaboral> laboral = new ArrayList<RrhhLaboral>();
           List<RrhhContratoEstado> crear = new ArrayList<RrhhContratoEstado>();
           List<RrhhContratoEstado> modificar = new ArrayList<RrhhContratoEstado>();        
        try {
            acuerdo.setEstado(Constants.ANULADO);
            acuerdo.setFechaUpdate(new Date());
            acuerdo.setUsuarioUpdate(usuario);
            
            for(RrhhAcuerdoContrato contrato: contratos){
                contrato.setEstado(Constants.ANULADO);
                contrato.setFechaUpdate(new Date());
                contrato.setUsuarioUpdate(usuario);
                
                RrhhLaboral tmpLaboral = contratoService.findLaboralByContrato(contrato.getIdContrato().getIdContrato());
                tmpLaboral.setEstado(Constants.INACTIVO);
                tmpLaboral.setUsuarioUpdate(usuario);
                tmpLaboral.setFechaUpdate(new Date());
                
                RrhhContratoEstado estado = generalService.findActiveByContrato(contrato.getIdContrato().getIdContrato());
                estado.setEstado("F");
                estado.setFechaUpdate(new Date());
                estado.setUsuarioUpdate(usuario);
                
                if(estado == null)
                    throw new Exception("estado es null");
                
                RrhhContratoEstado newEstado = new RrhhContratoEstado();
                newEstado.setDocumento(estado.getDocumento());
                newEstado.setEstado(Constants.ACTIVO);
                newEstado.setFechaInsert(new Date());
                newEstado.setUsuarioInsert(usuario);
                newEstado.setIdCatalogoEstado(generalService.findEstadoById(BigDecimal.valueOf(4)));
                newEstado.setIdContrato(estado.getIdContrato());
                if(estado.getObservacion() != null && !estado.getObservacion().isEmpty()){
                    newEstado.setObservacion(estado.getObservacion());
                }
                
                laboral.add(tmpLaboral);
                modificar.add(estado);
                crear.add(newEstado);
                updateContrato.add(contrato);
            }
            
            persist.setAcuerdo(contratos);
            persist.setAprobacion(acuerdo);
            persist.setCreate(crear);
            persist.setLaboral(laboral);
            persist.setUpdate(modificar);
            
        } catch (Exception e) {
            persist = null;
            log.error("setPersistAcuerdoAprobacion",e);
        }
        return persist;
    }
}
