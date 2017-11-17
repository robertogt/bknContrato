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
public class ResultsTexto {

    private BigDecimal titulo;
    private String nombre;

    public ResultsTexto() {
    }

    public ResultsTexto(BigDecimal titulo, String nombre) {
        this.titulo = titulo;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getTitulo() {
        return titulo;
    }

    public void setTitulo(BigDecimal titulo) {
        this.titulo = titulo;
    }
    
    
}
