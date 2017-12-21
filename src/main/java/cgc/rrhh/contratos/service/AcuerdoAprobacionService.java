/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.service;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhAcuerdoAprobacion;
import cgc.rrhh.contratos.model.RrhhAcuerdoContrato;
import cgc.rrhh.contratos.model.RrhhContrato;
import cgc.rrhh.contratos.model.RrhhContratoEstado;
import cgc.rrhh.contratos.model.RrhhLaboral;
import cgc.rrhh.contratos.pojo.PersistAcuerdoAprobacion;
import cgc.rrhh.contratos.pojo.PersistAcuerdoContrato;
import cgc.rrhh.contratos.pojo.ResultsContrato;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;

/**
 *
 * @author jrroquel
 */
@Stateless
@LocalBean
public class AcuerdoAprobacionService extends GenericAbstractService<RrhhAcuerdoAprobacion>{
     @PersistenceContext(unitName = Constants.PERSIST_RUE)
    private EntityManager em;
    
    private static final Logger log = Logger.getLogger(AcuerdoAprobacionService.class);
    
    public AcuerdoAprobacionService(){
        super(RrhhAcuerdoAprobacion.class);
    }
    
    public RrhhAcuerdoAprobacion crearAcuerdoAprobacion(RrhhAcuerdoAprobacion acuerdoAprobacion, PersistAcuerdoAprobacion persist) throws Exception{
        
        try{
            
            validaObjetos(acuerdoAprobacion,persist);            
            em.persist(acuerdoAprobacion);
            
            if(acuerdoAprobacion.getIdAcuerdoAprobacion() == null)
                throw new Exception("Error al crear el acuerdo");
            
            for(RrhhAcuerdoContrato acuerdoContrato: persist.getAcuerdo()){
                acuerdoContrato.setIdAcuerdoAprobacion(acuerdoAprobacion);
                em.persist(acuerdoContrato);      
            }  
            
            for(RrhhLaboral laboral: persist.getLaboral()){
                em.merge(laboral);
            }
            
            for(RrhhContratoEstado modificar: persist.getUpdate()){
                em.merge(modificar);
            }
            
            for(RrhhContratoEstado crear: persist.getCreate()){
                em.persist(crear);
            }
            
        }catch(Exception ex){
            log.error("crearAcuerdoAprobacion",ex);
            throw new Exception(ex.getMessage());
        }
        
        return acuerdoAprobacion;
    }
    
    private void validaObjetos(RrhhAcuerdoAprobacion acuerdoAprobacion, PersistAcuerdoAprobacion persist) throws Exception{
        if(acuerdoAprobacion == null)
            throw new Exception("AcuerdoAprobacion es nulo");
            
        if(persist == null)
            throw new Exception("acuerdosContrato es nulo");
    }
        
    public BigDecimal findCorrelativo(String renglon, String tipoServicios, String anio){
        try {
            Query query = em.createNativeQuery("SELECT NVL(MAX(A.NUMERO_ACUERDO),0) + 1 CORRELATIVO FROM RRHH_ACUERDO_APROBACION A WHERE A.RENGLON = ? AND A.TIPO_SERVICIOS = ? AND A.ANIO = ?  ");
            query.setParameter(1, renglon);
            query.setParameter(2, tipoServicios);
            query.setParameter(3, anio);
            
            BigDecimal value = (BigDecimal)query.getSingleResult();
            return value;
        } catch (Exception e) {
            log.error("findCorrelativo: ",e);
            return null;
        }
    }
    
    public List<RrhhAcuerdoAprobacion> listAllAcuerdos(BigInteger anio){
        try {
            TypedQuery<RrhhAcuerdoAprobacion> query = em
                    .createNamedQuery("RrhhAcuerdoAprobacion.findByAnio",RrhhAcuerdoAprobacion.class);
            query.setParameter("anio", anio);
            return query.getResultList();
        } catch (Exception e) {
            log.error("listAllAcuerdos: ",e);
            return new ArrayList<RrhhAcuerdoAprobacion>();
        }
    }
    
     public List<RrhhAcuerdoContrato> listAllContratosByAcuerdo(BigDecimal idAcuerdo){
        try {
            TypedQuery<RrhhAcuerdoContrato> query = em
                    .createNamedQuery("RrhhAcuerdoContrato.findByAcuerdoAprobacion",RrhhAcuerdoContrato.class);
            query.setParameter("acuerdoAprobacion", idAcuerdo);
            return query.getResultList();
        } catch (Exception e) {
            log.error("listAllContratosByAcuerdo: ",e);
            return new ArrayList<RrhhAcuerdoContrato>();
        }
    }
     
     public RrhhAcuerdoContrato findAcuerdoByContrato(BigDecimal idAcuerdo, BigDecimal idContrato){
         try {
             TypedQuery<RrhhAcuerdoContrato> query = em
                     .createNamedQuery("RrhhAcuerdoContrato.findByAcuerdoByContrato",RrhhAcuerdoContrato.class);
             query.setParameter("acuerdoAprobacion", idAcuerdo);
             query.setParameter("contrato", idContrato);
             
             return query.getSingleResult();
         } catch (Exception e) {
            log.error("findAcuerdoByContrato: ",e);
            return null;
         }
     }

     public void updateAcuerdo(List<RrhhAcuerdoContrato> contratos) throws Exception{
         try {
             
             if(contratos == null)
                 throw new Exception("acuerdocontratos es nulo");
             
             
             for(RrhhAcuerdoContrato contrato: contratos){
                 em.merge(contrato);
             }
         } catch (Exception e) {
             log.error("updateAcuerdo",e);
             throw new Exception(e.getMessage());
         }
     }
     
     
     public RrhhAcuerdoAprobacion findAcuerdoById(BigDecimal acuerdo) throws Exception{
         try {
             TypedQuery<RrhhAcuerdoAprobacion> query = em
                     .createNamedQuery("RrhhAcuerdoAprobacion.findByIdAcuerdoAprobacion",RrhhAcuerdoAprobacion.class);
             query.setParameter("idAcuerdoAprobacion", acuerdo);
             return query.getSingleResult();
         } catch (Exception e) {
             log.error("findAcuerdoById: ",e);
             throw new Exception(e.getMessage());
         }
     }
     
     public List<ResultsContrato> findAcuerdosByRenglonTipoAnio(String renglon,
             String tipo, String anio, BigDecimal acuerdo){
         try {
              TypedQuery<ResultsContrato> query = em
                     .createNamedQuery("RrhhAcuerdoContrato.NativeQueryAcuerdoContratos",ResultsContrato.class);
             query.setParameter("acuerdo", acuerdo);
             query.setParameter("renglon", renglon);
             query.setParameter("tipo", tipo);
             query.setParameter("anio", anio);             
             return query.getResultList();
         } catch (Exception e) {
             log.error("findAcuerdosByRenglonTipoAnio: ",e);
             return new ArrayList<ResultsContrato>();
         }
     }
     
     public List<ResultsContrato> listAllContratosAcuerdo(BigDecimal idAcuerdo){
        try {
            TypedQuery<ResultsContrato> query = em
                    .createNamedQuery("RrhhAcuerdoContrato.QueryContrato",ResultsContrato.class);
            query.setParameter("acuerdo", idAcuerdo);
            return query.getResultList();
        } catch (Exception e) {
            log.error("listAllContratosAcuerdo: ",e);
            return new ArrayList<ResultsContrato>();
        }
    }
     
     public void updateAcuerdo(PersistAcuerdoContrato persist) throws Exception{
         try {
             if(persist == null)
                 throw new Exception("persist es nulo");
             
             
             for(RrhhAcuerdoContrato modif:persist.getUpdate()){
                 em.merge(modif);
             }
             
             for(RrhhAcuerdoContrato crear:persist.getCreate()){
                 em.persist(crear);
             }
             
             
             for(RrhhLaboral laboral:persist.getLaboral()){
                 em.merge(laboral);
             }
             
             for(RrhhContratoEstado modificar:persist.getModificarEstado()){
                 em.merge(modificar);
             }
             
             for(RrhhContratoEstado crear: persist.getCrearEstado()){
                 em.persist(crear);
             }
             
             
         } catch (Exception e) {
             log.error("updateAcuerdo",e);
             throw new Exception(e.getMessage());
         }
     }

     
     public void anularAcuerdo(PersistAcuerdoAprobacion persist) throws Exception{
         try {
             if(persist == null)
                 throw new Exception("persist es nulo");
             
             
             em.merge(persist.getAprobacion());
             
             for(RrhhAcuerdoContrato modif:persist.getAcuerdo()){
                 em.merge(modif);
             }
                          
             for(RrhhLaboral laboral:persist.getLaboral()){
                 em.merge(laboral);
             }
             
             for(RrhhContratoEstado modificar:persist.getUpdate()){
                 em.merge(modificar);
             }
             
             for(RrhhContratoEstado crear: persist.getCreate()){
                 em.persist(crear);
             }
             
             
         } catch (Exception e) {
             log.error("anularAcuerdo",e);
             throw new Exception(e.getMessage());
         }
     }
     
     
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
     
}
