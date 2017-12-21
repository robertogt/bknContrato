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
import javax.persistence.CascadeType;
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
@Table(name = "RRHH_CATALOGO_ESTADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhCatalogoEstado.findAll", query = "SELECT r FROM RrhhCatalogoEstado r")
    , @NamedQuery(name = "RrhhCatalogoEstado.findByIdCatalogoEstado", query = "SELECT r FROM RrhhCatalogoEstado r WHERE r.idCatalogoEstado = :idCatalogoEstado")
    , @NamedQuery(name = "RrhhCatalogoEstado.findByNombre", query = "SELECT r FROM RrhhCatalogoEstado r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhCatalogoEstado.findByAbreviatura", query = "SELECT r FROM RrhhCatalogoEstado r WHERE r.abreviatura = :abreviatura")
    , @NamedQuery(name = "RrhhCatalogoEstado.findByDescripcion", query = "SELECT r FROM RrhhCatalogoEstado r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "RrhhCatalogoEstado.findByEstado", query = "SELECT r FROM RrhhCatalogoEstado r WHERE r.estado = :estado ORDER BY r.idCatalogoEstado ASC")
    , @NamedQuery(name = "RrhhCatalogoEstado.findByUsuarioInsert", query = "SELECT r FROM RrhhCatalogoEstado r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhCatalogoEstado.findByFechaInsert", query = "SELECT r FROM RrhhCatalogoEstado r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhCatalogoEstado.findByUsuarioUpdate", query = "SELECT r FROM RrhhCatalogoEstado r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhCatalogoEstado.findByFechaUpdate", query = "SELECT r FROM RrhhCatalogoEstado r WHERE r.fechaUpdate = :fechaUpdate")})
public class RrhhCatalogoEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CATALOGO_ESTADO")
    private BigDecimal idCatalogoEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "ABREVIATURA")
    private String abreviatura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCatalogoEstado")
    private List<RrhhContratoEstado> rrhhContratoEstadoList;

    public RrhhCatalogoEstado() {
    }

    public RrhhCatalogoEstado(BigDecimal idCatalogoEstado) {
        this.idCatalogoEstado = idCatalogoEstado;
    }

    public RrhhCatalogoEstado(BigDecimal idCatalogoEstado, String nombre, String abreviatura, String descripcion, String estado, String usuarioInsert, Date fechaInsert) {
        this.idCatalogoEstado = idCatalogoEstado;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.descripcion = descripcion;
        this.estado = estado;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public BigDecimal getIdCatalogoEstado() {
        return idCatalogoEstado;
    }

    public void setIdCatalogoEstado(BigDecimal idCatalogoEstado) {
        this.idCatalogoEstado = idCatalogoEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    public List<RrhhContratoEstado> getRrhhContratoEstadoList() {
        return rrhhContratoEstadoList;
    }

    public void setRrhhContratoEstadoList(List<RrhhContratoEstado> rrhhContratoEstadoList) {
        this.rrhhContratoEstadoList = rrhhContratoEstadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatalogoEstado != null ? idCatalogoEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhCatalogoEstado)) {
            return false;
        }
        RrhhCatalogoEstado other = (RrhhCatalogoEstado) object;
        if ((this.idCatalogoEstado == null && other.idCatalogoEstado != null) || (this.idCatalogoEstado != null && !this.idCatalogoEstado.equals(other.idCatalogoEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhCatalogoEstado[ idCatalogoEstado=" + idCatalogoEstado + " ]";
    }
    
}
