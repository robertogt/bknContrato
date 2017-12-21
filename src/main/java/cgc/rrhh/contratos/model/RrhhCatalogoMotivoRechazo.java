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
@Table(name = "RRHH_CATALOGO_MOTIVO_RECHAZO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhCatalogoMotivoRechazo.findAll", query = "SELECT r FROM RrhhCatalogoMotivoRechazo r")
    , @NamedQuery(name = "RrhhCatalogoMotivoRechazo.findByIdCatalogoMotivoRechazo", query = "SELECT r FROM RrhhCatalogoMotivoRechazo r WHERE r.idCatalogoMotivoRechazo = :idCatalogoMotivoRechazo")
    , @NamedQuery(name = "RrhhCatalogoMotivoRechazo.findByNombre", query = "SELECT r FROM RrhhCatalogoMotivoRechazo r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhCatalogoMotivoRechazo.findByDescripcion", query = "SELECT r FROM RrhhCatalogoMotivoRechazo r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "RrhhCatalogoMotivoRechazo.findByUsuarioInsert", query = "SELECT r FROM RrhhCatalogoMotivoRechazo r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhCatalogoMotivoRechazo.findByFechaInsert", query = "SELECT r FROM RrhhCatalogoMotivoRechazo r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhCatalogoMotivoRechazo.findByUsuarioUpdate", query = "SELECT r FROM RrhhCatalogoMotivoRechazo r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhCatalogoMotivoRechazo.findByFechaUpdate", query = "SELECT r FROM RrhhCatalogoMotivoRechazo r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhCatalogoMotivoRechazo.findByEstado", query = "SELECT r FROM RrhhCatalogoMotivoRechazo r WHERE r.estado = :estado")})
public class RrhhCatalogoMotivoRechazo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CATALOGO_MOTIVO_RECHAZO")
    private BigDecimal idCatalogoMotivoRechazo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @OneToMany(mappedBy = "idCatalogoMotivoRechazo")
    private List<RrhhMotivoRechazo> rrhhMotivoRechazoList;

    public RrhhCatalogoMotivoRechazo() {
    }

    public RrhhCatalogoMotivoRechazo(BigDecimal idCatalogoMotivoRechazo) {
        this.idCatalogoMotivoRechazo = idCatalogoMotivoRechazo;
    }

    public RrhhCatalogoMotivoRechazo(BigDecimal idCatalogoMotivoRechazo, String nombre, String descripcion, String usuarioInsert, Date fechaInsert, String estado) {
        this.idCatalogoMotivoRechazo = idCatalogoMotivoRechazo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
        this.estado = estado;
    }

    public BigDecimal getIdCatalogoMotivoRechazo() {
        return idCatalogoMotivoRechazo;
    }

    public void setIdCatalogoMotivoRechazo(BigDecimal idCatalogoMotivoRechazo) {
        this.idCatalogoMotivoRechazo = idCatalogoMotivoRechazo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<RrhhMotivoRechazo> getRrhhMotivoRechazoList() {
        return rrhhMotivoRechazoList;
    }

    public void setRrhhMotivoRechazoList(List<RrhhMotivoRechazo> rrhhMotivoRechazoList) {
        this.rrhhMotivoRechazoList = rrhhMotivoRechazoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatalogoMotivoRechazo != null ? idCatalogoMotivoRechazo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhCatalogoMotivoRechazo)) {
            return false;
        }
        RrhhCatalogoMotivoRechazo other = (RrhhCatalogoMotivoRechazo) object;
        if ((this.idCatalogoMotivoRechazo == null && other.idCatalogoMotivoRechazo != null) || (this.idCatalogoMotivoRechazo != null && !this.idCatalogoMotivoRechazo.equals(other.idCatalogoMotivoRechazo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhCatalogoMotivoRechazo[ idCatalogoMotivoRechazo=" + idCatalogoMotivoRechazo + " ]";
    }
    
}
