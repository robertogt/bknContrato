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
import cgc.rrhh.contratos.service.AcuerdoAprobacionService;
import cgc.rrhh.contratos.service.GeneralService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author jrroquel
 */
@Stateless
@Path(Constants.ACUERDO_APROBACION)
public class AcuerdoAprobacionREST {
    
    @EJB
    private AcuerdoAprobacionService acuerdoAprobacionService;
    
    @EJB
    private GeneralService generalService;
    
    @POST
    @Produces
    public ResponseData crearAcuerdoAprobacion(RrhhAcuerdoAprobacion acuerdoAprobacion){
        Calendar now = Calendar.getInstance();
        ResponseData response = new ResponseData();
        response.setCode(403);
        response.setMessage("Error al obtener la información");
        
        String correlativo = acuerdoAprobacionService
                            .findCorrelativo(acuerdoAprobacion.getRenglon().getRenglon(), acuerdoAprobacion.getTipoServicios(),
                                             String.valueOf(now.get(Calendar.YEAR)))
                            .toString();
        
        String identificadorAcuerdo = generarIdentificadorAcuerdo(acuerdoAprobacion.getRenglon().getRenglon(), acuerdoAprobacion.getTipoServicios(), correlativo);
        
        return response;
    }
    
    private List<RrhhAcuerdoContrato> generarAcuerdosContrato(String usuario, RrhhAcuerdoAprobacion acuerdoAprobacion){
        List<RrhhAcuerdoContrato> acuerdos = new ArrayList<RrhhAcuerdoContrato>();
        try {
            
            for(RrhhAcuerdoContrato acuerdo: acuerdoAprobacion.getRrhhAcuerdoContratoList()){
                
                RrhhAcuerdoContrato acuerdoContrato = new RrhhAcuerdoContrato();
                
                acuerdoContrato.setIdAcuerdoAprobacion(acuerdoAprobacion);
                acuerdoContrato.setIdContrato(acuerdoAprobacion.getidcon);
                acuerdoContrato.setEstado(Constants.ACTIVO);
                acuerdoContrato.setFechaInsert(new Date());
                acuerdoContrato.setUsuarioInsert(usuario);
                acuerdoContrato.
                        
                
                        actividadContrato.setIdActividad(actividadPerfilService
                            .findActividadById(actividadResult.getIdActividad()));                       
                        actividadContrato.setIdPerfil(actividadPerfilService
                                .findPerfilById(funcionario.getIdPerfil()));
                    actividades.add(actividadContrato);
                
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            acuerdos = new ArrayList<RrhhAcuerdoContrato>();
        }
        
        return acuerdos;
                
    }
    
    private String generarIdentificadorAcuerdo(String renglon, String tipoServicios, String correlativo){
        try {
            Calendar now = Calendar.getInstance();
            
            if(correlativo == null)
                throw new Exception("Ocurrio un error con el correlativo. ");
            
            
            StringBuilder builder = new StringBuilder();
            builder.append("AARRHH");
            builder.append("-");
            if(tipoServicios.equalsIgnoreCase("T")){
                builder.append("ST");
            }else{
                builder.append("SP");
            }
            builder.append("-");
            builder.append(generalService.getRenglonById(renglon).getNombre());
            builder.append("-");
            builder.append(correlativo);
            builder.append("-");
            builder.append(now.get(Calendar.YEAR));
            
            System.out.println(builder.toString());
            return builder.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
            
}
