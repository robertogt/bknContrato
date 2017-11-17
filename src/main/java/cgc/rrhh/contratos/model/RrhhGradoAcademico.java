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
@Table(name = "RRHH_GRADO_ACADEMICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhGradoAcademico.findAll", query = "SELECT r FROM RrhhGradoAcademico r")
    , @NamedQuery(name = "RrhhGradoAcademico.findByGradoAcademico", query = "SELECT r FROM RrhhGradoAcademico r WHERE r.gradoAcademico = :gradoAcademico")
    , @NamedQuery(name = "RrhhGradoAcademico.findByNombre", query = "SELECT r FROM RrhhGradoAcademico r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhGradoAcademico.findByEstado", query = "SELECT r FROM RrhhGradoAcademico r WHERE r.estado = :estado")})
public class RrhhGradoAcademico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "GRADO_ACADEMICO")
    private String gradoAcademico;
    @Size(max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @OneToMany(mappedBy = "gradoAcademico")
    private List<RrhhAcademico> rrhhAcademicoList;

    public RrhhGradoAcademico() {
    }

    public RrhhGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<RrhhAcademico> getRrhhAcademicoList() {
        return rrhhAcademicoList;
    }

    public void setRrhhAcademicoList(List<RrhhAcademico> rrhhAcademicoList) {
        this.rrhhAcademicoList = rrhhAcademicoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gradoAcademico != null ? gradoAcademico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhGradoAcademico)) {
            return false;
        }
        RrhhGradoAcademico other = (RrhhGradoAcademico) object;
        if ((this.gradoAcademico == null && other.gradoAcademico != null) || (this.gradoAcademico != null && !this.gradoAcademico.equals(other.gradoAcademico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhGradoAcademico[ gradoAcademico=" + gradoAcademico + " ]";
    }
    
}
