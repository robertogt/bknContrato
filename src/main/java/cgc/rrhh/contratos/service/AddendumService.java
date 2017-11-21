/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.service;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhContrato;
import cgc.rrhh.contratos.pojo.ResultsActividad;
import cgc.rrhh.contratos.pojo.ResultsFuncionario;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jrroquel
 */
@Stateless
@LocalBean
public class AddendumService extends GenericAbstractService<RrhhContrato>{
    @PersistenceContext(unitName = Constants.PERSIST_RUE)
    private EntityManager em;
    
    public AddendumService(){
        super(RrhhContrato.class);
    }
    
    public ResultsFuncionario findContratoByid(BigDecimal idContrato ){
        try {
            
            TypedQuery<ResultsFuncionario> query = em
                    .createNamedQuery("RrhhContrato.findByContrato", ResultsFuncionario.class);
            query.setMaxResults(1);
            query.setParameter("idContrato", idContrato);      
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
