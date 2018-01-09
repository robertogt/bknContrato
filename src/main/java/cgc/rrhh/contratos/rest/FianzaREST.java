/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.rest;

import cgc.rhh.contratos.util.Constants;
import cgc.rhh.contratos.util.ResponseData;
import cgc.rrhh.contratos.model.RrhhContratoEstado;
import cgc.rrhh.contratos.model.RrhhLaboral;
import cgc.rrhh.contratos.service.AsesorService;
import cgc.rrhh.contratos.service.ContratoService;
import cgc.rrhh.contratos.service.GeneralService;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import org.apache.log4j.Logger;

/**
 *
 * @author ejmorales
 */
 @Stateless
 @RolesAllowed("rrhh_contrato")
 @Path(Constants.FIANZA)
public class FianzaREST {
     
     @EJB
     private ContratoService contratoService;
     
     @EJB
     private GeneralService generalService;
    
     private static final Logger log = Logger.getLogger(FianzaREST.class);
     
     @POST
     @Produces(MediaType.APPLICATION_JSON)
     public ResponseData guardarFianza(@FormParam("contrato") BigDecimal contrato,
             @FormParam("fianza") String fianza,
             @Context SecurityContext sc){
         ResponseData response = new ResponseData();
         response.setCode(403);
         response.setMessage("Error al validar la informacion");
         try {
             if(contrato != null && fianza != null && !fianza.isEmpty()){
                 //String usuario = "S/U";
                 String usuario = sc.getUserPrincipal().getName().toUpperCase();
                 RrhhLaboral laboral = contratoService.findLaboralByContrato(contrato);
                 if(laboral == null)
                     throw new Exception("Laboral es nulo");
                 
                 laboral.setNumeroFianza(fianza);
                 
                 boolean cambio = false;
                 RrhhContratoEstado contratoEstado = generalService
                         .findActiveByContrato(laboral.getIdContrato().getIdContrato());
                 RrhhContratoEstado newContratoEstado = null;
                 
                 if(contratoEstado.getIdCatalogoEstado().getIdCatalogoEstado().equals(BigDecimal.valueOf(3))){
                     cambio = true;
                 }
                 
                 if(contratoEstado.getIdCatalogoEstado().getIdCatalogoEstado().equals(BigDecimal.valueOf(1))){
                     cambio = true;
                 }
                 
                 if(cambio){
                     contratoEstado.setEstado("F");
                     contratoEstado.setUsuarioUpdate(usuario);
                     contratoEstado.setFechaUpdate(new Date());
                     
                     newContratoEstado = new RrhhContratoEstado();
                     newContratoEstado.setEstado(Constants.ACTIVO);
                     newContratoEstado.setFechaInsert(new Date());
                     newContratoEstado.setUsuarioInsert(usuario);
                     newContratoEstado.setIdCatalogoEstado(generalService.findEstadoById(BigDecimal.valueOf(2)));
                     newContratoEstado.setIdContrato(laboral.getIdContrato());
                 }
                 
                 contratoService.createFianza(laboral, contratoEstado, newContratoEstado, cambio);
                 response.setCode(200);
                 response.setMessage("Se Creo con éxito la fianza para el contrato: "+laboral.getNumeroContrato());
             }
         } catch (Exception e) {
             response.setCode(500);
             response.setMessage("Error Interno del Servidor");
             log.error("Error al guardar Fianza para el contrato: "+contrato,e);
         }
         return response;
     }
}
