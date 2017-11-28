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
@Table(name = "RRHH_MOVIMIENTO_PRESUPUESTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhMovimientosPresupuesto.findAll", query = "SELECT r FROM RrhhMovimientosPresupuesto r")
    , @NamedQuery(name = "RrhhMovimientosPresupuesto.findByIdMovimientosPresupuesto", query = "SELECT r FROM RrhhMovimientosPresupuesto r WHERE r.idMovimientosPresupuesto = :idMovimientosPresupuesto")
    , @NamedQuery(name = "RrhhMovimientosPresupuesto.findByMonto", query = "SELECT r FROM RrhhMovimientosPresupuesto r WHERE r.monto = :monto")
    , @NamedQuery(name = "RrhhMovimientosPresupuesto.findByUsuarioInsert", query = "SELECT r FROM RrhhMovimientosPresupuesto r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhMovimientosPresupuesto.findByFechaInsert", query = "SELECT r FROM RrhhMovimientosPresupuesto r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhMovimientosPresupuesto.findByContrato", query = "SELECT r FROM RrhhMovimientosPresupuesto r WHERE r.idContrato.idContrato = :contrato order by r.fechaInsert desc")})
public class RrhhMovimientosPresupuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "eSequence", sequenceName = "SEQ_RRHH_MOVIMIENTOS_PRE", allocationSize = 1)
    @GeneratedValue(generator = "eSequence",strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_MOVIMIENTOS_PRESUPUESTO")
    private BigDecimal idMovimientosPresupuesto;
    @Column(name = "MONTO")
    private BigDecimal monto;
    @Size(max = 50)
    @Column(name = "USUARIO_INSERT")
    private String usuarioInsert;
    @Column(name = "FECHA_INSERT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInsert;
    @JoinColumn(name = "ID_CONTRATO", referencedColumnName = "ID_CONTRATO")
    @ManyToOne
    private RrhhContrato idContrato;
    @JoinColumn(name = "ID_CONTROL_PRESUPUESTO", referencedColumnName = "ID_CONTROL_PRESUPUESTO")
    @ManyToOne
    private RrhhControlPresupuesto idControlPresupuesto;

    public RrhhMovimientosPresupuesto() {
    }

    public RrhhMovimientosPresupuesto(BigDecimal idMovimientosPresupuesto) {
        this.idMovimientosPresupuesto = idMovimientosPresupuesto;
    }

    public BigDecimal getIdMovimientosPresupuesto() {
        return idMovimientosPresupuesto;
    }

    public void setIdMovimientosPresupuesto(BigDecimal idMovimientosPresupuesto) {
        this.idMovimientosPresupuesto = idMovimientosPresupuesto;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
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

    public RrhhContrato getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(RrhhContrato idContrato) {
        this.idContrato = idContrato;
    }

    public RrhhControlPresupuesto getIdControlPresupuesto() {
        return idControlPresupuesto;
    }

    public void setIdControlPresupuesto(RrhhControlPresupuesto idControlPresupuesto) {
        this.idControlPresupuesto = idControlPresupuesto;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovimientosPresupuesto != null ? idMovimientosPresupuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhMovimientosPresupuesto)) {
            return false;
        }
        RrhhMovimientosPresupuesto other = (RrhhMovimientosPresupuesto) object;
        if ((this.idMovimientosPresupuesto == null && other.idMovimientosPresupuesto != null) || (this.idMovimientosPresupuesto != null && !this.idMovimientosPresupuesto.equals(other.idMovimientosPresupuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhMovimientosPresupuesto[ idMovimientosPresupuesto=" + idMovimientosPresupuesto + " ]";
    }
    
}
