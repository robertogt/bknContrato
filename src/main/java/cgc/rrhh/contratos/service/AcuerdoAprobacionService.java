/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.service;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhAcuerdoAprobacion;
import cgc.rrhh.contratos.model.RrhhAcuerdoContrato;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    
    private static final Logger log = Logger.getLogger(ContratoService.class);
    
    public AcuerdoAprobacionService(){
        super(RrhhAcuerdoAprobacion.class);
    }
    
    public boolean crearAcuerdoAprobacion(RrhhAcuerdoAprobacion acuerdoAprobacion, List<RrhhAcuerdoContrato> acuerdosContrato) throws Exception{
        boolean resultado = false;
        try{
            
            validaObjetos(acuerdoAprobacion,acuerdosContrato);            
            em.persist(acuerdoAprobacion);
            
            if(acuerdoAprobacion.getIdAcuerdoAprobacion() == null)
                throw new Exception("Error al crear el acuerdo");
            
            for(RrhhAcuerdoContrato acuerdoContrato: acuerdosContrato){
                acuerdoContrato.setIdAcuerdoAprobacion(acuerdoAprobacion);
                em.persist(acuerdoContrato);      
            }  
            
        }catch(Exception ex){
            log.error("crearAcuerdoAprobacion",ex);
            throw new Exception(ex.getMessage());
        }
        
        return resultado;
    }
    
    private void validaObjetos(RrhhAcuerdoAprobacion acuerdoAprobacion, List<RrhhAcuerdoContrato> acuerdosContrato) throws Exception{
        if(acuerdoAprobacion == null)
            throw new Exception("AcuerdoAprobacion es nulo");
            
        if(acuerdosContrato == null)
            throw new Exception("acuerdosContrato es nulo");
    }
        
    public BigDecimal findCorrelativo(String renglon, String tipoServicios, String anio){
        try {
            Query query = em.createNativeQuery("SELECT NVL(MAX(C.NUMERO_ACUERDO),0) + 1 CORRELATIVO FROM RRHH_ACUERDO_APROBACION A WHERE A.RENGLON = ? AND A.TIPO_SERVICIOS = ? AND A.ANIO = ?  ");
            query.setParameter(1, renglon);
            query.setParameter(2, tipoServicios);
            query.setParameter(3, anio);
            
            BigDecimal value = (BigDecimal)query.getSingleResult();
            return value;
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
