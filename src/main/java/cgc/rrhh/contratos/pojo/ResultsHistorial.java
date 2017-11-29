/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ejmorales
 */
public class ResultsHistorial {
    
   private BigDecimal idContratoEstado;
    private BigDecimal idCatalogoEstado;
    private BigDecimal idContrato;
    private String nombreEstado;
    private String observaciones;
    private String estado;
    private String usuarioInsert;
    private Date fechaInsert;
    private boolean documento;

    public ResultsHistorial() {
    }

    public ResultsHistorial(String nombreEstado, String observaciones, String usuarioInsert, Date fechaInsert) {
        this.nombreEstado = nombreEstado;
        this.observaciones = observaciones;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }
    
    public ResultsHistorial(BigDecimal idContratoEstado, BigDecimal idCatalogoEstado, BigDecimal idContrato, String nombreEstado, String observaciones, String estado, Date fechaInsert, String usuarioInsert, boolean documento) {
        this.idContratoEstado = idContratoEstado;
        this.idCatalogoEstado = idCatalogoEstado;
        this.idContrato = idContrato;
        this.nombreEstado = nombreEstado;
        this.observaciones = observaciones;
        this.estado = estado;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
        this.documento = documento;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public BigDecimal getIdContratoEstado() {
        return idContratoEstado;
    }

    public void setIdContratoEstado(BigDecimal idContratoEstado) {
        this.idContratoEstado = idContratoEstado;
    }

    public BigDecimal getIdCatalogoEstado() {
        return idCatalogoEstado;
    }

    public void setIdCatalogoEstado(BigDecimal idCatalogoEstado) {
        this.idCatalogoEstado = idCatalogoEstado;
    }

    public BigDecimal getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(BigDecimal idContrato) {
        this.idContrato = idContrato;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isDocumento() {
        return documento;
    }

    public void setDocumento(boolean documento) {
        this.documento = documento;
    }
    
    
    
}
