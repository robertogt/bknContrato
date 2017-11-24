/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.service;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhActividad;
import cgc.rrhh.contratos.model.RrhhActividadContrato;
import cgc.rrhh.contratos.model.RrhhLaboral;
import cgc.rrhh.contratos.model.RrhhPerfil;
import cgc.rrhh.contratos.pojo.ResultsActividad;
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
public class ActividadPerfilService {

    @PersistenceContext(unitName = Constants.PERSIST_RUE)
    private EntityManager em;

    private static final Logger log = Logger.getLogger(ActividadPerfilService.class);
    
     public List<RrhhPerfil> findAllByUbicacion(Integer idUbicacion){
        try {
            TypedQuery<RrhhPerfil> query = em
                    .createNamedQuery("RrhhPerfil.findByIdUbicacion", RrhhPerfil.class);
            query.setParameter("idUbicacion", idUbicacion);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return new ArrayList<RrhhPerfil>();
        }
    }
     
    public List<ResultsActividad> findAllActividadesByPerfil(BigDecimal idPerfil){
        try {
            TypedQuery<ResultsActividad> query = em
                    .createNamedQuery("RrhhActividad.NativeQueryActividad", ResultsActividad.class);
            query.setParameter(1, idPerfil);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return new ArrayList<ResultsActividad>();
        }
    }
    
    public List<ResultsActividad> findAllActividadesByContrato(BigDecimal idPerfil,
            BigDecimal idContrato){
        try {
            TypedQuery<ResultsActividad> query = em
                    .createNamedQuery("RrhhActividad.ActividadContrato", ResultsActividad.class);
            query.setParameter("perfil", idPerfil);
            query.setParameter("contrato", idContrato);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return new ArrayList<ResultsActividad>();
        }
    }
     
    public RrhhLaboral findContratoByRue(BigDecimal idRue){
        try {
            TypedQuery<RrhhLaboral> query = em
                .createNamedQuery("RrhhLaboral.findByRue",RrhhLaboral.class);
            query.setMaxResults(1);
            query.setParameter("rue", idRue);
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
    
    public BigDecimal getIdPerfilByContrato(BigDecimal idContrato){
        try {
            Query query = em.createNativeQuery("SELECT ID_PERFIL FROM RRHH_ACTIVIDAD_CONTRATO WHERE ID_CONTRATO = ? GROUP BY ID_PERFIL ", BigDecimal.class);
            query.setMaxResults(1);
            query.setParameter(1, idContrato);
            BigDecimal idPerfil = (BigDecimal)query.getSingleResult();
            System.out.println(idPerfil);
            return idPerfil;
        } catch (NoResultException | NonUniqueResultException nr){
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public RrhhActividad findActividadById(BigDecimal idActividad){
        try {
            TypedQuery<RrhhActividad> query = em
                .createNamedQuery("RrhhActividad.findByIdActividad",RrhhActividad.class);
            query.setMaxResults(1);
            query.setParameter("idActividad", idActividad);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public RrhhPerfil findPerfilById(BigDecimal idPerfil){
        try {
            TypedQuery<RrhhPerfil> query = em
                .createNamedQuery("RrhhPerfil.findByIdPerfil",RrhhPerfil.class);
            query.setMaxResults(1);
            query.setParameter("idPerfil", idPerfil);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public List<ResultsActividad> findActividadesByContrato(BigDecimal idContrato){
        try {
            TypedQuery<ResultsActividad> query = em
                .createNamedQuery("RrhhActividad.actividadByContrato",ResultsActividad.class);            
            query.setParameter(1, idContrato);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public List<RrhhActividadContrato> findActividadesByPerfilContrato(BigDecimal idPerfil, BigDecimal idContrato){
        try {
            TypedQuery<RrhhActividadContrato> query = em
                .createNamedQuery("RrhhActividadContrato.findByPerfilContrato",RrhhActividadContrato.class);            
            query.setParameter("perfil", idPerfil);
            query.setParameter("contrato", idContrato);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }
    
     public List<RrhhActividadContrato> findActividadContratoByContrato(BigDecimal idContrato){
        try {
            TypedQuery<RrhhActividadContrato> query = em
                .createNamedQuery("RrhhActividadContrato.findByContrato",RrhhActividadContrato.class);
            query.setParameter("contrato", idContrato);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }
     
     public List<RrhhActividad> findActividadesByIdContrato(BigDecimal idContrato){
         try {
             TypedQuery<RrhhActividad> query = em
                .createNamedQuery("RrhhActividadContrato.findByActividadesByContrato",RrhhActividad.class);
            query.setParameter("contrato", idContrato);
            return query.getResultList();
         } catch (Exception e) {
             log.info("findActividadesByIdContrato: ",e);
             return new ArrayList<RrhhActividad>();
         }
     }
    
    
}
