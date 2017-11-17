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
@Table(name = "RRHH_PUESTO_NOMINAL_CLASS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhPuestoNominalClass.findAll", query = "SELECT r FROM RrhhPuestoNominalClass r")
    , @NamedQuery(name = "RrhhPuestoNominalClass.findByPuestoNominal", query = "SELECT r FROM RrhhPuestoNominalClass r WHERE r.puestoNominal = :puestoNominal")
    , @NamedQuery(name = "RrhhPuestoNominalClass.findByNombre", query = "SELECT r FROM RrhhPuestoNominalClass r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhPuestoNominalClass.findByEstado", query = "SELECT r FROM RrhhPuestoNominalClass r WHERE r.estado = :estado")
    , @NamedQuery(name = "RrhhPuestoNominalClass.findByUsuarioInsert", query = "SELECT r FROM RrhhPuestoNominalClass r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhPuestoNominalClass.findByFechaInsert", query = "SELECT r FROM RrhhPuestoNominalClass r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhPuestoNominalClass.findByUsuarioUpdate", query = "SELECT r FROM RrhhPuestoNominalClass r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhPuestoNominalClass.findByFechaUpdate", query = "SELECT r FROM RrhhPuestoNominalClass r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhPuestoNominalClass.findBySueldoBase", query = "SELECT r FROM RrhhPuestoNominalClass r WHERE r.sueldoBase = :sueldoBase")
    , @NamedQuery(name = "RrhhPuestoNominalClass.findByCodigoPuestoSag", query = "SELECT r FROM RrhhPuestoNominalClass r WHERE r.codigoPuestoSag = :codigoPuestoSag")
    , @NamedQuery(name = "RrhhPuestoNominalClass.findByTipoPuesto", query = "SELECT r FROM RrhhPuestoNominalClass r WHERE r.tipoPuesto = :tipoPuesto")})
public class RrhhPuestoNominalClass implements Serializable {

    @OneToMany(mappedBy = "puestoNominal")
    private List<RrhhPuestoNominal> rrhhPuestoNominalList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PUESTO_NOMINAL")
    private BigDecimal puestoNominal;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 50)
    @Column(name = "USUARIO_INSERT")
    private String usuarioInsert;
    @Column(name = "FECHA_INSERT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInsert;
    @Size(max = 50)
    @Column(name = "USUARIO_UPDATE")
    private String usuarioUpdate;
    @Column(name = "FECHA_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUpdate;
    @Column(name = "SUELDO_BASE")
    private BigDecimal sueldoBase;
    @Column(name = "CODIGO_PUESTO_SAG")
    private Long codigoPuestoSag;
    @Size(max = 1)
    @Column(name = "TIPO_PUESTO")
    private String tipoPuesto;
    @OneToMany(mappedBy = "puestoNominal")
    private List<RrhhGuatenominas> rrhhGuatenominasList;
    @OneToMany(mappedBy = "puestoNominal")
    private List<RrhhLaboral> rrhhLaboralList;

    public RrhhPuestoNominalClass() {
    }

    public RrhhPuestoNominalClass(BigDecimal puestoNominal) {
        this.puestoNominal = puestoNominal;
    }

    public BigDecimal getPuestoNominal() {
        return puestoNominal;
    }

    public void setPuestoNominal(BigDecimal puestoNominal) {
        this.puestoNominal = puestoNominal;
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

    public BigDecimal getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(BigDecimal sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public Long getCodigoPuestoSag() {
        return codigoPuestoSag;
    }

    public void setCodigoPuestoSag(Long codigoPuestoSag) {
        this.codigoPuestoSag = codigoPuestoSag;
    }

    public String getTipoPuesto() {
        return tipoPuesto;
    }

    public void setTipoPuesto(String tipoPuesto) {
        this.tipoPuesto = tipoPuesto;
    }

    @XmlTransient
    public List<RrhhGuatenominas> getRrhhGuatenominasList() {
        return rrhhGuatenominasList;
    }

    public void setRrhhGuatenominasList(List<RrhhGuatenominas> rrhhGuatenominasList) {
        this.rrhhGuatenominasList = rrhhGuatenominasList;
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
        hash += (puestoNominal != null ? puestoNominal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhPuestoNominalClass)) {
            return false;
        }
        RrhhPuestoNominalClass other = (RrhhPuestoNominalClass) object;
        if ((this.puestoNominal == null && other.puestoNominal != null) || (this.puestoNominal != null && !this.puestoNominal.equals(other.puestoNominal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhPuestoNominalClass[ puestoNominal=" + puestoNominal + " ]";
    }

    @XmlTransient
    public List<RrhhPuestoNominal> getRrhhPuestoNominalList() {
        return rrhhPuestoNominalList;
    }

    public void setRrhhPuestoNominalList(List<RrhhPuestoNominal> rrhhPuestoNominalList) {
        this.rrhhPuestoNominalList = rrhhPuestoNominalList;
    }
    
}
