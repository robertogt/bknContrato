/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.service;

import cgc.rrhh.contratos.model.RrhhLaboral;
import cgc.rrhh.contratos.model.RrhhMunicipio;
import cgc.rrhh.contratos.model.RrhhRue;
import cgc.rrhh.contratos.pojo.ResultsActividad;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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
@LocalBean
public class GenerarContrato {
    
     @EJB
    private GeneralService generalService;
    
    @EJB
    private ActividadPerfilService actividadPerfilService;
    
    @EJB
    private ContratoService contratoService;
    
    private Logger log = Logger.getLogger(GenerarContrato.class);
   
    public ByteArrayOutputStream generarContrato(BigDecimal id) throws Exception{       

            ByteArrayOutputStream baos = new ByteArrayOutputStream();        
	    try {
                log.info("generarContrato: "+id);
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
                     if(laboral.getIdContrato().getAcademico().getNumeroColegiado() != null 
                            && !laboral.getIdContrato().getAcademico().getNumeroColegiado().isEmpty()){
                        range.replaceText("<COLEGIADO>",Colegiado.toText(laboral.getIdContrato().getAcademico().getNumeroColegiado()));
                    }
                    if(laboral.getIdContrato().getAcademico().getColegioProfesional() != null){
                        range.replaceText("<COLEGIO>",laboral.getIdContrato().getAcademico().getColegioProfesional().getNombreColegioProfesional().toUpperCase());
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
                 
                 
                        document.write(baos);
		} catch (Exception e) {
			log.error("generarContrato: ",e);
                        baos = null;
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