/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "RRHH_PAIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhPais.findAll", query = "SELECT r FROM RrhhPais r")
    , @NamedQuery(name = "RrhhPais.findByPais", query = "SELECT r FROM RrhhPais r WHERE r.pais = :pais")
    , @NamedQuery(name = "RrhhPais.findByNombre", query = "SELECT r FROM RrhhPais r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhPais.findByNacionalidad", query = "SELECT r FROM RrhhPais r WHERE r.nacionalidad = :nacionalidad")})
public class RrhhPais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "PAIS")
    private String pais;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 70)
    @Column(name = "NACIONALIDAD")
    private String nacionalidad;
    @OneToMany(mappedBy = "pais")
    private List<RrhhRue> rrhhRueList;

    public RrhhPais() {
    }

    public RrhhPais(String pais) {
        this.pais = pais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @XmlTransient
    public List<RrhhRue> getRrhhRueList() {
        return rrhhRueList;
    }

    public void setRrhhRueList(List<RrhhRue> rrhhRueList) {
        this.rrhhRueList = rrhhRueList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pais != null ? pais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhPais)) {
            return false;
        }
        RrhhPais other = (RrhhPais) object;
        if ((this.pais == null && other.pais != null) || (this.pais != null && !this.pais.equals(other.pais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhPais[ pais=" + pais + " ]";
    }
    
}
