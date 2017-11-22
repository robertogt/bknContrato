/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.rest;

import cgc.rhh.contratos.util.Constants;
import cgc.rhh.contratos.util.ResponseData;
import cgc.rrhh.contratos.pojo.ResultsActividad;
import cgc.rrhh.contratos.pojo.ResultsFuncionario;
import cgc.rrhh.contratos.service.ActividadPerfilService;
import cgc.rrhh.contratos.service.AddendumService;
import cgc.rrhh.contratos.service.ContratoService;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jrroquel
 */
@Stateless
@Path(Constants.ADDENDUM)
public class AddendumREST {
    
    @EJB
    private ActividadPerfilService actividadPerfilService;
    
    @EJB
    private AddendumService addendumService;
    
    @EJB
    private ContratoService contratoService;
    
    @GET
    @Path(Constants.CONTRATO)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData findContrato(@QueryParam("idContrato") BigDecimal idContrato){
        ResponseData response = new ResponseData();
        System.out.println(idContrato);
        try {
            System.out.println(idContrato);
            ResultsFuncionario funcionarioRue = addendumService.findContratoByid(idContrato);
            List<ResultsActividad> actividades = actividadPerfilService.findAllActividadesByContrato(funcionarioRue.getIdPerfil(), idContrato);
            funcionarioRue.setActividades(actividades);
            response.setCode(200);
            response.setData(funcionarioRue);
            response.setMessage("Response status OK");                
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage("Error interno del servidor");
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
        }
        return response;
    }
    
}
