/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.service;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.TcFuncionarios;
import cgc.rrhh.contratos.pojo.ResultsFuncionarios;
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
public  class FuncionariosService {

    @PersistenceContext(unitName = Constants.PERSIST_RUE)
    private EntityManager em;
    
    public FuncionariosService() {
    }
    
    public List<TcFuncionarios> finAllFuncionarios(){
        try {
            TypedQuery<TcFuncionarios> query = em
                    .createNamedQuery("TcFuncionarios.findAll", TcFuncionarios.class);
            query.setMaxResults(5000);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<TcFuncionarios>();
        }
    }
    
     public TcFuncionarios findFuncionarioByDPI(String dpi){
        try {
            TypedQuery<TcFuncionarios> query = em
                    .createNamedQuery("TcFuncionarios.findByDPI", TcFuncionarios.class);
            query.setParameter("dpi",dpi);
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public List<TcFuncionarios> findAllFuncionariosByDPI(String dpi){
        try {
            TypedQuery<TcFuncionarios> query = em
                    .createNamedQuery("TcFuncionarios.NativeQueryDPIAllTable", TcFuncionarios.class);
            query.setParameter(1,dpi);
            query.setMaxResults(5000);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<TcFuncionarios>();
        }
    }
    
    public List<ResultsFuncionarios> findFuncionariosByDPI(String dpi){
        try {
            TypedQuery<ResultsFuncionarios> query = em
                    .createNamedQuery("TcFuncionarios.NativeQueryDPI", ResultsFuncionarios.class);
            query.setParameter("dpi",dpi);
            query.setMaxResults(5000);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<ResultsFuncionarios>();
        }
    }
    
    public List<TcFuncionarios> findFuncionarioByDpi(String dpi){       
        try {
            TypedQuery<TcFuncionarios> query = em
                    .createNamedQuery(" TcFuncionarios.findFuncionarioByDpi", TcFuncionarios.class);
            query.setParameter(1,dpi);
            query.setMaxResults(5000);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
