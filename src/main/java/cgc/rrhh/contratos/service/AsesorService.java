/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.service;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhCatalogoEstado;
import cgc.rrhh.contratos.model.RrhhContratoEstado;
import cgc.rrhh.contratos.pojo.ResultsContrato;
import cgc.rrhh.contratos.pojo.ResultsHistorial;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ejmorales
 */
@Stateless
@LocalBean
public class AsesorService {
    
    @PersistenceContext(unitName = Constants.PERSIST_RUE)
    private EntityManager em;
    
    public List<ResultsContrato> listAllByAsesor(){
        try {
            TypedQuery<ResultsContrato> query = em
                    .createNamedQuery("RrhhContrato.listAllContrato",ResultsContrato.class);
            query.setParameter(1, 1);
            query.setParameter(2, 'I');
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
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
            e.printStackTrace();
            System.out.println(e.getMessage());
            return new ArrayList<ResultsHistorial>();
        }
    }
    
    
    public RrhhContratoEstado findEstadoByContrato(BigDecimal idContrato) throws Exception{
        try {
            TypedQuery<RrhhContratoEstado> query = em
                    .createNamedQuery("RrhhContratoEstado.findByIdContrato",RrhhContratoEstado.class);
            query.setParameter("idContrato", idContrato);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
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
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    
}
