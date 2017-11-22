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
    private ActividadPerfilService actividadPerfilService;
    
    @EJB
    private ContratoService contratoService;
    
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
                RrhhContratoEstado antEstado = asesorService.findEstadoByContrato(contrato);
                if(antEstado != null){
                    antEstado.setEstado("F");
                    antEstado.setUsuarioUpdate("S/U");
                    antEstado.setFechaUpdate(new Date());
                    
                    RrhhContratoEstado newEstado = new RrhhContratoEstado();
                    newEstado.setEstado(Constants.ACTIVO);
                    newEstado.setIdContrato(antEstado.getIdContrato());
                    newEstado.setIdCatalogoEstado(generalService.findEstadoById(BigDecimal.valueOf(3)));
                    newEstado.setUsuarioInsert("S/U");
                    newEstado.setDocumento(this.generarContrato(antEstado.getIdContrato().getIdContrato()).toByteArray());
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
                 RrhhContratoEstado antEstado = asesorService.findEstadoByContrato(contrato);
                 if(antEstado != null){
                    antEstado.setEstado("F");
                    antEstado.setUsuarioUpdate("S/U");
                    antEstado.setFechaUpdate(new Date());
                    
                    RrhhContratoEstado newEstado = new RrhhContratoEstado();
                    newEstado.setEstado(Constants.ACTIVO);
                    newEstado.setIdContrato(antEstado.getIdContrato());
                    newEstado.setDocumento(docBytes);
                    newEstado.setObservacion(URLDecoder.decode(observaciones,"UTF-8"));
                    newEstado.setIdCatalogoEstado(generalService.findEstadoById(BigDecimal.valueOf(2)));
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
    
    
    public ByteArrayOutputStream generarContrato(BigDecimal id) throws Exception{       

            ByteArrayOutputStream baos = new ByteArrayOutputStream();        
	    try {
                
                RrhhLaboral laboral = contratoService.findLaboralByContrato(id);
                
                if(laboral == null)
                    throw new Exception("No se encontro Informacion del Id Solicitado");
                 
                 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                 Contrato contrato = new Contrato(laboral.getIdContrato().getCorrelativoContrato().intValue(),
                         laboral.getHonorario().intValue(),
                         format.format(laboral.getFechaDel()),
                         format.format(laboral.getFechaAl()),
                         format.format(laboral.getFechaCambioTipoMovimiento()));
                 
                 InputStream fileInputStream = new ByteArrayInputStream(laboral.getIdContrato().getIdPlantilla().getArchivo());
                 BufferedInputStream buffInputStream = new BufferedInputStream(fileInputStream);
	    	 HWPFDocument document = new HWPFDocument(new POIFSFileSystem(buffInputStream)); 
                 Range range = document.getRange(); 
                 range.sanityCheck();
                    range.replaceText("<NUMERO_DE_CONTRATO_EN_LETRAS>", contrato.getNumeroContratoLetras());
                    range.replaceText("<NUMERO_DE_CONTRATO>", laboral.getIdContrato().getCorrelativoContrato().toString());
                    range.replaceText("<DIA_CONTRATO>", contrato.getFechaContrato());
                    range.replaceText("<NOMBRE>", this.crearNombre(laboral.getIdRue()));
                    range.replaceText("<EDAD>", Edad.toText(format.format(laboral.getIdRue().getFechaNacimiento())));
                    range.replaceText("<ESTADO_CIVIL>", this.formatEstadoCivil(laboral.getIdRue().getEstadoCivil(), laboral.getIdRue().getGenero()));
                    range.replaceText("<NACIONALIDAD>", laboral.getIdRue().getPais().getNacionalidad().toUpperCase());
                    range.replaceText("<PROFESIÓN>", laboral.getIdContrato().getAcademico().getTitulo().getNombre().toUpperCase());
                    range.replaceText("<DPI>", Dpi.toText(this.formatDpi(laboral.getIdRue().getCui())));
                    range.replaceText("<NIT>", Nit.toText(laboral.getIdRue().getNit()));
                    range.replaceText("<RESIDENCIA>", this.formatResidencia(laboral.getIdRue()));
                    range.replaceText("<UBICACIÓN>", laboral.getUbicacionFuncional().getNombre().toUpperCase());
                    range.replaceText("<ACTIVIDADES>",this.concatActividades(laboral.getIdContrato().getIdContrato()));
                    range.replaceText("<MONTO_TOTAL_EN_LETRAS>", contrato.getMontoTotalEnLetras());
                    range.replaceText("<PLAZO>",contrato.getPlazoEnLetras());
                    range.replaceText("<COLEGIADO>",Colegiado.toText(laboral.getIdContrato().getAcademico().getNumeroColegiado()));
                    range.replaceText("<COLEGIO>",laboral.getIdContrato().getAcademico().getColegioProfesional().getNombreColegioProfesional().toUpperCase());
                    range.replaceText("DIECISÉIS .","DIECISÉIS.");
                    range.replaceText(" ) ",") ");
                    range.replaceText("  ("," (");
                    range.replaceText(" , ",", ");
                    range.replaceText(".  ",". ");
                    range.replaceText(" .",".");
                    range.replaceText(" ,",", ");
                    range.replaceText("  ESPACIO"," ESPACIO");
                    range.replaceText("  años"," años");
                    range.replaceText("UNO MIL","UN MIL");
                    range.replaceText("UNO MILLONES","UN MILLONES");
                    range.replaceText("UNO años","UN años");
                    range.replaceText("  "," ");
                 
                 
                        document.write(baos);
		} catch (Exception e) {
			System.out.println(e.getMessage());
                        throw new Exception(e.getMessage());
		}
            
            return baos;
    }
    
    
    private String concatActividades(BigDecimal idContrato) throws Exception{
        StringBuilder builder = new StringBuilder();
        try {            
            List<ResultsActividad> actividades = actividadPerfilService.findActividadesByContrato(idContrato);
            for(ResultsActividad actividad: actividades){
                builder.append(" ");
                builder.append(actividad.getIdActividad());
                builder.append(") ");
                builder.append(actividad.getDescripcion());
            }
            
            builder.append(".");
        } catch (Exception e) {
            throw new Exception("error en actividades ");
        }
        return builder.toString().toUpperCase();
    }
    
    private String crearNombre(RrhhRue rue) throws Exception {
        StringBuilder cadena = new StringBuilder();
            try {
                cadena.append(rue.getPrimerNombre());
                if(rue.getSegundoNombre() != null && !rue.getSegundoNombre().isEmpty()){    
                    cadena.append(" ");
                    cadena.append(rue.getSegundoNombre());
                }                
                
                if(rue.getTercerNombre() != null && !rue.getTercerNombre().isEmpty()){
                    cadena.append(" ");
                    cadena.append(rue.getTercerNombre());
                }                
                
                cadena.append(" ");
                cadena.append(rue.getPrimerApellido());
                if(rue.getSegundoApellido() != null && !rue.getSegundoApellido().isEmpty()){
                    cadena.append(" ");
                    cadena.append(rue.getSegundoApellido());
                }                
                if(rue.getApellidoCasada() != null && !rue.getApellidoCasada().isEmpty()){
                    cadena.append(" DE ");
                    cadena.append(rue.getApellidoCasada());
                }                
            } catch (Exception e) {
                throw new Exception("nombre es null");
            }
          return cadena.toString().toUpperCase();
    }
    
    private String formatDpi(String dpi) throws Exception{
        String cui = "";
        try {
            cui = dpi.replaceAll(" ", "");
            cui = cui.replaceAll("-", "");
        } catch (Exception e) {
            throw new Exception("dpi es null");
        }
        return cui;
    }
    
    
    private String formatResidencia(RrhhRue rue) throws Exception{
       StringBuilder builder = new StringBuilder();
        try {
            RrhhMunicipio municipio = generalService.getMunicipioDepto(rue.getMunicipioVivienda(), rue.getDepartamentoVivienda());
            if(municipio == null)
                throw new Exception("formatResidenciaException");
            
            
            builder.append(rue.getDireccion());
            if(rue.getZonaVivienda() != null && rue.getZonaVivienda() != 0){
                builder.append(" ZONA ");
                builder.append(rue.getZonaVivienda());
            }
            builder.append(" ");
            builder.append(municipio.getNombre());
            builder.append(", ");
            builder.append(municipio.getRrhhDepartamento().getNombre());
        } catch (Exception e) {
            throw new Exception("Residencia es null");
        }
        return builder.toString().toUpperCase();
    }
    
    private String formatEstadoCivil(String estadoCivil,String genero) throws Exception{
        String estado = "";
        try {
            if(genero.equalsIgnoreCase("M")){
                if(estadoCivil.equalsIgnoreCase("S")){
                    estado = "SOLTERO";
                }else if(estadoCivil.equalsIgnoreCase("C")){
                    estado = "CASADO";
                }else if(estadoCivil.equalsIgnoreCase("V")){
                    estado = "VIUDO";
                }else if(estadoCivil.equalsIgnoreCase("D")){
                    estado = "DIVORCIADO";
                }else if(estadoCivil.equalsIgnoreCase("U")){
                    estado = "UNIDO";
                }
            }else{
                if(estadoCivil.equalsIgnoreCase("S")){
                    estado = "SOLTERA";
                }else if(estadoCivil.equalsIgnoreCase("C")){
                    estado = "CASADA";
                }else if(estadoCivil.equalsIgnoreCase("V")){
                    estado = "VIUDA";
                }else if(estadoCivil.equalsIgnoreCase("D")){
                    estado = "DIVORCIADA";
                }else if(estadoCivil.equalsIgnoreCase("U")){
                    estado = "UNIDA";
                }
            }
        } catch (Exception e) {
            throw new Exception("estado civil es null");
        }
        
        return estado;
    }
    
}
