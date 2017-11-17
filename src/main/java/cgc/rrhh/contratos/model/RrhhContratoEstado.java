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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "RRHH_CONTRATO_ESTADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhContratoEstado.findAll", query = "SELECT r FROM RrhhContratoEstado r")
    , @NamedQuery(name = "RrhhContratoEstado.findByIdContratoEstado", query = "SELECT r FROM RrhhContratoEstado r WHERE r.idContratoEstado = :idContratoEstado")
    , @NamedQuery(name = "RrhhContratoEstado.findByIdContrato", query = "SELECT r FROM RrhhContratoEstado r WHERE r.idContrato = :idContrato")
    , @NamedQuery(name = "RrhhContratoEstado.findByObservacion", query = "SELECT r FROM RrhhContratoEstado r WHERE r.observacion = :observacion")
    , @NamedQuery(name = "RrhhContratoEstado.findByUsuarioInsert", query = "SELECT r FROM RrhhContratoEstado r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhContratoEstado.findByFechaInsert", query = "SELECT r FROM RrhhContratoEstado r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhContratoEstado.findByUsuarioUpdate", query = "SELECT r FROM RrhhContratoEstado r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhContratoEstado.findByFechaUpdate", query = "SELECT r FROM RrhhContratoEstado r WHERE r.fechaUpdate = :fechaUpdate")})
public class RrhhContratoEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "gSequence", sequenceName = "SEQ_RRHH_CONTRATO_ESTADO", allocationSize = 1)
    @GeneratedValue(generator = "gSequence",strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_CONTRATO_ESTADO")
    private BigDecimal idContratoEstado;
    @Basic(optional = false)
    @JoinColumn(name = "ID_CONTRATO", referencedColumnName = "ID_CONTRATO")
    @ManyToOne(optional = false)
    private RrhhContrato idContrato;
    @Basic(optional = true)
    @Size(min = 1, max = 500)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Basic(optional = true)
    @Lob
    @Column(name = "DOCUMENTO")
    private byte[] documento;
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
    @JoinColumn(name = "ID_CATALOGO_ESTADO", referencedColumnName = "ID_CATALOGO_ESTADO")
    @ManyToOne(optional = false)
    private RrhhCatalogoEstado idCatalogoEstado;

    public RrhhContratoEstado() {
    }

    public RrhhContratoEstado(BigDecimal idContratoEstado) {
        this.idContratoEstado = idContratoEstado;
    }

    public RrhhContratoEstado(BigDecimal idContratoEstado, BigInteger idContrato, String observacion, byte[] documento, String usuarioInsert, Date fechaInsert) {
        this.idContratoEstado = idContratoEstado;
        this.observacion = observacion;
        this.documento = documento;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public BigDecimal getIdContratoEstado() {
        return idContratoEstado;
    }

    public void setIdContratoEstado(BigDecimal idContratoEstado) {
        this.idContratoEstado = idContratoEstado;
    }

    public RrhhContrato getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(RrhhContrato idContrato) {
        this.idContrato = idContrato;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
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

    public RrhhCatalogoEstado getIdCatalogoEstado() {
        return idCatalogoEstado;
    }

    public void setIdCatalogoEstado(RrhhCatalogoEstado idCatalogoEstado) {
        this.idCatalogoEstado = idCatalogoEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContratoEstado != null ? idContratoEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhContratoEstado)) {
            return false;
        }
        RrhhContratoEstado other = (RrhhContratoEstado) object;
        if ((this.idContratoEstado == null && other.idContratoEstado != null) || (this.idContratoEstado != null && !this.idContratoEstado.equals(other.idContratoEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhContratoEstado[ idContratoEstado=" + idContratoEstado + " ]";
    }
    
}