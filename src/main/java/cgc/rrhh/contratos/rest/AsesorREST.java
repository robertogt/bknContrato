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
import cgc.rrhh.contratos.model.RrhhMunicipio;
import cgc.rrhh.contratos.model.RrhhRue;
import cgc.rrhh.contratos.pojo.ResultsActividad;
import cgc.rrhh.contratos.pojo.ResultsContrato;
import cgc.rrhh.contratos.pojo.ResultsHistorial;
import cgc.rrhh.contratos.service.ActividadPerfilService;
import cgc.rrhh.contratos.service.AsesorService;
import cgc.rrhh.contratos.service.ContratoService;
import cgc.rrhh.contratos.service.GeneralService;
import cgc.rrhh.contratos.service.GenerarContrato;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
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
@Path(Constants.ASESOR)
public class AsesorREST {

    @EJB
    private AsesorService asesorService;
    
    @EJB
    private GeneralService generalService;
    
    @EJB
    private GenerarContrato generarContrato;
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ResultsContrato> findAllContratoAsesor(){
       return asesorService.listAllByAsesor();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(Constants.HISTORIAL)
    public List<ResultsHistorial> listHistorialCambios(@QueryParam("contrato") BigDecimal contrato){
        List<ResultsHistorial> historialCambios = new ArrayList<ResultsHistorial>();
        try {
          if(contrato != null){
              historialCambios =  asesorService.findHistorialByContrato(contrato);
          }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return historialCambios;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path(Constants.APROBAR)
    public ResponseData aprobar(@FormParam("contrato") BigDecimal contrato){
        ResponseData response = new ResponseData();
        response.setCode(403);
        response.setMessage("Error al realizar la petición");
        try {
            if(contrato != null){
                RrhhContratoEstado antEstado = asesorService.findEstadoByContrato(contrato,BigDecimal.valueOf(2));
                if(antEstado != null){
                    antEstado.setEstado("F");
                    antEstado.setUsuarioUpdate("S/U");
                    antEstado.setFechaUpdate(new Date());
                    
                    RrhhContratoEstado newEstado = new RrhhContratoEstado();
                    newEstado.setEstado(Constants.ACTIVO);
                    newEstado.setIdContrato(antEstado.getIdContrato());
                    newEstado.setIdCatalogoEstado(generalService.findEstadoById(BigDecimal.valueOf(4)));
                    newEstado.setUsuarioInsert("S/U");
                    newEstado.setDocumento(generarContrato.generarContrato(antEstado.getIdContrato().getIdContrato()).toByteArray());
                    newEstado.setFechaInsert(new Date());
                    asesorService.aprobarRegistro(antEstado,newEstado);
                     response.setCode(200);
                    response.setMessage("Contrato aprobado con exito");
                }
            }
        } catch (Exception e) {
            response.setCode(500);
            System.out.println(e.getMessage());
        }
        return response;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path(Constants.RECHAZAR)
    public ResponseData rechazar(@FormDataParam("file") InputStream uploadedInputStream,
           @FormDataParam("file") FormDataContentDisposition fileDetail,
           @FormDataParam("contrato") BigDecimal contrato,
           @FormDataParam("observaciones") String observaciones){
        ResponseData response = new ResponseData();
        response.setCode(403);
        response.setMessage("Error al cargar la información");
        try {
             byte[] docBytes = this.writeFiletoBytes(uploadedInputStream);
             if(docBytes != null){
                 RrhhContratoEstado antEstado = asesorService.findEstadoByContrato(contrato,BigDecimal.valueOf(2));
                 if(antEstado != null){
                    antEstado.setEstado("F");
                    antEstado.setUsuarioUpdate("S/U");
                    antEstado.setFechaUpdate(new Date());
                    
                    RrhhContratoEstado newEstado = new RrhhContratoEstado();
                    newEstado.setEstado(Constants.ACTIVO);
                    newEstado.setIdContrato(antEstado.getIdContrato());
                    newEstado.setDocumento(docBytes);
                    newEstado.setObservacion(URLDecoder.decode(observaciones,"UTF-8"));
                    newEstado.setIdCatalogoEstado(generalService.findEstadoById(BigDecimal.valueOf(3)));
                    newEstado.setUsuarioInsert("S/U");
                    newEstado.setFechaInsert(new Date());
                    asesorService.aprobarRegistro(antEstado,newEstado);
                    response.setCode(200);
                    response.setMessage("Contrato rechazado con exito");
                 }
             }
        } catch (Exception e) {
            response.setCode(500);
            System.out.println(e.getMessage());
        }
        return response;
    }
    
     public byte[] writeFiletoBytes(InputStream uploadedInputStream){
        ByteArrayOutputStream buffer = null;
        
        try {
            int nRead;
            byte[] bytes = new byte[5 * 1024 * 1024];        
             buffer = new ByteArrayOutputStream();
            while ((nRead = uploadedInputStream.read(bytes, 0, bytes.length)) != -1) {
                buffer.write(bytes, 0, nRead);
              }

            buffer.flush();
        } catch (Exception e) {
            System.out.println("writeImgtoBytes: "+e.getMessage());            
        }
        return buffer.toByteArray();
    }
    
}
