/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.service;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhActividadContrato;
import cgc.rrhh.contratos.model.RrhhContrato;
import cgc.rrhh.contratos.model.RrhhHistoricoLaboral;
import cgc.rrhh.contratos.model.RrhhLaboral;
import cgc.rrhh.contratos.model.RrhhMovimientosPresupuesto;
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
    
    public void crearAddendum(RrhhLaboral laboral,RrhhContrato contrato,
            RrhhMovimientosPresupuesto diff, RrhhMovimientosPresupuesto nuevo,
            List<RrhhActividadContrato> actividades, RrhhHistoricoLaboral historico) throws Exception{
        try {
            if(laboral == null)
                throw new Exception("Laboral es nulo");
            
            if(contrato == null)
                throw new Exception("contrato es nulo");
            
            if(diff == null)
                throw new Exception("diff es nulo");
            
            if(nuevo == null)
                throw new Exception("nuevo es nulo");
            
            if(actividades == null)
                throw new Exception("actividades es nulo");
            
            if(historico == null)
                throw new Exception("historico es nulo");
            em.persist(contrato);
            
            if(contrato.getIdContrato() == null)
                throw new Exception("id Contrato es nulo");
            
            em.persist(historico);
            
            laboral.setIdContrato(contrato);
            em.merge(laboral);
            
            for(RrhhActividadContrato actContrato: actividades){
                actContrato.setIdContrato(contrato);
                em.persist(actContrato);
            }
            
            diff.setIdContrato(contrato);
            em.persist(diff);
            
            nuevo.setIdContrato(contrato);
            em.persist(nuevo);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
