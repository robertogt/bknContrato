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
public class ResultsAcademico {
    
    private BigDecimal academico;
    private BigDecimal titulo;
    private String nombreTitulo;
    private String numeroColegiado;
    private BigDecimal colegioProfesional;

    public ResultsAcademico() {
    }

    public ResultsAcademico(BigDecimal academico,BigDecimal titulo,String nombreTitulo, String numeroColegiado, BigDecimal colegioProfesional) {
        this.academico = academico;
        this.titulo = titulo;
        this.nombreTitulo = nombreTitulo;
        this.numeroColegiado = numeroColegiado;
        this.colegioProfesional = colegioProfesional;
    }

    public BigDecimal getAcademico() {
        return academico;
    }

    public void setAcademico(BigDecimal academico) {
        this.academico = academico;
    }
    
    public BigDecimal getTitulo() {
        return titulo;
    }

    public void setTitulo(BigDecimal titulo) {
        this.titulo = titulo;
    }

    public String getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(String numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

    public BigDecimal getColegioProfesional() {
        return colegioProfesional;
    }

    public void setColegioProfesional(BigDecimal colegioProfesional) {
        this.colegioProfesional = colegioProfesional;
    }

    public String getNombreTitulo() {
        return nombreTitulo;
    }

    public void setNombreTitulo(String nombreTitulo) {
        this.nombreTitulo = nombreTitulo;
    }
    
    
    
}
