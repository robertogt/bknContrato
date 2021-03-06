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
import cgc.rrhh.contratos.model.RrhhLaboral;
import cgc.rrhh.contratos.model.RrhhMovimientosPresupuesto;
import cgc.rrhh.contratos.model.RrhhUbicacionFuncional;
import cgc.rrhh.contratos.pojo.PersistAcademico;
import cgc.rrhh.contratos.pojo.PersistActividades;
import cgc.rrhh.contratos.pojo.ResultsActividad;
import cgc.rrhh.contratos.pojo.ResultsFuncionario;
import cgc.rrhh.contratos.service.ActividadPerfilService;
import cgc.rrhh.contratos.service.AsesorService;
import cgc.rrhh.contratos.service.ColegioService;
import cgc.rrhh.contratos.service.ContratoService;
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
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import org.apache.log4j.Logger;
import rrhh.calculos.contrato.Contrato;

/**
 *
 * @author ejmorales
 */
@Stateless
@RolesAllowed("rrhh_contrato")
@Path(Constants.EDITAR)
public class ContratoEditarREST {
    
     @EJB
    private ContratoService contratoService;
    
    @EJB
    private GeneralService generalService;
    
    @EJB
    private ActividadPerfilService actividadPerfilService;
    
    @EJB
    private ColegioService colegioService;
    
    @EJB
    private TitulosService titulosService;
    
    @EJB
    private AsesorService asesorService;
    
    private static final Logger log = Logger.getLogger(ContratoEditarREST.class);
    
    private String findCorrelativo(String renglon, String tipoServicios) throws Exception{        
        Calendar now = Calendar.getInstance();
        Integer count = 0;
        BigDecimal correlativo = BigDecimal.ONE;
        try {
            do{
                count++;
                correlativo = contratoService
                                   .findCorrelativo(count,renglon, 
                                           tipoServicios,
                                           String.valueOf(now.get(Calendar.YEAR)));                 
            }while(correlativo != null);
        } catch (Exception e) {
            log.error("findCorrelativo: ",e);
            throw new Exception("Error findCorrelativo");            
        }
        
        return count.toString();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData editarContrato(ResultsFuncionario funcionario,
            @Context SecurityContext sc){
        ResponseData response = new ResponseData();
        response.setCode(403);
        response.setMessage("Error al editar contrato");
        try {
            //String usuario = "S/U";
            String usuario = sc.getUserPrincipal().getName().toUpperCase();
            
            if(funcionario != null && funcionario.getIdContrato() != null){                
                RrhhLaboral laboral = contratoService.findLaboralByContrato(funcionario.getIdContrato());
                
                if(laboral == null)
                    throw new Exception("laboral es nulo");
                
                funcionario.setIdRue(BigDecimal.valueOf(laboral.getIdRue().getIdRue()));
                RrhhMovimientosPresupuesto movimiento = contratoService
                        .findMovimientoByContrato(laboral.getIdContrato().getIdContrato());
                
                if(movimiento == null)
                    throw new Exception("movimiento es nulo");
                
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    if(!laboral.getEstado().equalsIgnoreCase("A") && !laboral.getEstado().equalsIgnoreCase("B") && !laboral.getEstado().equalsIgnoreCase("X")){
                        
                       boolean change = false;
                       String correlativo = null;
                        if(!funcionario.getRenglon().equalsIgnoreCase(laboral.getRenglon().getRenglon())){
                           change = true;                            
                       }
                        
                       if(!funcionario.getTipoServicios().equalsIgnoreCase(laboral.getTipoServicios())){
                           change = true;
                       }
                       
                       if(change){
                           //Calendar now = Calendar.getInstance();
                           /*correlativo = contratoService
                                   .findCorrelativo(funcionario.getRenglon(), 
                                           funcionario.getTipoServicios(),
                                           String.valueOf(now.get(Calendar.YEAR))).toString();*/
                           correlativo = this.findCorrelativo(funcionario.getRenglon(), funcionario.getTipoServicios());
                       }
                        
                       RrhhLaboral modifLaboral = this.setLaboral(usuario,laboral,funcionario,change,correlativo);
                       RrhhContrato contrato = this.setContrato(usuario,laboral.getIdContrato(),funcionario,change,correlativo);
                       PersistAcademico academico = this.setAcademico(usuario, funcionario);
                       RrhhMovimientosPresupuesto crear = new RrhhMovimientosPresupuesto();
                       RrhhMovimientosPresupuesto anular = new RrhhMovimientosPresupuesto();
                       
                       boolean cambio = false;
                       
                       if(funcionario.getHonorario().compareTo(laboral.getHonorario().doubleValue()) != 0){
                           cambio = true;
                       }
                       
                       String fechaDel = format.format(format.parse(funcionario.getFechaDel()));
                       String fechaAl = format.format(format.parse(funcionario.getFechaAl()));
                       
                       if(!format.format(laboral.getFechaDel()).equalsIgnoreCase(fechaDel)){
                           cambio = true;
                       }
                       
                       if(!format.format(laboral.getFechaAl()).equalsIgnoreCase(fechaAl)){
                           cambio = true;
                       }
                       /*if(!format.format(laboral.getFechaDel()).equalsIgnoreCase(funcionario.getFechaDel())){
                           cambio = true;
                       }
                       
                       if(!format.format(laboral.getFechaAl()).equalsIgnoreCase(funcionario.getFechaAl())){
                           cambio = true;
                       }*/
                       
                       if(cambio){
                           
                           Contrato temp = new Contrato(laboral.getIdContrato().getCorrelativoContrato().intValue(),
                                   funcionario.getHonorario(),
                                   funcionario.getFechaDel(),
                                   funcionario.getFechaAl(),
                                   funcionario.getFechaCambioTipoMovimiento());
                           
                           BigDecimal montoTotal = contratoService.findMontoTotal(movimiento.getIdControlPresupuesto().getIdControlPresupuesto());
                           log.info(montoTotal);
                           BigDecimal montoReal = montoTotal.add(movimiento.getMonto().abs());
                           log.info(montoReal);
                                
                              log.info(movimiento.getIdControlPresupuesto().getIdControlPresupuesto());
                               anular.setFechaInsert(new Date());
                               anular.setUsuarioInsert(usuario);
                               anular.setIdContrato(contrato);
                               anular.setIdControlPresupuesto(movimiento.getIdControlPresupuesto());
                               anular.setMonto(movimiento.getMonto().abs());
                           
                           BigDecimal montoContrato = BigDecimal.valueOf(temp.getMontoTotal());
                           if(montoContrato.compareTo(montoReal) != 1){
                               modifLaboral.setHonorario(BigDecimal.valueOf(funcionario.getHonorario()));    
                               modifLaboral.setFuente(movimiento.getIdControlPresupuesto().getIdFuenteFinanciamiento());
                               
                               log.info(movimiento.getIdControlPresupuesto().getIdControlPresupuesto());
                               crear.setFechaInsert(new Date());
                               crear.setUsuarioInsert(usuario);
                               crear.setIdContrato(contrato);
                               crear.setIdControlPresupuesto(movimiento.getIdControlPresupuesto());
                               crear.setMonto(montoContrato.negate());
                           }else{
                               Calendar now = Calendar.getInstance();
                               List<RrhhControlPresupuesto> presupuestos = contratoService
                                  .getPresupuestoByAnioRenglon(String.valueOf(now.get(Calendar.YEAR)), funcionario.getRenglon());
                               
                               if(presupuestos != null && !presupuestos.isEmpty()){                               
                               Iterator it = presupuestos.iterator();
                                boolean isPresupuesto = false;
                                RrhhControlPresupuesto fuente = new RrhhControlPresupuesto();
                                while(it.hasNext()){
                                    RrhhControlPresupuesto presupuesto = (RrhhControlPresupuesto)it.next();
                                    BigDecimal montoFinal = contratoService.findMontoTotal(presupuesto.getIdControlPresupuesto());
                                    if(montoContrato.compareTo(montoFinal) != 1){
                                        isPresupuesto = true;
                                        fuente = presupuesto;
                                        break;
                                    }
                                }
                                
                                  if(isPresupuesto){
                                        modifLaboral.setFuente(fuente.getIdFuenteFinanciamiento());
                                        modifLaboral.setHonorario(BigDecimal.valueOf(funcionario.getHonorario()));
                                        log.info("isPresupuesto: "+fuente.getIdControlPresupuesto());
                                        crear.setFechaInsert(new Date());
                                        crear.setUsuarioInsert(usuario);
                                        crear.setIdContrato(contrato);
                                        crear.setIdControlPresupuesto(fuente);
                                        crear.setMonto(montoContrato.negate());
                                  }else{
                                        response.setCode(403);
                                        response.setMessage("Presupuesto insuficiente");
                                        throw new Exception("Presupuesto insuficiente");
                                   }
                               }else{
                                    response.setCode(403);
                                    response.setMessage("Error al consultar Presupuesto");
                                    throw new Exception("Error al consultar Presupuesto");
                               }
                           }
                           
                       }else{
                           crear = null;
                           anular = null;
                       }
                       
                       
                       List<RrhhActividadContrato> actividades = actividadPerfilService
                               .findActividadesByPerfilContrato(funcionario.getIdPerfil(),
                                       laboral.getIdContrato().getIdContrato());
                       ///***
                        PersistActividades persistActividades = new PersistActividades();
                       if(actividades != null && !actividades.isEmpty()){
                           persistActividades = this.actividadesUpdate(actividades, funcionario.getActividades(), usuario,
                                   funcionario.getIdPerfil(), laboral.getIdContrato());
                       }else{
                           List<RrhhActividadContrato> actContrato = actividadPerfilService
                                   .findActividadContratoByContrato(laboral.getIdContrato().getIdContrato());
                           for(RrhhActividadContrato contrat: actContrato){
                               contrat.setFechaUpdate(new Date());
                               contrat.setUsuarioUpdate(usuario);
                               contrat.setEstado(Constants.ANULADO);
                           }
                           
                           persistActividades.setUpdate(actContrato);
                           
                           List<RrhhActividadContrato> ingActividad = new ArrayList<RrhhActividadContrato>();
                           for(ResultsActividad temp: funcionario.getActividades()){
                                if(temp.isSeleccionado()){
                                    RrhhActividadContrato newActContrato = new RrhhActividadContrato();
                                    newActContrato.setEstado(Constants.ACTIVO);
                                    newActContrato.setFechaInsert(new Date());
                                    newActContrato.setIdActividad(actividadPerfilService.findActividadById(temp.getIdActividad()));
                                    newActContrato.setIdContrato(laboral.getIdContrato());
                                    newActContrato.setIdPerfil(actividadPerfilService.findPerfilById(funcionario.getIdPerfil()));
                                    newActContrato.setUsuarioInsert(usuario);
                                    ingActividad.add(newActContrato);
                                }
                           }
                           
                           persistActividades.setCrear(ingActividad);
                           
                       }
                       
                        RrhhContratoEstado estado = asesorService.findEstadoByContrato(laboral.getIdContrato().getIdContrato(),BigDecimal.valueOf(3));
                        RrhhContratoEstado contratoEstado = null;
                        
                        if(estado != null){
                                contratoEstado = new RrhhContratoEstado();
                                contratoEstado.setEstado(Constants.ACTIVO);
                                contratoEstado.setFechaInsert(new Date());
                                contratoEstado.setIdContrato(laboral.getIdContrato());
                                contratoEstado.setIdCatalogoEstado(generalService.findEstadoById(BigDecimal.valueOf(2)));
                                contratoEstado.setUsuarioInsert(usuario);
                                
                                
                                estado.setEstado("F");
                                estado.setFechaUpdate(new Date());
                                estado.setUsuarioUpdate(usuario);
                        }
                       
                       
                       contratoService.editarContrato(laboral, contrato, academico, crear, anular, persistActividades,estado, contratoEstado);
                       response.setCode(200);
                       response.setMessage("Contrato "+laboral.getNumeroContrato()+" editado con éxito");
                       
                       
                    }else{                        
                        response.setMessage("No se puede editar un contrato Activo, de Baja o Anulado. ");                
                    }   
            }
        } catch (Exception e) {
             log.error("editarContrato: ",e);
        }
        return response;
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
                        
            return builder.toString();
        } catch (Exception e) {
            log.error("numeroContrato: ",e);
            return null;
        }
    }
    
    private PersistActividades actividadesUpdate(List<RrhhActividadContrato> actividadContrato, 
            List<ResultsActividad> resultsActividad, String usuario, BigDecimal idPerfil, RrhhContrato contrato){
        PersistActividades persist = new PersistActividades();
        
        List<RrhhActividadContrato> update = new ArrayList<RrhhActividadContrato>();
        List<RrhhActividadContrato> create = new ArrayList<RrhhActividadContrato>();
        try {
            for(RrhhActividadContrato actividad: actividadContrato){
                boolean eval = false;
                
                for(ResultsActividad actividadTarget: resultsActividad){                   
                    if(actividad.getIdActividad().getIdActividad().equals(actividadTarget.getIdActividad())
                            && actividadTarget.isSeleccionado()){
                        if(!actividad.getEstado().equalsIgnoreCase(Constants.ACTIVO)){
                            actividad.setFechaUpdate(new Date());
                            actividad.setUsuarioUpdate(usuario);
                            actividad.setEstado(Constants.ACTIVO);
                            update.add(actividad);                         
                        }
                           eval = true;
                    }
                }
                
                if(!eval){
                    actividad.setFechaUpdate(new Date());
                    actividad.setUsuarioUpdate(usuario);
                    actividad.setEstado(Constants.ANULADO);
                    update.add(actividad);
                }
            }
            
            
            for(ResultsActividad results: resultsActividad){
                boolean eval = true;
                for(RrhhActividadContrato contratoActividad: actividadContrato){
                    if(results.getIdActividad().equals(contratoActividad.getIdActividad().getIdActividad())){
                       eval = false;
                    }else if (!results.isSeleccionado()){
                        eval = false;
                    }
                }
                
                if(eval){
                        RrhhActividadContrato newActividad = new RrhhActividadContrato();
                        newActividad.setEstado(Constants.ACTIVO);
                        newActividad.setFechaInsert(new Date());
                        newActividad.setUsuarioInsert(usuario);
                        newActividad.setIdActividad(actividadPerfilService.findActividadById(results.getIdActividad()));
                        newActividad.setIdPerfil(actividadPerfilService.findPerfilById(idPerfil));
                        newActividad.setIdContrato(contrato);
                        
                        create.add(newActividad);
                }
            }
        } catch (Exception e) {
            log.error("actividadesUpdate: ",e);
        }
        persist.setCrear(create);
        persist.setUpdate(update);
        
        return persist;
    }
    
    
    private RrhhLaboral setLaboral(String usuario,RrhhLaboral laboral,
            ResultsFuncionario funcionario, boolean  change, String correlativo) throws Exception{
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");            
            RrhhUbicacionFuncional ubicacionFuncional = generalService
                    .getUbicacionFuncional(funcionario.getUbicacionFuncional());
            log.info(ubicacionFuncional.getNombre());
            Calendar del = Calendar.getInstance();
                del.setTime(format.parse(funcionario.getFechaDel()));
            Calendar al = Calendar.getInstance();
                al.setTime(format.parse(funcionario.getFechaAl()));
            int diffMonth = al.get(Calendar.MONTH) - del.get(Calendar.MONTH) + 1;
            
            if(change){
                laboral.setDocumentoMovimiento(this.numeroContrato(funcionario.getRenglon(), funcionario.getTipoServicios(), correlativo));
                laboral.setNumeroContrato(this.numeroContrato(funcionario.getRenglon(), funcionario.getTipoServicios(), correlativo));
            }
            
            laboral.setPlazoContrato(diffMonth+" meses");
            laboral.setEdificio(ubicacionFuncional.getEdificio());            
            laboral.setFechaAl(format.parse(funcionario.getFechaAl()));
            laboral.setFechaCambioTipoMovimiento(format.parse(funcionario.getFechaCambioTipoMovimiento()));
            laboral.setFechaDel(format.parse(funcionario.getFechaDel()));
            laboral.setFechaInicio(format.parse(funcionario.getFechaDel()));
            laboral.setFechaUpdate(new Date());
            //laboral.setFuente(fuente);
            //laboral.setHonorario(BigDecimal.ZERO);
            laboral.setRenglon(generalService.getRenglonById(funcionario.getRenglon()));
            laboral.setTipoServicios(funcionario.getTipoServicios());
            laboral.setUbicacionFuncional(ubicacionFuncional);
            laboral.setUsuarioUpdate(usuario);
            
            
            
            return laboral;
        } catch (Exception e) {
            log.error("setLaboral: ",e);
            throw new Exception("Error al cargar la informacion laboral. ");
        }
        
    }
    
    private RrhhContrato setContrato(String usuario, RrhhContrato contrato,
            ResultsFuncionario funcionario, boolean change, String correlativo) throws Exception{
        try {
             Calendar now = Calendar.getInstance();
            contrato.setAnio(BigInteger.valueOf(now.get(Calendar.YEAR)));
            contrato.setFechaUpdate(new Date());
            contrato.setUsuarioUpdate(usuario);
            if(funcionario.getObservaciones() != null && !funcionario.getObservaciones().isEmpty()){
               contrato.setObservaciones(funcionario.getObservaciones());
            }
            
            if(change){                
                contrato.setCorrelativoContrato(BigInteger.valueOf(Integer.valueOf(correlativo)));
            }
            
           contrato.setIdPlantilla(generalService
                    .getPlantillaByRenglonAnio(String.valueOf(now.get(Calendar.YEAR)),
                            funcionario.getRenglon(),
                            funcionario.getTipoServicios(),BigDecimal.valueOf(1)));
            
            return contrato;
        } catch (Exception e) {
            log.error("setContrato: ",e);
            throw new Exception(e.getMessage());
        }
    }
    
    
    private PersistAcademico setAcademico(String usuario, ResultsFuncionario resultsFuncionario){
        PersistAcademico persist = new PersistAcademico();
        RrhhAcademico academico = new RrhhAcademico();
        try {
            boolean create = false;
            
                    academico = generalService.findAcademicoByTituloRueColegio(
                    resultsFuncionario.getAcademico().getTitulo(),
                    resultsFuncionario.getIdRue(),
                    resultsFuncionario.getAcademico().getColegioProfesional(),
                    resultsFuncionario.getTipoServicios());
                    
                    if(academico == null){
                        create = true;
                    }else{
                        if(resultsFuncionario.getTipoServicios().equalsIgnoreCase("P")){
                            if(!academico.getNumeroColegiado().equalsIgnoreCase(resultsFuncionario.getAcademico().getNumeroColegiado())){
                                academico.setFechaUpdate(new Date());
                                academico.setUsuarioUpdate(usuario);
                                academico.setNumeroColegiado(resultsFuncionario.getAcademico().getNumeroColegiado());
                                persist.setUpdate(true);
                            }
                        }                        
                    }
            
            
            if(create){
                academico = new RrhhAcademico();
                /*if(resultsFuncionario.getIdRue() != null){
                    academico.setIdRue(generalService.getRueById(resultsFuncionario.getIdRue()));
                }*/
                academico.setGradoAcademico(titulosService.getGradoAcademicoById("39"));
                academico.setTitulo(titulosService
                        .getTituloById(resultsFuncionario.getAcademico().getTitulo()));
                academico.setEstado(Constants.ACTIVO);
                if(resultsFuncionario.getTipoServicios().equalsIgnoreCase("P")){
                    academico.setColegioProfesional(colegioService
                            .getTituloById(resultsFuncionario.getAcademico().getColegioProfesional()));
                    academico.setNumeroColegiado(resultsFuncionario.getAcademico().getNumeroColegiado());
                    academico.setNivelEducativo("5");
                }else{
                    academico.setNivelEducativo("4");
                }
                academico.setFechaInsert(new Date());
                academico.setUsuarioInsert(usuario);
                
                
                persist.setCreate(true);
            }
            
            
            
        } catch (Exception e) {
            log.error("setAcademico: ",e);
            academico = null;
        }
        
        persist.setRrhhAcademico(academico);
        return persist;
    }
}
