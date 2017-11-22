/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.pojo;

import java.util.Date;

/**
 *
 * @author ejmorales
 */
public class ResultsHistorial {
    
    private String nombreEstado;
    private String observaciones;
    private String usuarioInsert;
    private Date fechaInsert;

    public ResultsHistorial() {
    }

    public ResultsHistorial(String nombreEstado, String observaciones, String usuarioInsert, Date fechaInsert) {
        this.nombreEstado = nombreEstado;
        this.observaciones = observaciones;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
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
    
    
    
}
