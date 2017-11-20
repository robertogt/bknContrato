/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.pojo;

import java.math.BigDecimal;

/**
 *
 * @author ejmorales
 */
public class ResultsActividad {
    
    private BigDecimal idActividad;
    private String nombre;
    private String Descripcion;
    private boolean seleccionado;

    public ResultsActividad() {
    }

    public ResultsActividad(BigDecimal idActividad, String nombre, String Descripcion, boolean seleccionado) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.Descripcion = Descripcion;
        this.seleccionado = seleccionado;
    }
    
    public ResultsActividad(BigDecimal idActividad, String Descripcion){
        this.idActividad = idActividad;
        this.Descripcion = Descripcion;
    }

    public BigDecimal getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(BigDecimal idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
    
    
    
}
