/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.rest;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhColegioProfesional;
import cgc.rrhh.contratos.model.RrhhTitulo;
import cgc.rrhh.contratos.pojo.ResultsTexto;
import cgc.rrhh.contratos.service.GenericAbstractService;
import cgc.rrhh.contratos.service.TitulosService;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@Path(Constants.TITULOS)
public class TitulosRest{

    @EJB
    private TitulosService titulosService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ResultsTexto> listAll(@QueryParam("texto") String texto){
        List<ResultsTexto> result = new ArrayList<ResultsTexto>();
        try {
            result = titulosService.listTitulosByTexto(URLDecoder.decode(texto,"UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
        }
        
        return result;
    }
    
    
    
}
