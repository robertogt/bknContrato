/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.service;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhColegioProfesional;
import cgc.rrhh.contratos.model.RrhhTitulo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;

/**
 *
 * @author ejmorales
 */
@Stateless
@LocalBean
public class ColegioService {
    
    @PersistenceContext(unitName = Constants.PERSIST_RUE)
    private EntityManager em;
    
    private static final Logger log = Logger.getLogger(ColegioService.class);

    public ColegioService() {
    }
    
    public List<RrhhColegioProfesional> findAllColegios(){
        try {
            TypedQuery<RrhhColegioProfesional> query = em
                    .createNamedQuery("RrhhColegioProfesional.findAll", RrhhColegioProfesional.class);
            return query.getResultList();
        } catch (Exception e) {
            log.error("findAllColegios: ",e);
            return new ArrayList<RrhhColegioProfesional>();
        }
    }
    
    
     public RrhhColegioProfesional getTituloById(BigDecimal colegioProfesional){
        try {
            TypedQuery<RrhhColegioProfesional> query = em
                    .createNamedQuery("RrhhColegioProfesional.findByColegioProfesional",RrhhColegioProfesional.class);
            query.setParameter("colegioProfesional", colegioProfesional);
            return query.getSingleResult();
        } catch (Exception e) {
            log.error("getTituloById: ",e);
            return null;
        }
    }
    
}
