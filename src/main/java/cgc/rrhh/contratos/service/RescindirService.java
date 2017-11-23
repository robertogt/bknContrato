/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.service;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhHistoricoLaboral;
import cgc.rrhh.contratos.model.RrhhLaboral;
import cgc.rrhh.contratos.model.RrhhMovimientosPresupuesto;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ejmorales
 */
@Stateless
@LocalBean
public class RescindirService {
    @PersistenceContext(unitName = Constants.PERSIST_RUE)
    private EntityManager em;
    
    public void rescindir(RrhhLaboral laboral, RrhhHistoricoLaboral historico,
            RrhhMovimientosPresupuesto movimiento) throws Exception{
        try {
            if(laboral == null)
                throw new Exception("Laboral es nulo");
            
            if(historico == null)
                throw new Exception("historico es nulo");
            
            if(movimiento == null)
                throw new Exception("movimiento es nulo");
            
            em.persist(historico);
            
            if(historico.getHistoricoLaboral() == null)
                throw new Exception("error al crear historico");
            
            em.merge(laboral);
            
            em.persist(movimiento);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
