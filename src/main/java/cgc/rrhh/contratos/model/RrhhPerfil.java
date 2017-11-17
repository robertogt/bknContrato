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
@Table(name = "RRHH_PERFIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhPerfil.findAll", query = "SELECT r FROM RrhhPerfil r ORDER BY r.idPerfil DESC")
    , @NamedQuery(name = "RrhhPerfil.findByIdPerfil", query = "SELECT r FROM RrhhPerfil r WHERE r.idPerfil = :idPerfil")
    , @NamedQuery(name = "RrhhPerfil.findByIdUbicacion", query = "SELECT r FROM RrhhPerfil r WHERE r.idUbicacion = :idUbicacion AND r.estado = 'A' ORDER BY r.nombre ASC ")
    , @NamedQuery(name = "RrhhPerfil.findByNombre", query = "SELECT r FROM RrhhPerfil r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhPerfil.findByObservaciones", query = "SELECT r FROM RrhhPerfil r WHERE r.observaciones = :observaciones")
    , @NamedQuery(name = "RrhhPerfil.findByEstado", query = "SELECT r FROM RrhhPerfil r WHERE r.estado = :estado ORDER BY r.idPerfil DESC")
    , @NamedQuery(name = "RrhhPerfil.findByUsuarioInsert", query = "SELECT r FROM RrhhPerfil r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhPerfil.findByUsuarioUpdate", query = "SELECT r FROM RrhhPerfil r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhPerfil.findByFechaInsert", query = "SELECT r FROM RrhhPerfil r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhPerfil.findByFechaUpdate", query = "SELECT r FROM RrhhPerfil r WHERE r.fechaUpdate = :fechaUpdate")})
public class RrhhPerfil implements Serializable {

    @Size(max = 1000)
    @Column(name = "MOTIVO_ANULACION")
    private String motivoAnulacion;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERFIL")
    private BigDecimal idPerfil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UBICACION")
    private BigInteger idUbicacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 1000)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
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

    public RrhhPerfil() {
    }

    public RrhhPerfil(BigDecimal idPerfil) {
        this.idPerfil = idPerfil;
    }

    public RrhhPerfil(BigDecimal idPerfil, BigInteger idUbicacion, String nombre, String estado, String usuarioInsert, Date fechaInsert) {
        this.idPerfil = idPerfil;
        this.idUbicacion = idUbicacion;
        this.nombre = nombre;
        this.estado = estado;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public BigDecimal getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(BigDecimal idPerfil) {
        this.idPerfil = idPerfil;
    }

    public BigInteger getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(BigInteger idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
        hash += (idPerfil != null ? idPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhPerfil)) {
            return false;
        }
        RrhhPerfil other = (RrhhPerfil) object;
        if ((this.idPerfil == null && other.idPerfil != null) || (this.idPerfil != null && !this.idPerfil.equals(other.idPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhPerfil[ idPerfil=" + idPerfil + " ]";
    }

    public String getMotivoAnulacion() {
        return motivoAnulacion;
    }

    public void setMotivoAnulacion(String motivoAnulacion) {
        this.motivoAnulacion = motivoAnulacion;
    }
    
}
