/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.service;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhAcademico;
import cgc.rrhh.contratos.model.RrhhCatalogoEstado;
import cgc.rrhh.contratos.model.RrhhControlPresupuesto;
import cgc.rrhh.contratos.model.RrhhDepartamento;
import cgc.rrhh.contratos.model.RrhhMunicipio;
import cgc.rrhh.contratos.model.RrhhPlantilla;
import cgc.rrhh.contratos.model.RrhhPuestoFuncional;
import cgc.rrhh.contratos.model.RrhhPuestoNominal;
import cgc.rrhh.contratos.model.RrhhPuestoNominalClass;
import cgc.rrhh.contratos.model.RrhhRenglon;
import cgc.rrhh.contratos.model.RrhhRue;
import cgc.rrhh.contratos.model.RrhhTipoMovimiento;
import cgc.rrhh.contratos.model.RrhhUbicacionFuncional;
import cgc.rrhh.contratos.model.RrhhUbicacionNominal;
import java.math.BigDecimal;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ejmorales
 */
@Stateless
@LocalBean
public class GeneralService {
    
    @PersistenceContext(unitName = Constants.PERSIST_RUE)
    private EntityManager em;

    public GeneralService() {
    }
    
    public RrhhRenglon getRenglonById(String renglon){
        try {
            TypedQuery<RrhhRenglon> query = em
                    .createNamedQuery("RrhhRenglon.findByRenglon",RrhhRenglon.class);
            query.setParameter("renglon", renglon);            
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }        
    }
    
    public RrhhTipoMovimiento getTipoMovimientoById(BigDecimal idTipoMovimiento){
       try {
            TypedQuery<RrhhTipoMovimiento> query = em
                    .createNamedQuery("RrhhTipoMovimiento.findByTipoMovimiento",RrhhTipoMovimiento.class);
            query.setParameter("tipoMovimiento", idTipoMovimiento);            
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }     
    }
    
    public RrhhPuestoNominalClass getPuestoNominal(BigDecimal idPuestoNominal){
        try {
            TypedQuery<RrhhPuestoNominalClass> query = em
                    .createNamedQuery("RrhhPuestoNominalClass.findByPuestoNominal",RrhhPuestoNominalClass.class);
            query.setParameter("puestoNominal", idPuestoNominal);            
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }     
    }
    
    public RrhhUbicacionNominal getUbicacionNominal(BigDecimal ubicacionNominal){
       try {
            TypedQuery<RrhhUbicacionNominal> query = em
                    .createNamedQuery("RrhhUbicacionNominal.findByUbicacionNominal",RrhhUbicacionNominal.class);
            query.setParameter("ubicacionNominal", ubicacionNominal);            
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }     
    }
    
    public RrhhPuestoFuncional getPuestoFuncional(BigDecimal puestoFuncional){
       try {
            TypedQuery<RrhhPuestoFuncional> query = em
                    .createNamedQuery("RrhhPuestoFuncional.findByPuestoFuncional",RrhhPuestoFuncional.class);
            query.setParameter("puestoFuncional", puestoFuncional);            
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }     
    }
    
    
    public RrhhUbicacionFuncional getUbicacionFuncional(BigDecimal ubicacionFuncional){
        try {
            TypedQuery<RrhhUbicacionFuncional> query = em
                    .createNamedQuery("RrhhUbicacionFuncional.findByUbicacionFuncional",RrhhUbicacionFuncional.class);
            query.setParameter("ubicacionFuncional", ubicacionFuncional);            
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }     
    }
    
    public RrhhPlantilla getPlantillaByRenglonAnio(String anio, String renglon, String tipoServicio, BigDecimal tipoDocumento){
        try {
            TypedQuery<RrhhPlantilla> query = em
                    .createNamedQuery("RrhhPlantilla.findByRenglonAnioTipo",RrhhPlantilla.class);
            query.setParameter("anio", anio);            
            query.setParameter("renglon", renglon);            
            query.setParameter("tipoServicio", tipoServicio); 
            query.setParameter("tipoDocumento", tipoDocumento);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }     
    }
    
    public RrhhRue getRueById(BigDecimal idRue){
        try {
            TypedQuery<RrhhRue> query = em
                    .createNamedQuery("RrhhRue.findByIdRue",RrhhRue.class);
            query.setParameter("idRue", idRue);            
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }     
    }
    
    public RrhhAcademico findAcademicoByTituloRueColegio(BigDecimal titulo, BigDecimal rue,
            BigDecimal colegio, String tipoServicios){
        try {
            String namedQuery = "";
            if(tipoServicios.equalsIgnoreCase("P")){
                namedQuery = "RrhhAcademico.findByTituloRueColegio";
            }else{
                namedQuery = "RrhhAcademico.findByTituloRue";
            }
            
            TypedQuery<RrhhAcademico> query = em
                    .createNamedQuery(namedQuery,RrhhAcademico.class);
            query.setParameter("titulo", titulo);  
            query.setParameter("rue", rue);     
            if(tipoServicios.equalsIgnoreCase("P")){
                query.setParameter("colegioProfesional", colegio);  
            }
            
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException nr){
            return null;        
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }     
    }
    
    
    public String findCorrelativoRue(String cadena) throws Exception{
        try {
            Query query = em.createNativeQuery("SELECT LPAD(COUNT(*) + 1,2,'0') CORRELATIVO FROM RRHH_RUE WHERE UPPER(RUE) LIKE ('%'|| UPPER(?)||'%') ");
            query.setParameter(1, cadena);
            String value = (String)query.getSingleResult();
            return value;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("error al crerar rue");
        }
    }
    
    public RrhhCatalogoEstado findEstadoById(BigDecimal idEstado){
       try {
            TypedQuery<RrhhCatalogoEstado> query = em
                    .createNamedQuery("RrhhCatalogoEstado.findByIdCatalogoEstado",RrhhCatalogoEstado.class);
            query.setParameter("idCatalogoEstado", idEstado);            
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }     
    }
    
    public RrhhMunicipio getMunicipioDepto(String idMunicipio,String idDepartamento){
        try {
            TypedQuery<RrhhMunicipio> query = em
                    .createNamedQuery("RrhhMunicipio.findByMunicipioDepto",RrhhMunicipio.class);
            query.setParameter("municipio", idMunicipio);    
            query.setParameter("departamento", idDepartamento);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }     
    }
}
