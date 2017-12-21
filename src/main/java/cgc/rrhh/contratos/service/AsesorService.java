/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.service;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhCatalogoEstado;
import cgc.rrhh.contratos.model.RrhhCatalogoMotivoRechazo;
import cgc.rrhh.contratos.model.RrhhContratoEstado;
import cgc.rrhh.contratos.model.RrhhMotivoRechazo;
import cgc.rrhh.contratos.pojo.ResultsContrato;
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
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;

/**
 *
 * @author ejmorales
 */
@Stateless
@LocalBean
public class AsesorService {
    
    @PersistenceContext(unitName = Constants.PERSIST_RUE)
    private EntityManager em;
    
    private static final Logger log = Logger.getLogger(AsesorService.class);
    
    public List<RrhhCatalogoMotivoRechazo> listAllCatalogosActive(){
        try {
            TypedQuery<RrhhCatalogoMotivoRechazo> query = em
                    .createNamedQuery("RrhhCatalogoMotivoRechazo.findByEstado",RrhhCatalogoMotivoRechazo.class);
            query.setParameter("estado", Constants.ACTIVO);
            return query.getResultList();
        } catch (Exception e) {
            log.error("listAllCatalogosActive: ",e);
            return new ArrayList<RrhhCatalogoMotivoRechazo>();
        }
    }
    
    public RrhhCatalogoMotivoRechazo findMotivoById(BigDecimal idCatalogoMotivoRechazo){
        try {
            TypedQuery<RrhhCatalogoMotivoRechazo> query = em
                    .createNamedQuery("RrhhCatalogoMotivoRechazo.findByIdCatalogoMotivoRechazo",RrhhCatalogoMotivoRechazo.class);
            query.setParameter("idCatalogoMotivoRechazo", idCatalogoMotivoRechazo);
            return query.getSingleResult();
        } catch (Exception e) {
            log.error("findMotivoById: ",e);
            return null;
        }
    }
    
    public List<ResultsContrato> listAllByAsesor(){
        try {
            TypedQuery<ResultsContrato> query = em
                    .createNamedQuery("RrhhContrato.listAllContrato",ResultsContrato.class);
            query.setParameter(1, 2);
            query.setParameter(2, 'I');
            return query.getResultList();
        } catch (Exception e) {
           log.error("listAllByAsesor: ",e);
            return new ArrayList<ResultsContrato>();
        }
    }
    
    public List<ResultsHistorial> findHistorialByContrato(BigDecimal idContrato){
        try {
            TypedQuery<ResultsHistorial> query = em
                    .createNamedQuery("RrhhContratoEstado.findByContrato",ResultsHistorial.class);
            query.setParameter(1, idContrato);
            return query.getResultList();
        } catch (Exception e) {
            log.error("findHistorialByContrato: ",e);
            return new ArrayList<ResultsHistorial>();
        }
    }
    
    
    public RrhhContratoEstado findEstadoByContrato(BigDecimal idContrato,
            BigDecimal idCatalogoEstado) throws Exception{
        try {
            TypedQuery<RrhhContratoEstado> query = em
                    .createNamedQuery("RrhhContratoEstado.findByIdContrato",RrhhContratoEstado.class);
            query.setParameter("idContrato", idContrato);
            query.setParameter("catalogo", idCatalogoEstado);
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException nr) {
            return null;
        } catch (Exception e) {
            log.error("findEstadoByContrato: ",e);
            throw new Exception(e.getMessage());
        }
    }
    
    public void aprobarRegistro(RrhhContratoEstado update, RrhhContratoEstado create)
        throws Exception{
        try {
            
            if(update == null)
                throw new Exception("update es nulo");
            
            if(create == null)
                throw new Exception("create es nulo");
            
            em.persist(create);
            
            if(create.getIdContratoEstado() == null)
                throw new Exception("No se pudo guardar ContratoEstado");
            
            em.merge(update);
        } catch (Exception e) {
            log.error("aprobarRegistro: ",e);
            throw new Exception(e.getMessage());
        }
    }
    
    public void rechazarRegistro(RrhhContratoEstado update, RrhhContratoEstado create,
            List<RrhhMotivoRechazo> motivos)
        throws Exception{
        try {
            
            if(update == null)
                throw new Exception("update es nulo");
            
            if(create == null)
                throw new Exception("create es nulo");
            
            if(motivos == null)
                throw new Exception("motivos es nulo");
            
            em.persist(create);
            
            if(create.getIdContratoEstado() == null)
                throw new Exception("No se pudo guardar ContratoEstado");
            
            em.merge(update);
            
            
            for(RrhhMotivoRechazo motivo:motivos){
                motivo.setContratoEstado(create);
                em.persist(motivo);
            }
        } catch (Exception e) {
            log.error("rechazarRegistro: ",e);
            throw new Exception(e.getMessage());
        }
    }
    
    public List<RrhhCatalogoMotivoRechazo> findAllMotivosById(BigDecimal idContratoEstado){
        try {
            TypedQuery<RrhhCatalogoMotivoRechazo> query = em
                    .createNamedQuery("RrhhMotivoRechazo.findByIdContratoEstado",RrhhCatalogoMotivoRechazo.class);
            query.setParameter("contratoEstado", idContratoEstado);
           return query.getResultList();
        } catch (Exception e) {
            log.error("findAllMotivosById: ",e);
            return new ArrayList<RrhhCatalogoMotivoRechazo>();
        }
    }
    
}
