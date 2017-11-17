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
@Table(name = "RRHH_UBICACION_NOMINAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhUbicacionNominal.findAll", query = "SELECT r FROM RrhhUbicacionNominal r")
    , @NamedQuery(name = "RrhhUbicacionNominal.findByUbicacionNominal", query = "SELECT r FROM RrhhUbicacionNominal r WHERE r.ubicacionNominal = :ubicacionNominal")
    , @NamedQuery(name = "RrhhUbicacionNominal.findByNombre", query = "SELECT r FROM RrhhUbicacionNominal r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhUbicacionNominal.findByCodigoUbicacion", query = "SELECT r FROM RrhhUbicacionNominal r WHERE r.codigoUbicacion = :codigoUbicacion")
    , @NamedQuery(name = "RrhhUbicacionNominal.findByUsuarioInsert", query = "SELECT r FROM RrhhUbicacionNominal r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhUbicacionNominal.findByFechaInsert", query = "SELECT r FROM RrhhUbicacionNominal r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhUbicacionNominal.findByUsuarioUpdate", query = "SELECT r FROM RrhhUbicacionNominal r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhUbicacionNominal.findByFechaUpdate", query = "SELECT r FROM RrhhUbicacionNominal r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhUbicacionNominal.findByEstado", query = "SELECT r FROM RrhhUbicacionNominal r WHERE r.estado = :estado")
    , @NamedQuery(name = "RrhhUbicacionNominal.findByControlInterno", query = "SELECT r FROM RrhhUbicacionNominal r WHERE r.controlInterno = :controlInterno")
    , @NamedQuery(name = "RrhhUbicacionNominal.findByCodigoDeptoSag", query = "SELECT r FROM RrhhUbicacionNominal r WHERE r.codigoDeptoSag = :codigoDeptoSag")})
public class RrhhUbicacionNominal implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "UBICACION_NOMINAL")
    private BigDecimal ubicacionNominal;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 40)
    @Column(name = "CODIGO_UBICACION")
    private String codigoUbicacion;
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
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 4)
    @Column(name = "CONTROL_INTERNO")
    private String controlInterno;
    @Size(max = 4)
    @Column(name = "CODIGO_DEPTO_SAG")
    private String codigoDeptoSag;
    @OneToMany(mappedBy = "ubicacionNominal")
    private List<RrhhGuatenominas> rrhhGuatenominasList;
    @OneToMany(mappedBy = "ubicacionNominal")
    private List<RrhhLaboral> rrhhLaboralList;

    public RrhhUbicacionNominal() {
    }

    public RrhhUbicacionNominal(BigDecimal ubicacionNominal) {
        this.ubicacionNominal = ubicacionNominal;
    }

    public BigDecimal getUbicacionNominal() {
        return ubicacionNominal;
    }

    public void setUbicacionNominal(BigDecimal ubicacionNominal) {
        this.ubicacionNominal = ubicacionNominal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoUbicacion() {
        return codigoUbicacion;
    }

    public void setCodigoUbicacion(String codigoUbicacion) {
        this.codigoUbicacion = codigoUbicacion;
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

    public String getControlInterno() {
        return controlInterno;
    }

    public void setControlInterno(String controlInterno) {
        this.controlInterno = controlInterno;
    }

    public String getCodigoDeptoSag() {
        return codigoDeptoSag;
    }

    public void setCodigoDeptoSag(String codigoDeptoSag) {
        this.codigoDeptoSag = codigoDeptoSag;
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
        hash += (ubicacionNominal != null ? ubicacionNominal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhUbicacionNominal)) {
            return false;
        }
        RrhhUbicacionNominal other = (RrhhUbicacionNominal) object;
        if ((this.ubicacionNominal == null && other.ubicacionNominal != null) || (this.ubicacionNominal != null && !this.ubicacionNominal.equals(other.ubicacionNominal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhUbicacionNominal[ ubicacionNominal=" + ubicacionNominal + " ]";
    }
    
}
