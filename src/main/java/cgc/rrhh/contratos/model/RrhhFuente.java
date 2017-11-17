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
@Table(name = "RRHH_FUENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhFuente.findAll", query = "SELECT r FROM RrhhFuente r")
    , @NamedQuery(name = "RrhhFuente.findByFuente", query = "SELECT r FROM RrhhFuente r WHERE r.fuente = :fuente")
    , @NamedQuery(name = "RrhhFuente.findByNombre", query = "SELECT r FROM RrhhFuente r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhFuente.findByCodigoFuente", query = "SELECT r FROM RrhhFuente r WHERE r.codigoFuente = :codigoFuente")
    , @NamedQuery(name = "RrhhFuente.findByUsuarioInsert", query = "SELECT r FROM RrhhFuente r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhFuente.findByFechaInsert", query = "SELECT r FROM RrhhFuente r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhFuente.findByUsuarioUpdate", query = "SELECT r FROM RrhhFuente r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhFuente.findByFechaUpdate", query = "SELECT r FROM RrhhFuente r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhFuente.findByEstado", query = "SELECT r FROM RrhhFuente r WHERE r.estado = :estado ORDER BY r.fuente DESC")
    , @NamedQuery(name = "RrhhFuente.findByComplemento", query = "SELECT r FROM RrhhFuente r WHERE r.complemento = :complemento")
    , @NamedQuery(name = "RrhhFuente.findByDescripcion", query = "SELECT r FROM RrhhFuente r WHERE r.descripcion = :descripcion")})
public class RrhhFuente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FUENTE")
    private BigDecimal fuente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 40)
    @Column(name = "CODIGO_FUENTE")
    private String codigoFuente;
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
    @Size(max = 50)
    @Column(name = "COMPLEMENTO")
    private String complemento;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fuente")
    private List<RrhhLaboral> rrhhLaboralList;

    public RrhhFuente() {
    }

    public RrhhFuente(BigDecimal fuente) {
        this.fuente = fuente;
    }

    public RrhhFuente(BigDecimal fuente, String nombre, String usuarioInsert, Date fechaInsert) {
        this.fuente = fuente;
        this.nombre = nombre;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public BigDecimal getFuente() {
        return fuente;
    }

    public void setFuente(BigDecimal fuente) {
        this.fuente = fuente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoFuente() {
        return codigoFuente;
    }

    public void setCodigoFuente(String codigoFuente) {
        this.codigoFuente = codigoFuente;
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (fuente != null ? fuente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhFuente)) {
            return false;
        }
        RrhhFuente other = (RrhhFuente) object;
        if ((this.fuente == null && other.fuente != null) || (this.fuente != null && !this.fuente.equals(other.fuente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhFuente[ fuente=" + fuente + " ]";
    }
    
}
