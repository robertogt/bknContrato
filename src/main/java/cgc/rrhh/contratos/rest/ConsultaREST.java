/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.rest;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhActividad;
import cgc.rrhh.contratos.model.RrhhContrato;
import cgc.rrhh.contratos.model.RrhhContratoEstado;
import cgc.rrhh.contratos.model.RrhhLaboral;
import cgc.rrhh.contratos.model.RrhhMunicipio;
import cgc.rrhh.contratos.model.RrhhRue;
import cgc.rrhh.contratos.model.RrhhTitulo;
import cgc.rrhh.contratos.pojo.ResultsActividad;
import cgc.rrhh.contratos.service.ContratoService;
import cgc.rrhh.contratos.service.GeneralService;
import cgc.rrhh.contratos.service.ActividadPerfilService;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import org.apache.log4j.Logger;
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
@RolesAllowed("rrhh_contrato")
@Path(Constants.GENERAR)
public class ConsultaREST {
    
    @EJB
    private ContratoService contratoService;
    
    @EJB
    private GeneralService generalService;
    
    @EJB
    private ActividadPerfilService actividadPerfilService;
    
    private static final Logger log = Logger.getLogger(ConsultaREST.class);
    
    private static final BigDecimal RECHAZADO = BigDecimal.valueOf(3);
    private static final BigDecimal ACEPTADO = BigDecimal.valueOf(4);
    private static final BigDecimal APROBADO = BigDecimal.valueOf(5);
    private static final BigDecimal ADDENDUM = BigDecimal.valueOf(6);
    private static final BigDecimal CONTADOR_PUBLICO_AUDITOR = BigDecimal.valueOf(11);
    private static final BigDecimal ADMIN_CONTADOR_PUBLICO_AUDITOR = BigDecimal.valueOf(612);
    
    @GET
    @Path(Constants.RECHAZADO+"/{id}") 
    public void generarContratoRechazado(@Context HttpServletResponse response,@PathParam("id") BigDecimal id,
            @Context SecurityContext sc) throws Exception{            	    
	    try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    	//DocWord word = super.getDocument(1);
              //  ResultsArchivo archivo = super.findArchivoByIdPlantilla(id);
                RrhhLaboral laboral = contratoService.findLaboralByContrato(id);
                
                if(laboral == null)
                    throw new Exception("No se encontro Informacion del Id Solicitado");
                
                RrhhContratoEstado contratoEstado = generalService.findActiveByContrato(laboral.getIdContrato().getIdContrato());
                
                if(contratoEstado == null)
                    throw new Exception("El contrato no tiene un estado actualmente");
                 
                log.info("ENTRO A GENERAR CONTRATO ESTADO: "+sc.getUserPrincipal().getName().toUpperCase()
                        +" a la hora: "+new Date()+" NUMERO CONTRATO: "+laboral.getNumeroContrato());                
                        
                        response.setContentType("application/msword");
                        response.setHeader("Content-disposition", "filename="+laboral.getNumeroContrato()+".doc");
                        
                        
                        if(contratoEstado.getIdCatalogoEstado().getIdCatalogoEstado().equals(RECHAZADO)){
                            if(contratoEstado.getDocumento() != null){
                                InputStream fileInputStream = new ByteArrayInputStream(contratoEstado.getDocumento());
                                baos = this.convertInputStreamToByteArrayOutputStream(fileInputStream);
                            }                            
                        }else{
                            throw new Exception("El id Catalogo no es estado rechazado");
                        }
                                            
                        
			OutputStream os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
		} catch (Exception e) {
                        log.error("ConsultaREST: ",e);
                        throw new Exception(e.getMessage());
		}
    }
    
    @GET
    @Path("/{id}") 
    public void generarContrato(@Context HttpServletResponse response,@PathParam("id") BigDecimal id,
            @Context SecurityContext sc) throws Exception{            	    
	    try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    	//DocWord word = super.getDocument(1);
              //  ResultsArchivo archivo = super.findArchivoByIdPlantilla(id);
                RrhhLaboral laboral = contratoService.findLaboralByContrato(id);
                
                if(laboral == null)
                    throw new Exception("No se encontro Informacion del Id Solicitado");
                
                RrhhContratoEstado contratoEstado = generalService.findActiveByContrato(laboral.getIdContrato().getIdContrato());
                
                if(contratoEstado == null)
                    throw new Exception("El contrato no tiene un estado actualmente");
                 
                log.info("ENTRO A GENERAR CONTRATO: "+sc.getUserPrincipal().getName().toUpperCase()
                        +" a la hora: "+new Date()+" NUMERO CONTRATO: "+laboral.getNumeroContrato());
                log.info("idCatalogo: "+contratoEstado.getIdCatalogoEstado().getIdCatalogoEstado());
                        
                        response.setContentType("application/msword");
                        response.setHeader("Content-disposition", "filename="+laboral.getNumeroContrato()+".doc");
                        
                        boolean generarFromBd = false;
                        
                        if(contratoEstado.getIdCatalogoEstado().getIdCatalogoEstado().equals(ACEPTADO)){
                            generarFromBd = true;
                        }
                        
                        if(contratoEstado.getIdCatalogoEstado().getIdCatalogoEstado().equals(APROBADO)){
                            generarFromBd = true;
                        }
                        
                        if(contratoEstado.getIdCatalogoEstado().getIdCatalogoEstado().equals(ADDENDUM)){
                            generarFromBd = true;
                        }
                        
                        if(generarFromBd){
                            InputStream fileInputStream = new ByteArrayInputStream(contratoEstado.getDocumento());
                            baos = this.convertInputStreamToByteArrayOutputStream(fileInputStream);
                        }else{
                            baos = this.generateContrat(laboral);
                        }
                        
                        
			OutputStream os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
		} catch (Exception e) {
                        log.error("ConsultaREST: ",e);
                        throw new Exception(e.getMessage());
		}
    }
    
    private ByteArrayOutputStream convertInputStreamToByteArrayOutputStream(InputStream inputStream) {
		 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
 
			byte[] buffer = new byte[5 * 1024 * 1024];
			baos = new ByteArrayOutputStream();
 
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}
 
		} catch (FileNotFoundException e) {
			log.error("FileNotFound: ",e);
		} catch (IOException e) {
			log.error("IOException: ",e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error("IOException: ",e);
				}
			}
		}
		return baos;
	}
    
    private ByteArrayOutputStream generateContrat(RrhhLaboral laboral){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
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
                    range.replaceText("<DIA_CONTRATO>", this.replaceFinalS(contrato.getFechaContrato()));
                    range.replaceText("<NOMBRE>", this.crearNombre(laboral.getIdRue()));
                    range.replaceText("<EDAD>", Edad.toText(format.format(laboral.getIdRue().getFechaNacimiento())));
                    range.replaceText("<ESTADO_CIVIL>", this.formatEstadoCivil(laboral.getIdRue().getEstadoCivil(), laboral.getIdRue().getGenero()));
                    range.replaceText("<NACIONALIDAD>", this.getNacionalidad(laboral.getIdRue()));
                    range.replaceText("<PROFESIÓN>", this.getProfesion(laboral.getIdRue().getGenero(), laboral.getIdContrato().getAcademico().getTitulo()));
                    range.replaceText("<DPI>", this.formatDpi(laboral.getIdRue().getCui()));
                    range.replaceText("<NIT>", Nit.toText(laboral.getIdRue().getNit()));
                    range.replaceText("<RESIDENCIA>", this.formatResidencia(laboral.getIdRue()));
                    range.replaceText("<UBICACIÓN>", laboral.getUbicacionFuncional().getTipoUbicacion().getPrefijo()+" "+laboral.getUbicacionFuncional().getNombre().toUpperCase());
                    range.replaceText("<ACTIVIDADES>",this.concatActividades(laboral.getIdContrato().getIdContrato()));
                    range.replaceText("<MONTO_TOTAL_EN_LETRAS>", contrato.getMontoTotalEnLetras());
                    range.replaceText("<PLAZO>",contrato.getPlazoEnLetras());
                    if(laboral.getIdContrato().getAcademico().getNumeroColegiado() != null 
                            && !laboral.getIdContrato().getAcademico().getNumeroColegiado().isEmpty()){
                        range.replaceText("<COLEGIADO>",Colegiado.toText(laboral.getIdContrato().getAcademico().getNumeroColegiado()));
                    }
                    if(laboral.getIdContrato().getAcademico().getColegioProfesional() != null){
                        range.replaceText("<COLEGIO>",this.replaceTituloColegio(laboral.getIdContrato().getAcademico().getColegioProfesional().getNombreColegioProfesional().toLowerCase()));
                    }                    
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
                 
                 
                                 
		//	baos = this.convertInputStreamToByteArrayOutputStream(fileInputStream);
                        document.write(baos);
        } catch (Exception e) {
        }
        return baos;
    }
    
    
    private String replaceTituloColegio(String text){        
        String[] pairs = text.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String pair:pairs){
            if(pair.equals("y") || pair.equals("de")){
                sb.append(pair);     
            }else{
                String temp = pair.substring(0,1).toUpperCase()+pair.substring(1);
                sb.append(temp);                        
            }
            sb.append(" ");
        }
        return sb.toString();
    }
    
    private String getNacionalidad(RrhhRue rue){
        String nacionalidad = rue.getPais().getNacionalidad().toUpperCase();
        try {
           if(rue.getGenero().equalsIgnoreCase("F")){
               nacionalidad = nacionalidad.substring(0,nacionalidad.length()-1);
               nacionalidad = nacionalidad+"A";
           } 
        } catch (Exception e) {
            log.error("Error Nacionalidad: ",e);
            System.out.println("Error Nacionalidad");
        }
        System.out.println(nacionalidad);
        return nacionalidad;
    }
    
    private String replaceFinalS(String text){
       
        String subs = text;
        try {
            
            if(subs.endsWith(" ")){
                subs = subs.substring(0,subs.length() - 1);
            }
        
        } catch (Exception e) {
            log.error("Error character: ",e);
            System.out.println("Error replace espacio");
        }
         return subs;
    }
    
    private String getProfesion(String genero, RrhhTitulo titulo){
        String profesion = titulo.getNombre();
        try {
            if(genero != null){
                System.out.println(genero);
                if(genero.equalsIgnoreCase("M")){
                    profesion = profesion.replace("INGENIERIA", "INGENIERO");
                    profesion = profesion.replace("INGENIERÍA", "INGENIERO");
                    profesion = profesion.replace("LICENCIATURA", "LICENCIADO");
                    profesion = profesion.replace("ADMINISTRACION", "ADMINISTRADOR");
                    profesion = profesion.replace("ADMINISTRACIÓN", "ADMINISTRADOR");
                    
                }else{                
                    profesion = profesion.replace("INGENIERIA", "INGENIERA");
                    profesion = profesion.replace("INGENIERÍA", "INGENIERA");
                    profesion = profesion.replace("LICENCIATURA", "LICENCIADA");
                    profesion = profesion.replace("ADMINISTRACION", "ADMINISTRADORA");
                    profesion = profesion.replace("ADMINISTRACIÓN", "ADMINISTRADORA");
                    
                    profesion = profesion.replace("INGENIERO", "INGENIERA");
                    profesion = profesion.replace("LICENCIADO", "LICENCIADA");
                    profesion = profesion.replace("ARQUITECTO", "ARQUITECTA");
                    profesion = profesion.replace("ADMINISTRADOR", "ADMINISTRADORA");
                    
                    if(titulo.getTitulo().equals(CONTADOR_PUBLICO_AUDITOR)){
                        profesion = profesion.replace("CONTADOR", "CONTADORA");
                        profesion = profesion.replace("PÚBLICO", "PÚBLICA");
                        profesion = profesion.replace("PUBLICO", "PUBLICA");
                        profesion = profesion.replace("AUDITOR", "AUDITORA");
                    }
                    
                    if(titulo.getTitulo().equals(ADMIN_CONTADOR_PUBLICO_AUDITOR)){
                        profesion = profesion.replace("CONTADOR", "CONTADORA");
                        profesion = profesion.replace("PÚBLICO", "PÚBLICA");
                        profesion = profesion.replace("PUBLICO", "PUBLICA");
                        profesion = profesion.replace("AUDITOR", "AUDITORA");
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error con la profesion",e);
        }            
        System.out.println(profesion);
        return profesion;
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
        return builder.toString();
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
        StringBuilder builder = new StringBuilder();
        try {
            cui = dpi.replaceAll(" ", "");
            cui = cui.replaceAll("-", "");
            String temp = Dpi.toText(cui);
            
            builder.append(temp.substring(0,temp.lastIndexOf("(")+1));
            builder.append(dpi);
            builder.append(temp.substring(temp.lastIndexOf(")")));
            
        } catch (Exception e) {
            throw new Exception("dpi es null");
        }
        return builder.toString();
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
