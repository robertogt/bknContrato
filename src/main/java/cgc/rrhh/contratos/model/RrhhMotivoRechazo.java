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
@Table(name = "RRHH_MOTIVO_RECHAZO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhMotivoRechazo.findAll", query = "SELECT r FROM RrhhMotivoRechazo r")
    , @NamedQuery(name = "RrhhMotivoRechazo.findByIdMotivoRechazo", query = "SELECT r FROM RrhhMotivoRechazo r WHERE r.idMotivoRechazo = :idMotivoRechazo")
    , @NamedQuery(name = "RrhhMotivoRechazo.findByUsuarioInsert", query = "SELECT r FROM RrhhMotivoRechazo r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhMotivoRechazo.findByFechaInsert", query = "SELECT r FROM RrhhMotivoRechazo r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhMotivoRechazo.findByIdContratoEstado", query = "SELECT a FROM RrhhMotivoRechazo r INNER JOIN r.idCatalogoMotivoRechazo a WHERE r.contratoEstado.idContratoEstado = :contratoEstado")})
public class RrhhMotivoRechazo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "kSequence", sequenceName = "SEQ_RRHH_MOTIVO_RECHAZO", allocationSize = 1)
    @GeneratedValue(generator = "kSequence",strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_MOTIVO_RECHAZO")
    private BigDecimal idMotivoRechazo;
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
    @JoinColumn(name = "ID_CATALOGO_MOTIVO_RECHAZO", referencedColumnName = "ID_CATALOGO_MOTIVO_RECHAZO")
    @ManyToOne
    private RrhhCatalogoMotivoRechazo idCatalogoMotivoRechazo;
    @JoinColumn(name = "ID_CONTRATO_ESTADO", referencedColumnName = "ID_CONTRATO_ESTADO")
    @ManyToOne
    private RrhhContratoEstado contratoEstado;

    public RrhhMotivoRechazo() {
    }

    public RrhhMotivoRechazo(BigDecimal idMotivoRechazo) {
        this.idMotivoRechazo = idMotivoRechazo;
    }

    public RrhhMotivoRechazo(BigDecimal idMotivoRechazo, String usuarioInsert, Date fechaInsert) {
        this.idMotivoRechazo = idMotivoRechazo;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public RrhhContratoEstado getContratoEstado() {
        return contratoEstado;
    }

    public void setContratoEstado(RrhhContratoEstado contratoEstado) {
        this.contratoEstado = contratoEstado;
    }
    
    

    public BigDecimal getIdMotivoRechazo() {
        return idMotivoRechazo;
    }

    public void setIdMotivoRechazo(BigDecimal idMotivoRechazo) {
        this.idMotivoRechazo = idMotivoRechazo;
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

    public RrhhCatalogoMotivoRechazo getIdCatalogoMotivoRechazo() {
        return idCatalogoMotivoRechazo;
    }

    public void setIdCatalogoMotivoRechazo(RrhhCatalogoMotivoRechazo idCatalogoMotivoRechazo) {
        this.idCatalogoMotivoRechazo = idCatalogoMotivoRechazo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMotivoRechazo != null ? idMotivoRechazo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhMotivoRechazo)) {
            return false;
        }
        RrhhMotivoRechazo other = (RrhhMotivoRechazo) object;
        if ((this.idMotivoRechazo == null && other.idMotivoRechazo != null) || (this.idMotivoRechazo != null && !this.idMotivoRechazo.equals(other.idMotivoRechazo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhMotivoRechazo[ idMotivoRechazo=" + idMotivoRechazo + " ]";
    }
    
}
