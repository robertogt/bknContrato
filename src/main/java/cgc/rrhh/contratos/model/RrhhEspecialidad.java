/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ejmorales
 */
@Entity
@Table(name = "RRHH_ESPECIALIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhEspecialidad.findAll", query = "SELECT r FROM RrhhEspecialidad r")
    , @NamedQuery(name = "RrhhEspecialidad.findByEspecialidad", query = "SELECT r FROM RrhhEspecialidad r WHERE r.especialidad = :especialidad")
    , @NamedQuery(name = "RrhhEspecialidad.findByNombre", query = "SELECT r FROM RrhhEspecialidad r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhEspecialidad.findByEstado", query = "SELECT r FROM RrhhEspecialidad r WHERE r.estado = :estado")
    , @NamedQuery(name = "RrhhEspecialidad.findByUsuarioInsert", query = "SELECT r FROM RrhhEspecialidad r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhEspecialidad.findByFechaInsert", query = "SELECT r FROM RrhhEspecialidad r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhEspecialidad.findByUsuarioUpdate", query = "SELECT r FROM RrhhEspecialidad r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhEspecialidad.findByFechaUpdate", query = "SELECT r FROM RrhhEspecialidad r WHERE r.fechaUpdate = :fechaUpdate")})
public class RrhhEspecialidad implements Serializable {

    @OneToMany(mappedBy = "especialidad")
    private List<RrhhPuestoNominal> rrhhPuestoNominalList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESPECIALIDAD")
    private BigDecimal especialidad;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USUARIO_INSERT")
    private String usuarioInsert;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INSERT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInsert;
    @Size(max = 50)
    @Column(name = "USUARIO_UPDATE")
    private String usuarioUpdate;
    @Column(name = "FECHA_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUpdate;
    @OneToMany(mappedBy = "especialidad")
    private List<RrhhGuatenominas> rrhhGuatenominasList;

    public RrhhEspecialidad() {
    }

    public RrhhEspecialidad(BigDecimal especialidad) {
        this.especialidad = especialidad;
    }

    public RrhhEspecialidad(BigDecimal especialidad, String estado, String usuarioInsert, Date fechaInsert) {
        this.especialidad = especialidad;
        this.estado = estado;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public BigDecimal getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(BigDecimal especialidad) {
        this.especialidad = especialidad;
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

    public String getUsuarioUpdate() {
        return usuarioUpdate;
    }

    public void setUsuarioUpdate(String usuarioUpdate) {
        this.usuarioUpdate = usuarioUpdate;
    }

    public Date getFechaUpdate() {
        return fechaUpdate;
    }

    public void setFechaUpdate(Date fechaUpdate) {
        this.fechaUpdate = fechaUpdate;
    }

    @XmlTransient
    public List<RrhhGuatenominas> getRrhhGuatenominasList() {
        return rrhhGuatenominasList;
    }

    public void setRrhhGuatenominasList(List<RrhhGuatenominas> rrhhGuatenominasList) {
        this.rrhhGuatenominasList = rrhhGuatenominasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (especialidad != null ? especialidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhEspecialidad)) {
            return false;
        }
        RrhhEspecialidad other = (RrhhEspecialidad) object;
        if ((this.especialidad == null && other.especialidad != null) || (this.especialidad != null && !this.especialidad.equals(other.especialidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhEspecialidad[ especialidad=" + especialidad + " ]";
    }

    @XmlTransient
    public List<RrhhPuestoNominal> getRrhhPuestoNominalList() {
        return rrhhPuestoNominalList;
    }

    public void setRrhhPuestoNominalList(List<RrhhPuestoNominal> rrhhPuestoNominalList) {
        this.rrhhPuestoNominalList = rrhhPuestoNominalList;
    }
    
}
