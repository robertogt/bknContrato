/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.rest;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhActividad;
import cgc.rrhh.contratos.pojo.ResultsContrato;
import cgc.rrhh.contratos.service.ActividadPerfilService;
import cgc.rrhh.contratos.service.ContratoService;
import cgc.rrhh.contratos.service.GeneralService;
import java.math.BigDecimal;
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
    @Path("/actividades")
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
}
