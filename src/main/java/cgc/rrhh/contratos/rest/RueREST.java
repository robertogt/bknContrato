/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.rest;

import cgc.rhh.contratos.util.Constants;
import cgc.rhh.contratos.util.ResponseData;
import cgc.rrhh.contratos.model.RrhhDepartamento;
import cgc.rrhh.contratos.model.RrhhMunicipio;
import cgc.rrhh.contratos.model.RrhhRue;
import cgc.rrhh.contratos.pojo.ResultsMunicipio;
import cgc.rrhh.contratos.service.GeneralService;
import cgc.rrhh.contratos.service.RueService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
@Path(Constants.RUE)
public class RueREST {
    
    @EJB
    private RueService rueService;
    
    @EJB
    private GeneralService generalService;
    
    private static final Logger log = Logger.getLogger(RueREST.class);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData<RrhhRue> getRueById(@QueryParam("rue") Long rue){
        ResponseData response = new ResponseData();
        response.setCode(403);
        response.setMessage("Error al consultar Rue.");
        try {
            if(rue != null){
                RrhhRue rrhhRue = rueService.findRueById(rue);
                if(rrhhRue == null)
                    throw new Exception("Error al consultar Rue");
                
                response.setCode(200);
                response.setMessage("OK");
                response.setData(rrhhRue);
            }            
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage("Error interno del servidor.");
            log.error("getRueById: ",e);
        }
        return response;
    }
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData modificarRue(RrhhRue rue,@Context SecurityContext sc){
        ResponseData response = new ResponseData();
        response.setCode(403);
        response.setMessage("Error al Modificar Rue");
        try {
            //String usuario = "S/U"; 
            String usuario = sc.getUserPrincipal().getName().toUpperCase();
            if(rue != null && rue.getIdRue() != null){
                RrhhRue rrhhRue = rueService.findRueById(rue.getIdRue());
                if(rrhhRue == null)
                    throw new Exception("Rue es Nulo");
                
              /*  RrhhMunicipio municipio = generalService.getMunicipioDepto(rue.getMunicipioVivienda(), rue.getDepartamentoVivienda());
                if(municipio == null)
                    throw new Exception("Error en RRhhMunicipio");*/
                rrhhRue.setCui(rue.getCui());
                rrhhRue.setGenero(rue.getGenero());
                rrhhRue.setDireccion(rue.getDireccion());
                rrhhRue.setMunicipioVivienda(rue.getMunicipioVivienda());
                rrhhRue.setDepartamentoVivienda(rue.getDepartamentoVivienda());
              //  rrhhRue.setRrhhMunicipio(municipio);
                rrhhRue.setZonaVivienda(rue.getZonaVivienda());
                rrhhRue.setEstadoCivil(rue.getEstadoCivil());
                rrhhRue.setFechaNacimiento(rue.getFechaNacimiento());
                rrhhRue.setUsuarioUpdate(usuario);
                rrhhRue.setFechaUpdate(new Date());
                
                rueService.modificarRue(rrhhRue);
                response.setCode(200);
                response.setMessage("Rue:"+rrhhRue.getIdRue()+" Modificado con exito.");
            }            
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage("Error interno del servidor.");
            log.error("modificarRue: ",e);
        }
        return response;
    }
    
    @GET
    @Path(Constants.MUNICIPIOS+Constants.DEPARTAMENTOS)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ResultsMunicipio> getMunicipioDepto(){
        return rueService.deptoMunicipio();
    }
    
    
    @GET
    @Path(Constants.DEPARTAMENTOS)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RrhhDepartamento> getDepartamentos(){
        return rueService.getDepartamentos();
    }
    
    @GET
    @Path(Constants.MUNICIPIOS)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RrhhMunicipio> findMunicipioDepto(@QueryParam("departamento") String departamento){
        try {
            if(departamento != null && !departamento.isEmpty()){
                return rueService.getMunicipiosByDepartamento(departamento);
            }
        } catch (Exception e) {
            log.error("findMunicipioDepto: ",e);
        }
        return new ArrayList<RrhhMunicipio>();
    }
    
}
