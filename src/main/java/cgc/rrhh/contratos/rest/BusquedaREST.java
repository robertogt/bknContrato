/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.rest;

import cgc.rhh.contratos.util.Constants;
import cgc.rhh.contratos.util.ResponseData;
import cgc.rrhh.contratos.model.RrhhActividad;
import cgc.rrhh.contratos.model.RrhhLaboral;
import cgc.rrhh.contratos.pojo.ResultsAcademico;
import cgc.rrhh.contratos.pojo.ResultsActividad;
import cgc.rrhh.contratos.pojo.ResultsContrato;
import cgc.rrhh.contratos.pojo.ResultsFuncionario;
import cgc.rrhh.contratos.service.ActividadPerfilService;
import cgc.rrhh.contratos.service.ContratoService;
import cgc.rrhh.contratos.service.GeneralService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import rrhh.calculos.contrato.Edad;

/**
 *
 * @author ejmorales
 */
@Stateless
@Path(Constants.CONSULTA)
public class BusquedaREST {
    
    @EJB
    private ActividadPerfilService actividadPerfilService;
    
    @EJB
    private ContratoService contratoService;
    
    private static final Logger log = Logger.getLogger(BusquedaREST.class);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ResultsContrato> findAllContratos(@QueryParam("anio") String anio){
        List<ResultsContrato> contratos = new ArrayList<ResultsContrato>();
        try {
            Calendar now = Calendar.getInstance();
            
            if(anio == null){                
                anio = String.valueOf(now.get(Calendar.YEAR));
            }
            
            contratos = contratoService.findAllContratosByAnio(anio);
            
        } catch (Exception e) {            
            log.error("Error BusquedaREST findAllContratos: ",e);
        }
        return contratos;
    }
    
    @GET
    @Path(Constants.ACTIVIDADES)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RrhhActividad> findAllActividadesByContrato(@QueryParam("contrato") BigDecimal contrato){
        List<RrhhActividad> actividades = new ArrayList<RrhhActividad>();
        try {
            
            actividades = actividadPerfilService.findActividadesByIdContrato(contrato);
            
        } catch (Exception e) {            
            log.error("Error BusquedaREST findAllContratos: ",e);
        }
        return actividades;
    }
    
    @GET
    @Path(Constants.EDIT)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData<ResultsContrato> findContrato(@QueryParam("contrato") BigDecimal contrato) {
        ResponseData response = new ResponseData();
        response.setCode(403);
        response.setMessage("Error al cargar la información ");
        try {
            if(contrato != null){
                ResultsContrato resultsContrato = contratoService.findEditContratoByIdContrato(contrato);
                if(resultsContrato != null){
                    
                     BigDecimal idPerfil = actividadPerfilService.getIdPerfilByContrato(resultsContrato.getIdContrato());
                     
                     if(idPerfil == null)
                         throw new Exception("idPerfil es nulo");
                     
                                List<ResultsActividad> actividades = actividadPerfilService.findAllActividadesByContrato(idPerfil, resultsContrato.getIdContrato());
                                if(!actividades.isEmpty()){
                                    resultsContrato.setIdPerfil(idPerfil);
                                    resultsContrato.setActividades(actividades);
                                }
                          
                            
                    ResultsAcademico resultsAcademico = contratoService.getAcademicoByContrato(resultsContrato.getIdContrato());
                    
                    
                    if(resultsAcademico == null)
                        throw new Exception("resultsAcademico is null");
                    
                    resultsContrato.setAcademico(resultsAcademico);
                    
                    response.setCode(200);
                    response.setMessage("Ok");
                    response.setData(resultsContrato);
                }
            }
        } catch (Exception e) {
            response.setCode(500);
            log.error("Error al buscar Contrato: "+contrato+" ",e);
        }
        return response;
    }
    
    @GET
    @Path(Constants.ACEPTADOS)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ResultsContrato> findAllAceptados(@QueryParam("renglon") BigDecimal renglon, @QueryParam("tipoServicios") String tipoServicios){
        List<ResultsContrato> contratos = new ArrayList<ResultsContrato>();
        try {
            contratos = contratoService.findContratosAceptados(renglon, tipoServicios);            
        } catch (Exception e) {            
            log.error("Error BusquedaREST findAllAprobados: ",e);
        }
        return contratos;
    }
}
