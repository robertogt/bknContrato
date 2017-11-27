/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.rest;

import cgc.rrhh.contratos.pojo.ResultsFuncionarios;
import cgc.rrhh.contratos.service.FuncionariosService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import rrhh.calculos.contrato.Colegiado;
import rrhh.calculos.contrato.Contrato;
import rrhh.calculos.contrato.Dpi;
import rrhh.calculos.contrato.Edad;
import rrhh.calculos.contrato.Nit;

/**
 *
 * @author ejmorales
 */
@Stateless
@Path("funcionarios")
public class FuncionariosREST {
    
    @EJB
    private FuncionariosService funcionariosService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ResultsFuncionarios> listAllFuncionariosByDPI(
            @QueryParam("dpi") String dpi){
        List<ResultsFuncionarios> result = new ArrayList<ResultsFuncionarios>();
        if(dpi != null && !dpi.isEmpty() && dpi.length() > 3){
            result = funcionariosService.findFuncionariosByDPI(dpi);
        }                    
        
        return result;
    }
    
   /* public static void main(String[] args) throws Exception{
//        System.out.println(Dpi.toText("2133710490101"));
//        System.out.println(Edad.getEdad("19/01/1992"));
//        System.out.println(Edad.toText("19/01/1992").replaceFirst(" ", ""));
//        System.out.println(Nit.toText("2059349K"));
//        System.out.println(Colegiado.toText("442"));
//        Contrato test = new Contrato(1,12000.00,"15/01/2018","31/12/2018","15/01/2018");
//        System.out.println(test.getMontoTotalEnLetras());
//        System.out.println(test.getPlazoEnLetras());
    }*/
   /* public static void main(String[] args) throws Exception{       
       SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
       Calendar del = Calendar.getInstance();
        del.setTime(format.parse("20/01/2017"));
       Calendar al = Calendar.getInstance();
        al.setTime(format.parse("31/12/2017"));
        int diffMonth = al.get(Calendar.MONTH) - del.get(Calendar.MONTH) + 1;
        
        
        Calendar now = Calendar.getInstance();
        now.set(Calendar.MONTH, Calendar.JANUARY);
        String fecha = format.format(now.getTime());
        
        System.out.println(fecha.replaceAll("/", ""));
        
    }*/
    
}
