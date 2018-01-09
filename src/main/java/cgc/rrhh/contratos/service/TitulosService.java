/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.service;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhGradoAcademico;
import cgc.rrhh.contratos.model.RrhhTitulo;
import cgc.rrhh.contratos.pojo.ResultsTexto;
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
public class TitulosService {
    
    
    @PersistenceContext(unitName = Constants.PERSIST_RUE)
    private EntityManager em;
    
    private static final Logger log = Logger.getLogger(TitulosService.class);
    
    public TitulosService() {
    }
    
    public List<ResultsTexto> listTitulosByTexto(String texto){
        try {
            TypedQuery<ResultsTexto> query = em
                    .createNamedQuery("RrhhTitulo.NativeQueryTitulos",ResultsTexto.class);
            query.setParameter(1, texto);
            return query.getResultList();
        } catch (Exception e) {
            log.error("listTitulosByTexto: ",e);
            return new ArrayList<ResultsTexto>();
        }
    }
    
    public RrhhTitulo getTituloById(BigDecimal titulo){
        try {
            TypedQuery<RrhhTitulo> query = em
                    .createNamedQuery("RrhhTitulo.findByTitulo",RrhhTitulo.class);
            query.setParameter("titulo", titulo);
            return query.getSingleResult();
        } catch (Exception e) {
            log.error("getTituloById: ",e);
            return null;
        }
    }
    
    public RrhhGradoAcademico getGradoAcademicoById(String gradoAcademico){
        try {
            TypedQuery<RrhhGradoAcademico> query = em
                    .createNamedQuery("RrhhGradoAcademico.findByGradoAcademico",RrhhGradoAcademico.class);
            query.setParameter("gradoAcademico", gradoAcademico);
            return query.getSingleResult();
        } catch (Exception e) {
            log.error("getGradoAcademicoById: ",e);
            return null;
        }
    }
    
}
