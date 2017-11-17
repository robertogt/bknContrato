/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.rest;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhColegioProfesional;
import cgc.rrhh.contratos.service.ColegioService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ejmorales
 */
@Stateless
@Path(Constants.COLEGIOS)
public class ColegiosRest{
    
    @EJB
    private ColegioService colegioService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RrhhColegioProfesional> listAll(){
        List<RrhhColegioProfesional> result = new ArrayList<RrhhColegioProfesional>();
        try {
            result = colegioService.findAllColegios();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
        }
        
        return result;
    }
    
    
  
}
