/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ejmorales
 */
@Embeddable
public class RrhhMunicipioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "MUNICIPIO")
    private String municipio;

    public RrhhMunicipioPK() {
    }

    public RrhhMunicipioPK(String departamento, String municipio) {
        this.departamento = departamento;
        this.municipio = municipio;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (departamento != null ? departamento.hashCode() : 0);
        hash += (municipio != null ? municipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhMunicipioPK)) {
            return false;
        }
        RrhhMunicipioPK other = (RrhhMunicipioPK) object;
        if ((this.departamento == null && other.departamento != null) || (this.departamento != null && !this.departamento.equals(other.departamento))) {
            return false;
        }
        if ((this.municipio == null && other.municipio != null) || (this.municipio != null && !this.municipio.equals(other.municipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhMunicipioPK[ departamento=" + departamento + ", municipio=" + municipio + " ]";
    }
    
}
