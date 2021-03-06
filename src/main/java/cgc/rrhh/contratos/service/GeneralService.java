/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.service;

import cgc.rhh.contratos.util.Constants;
import cgc.rrhh.contratos.model.RrhhAcademico;
import cgc.rrhh.contratos.model.RrhhCatalogoEstado;
import cgc.rrhh.contratos.model.RrhhCatalogoMotivoRechazo;
import cgc.rrhh.contratos.model.RrhhContrato;
import cgc.rrhh.contratos.model.RrhhContratoEstado;
import cgc.rrhh.contratos.model.RrhhControlPresupuesto;
import cgc.rrhh.contratos.model.RrhhDepartamento;
import cgc.rrhh.contratos.model.RrhhMunicipio;
import cgc.rrhh.contratos.model.RrhhPais;
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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;

/**
 *
 * @author ejmorales
 */
@Stateless
@LocalBean
public class GeneralService {
    
    @PersistenceContext(unitName = Constants.PERSIST_RUE)
    private EntityManager em;
    
    private static final Logger log = Logger.getLogger(GeneralService.class);

    public GeneralService() {
    }
    
    public RrhhCatalogoMotivoRechazo findCatalovoMotivoById(BigDecimal idMotivo) throws Exception{
        try {
            TypedQuery<RrhhCatalogoMotivoRechazo> query = em
                    .createNamedQuery("RrhhCatalogoMotivoRechazo.findByIdCatalogoMotivoRechazo",RrhhCatalogoMotivoRechazo.class);
            query.setParameter("idCatalogoMotivoRechazo", idMotivo);
            return query.getSingleResult();
        } catch (NoResultException nr) {
            return null;
        } catch (Exception e) {
            log.error("findCatalovoMotivoById: ",e);
            throw new Exception(e.getMessage());
        }
    }
    
    public RrhhMunicipio findMunicipio(String departamento,String municipio) throws Exception{
       try {
            TypedQuery<RrhhMunicipio> query = em
                    .createNamedQuery("RrhhMunicipio.findByMunicipioDepto",RrhhMunicipio.class);
            query.setParameter("municipio", municipio);
            query.setParameter("departamento", departamento);
            return query.getSingleResult();
        } catch (NoResultException nr) {
            return null;
        } catch (Exception e) {
            log.error("findMunicipio: ",e);
            throw new Exception(e.getMessage());
        }
    }
    
    public RrhhPais finPaisById(String pais) throws Exception{
        try {
            TypedQuery<RrhhPais> query = em
                    .createNamedQuery("RrhhPais.findByPais",RrhhPais.class);
            query.setParameter("pais", pais);
            return query.getSingleResult();
        } catch (NoResultException nr) {
            return null;
        } catch (Exception e) {
            log.error("finPaisById: ",e);
            throw new Exception(e.getMessage());
        }
    }
    
    public RrhhContratoEstado findActiveByContrato(BigDecimal idContrato) throws Exception{
        try {
            TypedQuery<RrhhContratoEstado> query = em
                    .createNamedQuery("RrhhContratoEstado.findActiveByContrato",RrhhContratoEstado.class);
            query.setParameter("idContrato", idContrato);
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException nr) {
            return null;
        } catch (Exception e) {
            log.error("findActiveByContrato: ",e);
            throw new Exception(e.getMessage());
        }
    }
    
    public RrhhRenglon getRenglonById(String renglon){
        try {
            TypedQuery<RrhhRenglon> query = em
                    .createNamedQuery("RrhhRenglon.findByRenglon",RrhhRenglon.class);
            query.setParameter("renglon", renglon);            
            return query.getSingleResult();
        } catch (Exception e) {
            log.error("getRenglonById: ",e);
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
            log.error("getTipoMovimientoById: ",e);
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
            log.error("getPuestoNominal: ",e);
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
            log.error("getUbicacionNominal: ",e);
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
            log.error("getPuestoFuncional: ",e);
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
            log.error("getUbicacionFuncional: ",e);
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
            log.error("getPlantillaByRenglonAnio: ",e);
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
            log.error("getRueById: ",e);
            return null;
        }     
    }
    
    public RrhhAcademico findAcademicoByTituloRueColegio(BigDecimal titulo, BigDecimal rue,
            BigDecimal colegio, String tipoServicios) throws Exception{
        try {
            String namedQuery = "";
            if(tipoServicios.equalsIgnoreCase("P")){
                namedQuery = "RrhhAcademico.findByTituloRueColegio";
            }else{
                namedQuery = "RrhhAcademico.findByTituloRue";
            }
            
            TypedQuery<RrhhAcademico> query = em
                    .createNamedQuery(namedQuery,RrhhAcademico.class);
            query.setMaxResults(1);
            query.setParameter("titulo", titulo);  
            query.setParameter("rue", rue);     
            if(tipoServicios.equalsIgnoreCase("P")){
                query.setParameter("colegioProfesional", colegio);  
            }
            
            return query.getSingleResult();
        } catch (NoResultException nr){
            return null;        
        } catch (Exception e) {
            log.error("findAcademicoByTituloRueColegio: ",e);
            throw new Exception(e.getMessage());
        }     
    }
    
    
    public String findCorrelativoRue(String cadena) throws Exception{
        try {
            Query query = em.createNativeQuery("SELECT LPAD(COUNT(*) + 1,2,'0') CORRELATIVO FROM RRHH_RUE WHERE UPPER(RUE) LIKE ('%'|| UPPER(?)||'%') ");
            query.setParameter(1, cadena);
            String value = (String)query.getSingleResult();
            return value;
        } catch (Exception e) {
            log.error("findCorrelativoRue: ",e);
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
            log.error("findEstadoById: ",e);
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
            log.error("getMunicipioDepto: ",e);
            return null;
        }     
    }
    
    public RrhhContrato findContratoById(BigDecimal idContrato){
        try {
            TypedQuery<RrhhContrato> query = em
                    .createNamedQuery("RrhhContrato.findByIdContrato",RrhhContrato.class);
            query.setParameter("idContrato", idContrato);    
            return query.getSingleResult();
        } catch (Exception e) {
            log.error("getMunicipioDepto: ",e);
            return null;
        }
    }
    
    public List<RrhhCatalogoEstado> findCatalogoEstado(){
        try {
            TypedQuery<RrhhCatalogoEstado> query = em
                    .createNamedQuery("RrhhCatalogoEstado.findByEstado",RrhhCatalogoEstado.class);   
            query.setParameter("estado", Constants.ACTIVO);
            return query.getResultList();
        } catch (Exception e) {
            log.error("findCatalogoEstado: ",e);
            return new ArrayList<RrhhCatalogoEstado>();
        }
    }
}
