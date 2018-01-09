/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.service;

import java.util.List;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;

/**
 *
 * @author ejmorales
 */
public abstract class GenericAbstractService<T> {
    
    private Class<T> entityClass;
    
    private static final Logger log = Logger.getLogger(GenericAbstractService.class);

    public GenericAbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    protected abstract EntityManager getEntityManager();
    
    public List<T> findAll() throws Exception{
        try {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            return getEntityManager().createQuery(cq).getResultList();
        } catch (Exception e) {
            log.error("getEntityManager: ",e);
            throw new Exception(e.getMessage());
        }
    }
    
    public void create(T entity) throws Exception{
        getEntityManager().persist(entity);
    }
    
     public void edit(T entity) throws Exception{
        getEntityManager().merge(entity);
    }
     
     public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
}
