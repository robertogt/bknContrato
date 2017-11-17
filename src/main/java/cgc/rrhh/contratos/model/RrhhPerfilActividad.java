/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "RRHH_PERFIL_ACTIVIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhPerfilActividad.findAll", query = "SELECT r FROM RrhhPerfilActividad r")
    , @NamedQuery(name = "RrhhPerfilActividad.findByIdPerfilActividad", query = "SELECT r FROM RrhhPerfilActividad r WHERE r.idPerfilActividad = :idPerfilActividad")
    , @NamedQuery(name = "RrhhPerfilActividad.findByIdPerfil", query = "SELECT r FROM RrhhPerfilActividad r WHERE r.idPerfil = :idPerfil")
    , @NamedQuery(name = "RrhhPerfilActividad.findByIdActividad", query = "SELECT r FROM RrhhPerfilActividad r WHERE r.idActividad = :idActividad")
    , @NamedQuery(name = "RrhhPerfilActividad.findByEstado", query = "SELECT r FROM RrhhPerfilActividad r WHERE r.estado = :estado")
    , @NamedQuery(name = "RrhhPerfilActividad.findByUsuarioInsert", query = "SELECT r FROM RrhhPerfilActividad r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhPerfilActividad.findByUsuarioUpdate", query = "SELECT r FROM RrhhPerfilActividad r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhPerfilActividad.findByFechaInsert", query = "SELECT r FROM RrhhPerfilActividad r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhPerfilActividad.findByFechaUpdate", query = "SELECT r FROM RrhhPerfilActividad r WHERE r.fechaUpdate = :fechaUpdate")})
public class RrhhPerfilActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERFIL_ACTIVIDAD")
    private BigDecimal idPerfilActividad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERFIL")
    private BigInteger idPerfil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ACTIVIDAD")
    private BigInteger idActividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "USUARIO_INSERT")
    private String usuarioInsert;
    @Size(max = 200)
    @Column(name = "USUARIO_UPDATE")
    private String usuarioUpdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INSERT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInsert;
    @Column(name = "FECHA_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUpdate;

    public RrhhPerfilActividad() {
    }

    public RrhhPerfilActividad(BigDecimal idPerfilActividad) {
        this.idPerfilActividad = idPerfilActividad;
    }

    public RrhhPerfilActividad(BigDecimal idPerfilActividad, BigInteger idPerfil, BigInteger idActividad, String estado, String usuarioInsert, Date fechaInsert) {
        this.idPerfilActividad = idPerfilActividad;
        this.idPerfil = idPerfil;
        this.idActividad = idActividad;
        this.estado = estado;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public BigDecimal getIdPerfilActividad() {
        return idPerfilActividad;
    }

    public void setIdPerfilActividad(BigDecimal idPerfilActividad) {
        this.idPerfilActividad = idPerfilActividad;
    }

    public BigInteger getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(BigInteger idPerfil) {
        this.idPerfil = idPerfil;
    }

    public BigInteger getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(BigInteger idActividad) {
        this.idActividad = idActividad;
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

    public String getUsuarioUpdate() {
        return usuarioUpdate;
    }

    public void setUsuarioUpdate(String usuarioUpdate) {
        this.usuarioUpdate = usuarioUpdate;
    }

    public Date getFechaInsert() {
        return fechaInsert;
    }

    public void setFechaInsert(Date fechaInsert) {
        this.fechaInsert = fechaInsert;
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
        hash += (idPerfilActividad != null ? idPerfilActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhPerfilActividad)) {
            return false;
        }
        RrhhPerfilActividad other = (RrhhPerfilActividad) object;
        if ((this.idPerfilActividad == null && other.idPerfilActividad != null) || (this.idPerfilActividad != null && !this.idPerfilActividad.equals(other.idPerfilActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhPerfilActividad[ idPerfilActividad=" + idPerfilActividad + " ]";
    }
    
}
