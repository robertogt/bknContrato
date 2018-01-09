/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.service;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhDepartamento;
import cgc.rrhh.contratos.model.RrhhMunicipio;
import cgc.rrhh.contratos.model.RrhhRue;
import cgc.rrhh.contratos.pojo.ResultsMunicipio;
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
public class RueService {
    
    @PersistenceContext(unitName = Constants.PERSIST_RUE)
    private EntityManager em;
    
    private static final Logger log = Logger.getLogger(RueService.class);
    
    public RrhhRue findRueById(Long idRue) throws Exception{
        try {
            TypedQuery<RrhhRue> query = em
                    .createNamedQuery("RrhhRue.findByIdRue",RrhhRue.class);
            query.setParameter("idRue", idRue);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public void modificarRue(RrhhRue rue) throws Exception{
        try {
            em.merge(rue);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public List<ResultsMunicipio> deptoMunicipio() {
        try {
            TypedQuery<ResultsMunicipio> query = em
                    .createNamedQuery("RrhhMunicipio.NativeMunicipiosDepto", ResultsMunicipio.class);
            return query.getResultList();
        } catch (Exception e) {
            log.error("deptoMunicipio",e);
            return new ArrayList<ResultsMunicipio>();            
        }
    }
    
    public List<RrhhDepartamento> getDepartamentos(){
        try {
            TypedQuery<RrhhDepartamento> query = em
                    .createNamedQuery("RrhhDepartamento.findAll", RrhhDepartamento.class);
            return query.getResultList();
        } catch (Exception e) {
            log.error("getDepartamentos: ",e);
            return new ArrayList<RrhhDepartamento>();            
        }
    }
    
     public List<RrhhMunicipio> getMunicipiosByDepartamento(String departamento){
        try {
            TypedQuery<RrhhMunicipio> query = em
                    .createNamedQuery("RrhhMunicipio.findByDepartamento", RrhhMunicipio.class);
            query.setParameter("departamento", departamento);
            return query.getResultList();
        } catch (Exception e) {
            log.error("getMunicipiosByDepartamento: ",e);
            return new ArrayList<RrhhMunicipio>();            
        }
    }
    
    
}
