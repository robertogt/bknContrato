/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.service;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhAcademico;
import cgc.rrhh.contratos.model.RrhhActividadContrato;
import cgc.rrhh.contratos.model.RrhhContrato;
import cgc.rrhh.contratos.model.RrhhContratoEstado;
import cgc.rrhh.contratos.model.RrhhControlPresupuesto;
import cgc.rrhh.contratos.model.RrhhLaboral;
import cgc.rrhh.contratos.model.RrhhMovimientosPresupuesto;
import cgc.rrhh.contratos.model.RrhhRue;
import cgc.rrhh.contratos.pojo.PersistAcademico;
import cgc.rrhh.contratos.pojo.PersistActividades;
import cgc.rrhh.contratos.pojo.ResultsAcademico;
import cgc.rrhh.contratos.pojo.ResultsActividad;
import cgc.rrhh.contratos.pojo.ResultsContrato;
import cgc.rrhh.contratos.pojo.ResultsFuncionario;
import cgc.rrhh.contratos.pojo.ResultsHistorial;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;

/**
 *
 * @author ejmorales
 */
@Stateless
@LocalBean
public class ContratoService extends GenericAbstractService<RrhhContrato>{
    
    @PersistenceContext(unitName = Constants.PERSIST_RUE)
    private EntityManager em;
    
    private static final Logger log = Logger.getLogger(ContratoService.class);
    
    public ContratoService() {
        super(RrhhContrato.class);
    }
    
    
    public ResultsFuncionario findRueByDPI(String dpi){
        try {
            TypedQuery<ResultsFuncionario> query = em
                    .createNamedQuery("RrhhRue.findByDpi", ResultsFuncionario.class);
            query.setMaxResults(1);
            query.setParameter(1, dpi);            
            return query.getSingleResult();
        } catch (NonUniqueResultException | NoResultException  nr) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ResultsFuncionario findFuncionarioByDpi(String dpi){
        try {
            TypedQuery<ResultsFuncionario> query = em
                    .createNamedQuery("TcFuncionarios.NativefindByDpi", ResultsFuncionario.class);
            query.setMaxResults(1);
            query.setParameter(1, dpi);
            return query.getSingleResult();
        } catch (NonUniqueResultException | NoResultException nr){
             return null;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ResultsAcademico findAcademicoByRue(BigDecimal idRue, String tipoServicio){
        try {
            String nameQuery = "";
            if(tipoServicio.equalsIgnoreCase("T")){
                nameQuery = "RrhhRue.findAcademicoTecnico";
            }else if(tipoServicio.equalsIgnoreCase("P")){
                nameQuery = "RrhhRue.findAcademicoProfesional";
            }
            
            TypedQuery<ResultsAcademico> query = em
                    .createNamedQuery(nameQuery, ResultsAcademico.class);
            query.setMaxResults(1);
            query.setParameter(1, idRue);
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException nr) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ResultsAcademico getAcademicoByContrato(BigDecimal idContrato){
        try {
            TypedQuery<ResultsAcademico> query = em
                    .createNamedQuery("RrhhRue.findActividadByContrato", ResultsAcademico.class);
            query.setMaxResults(1);
            query.setParameter(1, idContrato);
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException nr) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public List<RrhhControlPresupuesto> getPresupuestoByAnioRenglon(String anio, String renglon){
        try {
            TypedQuery<RrhhControlPresupuesto> query = em
                    .createNamedQuery("RrhhControlPresupuesto.findByAnioRenglon",RrhhControlPresupuesto.class);
            query.setParameter("anio", anio);
            query.setParameter("renglon", renglon);            
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return new ArrayList<RrhhControlPresupuesto>();
        }
    }
    
    public BigDecimal findCorrelativo(String renglon, String tipoServicios, String anio){
        try {
            Query query = em.createNativeQuery("SELECT NVL(MAX(C.CORRELATIVO_CONTRATO),0) + 1 CORRELATIVO FROM RRHH_CONTRATO C INNER JOIN RRHH_LABORAL L ON C.ID_CONTRATO = L.ID_CONTRATO WHERE L.RENGLON = ? AND L.TIPO_SERVICIOS = ? AND C.ANIO = ?  ");
            query.setParameter(1, renglon);
            query.setParameter(2, tipoServicios);
            query.setParameter(3, anio);
            
            BigDecimal value = (BigDecimal)query.getSingleResult();
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public BigDecimal findMontoTotal(BigDecimal idPresupuesto)throws Exception{
        try {
            Query query = em.createNativeQuery("SELECT A.MONTO + B.MONTO MONTO_FINAL FROM ( " +
"SELECT MONTO FROM RRHH_CONTROL_PRESUPUESTO " +
"WHERE ID_CONTROL_PRESUPUESTO = ?id ) A, " +
"(SELECT NVL(SUM(MONTO),0) AS MONTO FROM RRHH_MOVIMIENTO_PRESUPUESTO " +
"WHERE ID_CONTROL_PRESUPUESTO = ?id ) B ");
            query.setParameter("id", idPresupuesto);
            
            BigDecimal total = (BigDecimal)query.getSingleResult();
            return total;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    
    
    /**
     * @param rue
     * @param contrato
     * @param actividades
     * @param laboral
     * @param academico       
     * @param movimiento       
     * @since 16-11-2017 
     * @return boolean
     * @throws java.lang.Exception 
    **/
    public boolean CrearContrato(RrhhRue rue,RrhhContrato contrato,
            List<RrhhActividadContrato> actividades, RrhhLaboral laboral,
            PersistAcademico academico, RrhhMovimientosPresupuesto movimiento,
            RrhhContratoEstado contratoEstado) throws Exception{
        boolean eval = false;
        try {
            
            if(rue == null)
                throw new Exception("Rue es nulo");
            
            if(contrato == null)
                throw new Exception("Contrato es nulo");
            
            if(actividades == null)
                throw new Exception("Actividades es nulo");
            
            if(laboral == null)
                throw new Exception("Laboral es nulo");
            
            if(academico == null)
                throw new Exception("Academico es nulo");
            
            if(movimiento == null)
                throw new Exception("MovimientoPresupuesto es nulo");
            
            if(contratoEstado == null)
                throw new Exception("ContratoEstado es nulo");
            
            
            if(rue.getIdRue() != null){                           
            
            }else{
                em.persist(rue);
                if(rue.getIdRue() == null)
                    throw new Exception("Error al crear Rue");
            }
            
            
            if(academico.isCreate()){
                academico.getRrhhAcademico().setIdRue(rue);
                em.persist(academico.getRrhhAcademico());
            }else if(academico.isUpdate()){
                em.merge(academico.getRrhhAcademico());
            }
            
            
            if(academico.getRrhhAcademico().getAcademico() == null)
                throw new Exception("Error al crear Academico");
            contrato.setAcademico(academico.getRrhhAcademico());
            em.persist(contrato);
            
            
            if(contrato.getIdContrato() == null)
                throw new Exception("Error al crear Contrato");
            laboral.setIdRue(rue);
            laboral.setIdContrato(contrato);
            em.persist(laboral);
            
            
            if(laboral.getLaboral() == null)
                throw new Exception("Error al crear Laboral");
            
            for(RrhhActividadContrato actividad: actividades){
                actividad.setIdContrato(contrato);
                em.persist(actividad);      
            }                        
            
            movimiento.setIdContrato(contrato);
            em.persist(movimiento);
            
            
            if(movimiento.getIdMovimientosPresupuesto() == null)
                throw new Exception("Error al crear Movimiento");
            
            
            contratoEstado.setIdContrato(contrato);
            em.persist(contratoEstado);
            
            if(contratoEstado.getIdContratoEstado() == null)
                throw new Exception("Error al crear Estado de Contrato");
            
            eval = true;
        } catch (Exception e) {
           log.error("CrearContrato",e);
            throw new Exception(e.getMessage());
            
        }
        return eval;
    }
    
    public void editarContrato(RrhhLaboral laboral, RrhhContrato contrato,
            PersistAcademico persistAcademico,RrhhMovimientosPresupuesto crear,
            RrhhMovimientosPresupuesto anular,PersistActividades persistActividad,
            RrhhContratoEstado modifEstado, RrhhContratoEstado crearEstado) throws Exception {
        try {
            RrhhAcademico academico = persistAcademico.getRrhhAcademico();
            
             if(contrato == null)
                throw new Exception("Contrato es nulo");
            
            if(academico == null)
                throw new Exception("academico es nulo");
            
            if(laboral == null)
                throw new Exception("Laboral es nulo");            
            
            if(persistActividad == null)
                throw new Exception("persistActividad es nulo");
            
            if(persistAcademico.isCreate()){
               academico.setIdRue(laboral.getIdRue());
               em.persist(academico);
            }else if(persistAcademico.isUpdate()){
                em.merge(academico);
            }
            
            if(academico.getAcademico() == null)
                throw new Exception("Academico es nulo");
            
            contrato.setAcademico(academico);
            em.merge(contrato);
            
            em.merge(laboral);
            
           if(anular != null){
               em.persist(anular);
            
                if(anular.getIdMovimientosPresupuesto() == null)
                    throw new Exception("Anular movimiento es nulo");
                
                if(crear == null)
                    throw new Exception("crear es nulo");
                
                 em.persist(crear);
            
                if(crear.getIdMovimientosPresupuesto() == null)
                    throw new Exception("crear movimiento es nulo");
                
           }
           
           if(modifEstado != null){
               em.merge(modifEstado);
               
               if(crearEstado == null)
                   throw new Exception("Crear Estado es nulo");
               
               em.persist(crearEstado);
               
               if(crearEstado.getIdContratoEstado() == null)
                   throw new Exception("No se pudo crear el Contrato Estado");
           }
            
            for(RrhhActividadContrato crearActividadContrato:persistActividad.getCrear()){
                em.persist(crearActividadContrato);
            }
            
            for(RrhhActividadContrato updateActividadContrato: persistActividad.getUpdate()){
                em.merge(updateActividadContrato);
            }
            
        } catch (Exception e) {
            log.error("error al editar contrato: ",e);
            throw new Exception(e.getMessage());
        }
    }
    
    public void createFianza(RrhhLaboral laboral, RrhhContratoEstado update,
            RrhhContratoEstado create, boolean cambio) throws Exception{
        try {
            if(laboral == null)
                throw new Exception("laboral es nulo");
            
            em.merge(laboral);
            
            if(cambio){
               em.merge(update);
               
               if(create == null)
                   throw new Exception("create  estado es nulo");
               
               em.persist(create);
               
               if(create.getIdContratoEstado() == null)
                   throw new Exception("no pudo crearse el estado contrato ");
            }
            
            
        } catch (Exception e) {
            log.error("createFianza: ",e);
            throw new Exception(e.getMessage());
        }
    }
    
    public RrhhLaboral findLaboralByContrato(BigDecimal idContrato){
        try {
            TypedQuery<RrhhLaboral> query = em
                    .createNamedQuery("RrhhLaboral.findByContrato",RrhhLaboral.class);
            query.setParameter("contrato", idContrato);            
            return query.getSingleResult();
        } catch (Exception e) {
            log.error("findLaboralByContrato: ",e);
            return null;
        }
    }
    
    public ResultsFuncionario findContratosByidContrato(BigDecimal idContrato ){
        try {
            TypedQuery<ResultsFuncionario> query = em
                    .createNamedQuery("RrhhContrato.findByContrato", ResultsFuncionario.class);
            query.setMaxResults(1);
            query.setParameter(1, idContrato);            
            return query.getSingleResult();
        } catch (NonUniqueResultException | NoResultException  nr) {
            return null;
        } catch (Exception e) {
            log.error("findContratosByidContrato: ",e);
            return null;
        }
    }
    
    public RrhhMovimientosPresupuesto findMovimientoByContrato(BigDecimal idContrato){
        try {
            TypedQuery<RrhhMovimientosPresupuesto> query = em
                    .createNamedQuery("RrhhMovimientosPresupuesto.findByContrato",RrhhMovimientosPresupuesto.class);
            query.setMaxResults(1);
            query.setParameter("contrato", idContrato);
            return query.getSingleResult();
          } catch (NonUniqueResultException | NoResultException  nr) {
            return null;
        } catch (Exception e) {
            log.error("findMovimientoByContrato: ",e);
            return null;
        }
    }
    
    public void crearMovimiento(RrhhMovimientosPresupuesto movimiento, RrhhLaboral laboral) throws Exception{
        try {
            if(movimiento == null)
                throw new Exception("movimiento es nulo");
            
            if(laboral == null)
                throw new Exception("laboral es nulo");
            
            em.persist(movimiento);
            em.merge(laboral);
        } catch (Exception e) {
           log.error("crearMovimiento: ",e);
            throw new Exception(e.getMessage());
        }
    }
    
    
    public List<ResultsContrato> findAllContratosByAnio(String anio){
        try {
            TypedQuery<ResultsContrato> query = em
                    .createNamedQuery("RrhhContrato.busquedaGeneral", ResultsContrato.class);
            query.setParameter(1, anio);
            return query.getResultList();
        } catch (Exception e) {
            log.error("findAllContratosByAnio: ",e);
            return new ArrayList<ResultsContrato>();
        }
    }
    
    public ResultsContrato findEditContratoByIdContrato(BigDecimal idContrato){
        try {
            TypedQuery<ResultsContrato> query = em
                    .createNamedQuery("RrhhContrato.findContratoEdit",ResultsContrato.class);
            query.setParameter(1, idContrato);
            return query.getSingleResult();
        } catch (Exception e) {
            log.error("findEditContratoByIdContrato: ",e);
            return null;
        }
    }
    
    public List<ResultsContrato> findContratosAceptados(BigDecimal renglon, String tipoServicios ){
        try {
            TypedQuery<ResultsContrato> query = em
                    .createNamedQuery("RrhhContrato.findAceptados", ResultsContrato.class);            
            query.setParameter("renglon", renglon);
            query.setParameter("tipoServicios", tipoServicios);
            
            return query.getResultList();
        } catch (NonUniqueResultException | NoResultException  nr) {
            return null;
        } catch (Exception e) {
            log.error("findContratosAceptados: ",e);
            return null;
        }
    }
    
    public List<ResultsHistorial> findHistorialByContrato(BigDecimal contrato){
        try {
            TypedQuery<ResultsHistorial> query = em
                    .createNamedQuery("RrhhContratoEstado.findHistorialContrato",ResultsHistorial.class);
            query.setParameter(1, contrato);
            return query.getResultList();
        } catch (Exception e) {
            log.error("findHistorialByContrato: ",e);
            return new ArrayList<ResultsHistorial>();
        }
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
     
     
    
}
