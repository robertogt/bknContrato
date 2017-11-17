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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "RRHH_PUESTO_NOMINAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhPuestoNominal.findAll", query = "SELECT r FROM RrhhPuestoNominal r")
    , @NamedQuery(name = "RrhhPuestoNominal.findByIdNominal", query = "SELECT r FROM RrhhPuestoNominal r WHERE r.idNominal = :idNominal")
    , @NamedQuery(name = "RrhhPuestoNominal.findByNombre", query = "SELECT r FROM RrhhPuestoNominal r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhPuestoNominal.findByNominalPadre", query = "SELECT r FROM RrhhPuestoNominal r WHERE r.nominalPadre = :nominalPadre")
    , @NamedQuery(name = "RrhhPuestoNominal.findByCodigoNominal", query = "SELECT r FROM RrhhPuestoNominal r WHERE r.codigoNominal = :codigoNominal")
    , @NamedQuery(name = "RrhhPuestoNominal.findByUsuarioInsert", query = "SELECT r FROM RrhhPuestoNominal r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhPuestoNominal.findByFechaInsert", query = "SELECT r FROM RrhhPuestoNominal r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhPuestoNominal.findByUsuarioUpdate", query = "SELECT r FROM RrhhPuestoNominal r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhPuestoNominal.findByFechaUpdate", query = "SELECT r FROM RrhhPuestoNominal r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhPuestoNominal.findBySalarioBase", query = "SELECT r FROM RrhhPuestoNominal r WHERE r.salarioBase = :salarioBase")
    , @NamedQuery(name = "RrhhPuestoNominal.findByPartida", query = "SELECT r FROM RrhhPuestoNominal r WHERE r.partida = :partida")
    , @NamedQuery(name = "RrhhPuestoNominal.findByEstado", query = "SELECT r FROM RrhhPuestoNominal r WHERE r.estado = :estado")})
public class RrhhPuestoNominal implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_NOMINAL")
    private BigDecimal idNominal;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "NOMINAL_PADRE")
    private BigInteger nominalPadre;
    @Size(max = 50)
    @Column(name = "CODIGO_NOMINAL")
    private String codigoNominal;
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
    @Column(name = "SALARIO_BASE")
    private BigDecimal salarioBase;
    @Size(max = 40)
    @Column(name = "PARTIDA")
    private String partida;
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @JoinColumn(name = "ESPECIALIDAD", referencedColumnName = "ESPECIALIDAD")
    @ManyToOne
    private RrhhEspecialidad especialidad;
    @JoinColumn(name = "PUESTO_NOMINAL", referencedColumnName = "PUESTO_NOMINAL")
    @ManyToOne
    private RrhhPuestoNominalClass puestoNominal;

    public RrhhPuestoNominal() {
    }

    public RrhhPuestoNominal(BigDecimal idNominal) {
        this.idNominal = idNominal;
    }

    public RrhhPuestoNominal(BigDecimal idNominal, String usuarioInsert, Date fechaInsert) {
        this.idNominal = idNominal;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public BigDecimal getIdNominal() {
        return idNominal;
    }

    public void setIdNominal(BigDecimal idNominal) {
        this.idNominal = idNominal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getNominalPadre() {
        return nominalPadre;
    }

    public void setNominalPadre(BigInteger nominalPadre) {
        this.nominalPadre = nominalPadre;
    }

    public String getCodigoNominal() {
        return codigoNominal;
    }

    public void setCodigoNominal(String codigoNominal) {
        this.codigoNominal = codigoNominal;
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

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(BigDecimal salarioBase) {
        this.salarioBase = salarioBase;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public RrhhEspecialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(RrhhEspecialidad especialidad) {
        this.especialidad = especialidad;
    }

    public RrhhPuestoNominalClass getPuestoNominal() {
        return puestoNominal;
    }

    public void setPuestoNominal(RrhhPuestoNominalClass puestoNominal) {
        this.puestoNominal = puestoNominal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNominal != null ? idNominal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhPuestoNominal)) {
            return false;
        }
        RrhhPuestoNominal other = (RrhhPuestoNominal) object;
        if ((this.idNominal == null && other.idNominal != null) || (this.idNominal != null && !this.idNominal.equals(other.idNominal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhPuestoNominal[ idNominal=" + idNominal + " ]";
    }
    
}
