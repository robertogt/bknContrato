/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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
@Table(name = "RRHH_ACTIVIDAD_CONTRATO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhActividadContrato.findAll", query = "SELECT r FROM RrhhActividadContrato r")
    , @NamedQuery(name = "RrhhActividadContrato.findByIdActividadContrato", query = "SELECT r FROM RrhhActividadContrato r WHERE r.idActividadContrato = :idActividadContrato")
    , @NamedQuery(name = "RrhhActividadContrato.findByEstado", query = "SELECT r FROM RrhhActividadContrato r WHERE r.estado = :estado")
    , @NamedQuery(name = "RrhhActividadContrato.findByUsuarioInsert", query = "SELECT r FROM RrhhActividadContrato r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhActividadContrato.findByFechaInsert", query = "SELECT r FROM RrhhActividadContrato r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhActividadContrato.findByUsuarioUpdate", query = "SELECT r FROM RrhhActividadContrato r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhActividadContrato.findByFechaUpdate", query = "SELECT r FROM RrhhActividadContrato r WHERE r.fechaUpdate = :fechaUpdate")})
public class RrhhActividadContrato implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "bSequence", sequenceName = "SEQ_RRHH_ACTIVIDAD_CONTRATO", allocationSize = 1)
    @GeneratedValue(generator = "bSequence",strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_ACTIVIDAD_CONTRATO")
    private BigDecimal idActividadContrato;
    @Size(max = 2)
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
    @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID_PERFIL")
    @ManyToOne
    private RrhhPerfil idPerfil;
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDAD")
    @ManyToOne
    private RrhhActividad idActividad;
    @JoinColumn(name = "ID_CONTRATO", referencedColumnName = "ID_CONTRATO")
    @ManyToOne
    private RrhhContrato idContrato;
    
    public RrhhActividadContrato() {
    }

    public RrhhActividadContrato(BigDecimal idActividadContrato) {
        this.idActividadContrato = idActividadContrato;
    }

    public BigDecimal getIdActividadContrato() {
        return idActividadContrato;
    }

    public void setIdActividadContrato(BigDecimal idActividadContrato) {
        this.idActividadContrato = idActividadContrato;
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

    public RrhhPerfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(RrhhPerfil idPerfil) {
        this.idPerfil = idPerfil;
    }

    public RrhhActividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(RrhhActividad idActividad) {
        this.idActividad = idActividad;
    }

    public RrhhContrato getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(RrhhContrato idContrato) {
        this.idContrato = idContrato;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividadContrato != null ? idActividadContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhActividadContrato)) {
            return false;
        }
        RrhhActividadContrato other = (RrhhActividadContrato) object;
        if ((this.idActividadContrato == null && other.idActividadContrato != null) || (this.idActividadContrato != null && !this.idActividadContrato.equals(other.idActividadContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhActividadContrato[ idActividadContrato=" + idActividadContrato + " ]";
    }
    
}
