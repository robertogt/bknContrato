/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.pojo;

/**
 *
 * @author ejmorales
 */
public class ResultsUbicacion {
    
    private Integer idUbicacionFuncional;
    private String nombre;

    public ResultsUbicacion() {
    }

    public ResultsUbicacion(Integer idUbicacionFuncional, String nombre) {
        this.idUbicacionFuncional = idUbicacionFuncional;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdUbicacionFuncional() {
        return idUbicacionFuncional;
    }

    public void setIdUbicacionFuncional(Integer idUbicacionFuncional) {
        this.idUbicacionFuncional = idUbicacionFuncional;
    }
    
    
}
