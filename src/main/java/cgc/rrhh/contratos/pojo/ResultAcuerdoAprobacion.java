/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.pojo;

import cgc.rrhh.contratos.model.RrhhAcuerdoContrato;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jrroquel
 */
public class ResultAcuerdoAprobacion {
    private BigDecimal idAcuerdoAprobacion;
    private BigInteger numeroAcuerdo;
    private String identificadorAcuerdo;
    private String renglon;
    private String tipoServicios;
    private String observaciones;
    private String estado;
    private String usuarioInsert;
    private Date fechaInsert;
    private String usuarioUpdate;
    private Date fechaUpdate;
    private List<ResultsContrato> contratos;

    public ResultAcuerdoAprobacion() {
    }

    public ResultAcuerdoAprobacion(BigDecimal idAcuerdoAprobacion, BigInteger numeroAcuerdo, String identificadorAcuerdo, String renglon, String tipoServicios, String observaciones, String estado, String usuarioInsert, Date fechaInsert, String usuarioUpdate, Date fechaUpdate, List<ResultsContrato> contratos) {
        this.idAcuerdoAprobacion = idAcuerdoAprobacion;
        this.numeroAcuerdo = numeroAcuerdo;
        this.identificadorAcuerdo = identificadorAcuerdo;
        this.renglon = renglon;
        this.tipoServicios = tipoServicios;
        this.observaciones = observaciones;
        this.estado = estado;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
        this.usuarioUpdate = usuarioUpdate;
        this.fechaUpdate = fechaUpdate;
        this.contratos = contratos;
    }

   

    public BigDecimal getIdAcuerdoAprobacion() {
        return idAcuerdoAprobacion;
    }

    public void setIdAcuerdoAprobacion(BigDecimal idAcuerdoAprobacion) {
        this.idAcuerdoAprobacion = idAcuerdoAprobacion;
    }

    public BigInteger getNumeroAcuerdo() {
        return numeroAcuerdo;
    }

    public void setNumeroAcuerdo(BigInteger numeroAcuerdo) {
        this.numeroAcuerdo = numeroAcuerdo;
    }

    public String getIdentificadorAcuerdo() {
        return identificadorAcuerdo;
    }

    public void setIdentificadorAcuerdo(String identificadorAcuerdo) {
        this.identificadorAcuerdo = identificadorAcuerdo;
    }

    public String getRenglon() {
        return renglon;
    }

    public void setRenglon(String renglon) {
        this.renglon = renglon;
    }

    public String getTipoServicios() {
        return tipoServicios;
    }

    public void setTipoServicios(String tipoServicios) {
        this.tipoServicios = tipoServicios;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuarioInsert() {
        return usuarioInsert;
    }

    public void setUsuarioInsert(String usuarioInsert) {
        this.usuarioInsert = usuarioInsert;
    }

    public Date getFechaInsert() {
        return fechaInsert;
    }

    public void setFechaInsert(Date fechaInsert) {
        this.fechaInsert = fechaInsert;
    }

    public String getUsuarioUpdate() {
        return usuarioUpdate;
    }

    public void setUsuarioUpdate(String usuarioUpdate) {
        this.usuarioUpdate = usuarioUpdate;
    }

    public Date getFechaUpdate() {
        return fechaUpdate;
    }

    public void setFechaUpdate(Date fechaUpdate) {
        this.fechaUpdate = fechaUpdate;
    }

    public List<ResultsContrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<ResultsContrato> contratos) {
        this.contratos = contratos;
    }

    
    
}
