/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.rest;

import cgc.rrhh.contratos.model.RrhhActividad;
import cgc.rrhh.contratos.model.RrhhLaboral;
import cgc.rrhh.contratos.model.RrhhPerfil;
import cgc.rrhh.contratos.pojo.ResultsActividad;
import cgc.rrhh.contratos.service.ActividadPerfilService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ejmorales
 */
@Stateless
@RolesAllowed("rrhh_contrato")
@Path("actividades")
public class ActividadPerfilREST {
    
    @EJB
    private ActividadPerfilService actividadPerfilService;
     
    @GET
    @Path("/perfiles/ubicacion")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RrhhPerfil> getAllPerfilesActivosByUbicacion(
            @QueryParam("idUbicacion") Integer idUbicacion) throws Exception{
        return actividadPerfilService.findAllByUbicacion(idUbicacion);
    }
    
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    public List<ResultsActividad> getAllActividades(@QueryParam("perfil") BigDecimal idPerfil,
            @QueryParam("rue") BigDecimal rue){
        List<ResultsActividad> actividad = new ArrayList<ResultsActividad>();
        try {
            if(rue != null){
                RrhhLaboral laboral = actividadPerfilService.findContratoByRue(rue);
                if(laboral != null && laboral.getIdContrato() != null 
                        && laboral.getIdContrato().getIdContrato() != null){
                    actividad = actividadPerfilService
                            .findAllActividadesByContrato(idPerfil, laboral.getIdContrato().getIdContrato());
                }else{
                    actividad = actividadPerfilService.findAllActividadesByPerfil(idPerfil);
                }
            }else if (idPerfil != null){
                actividad = actividadPerfilService.findAllActividadesByPerfil(idPerfil);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return actividad;
    }
    
    
}
