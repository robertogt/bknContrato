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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "RRHH_PUESTO_FUNCIONAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhPuestoFuncional.findAll", query = "SELECT r FROM RrhhPuestoFuncional r")
    , @NamedQuery(name = "RrhhPuestoFuncional.findByPuestoFuncional", query = "SELECT r FROM RrhhPuestoFuncional r WHERE r.puestoFuncional = :puestoFuncional")
    , @NamedQuery(name = "RrhhPuestoFuncional.findByNombreFuncional", query = "SELECT r FROM RrhhPuestoFuncional r WHERE r.nombreFuncional = :nombreFuncional")
    , @NamedQuery(name = "RrhhPuestoFuncional.findByCodigoFuncional", query = "SELECT r FROM RrhhPuestoFuncional r WHERE r.codigoFuncional = :codigoFuncional")
    , @NamedQuery(name = "RrhhPuestoFuncional.findByUsuarioInsert", query = "SELECT r FROM RrhhPuestoFuncional r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhPuestoFuncional.findByFechaInsert", query = "SELECT r FROM RrhhPuestoFuncional r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhPuestoFuncional.findByUsuarioUpdate", query = "SELECT r FROM RrhhPuestoFuncional r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhPuestoFuncional.findByFuncionalPadre", query = "SELECT r FROM RrhhPuestoFuncional r WHERE r.funcionalPadre = :funcionalPadre")
    , @NamedQuery(name = "RrhhPuestoFuncional.findByEstado", query = "SELECT r FROM RrhhPuestoFuncional r WHERE r.estado = :estado")
    , @NamedQuery(name = "RrhhPuestoFuncional.findByFechaUpdate", query = "SELECT r FROM RrhhPuestoFuncional r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhPuestoFuncional.findByIdCategoriaPuesto", query = "SELECT r FROM RrhhPuestoFuncional r WHERE r.idCategoriaPuesto = :idCategoriaPuesto")
    , @NamedQuery(name = "RrhhPuestoFuncional.findByCodigoFuncionalSag", query = "SELECT r FROM RrhhPuestoFuncional r WHERE r.codigoFuncionalSag = :codigoFuncionalSag")})
public class RrhhPuestoFuncional implements Serializable {

    @JoinColumn(name = "ID_CATEGORIA_PUESTO", referencedColumnName = "ID_CATEGORIA_PUESTO")
    @ManyToOne
    private RrhhCategoriaPuesto idCategoriaPuesto;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PUESTO_FUNCIONAL")
    private BigDecimal puestoFuncional;
    @Size(max = 100)
    @Column(name = "NOMBRE_FUNCIONAL")
    private String nombreFuncional;
    @Size(max = 50)
    @Column(name = "CODIGO_FUNCIONAL")
    private String codigoFuncional;
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
    @Column(name = "FUNCIONAL_PADRE")
    private BigInteger funcionalPadre;
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "FECHA_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUpdate;
    @Column(name = "CODIGO_FUNCIONAL_SAG")
    private Long codigoFuncionalSag;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "puestoFuncional")
    private List<RrhhLaboral> rrhhLaboralList;

    public RrhhPuestoFuncional() {
    }

    public RrhhPuestoFuncional(BigDecimal puestoFuncional) {
        this.puestoFuncional = puestoFuncional;
    }

    public RrhhPuestoFuncional(BigDecimal puestoFuncional, String usuarioInsert, Date fechaInsert) {
        this.puestoFuncional = puestoFuncional;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public BigDecimal getPuestoFuncional() {
        return puestoFuncional;
    }

    public void setPuestoFuncional(BigDecimal puestoFuncional) {
        this.puestoFuncional = puestoFuncional;
    }

    public String getNombreFuncional() {
        return nombreFuncional;
    }

    public void setNombreFuncional(String nombreFuncional) {
        this.nombreFuncional = nombreFuncional;
    }

    public String getCodigoFuncional() {
        return codigoFuncional;
    }

    public void setCodigoFuncional(String codigoFuncional) {
        this.codigoFuncional = codigoFuncional;
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

    public BigInteger getFuncionalPadre() {
        return funcionalPadre;
    }

    public void setFuncionalPadre(BigInteger funcionalPadre) {
        this.funcionalPadre = funcionalPadre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaUpdate() {
        return fechaUpdate;
    }

    public void setFechaUpdate(Date fechaUpdate) {
        this.fechaUpdate = fechaUpdate;
    }


    public Long getCodigoFuncionalSag() {
        return codigoFuncionalSag;
    }

    public void setCodigoFuncionalSag(Long codigoFuncionalSag) {
        this.codigoFuncionalSag = codigoFuncionalSag;
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
        hash += (puestoFuncional != null ? puestoFuncional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhPuestoFuncional)) {
            return false;
        }
        RrhhPuestoFuncional other = (RrhhPuestoFuncional) object;
        if ((this.puestoFuncional == null && other.puestoFuncional != null) || (this.puestoFuncional != null && !this.puestoFuncional.equals(other.puestoFuncional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhPuestoFuncional[ puestoFuncional=" + puestoFuncional + " ]";
    }

    public RrhhCategoriaPuesto getIdCategoriaPuesto() {
        return idCategoriaPuesto;
    }

    public void setIdCategoriaPuesto(RrhhCategoriaPuesto idCategoriaPuesto) {
        this.idCategoriaPuesto = idCategoriaPuesto;
    }
    
}
