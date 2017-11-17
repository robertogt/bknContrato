/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.rest;

import cgc.rhh.contratos.util.Constants;
import cgc.rhh.contratos.util.ResponseData;
import cgc.rrhh.contratos.model.RrhhAcademico;
import cgc.rrhh.contratos.model.RrhhActividad;
import cgc.rrhh.contratos.model.RrhhActividadContrato;
import cgc.rrhh.contratos.model.RrhhContrato;
import cgc.rrhh.contratos.model.RrhhContratoEstado;
import cgc.rrhh.contratos.model.RrhhControlPresupuesto;
import cgc.rrhh.contratos.model.RrhhFuente;
import cgc.rrhh.contratos.model.RrhhLaboral;
import cgc.rrhh.contratos.model.RrhhMovimientosPresupuesto;
import cgc.rrhh.contratos.model.RrhhRue;
import cgc.rrhh.contratos.model.RrhhUbicacionFuncional;
import cgc.rrhh.contratos.model.TcFuncionarios;
import cgc.rrhh.contratos.pojo.PersistAcademico;
import cgc.rrhh.contratos.pojo.ResultsAcademico;
import cgc.rrhh.contratos.pojo.ResultsActividad;
import cgc.rrhh.contratos.pojo.ResultsFuncionario;
import cgc.rrhh.contratos.service.ActividadPerfilService;
import cgc.rrhh.contratos.service.ColegioService;
import cgc.rrhh.contratos.service.ContratoService;
import cgc.rrhh.contratos.service.FuncionariosService;
import cgc.rrhh.contratos.service.GeneralService;
import cgc.rrhh.contratos.service.TitulosService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import rrhh.calculos.contrato.Contrato;
import rrhh.calculos.contrato.Edad;

/**
 *
 * @author ejmorales
 */
@Stateless
@Path(Constants.CONTRATO)
public class ContratoREST {
    
    @EJB
    private ContratoService contratoService;
    
    @EJB
    private ActividadPerfilService actividadPerfilService;
    
    @EJB
    private GeneralService generalService;
    
    @EJB
    private ColegioService colegioService;
    
    @EJB
    private TitulosService titulosService;
    
    @EJB
    private FuncionariosService funcionariosService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RrhhContrato> findAll() throws Exception{
        return contratoService.findAll();
    }
    
    @GET
    @Path(Constants.FUNCIONARIO)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData findFuncionario(@QueryParam("dpi") String dpi){
        ResponseData response = new ResponseData();
        try {
            if(dpi != null && !dpi.isEmpty()){
                ResultsFuncionario funcionarioRue = contratoService.findRueByDPI(dpi);
                if(funcionarioRue != null){
                    if(funcionarioRue.getEstado().equals("A") && !funcionarioRue.getRenglon().equals("1")){
                        response.setCode(403);
                        response.setMessage("El dpi No."+funcionarioRue.getDpi()+" ya cuenta con un contrato Activo actualmente.");
                    }else if(funcionarioRue.getEstado().equals("A") && funcionarioRue.getRenglon().equals("1")){
                      response.setCode(403);
                      response.setMessage("El dpi No."+funcionarioRue.getDpi()+" ya se encuentra Activo actualmente con renglon 011.");  
                    }else{
                        
                        if(funcionarioRue.getFechaNacimiento() != null){
                            funcionarioRue.setEdad(Edad.getEdad(funcionarioRue.getFechaNacimiento()));
                        }
                        
                        ResultsAcademico resultsAcademico = null;
                        
                        if(funcionarioRue.getIdContrato() != null){
                            BigDecimal idPerfil = actividadPerfilService.getIdPerfilByContrato(funcionarioRue.getIdContrato());
                            if(idPerfil != null){
                                List<ResultsActividad> actividades = actividadPerfilService.findAllActividadesByContrato(idPerfil, funcionarioRue.getIdContrato());
                                if(!actividades.isEmpty()){
                                    funcionarioRue.setIdPerfil(idPerfil);
                                    funcionarioRue.setActividades(actividades);
                                }
                            }
                            
                            resultsAcademico = contratoService.getAcademicoByContrato(funcionarioRue.getIdContrato());
                            
                            if(resultsAcademico == null){
                                resultsAcademico = contratoService.findAcademicoByRue(funcionarioRue.getIdRue(),funcionarioRue.getTipoServicios());
                            }
                            
                        }else{
                            resultsAcademico = contratoService.findAcademicoByRue(funcionarioRue.getIdRue(),funcionarioRue.getTipoServicios());
                        }
                        
                        if(resultsAcademico != null){
                            funcionarioRue.setInfoAcademica(resultsAcademico);
                        }
                        
                        response.setCode(200);
                        response.setData(funcionarioRue);
                        response.setMessage("Response status OK");
                    }
                }else{
                    ResultsFuncionario funcionarioPublico = contratoService.findFuncionarioByDpi(dpi);
                  
                    if(funcionarioPublico != null){
                        
                        if(funcionarioPublico.getFechaNacimiento() != null){
                                funcionarioPublico.setEdad(Edad.getEdad(funcionarioPublico.getFechaNacimiento()));
                        }
                      
                        response.setCode(200);
                        response.setData(funcionarioPublico);
                        response.setMessage("Response status OK");
                    }else{
                      response.setCode(403);
                      response.setMessage("Ocurrio un error al consultar la información");  
                    }
                }
            }
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage("Error interno del servidor");
        }
        return response;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData crearContrato(ResultsFuncionario resultsFuncionario,
            @Context SecurityContext sc){
        ResponseData response = new ResponseData();
        response.setCode(403);
        response.setMessage("Error al obtener la información");
        
        try {
            if(resultsFuncionario != null && this.validate(resultsFuncionario)){
                Calendar now = Calendar.getInstance();
              //  if(resultsFuncionario.getIdRue() != null){
                   List<RrhhControlPresupuesto> presupuestos = contratoService
                           .getPresupuestoByAnioRenglon(String.valueOf(now.get(Calendar.YEAR)), resultsFuncionario.getRenglon());
                   
                   Contrato libContrato = new Contrato(1,resultsFuncionario.getHonorario(),
                           resultsFuncionario.getFechaDel(),
                           resultsFuncionario.getFechaAl(),
                           resultsFuncionario.getFechaCambioTipoMovimiento());
                   BigDecimal montoTotal = BigDecimal
                           .valueOf(libContrato.getMontoTotal());
                   
                    System.out.println(montoTotal);
                   if(presupuestos != null && !presupuestos.isEmpty()){
                       Iterator it = presupuestos.iterator();
                       boolean isPresupuesto = false;
                       RrhhControlPresupuesto fuente = new RrhhControlPresupuesto();
                       while(it.hasNext()){
                           RrhhControlPresupuesto presupuesto = (RrhhControlPresupuesto)it.next();
                           BigDecimal montoFinal = contratoService.findMontoTotal(presupuesto.getIdControlPresupuesto());
                           if(montoTotal.compareTo(montoFinal) != 1){
                               isPresupuesto = true;
                               fuente = presupuesto;
                               break;
                           }
                       }
                       
                       if(isPresupuesto){
                           System.out.println("isPresupuesto: "+isPresupuesto);
                           //String usuario = sc.getUserPrincipal().getName().toUpperCase();
                           String usuario = "S/U";
                           String correlativo = contratoService
                                   .findCorrelativo(resultsFuncionario.getRenglon(), 
                                           resultsFuncionario.getTipoServicios(),
                                           String.valueOf(now.get(Calendar.YEAR)));
                           RrhhRue rue = this.createRue(usuario, resultsFuncionario);
                           RrhhLaboral laboral = this.createLaboral(usuario, 
                                   resultsFuncionario, 
                                   fuente.getIdFuenteFinanciamiento(), correlativo);
                           PersistAcademico academico = this.createAcademico(usuario, resultsFuncionario);
                           List<RrhhActividadContrato> actividades = this.createActividades(usuario, resultsFuncionario);
                           RrhhContrato contrato = this.createContrato(usuario, correlativo, resultsFuncionario);
                           RrhhMovimientosPresupuesto movimiento = this.createMovimiento(usuario, fuente, montoTotal);
                           RrhhContratoEstado contratoEstado = this.createEstado(usuario);
                           
                           boolean creado = contratoService
                                   .CrearContrato(rue, contrato, actividades, laboral, academico,movimiento,contratoEstado);
                           
                           if(creado){
                               response.setCode(200);
                               response.setMessage("Contrato Creado con exito....");
                           }
                                                      
                       }else{
                           response.setCode(403);
                           response.setMessage("Presupuesto insuficiente");
                       }
                   }else{
                       response.setCode(403);
                       response.setMessage("Error al consultar Presupuesto");
                   }
                   
               /* }else{
                    System.out.println("funcionario sin rue");
                }*/
            }else{
                response.setCode(403);
                response.setMessage("Error en validacion");
            }
        } catch (Exception e) {
           response.setCode(500);
           response.setMessage("Error interno del servidor");
        }
        return response;
    }
    
    private RrhhContratoEstado createEstado(String usuario){
        RrhhContratoEstado estado = new RrhhContratoEstado();
        try {
            estado.setFechaInsert(new Date());
            estado.setUsuarioInsert(usuario);
            estado.setIdCatalogoEstado(generalService.findEstadoById(BigDecimal.valueOf(1)));
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            estado = null;
        }
        
        return estado;
    }
    
    private RrhhMovimientosPresupuesto createMovimiento(String usuario,
            RrhhControlPresupuesto presupuesto, BigDecimal montoTotal){
        RrhhMovimientosPresupuesto movimientoPresupuesto = new RrhhMovimientosPresupuesto();
        try {
            movimientoPresupuesto.setFechaInsert(new Date());
            movimientoPresupuesto.setUsuarioInsert(usuario);
            movimientoPresupuesto.setMonto(montoTotal.negate());
            movimientoPresupuesto.setIdControlPresupuesto(presupuesto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            movimientoPresupuesto = null;
        }
        return movimientoPresupuesto;
    }
    
    private RrhhRue createRue(String usuario,ResultsFuncionario funcionario){
        RrhhRue rue = new RrhhRue();
        try {
            boolean create = false;
            if(funcionario.getIdRue() != null){
                rue = generalService.getRueById(funcionario.getIdRue());
            }else{
                create = true;
            }
            
            if(create){
                TcFuncionarios newFuncionario = funcionariosService
                        .findFuncionarioByDPI(funcionario.getDpi());
                
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();
                cal.setTime(newFuncionario.getFechaNacimiento());
                
                StringBuilder build = new StringBuilder();
                  build.append(newFuncionario.getPrimerApellido().toUpperCase().charAt(0));
                  if(newFuncionario.getSegundoApellido() != null){
                      build.append(newFuncionario.getSegundoApellido().toUpperCase().charAt(0));
                  }
                  build.append(newFuncionario.getPrimerNombre().toUpperCase().charAt(0));
                  if(newFuncionario.getSegundoNombre()!= null){
                      build.append(newFuncionario.getSegundoNombre().toUpperCase().charAt(0));
                  }
                  build.append("-");
                  build.append(format.format(cal.getTime()).replaceAll("/", ""));
                  build.append("-");
                  build.append(generalService.findCorrelativoRue(build.toString()));
                  
                
                rue.setRue(build.toString());
                rue.setUsuarioInsert(usuario);
                rue.setFechaInsert(new Date());
                rue.setPrimerNombre(newFuncionario.getPrimerNombre());
                rue.setSegundoNombre(newFuncionario.getSegundoNombre());
                rue.setPrimerApellido(newFuncionario.getPrimerApellido());
                rue.setSegundoApellido(newFuncionario.getSegundoApellido());
                rue.setFechaNacimiento(newFuncionario.getFechaNacimiento());
                rue.setGenero(newFuncionario.getSexo());
                rue.setEstadoCivil(newFuncionario.getEstadoCivil());
                rue.setTipoIdentificacion("2");
                rue.setNit(newFuncionario.getNit());
                rue.setDireccion(newFuncionario.getCalleAvenidaResidencia()
                        +" "+newFuncionario.getNumeroCasaResidencia()+" "+
                        newFuncionario.getColoniaBarrioResidencia());
                if(newFuncionario.getZonaResidencia() != null){
                    rue.setZonaVivienda(Short.valueOf(newFuncionario.getZonaResidencia().toString()));
                }               
                rue.setDepartamentoVivienda(newFuncionario.getDepartamentoResidencia());
                rue.setMunicipioVivienda(newFuncionario.getMunicipioResidencia());
                rue.setTelefonoCasa(newFuncionario.getTelefonoResidencia() != null ? 
                        newFuncionario.getTelefonoResidencia().toString() : "0");
                rue.setCuentaBancaria("0");
                rue.setLicenciaConducir("2");
                rue.setLicenciaArmas("2");
                rue.setEstado(Constants.ACTIVO);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            rue = null;
        }
        
        return rue;
    }
    
    private RrhhLaboral createLaboral(String usuario,ResultsFuncionario funcionario, RrhhFuente fuente, String correlativoContrato){
        RrhhLaboral laboral = new RrhhLaboral();
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");            
            Calendar del = Calendar.getInstance();
                del.setTime(format.parse(funcionario.getFechaDel()));
            Calendar al = Calendar.getInstance();
                al.setTime(format.parse(funcionario.getFechaAl()));
            int diffMonth = al.get(Calendar.MONTH) - del.get(Calendar.MONTH) + 1;
            
            laboral.setPlazoContrato(diffMonth+" meses");
            laboral.setUsuarioInsert(usuario);
            laboral.setFechaInsert(new Date());
            laboral.setBonoProfesional("N");
            laboral.setDocumentoMovimiento(this.numeroContrato(funcionario.getRenglon(), funcionario.getTipoServicios(),correlativoContrato));
            laboral.setEstado(Constants.ACTIVO);
            laboral.setFechaAl(format.parse(funcionario.getFechaAl()));
            laboral.setFechaDel(format.parse(funcionario.getFechaDel()));
            laboral.setFechaCambioTipoMovimiento(format
                    .parse(funcionario.getFechaCambioTipoMovimiento()));
            laboral.setFechaInicio(format.parse(funcionario.getFechaDel()));
            laboral.setFuente(fuente);
            laboral.setHonorario(BigDecimal.valueOf(funcionario.getHonorario()));
            if(funcionario.getIdRue() != null){
                laboral.setIdRue(null);
            }
            
            RrhhUbicacionFuncional ubicacionFuncional = generalService.getUbicacionFuncional(funcionario.getUbicacionFuncional());
            
            laboral.setNumeroContrato(this.numeroContrato(funcionario.getRenglon(), funcionario.getTipoServicios(),correlativoContrato));
            laboral.setPuestoNominal(generalService.getPuestoNominal(BigDecimal.valueOf(69)));
            laboral.setPuestoFuncional(generalService.getPuestoFuncional(BigDecimal.valueOf(61)));
            laboral.setUbicacionNominal(generalService.getUbicacionNominal(BigDecimal.valueOf(71)));
            laboral.setRenglon(generalService.getRenglonById(funcionario.getRenglon()));
            laboral.setTipoMovimiento(generalService.getTipoMovimientoById(BigDecimal.valueOf(48)));
            laboral.setTipoServicios(funcionario.getTipoServicios());
            laboral.setUbicacionFuncional(ubicacionFuncional);
            laboral.setEdificio(ubicacionFuncional.getEdificio());
            laboral.setNumeroDocumento("0");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            laboral = null;
        }
        return laboral;
    }
    
    private RrhhContrato createContrato(String usuario,String correlativoContrato,ResultsFuncionario resultsFuncionario){
        RrhhContrato contrato = new RrhhContrato();
        try {
            Calendar now = Calendar.getInstance();
            contrato.setFechaInsert(new Date());
            contrato.setUsuarioInsert(usuario);
            contrato.setAnio(BigInteger.valueOf(now.get(Calendar.YEAR)));
            contrato.setIdPlantilla(generalService
                    .getPlantillaByRenglonAnio(String.valueOf(now.get(Calendar.YEAR)),
                            resultsFuncionario.getRenglon(),
                            resultsFuncionario.getTipoServicios()));
            contrato.setCorrelativoContrato(BigInteger.valueOf(Integer.parseInt(correlativoContrato)));
            /*if(resultsFuncionario.getIdRue() != null){
                contrato.setIdRue(generalService.getRueById(resultsFuncionario.getIdRue()));
            }*/
            if(contrato.getObservaciones() != null && contrato.getObservaciones().isEmpty()){
                contrato.setObservaciones(resultsFuncionario.getObservaciones());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            contrato = null;
        }
        return contrato;
    }
    
    private List<RrhhActividadContrato> createActividades(String usuario, ResultsFuncionario funcionario){
        List<RrhhActividadContrato> actividades = new ArrayList<RrhhActividadContrato>();
        try {
            for(ResultsActividad actividadResult: funcionario.getActividades()){
                if(actividadResult.isSeleccionado()){
                    RrhhActividadContrato actividadContrato = new RrhhActividadContrato();
                        actividadContrato.setEstado(Constants.ACTIVO);
                        actividadContrato.setFechaInsert(new Date());
                        actividadContrato.setUsuarioInsert(usuario);
                        actividadContrato.setIdActividad(actividadPerfilService
                            .findActividadById(actividadResult.getIdActividad()));                       
                        actividadContrato.setIdPerfil(actividadPerfilService
                                .findPerfilById(funcionario.getIdPerfil()));
                    actividades.add(actividadContrato);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            actividades = new ArrayList<RrhhActividadContrato>();
        }
        return actividades;
    }
    
    private PersistAcademico createAcademico(String usuario, ResultsFuncionario resultsFuncionario){
        PersistAcademico persist = new PersistAcademico();
        RrhhAcademico academico = new RrhhAcademico();
        try {
            boolean create = false;
            
            if(resultsFuncionario.getIdRue() != null){
                    academico = generalService.findAcademicoByTituloRueColegio(
                    resultsFuncionario.getInfoAcademica().getTitulo(),
                    resultsFuncionario.getIdRue(),
                    resultsFuncionario.getInfoAcademica().getColegioProfesional(),
                    resultsFuncionario.getTipoServicios());
                    
                    if(academico == null){
                        create = true;
                    }else{
                        if(resultsFuncionario.getTipoServicios().equalsIgnoreCase("P")){
                            if(!academico.getNumeroColegiado().equalsIgnoreCase(resultsFuncionario.getInfoAcademica().getNumeroColegiado())){
                                academico.setFechaUpdate(new Date());
                                academico.setUsuarioUpdate(usuario);
                                academico.setNumeroColegiado(resultsFuncionario.getInfoAcademica().getNumeroColegiado());
                                persist.setUpdate(true);
                            }
                        }                        
                    }
            }else{
                create = true;
            }
            
            if(create){
                academico = new RrhhAcademico();
                /*if(resultsFuncionario.getIdRue() != null){
                    academico.setIdRue(generalService.getRueById(resultsFuncionario.getIdRue()));
                }*/
                academico.setGradoAcademico(titulosService.getGradoAcademicoById(BigDecimal.valueOf(39)));
                academico.setTitulo(titulosService
                        .getTituloById(resultsFuncionario.getInfoAcademica().getTitulo()));
                academico.setEstado(Constants.ACTIVO);
                if(resultsFuncionario.getTipoServicios().equalsIgnoreCase("P")){
                    academico.setColegioProfesional(colegioService
                            .getTituloById(resultsFuncionario.getInfoAcademica().getColegioProfesional()));
                    academico.setNumeroColegiado(resultsFuncionario.getInfoAcademica().getNumeroColegiado());
                    academico.setNivelEducativo("5");
                }else{
                    academico.setNivelEducativo("4");
                }
                academico.setFechaInsert(new Date());
                academico.setUsuarioInsert(usuario);
                
                
                persist.setCreate(true);
            }
            
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            academico = null;
        }
        
        persist.setRrhhAcademico(academico);
        return persist;
    }
    
    private String numeroContrato(String renglon, String tipoServicios, String correlativo){
        try {
            Calendar now = Calendar.getInstance();
            
            if(correlativo == null)
                throw new Exception("Ocurrio un error con el correlativo. ");
            
            
            StringBuilder builder = new StringBuilder();
            builder.append("RRHH");
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
    
    private boolean validate(ResultsFuncionario resultsFuncionario){
        boolean eval = true;
        try {
            
            if(resultsFuncionario.getActividades() == null){
                eval = false;
            }else if(resultsFuncionario.getActividades().isEmpty()){
                eval = false;
            }else {
                boolean active = false;
                for(ResultsActividad actividad:resultsFuncionario.getActividades()){
                    if(actividad.isSeleccionado()){
                        eval = true;
                        active = true;
                        break;
                    }
                }
                
                if(!active){
                    eval = false;
                }
            }
                       
            if(resultsFuncionario.getDpi() == null){
                eval = false;
            }else if(resultsFuncionario.getDpi().isEmpty()){
                eval = false;
            }
            
            if(resultsFuncionario.getFechaAl() == null){
                eval = false;
            }else if(resultsFuncionario.getFechaAl().isEmpty()){
                eval = false;
            }
            
            if(resultsFuncionario.getFechaCambioTipoMovimiento() == null){
                eval = false;
            }else if(resultsFuncionario.getFechaCambioTipoMovimiento().isEmpty()){
                eval = false;
            }
            
            if(resultsFuncionario.getFechaDel() == null){
                eval = false;
            }else if(resultsFuncionario.getFechaDel().isEmpty()){                
                eval = false;                 
            }
            
            if(resultsFuncionario.getHonorario() == null){
                eval = false;
            }
            
            if(resultsFuncionario.getIdPerfil() == null){
                eval = false;
            }
            
            if(resultsFuncionario.getTipoServicios() == null){
                eval = false;
            }else if(resultsFuncionario.getTipoServicios().isEmpty()){
                eval = false;
            }else{
                if(resultsFuncionario.getInfoAcademica() == null){
                     eval = false;
                }else{
                    if(resultsFuncionario.getInfoAcademica().getTitulo() == null){
                        eval = false;
                    }

                    if(resultsFuncionario.getTipoServicios().equalsIgnoreCase("P")){
                        if(resultsFuncionario.getInfoAcademica().getColegioProfesional() == null){
                            eval = false;
                        }

                        if(resultsFuncionario.getInfoAcademica().getNumeroColegiado() == null){
                            eval = false;
                        }else if(resultsFuncionario.getInfoAcademica().getNumeroColegiado().isEmpty()){
                            eval = false;
                        }
                    }
                }
            }
            
            if(resultsFuncionario.getRenglon() == null){
                eval = false;
            }else if(resultsFuncionario.getRenglon().isEmpty()){
                eval = false;
            }
            
            if(resultsFuncionario.getUbicacionFuncional() == null){
                eval = false;
            }
            
        } catch (Exception e) {
           System.out.println(e.getMessage());
           eval = false;
        }
        return eval;
    }
    
}
