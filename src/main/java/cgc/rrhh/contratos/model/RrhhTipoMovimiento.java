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
@Table(name = "RRHH_TIPO_MOVIMIENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhTipoMovimiento.findAll", query = "SELECT r FROM RrhhTipoMovimiento r")
    , @NamedQuery(name = "RrhhTipoMovimiento.findByTipoMovimiento", query = "SELECT r FROM RrhhTipoMovimiento r WHERE r.tipoMovimiento = :tipoMovimiento")
    , @NamedQuery(name = "RrhhTipoMovimiento.findByNombre", query = "SELECT r FROM RrhhTipoMovimiento r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhTipoMovimiento.findByTipoConcepto", query = "SELECT r FROM RrhhTipoMovimiento r WHERE r.tipoConcepto = :tipoConcepto")
    , @NamedQuery(name = "RrhhTipoMovimiento.findByUsuarioInsert", query = "SELECT r FROM RrhhTipoMovimiento r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhTipoMovimiento.findByFechaInsert", query = "SELECT r FROM RrhhTipoMovimiento r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhTipoMovimiento.findByUsuarioUpdate", query = "SELECT r FROM RrhhTipoMovimiento r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhTipoMovimiento.findByFechaUpdate", query = "SELECT r FROM RrhhTipoMovimiento r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhTipoMovimiento.findByEstado", query = "SELECT r FROM RrhhTipoMovimiento r WHERE r.estado = :estado")})
public class RrhhTipoMovimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_MOVIMIENTO")
    private BigDecimal tipoMovimiento;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 1)
    @Column(name = "TIPO_CONCEPTO")
    private String tipoConcepto;
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
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoMovimiento")
    private List<RrhhLaboral> rrhhLaboralList;

    public RrhhTipoMovimiento() {
    }

    public RrhhTipoMovimiento(BigDecimal tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public RrhhTipoMovimiento(BigDecimal tipoMovimiento, String usuarioInsert, Date fechaInsert) {
        this.tipoMovimiento = tipoMovimiento;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public BigDecimal getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(BigDecimal tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoConcepto() {
        return tipoConcepto;
    }

    public void setTipoConcepto(String tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
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
    public List<RrhhLaboral> getRrhhLaboralList() {
        return rrhhLaboralList;
    }

    public void setRrhhLaboralList(List<RrhhLaboral> rrhhLaboralList) {
        this.rrhhLaboralList = rrhhLaboralList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoMovimiento != null ? tipoMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhTipoMovimiento)) {
            return false;
        }
        RrhhTipoMovimiento other = (RrhhTipoMovimiento) object;
        if ((this.tipoMovimiento == null && other.tipoMovimiento != null) || (this.tipoMovimiento != null && !this.tipoMovimiento.equals(other.tipoMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhTipoMovimiento[ tipoMovimiento=" + tipoMovimiento + " ]";
    }
    
}
