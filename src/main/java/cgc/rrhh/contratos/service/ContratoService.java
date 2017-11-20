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
import cgc.rrhh.contratos.pojo.ResultsAcademico;
import cgc.rrhh.contratos.pojo.ResultsActividad;
import cgc.rrhh.contratos.pojo.ResultsFuncionario;
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

/**
 *
 * @author ejmorales
 */
@Stateless
@LocalBean
public class ContratoService extends GenericAbstractService<RrhhContrato>{
    
    @PersistenceContext(unitName = Constants.PERSIST_RUE)
    private EntityManager em;
    
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
    
    public String findCorrelativo(String renglon, String tipoServicios, String anio){
        try {
            Query query = em.createNativeQuery("SELECT LPAD(NVL(MAX(C.CORRELATIVO_CONTRATO),0) + 1,3,'0') CORRELATIVO FROM RRHH_CONTRATO C INNER JOIN RRHH_LABORAL L ON C.ID_CONTRATO = L.ID_CONTRATO WHERE L.RENGLON = ? AND L.TIPO_SERVICIOS = ? AND C.ANIO = ?  ");
            query.setParameter(1, renglon);
            query.setParameter(2, tipoServicios);
            query.setParameter(3, anio);
            
            String value = (String)query.getSingleResult();
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
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new Exception(e.getMessage());
            
        }
        return eval;
    }
    
    public RrhhLaboral findLaboralByContrato(BigDecimal idContrato){
        try {
            TypedQuery<RrhhLaboral> query = em
                    .createNamedQuery("RrhhLaboral.findByContrato",RrhhLaboral.class);
            query.setParameter("contrato", idContrato);            
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
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
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
     
     
    
}
