/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ejmorales
 */
@Entity
@Table(name = "RRHH_COLEGIO_PROFESIONAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhColegioProfesional.findAll", query = "SELECT r FROM RrhhColegioProfesional r ORDER BY r.nombreColegioProfesional ASC")
    , @NamedQuery(name = "RrhhColegioProfesional.findByColegioProfesional", query = "SELECT r FROM RrhhColegioProfesional r WHERE r.colegioProfesional = :colegioProfesional")
    , @NamedQuery(name = "RrhhColegioProfesional.findByNombreColegioProfesional", query = "SELECT r FROM RrhhColegioProfesional r WHERE r.nombreColegioProfesional = :nombreColegioProfesional")
    , @NamedQuery(name = "RrhhColegioProfesional.findByUsuarioInsert", query = "SELECT r FROM RrhhColegioProfesional r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhColegioProfesional.findByFechaInsert", query = "SELECT r FROM RrhhColegioProfesional r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhColegioProfesional.findByUsuarioUpdate", query = "SELECT r FROM RrhhColegioProfesional r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhColegioProfesional.findByFechaUpdate", query = "SELECT r FROM RrhhColegioProfesional r WHERE r.fechaUpdate = :fechaUpdate")})
public class RrhhColegioProfesional implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COLEGIO_PROFESIONAL")
    private BigDecimal colegioProfesional;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_COLEGIO_PROFESIONAL")
    private String nombreColegioProfesional;
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

    public RrhhColegioProfesional() {
    }

    public RrhhColegioProfesional(BigDecimal colegioProfesional) {
        this.colegioProfesional = colegioProfesional;
    }

    public RrhhColegioProfesional(BigDecimal colegioProfesional, String nombreColegioProfesional, String usuarioInsert, Date fechaInsert) {
        this.colegioProfesional = colegioProfesional;
        this.nombreColegioProfesional = nombreColegioProfesional;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public BigDecimal getColegioProfesional() {
        return colegioProfesional;
    }

    public void setColegioProfesional(BigDecimal colegioProfesional) {
        this.colegioProfesional = colegioProfesional;
    }

    public String getNombreColegioProfesional() {
        return nombreColegioProfesional;
    }

    public void setNombreColegioProfesional(String nombreColegioProfesional) {
        this.nombreColegioProfesional = nombreColegioProfesional;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (colegioProfesional != null ? colegioProfesional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhColegioProfesional)) {
            return false;
        }
        RrhhColegioProfesional other = (RrhhColegioProfesional) object;
        if ((this.colegioProfesional == null && other.colegioProfesional != null) || (this.colegioProfesional != null && !this.colegioProfesional.equals(other.colegioProfesional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhColegioProfesional[ colegioProfesional=" + colegioProfesional + " ]";
    }
    
}
