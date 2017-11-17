/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ejmorales
 */
@Entity
@Table(name = "RRHH_DEPARTAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhDepartamento.findAll", query = "SELECT r FROM RrhhDepartamento r")
    , @NamedQuery(name = "RrhhDepartamento.findByDepartamento", query = "SELECT r FROM RrhhDepartamento r WHERE r.departamento = :departamento")
    , @NamedQuery(name = "RrhhDepartamento.findByNombre", query = "SELECT r FROM RrhhDepartamento r WHERE r.nombre = :nombre")})
public class RrhhDepartamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rrhhDepartamento")
    private List<RrhhMunicipio> rrhhMunicipioList;

    public RrhhDepartamento() {
    }

    public RrhhDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<RrhhMunicipio> getRrhhMunicipioList() {
        return rrhhMunicipioList;
    }

    public void setRrhhMunicipioList(List<RrhhMunicipio> rrhhMunicipioList) {
        this.rrhhMunicipioList = rrhhMunicipioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (departamento != null ? departamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhDepartamento)) {
            return false;
        }
        RrhhDepartamento other = (RrhhDepartamento) object;
        if ((this.departamento == null && other.departamento != null) || (this.departamento != null && !this.departamento.equals(other.departamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhDepartamento[ departamento=" + departamento + " ]";
    }
    
}
