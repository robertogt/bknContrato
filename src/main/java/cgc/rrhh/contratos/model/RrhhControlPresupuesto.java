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
@Table(name = "RRHH_CONTROL_PRESUPUESTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhControlPresupuesto.findAll", query = "SELECT r FROM RrhhControlPresupuesto r")
    , @NamedQuery(name = "RrhhControlPresupuesto.findByIdControlPresupuesto", query = "SELECT r FROM RrhhControlPresupuesto r WHERE r.idControlPresupuesto = :idControlPresupuesto")
    , @NamedQuery(name = "RrhhControlPresupuesto.findByFechaDocumentoApoyo", query = "SELECT r FROM RrhhControlPresupuesto r WHERE r.fechaDocumentoApoyo = :fechaDocumentoApoyo")
    , @NamedQuery(name = "RrhhControlPresupuesto.findByMonto", query = "SELECT r FROM RrhhControlPresupuesto r WHERE r.monto = :monto")
    , @NamedQuery(name = "RrhhControlPresupuesto.findByAnioRenglon", query = "SELECT r FROM RrhhControlPresupuesto r WHERE r.anio = :anio AND r.idRenglonPresupuesto.renglon = :renglon AND r.estado = 'A' ORDER BY r.fechaInsert ASC")
    , @NamedQuery(name = "RrhhControlPresupuesto.findByEstado", query = "SELECT r FROM RrhhControlPresupuesto r WHERE r.estado = :estado")
    , @NamedQuery(name = "RrhhControlPresupuesto.findByUsuarioInsert", query = "SELECT r FROM RrhhControlPresupuesto r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhControlPresupuesto.findByFechaInsert", query = "SELECT r FROM RrhhControlPresupuesto r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhControlPresupuesto.findByUsuarioUpdate", query = "SELECT r FROM RrhhControlPresupuesto r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhControlPresupuesto.findByFechaUpdate", query = "SELECT r FROM RrhhControlPresupuesto r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhControlPresupuesto.findByObservaciones", query = "SELECT r FROM RrhhControlPresupuesto r WHERE r.observaciones = :observaciones")
    , @NamedQuery(name = "RrhhControlPresupuesto.findByMotivoAnulacion", query = "SELECT r FROM RrhhControlPresupuesto r WHERE r.motivoAnulacion = :motivoAnulacion")
    , @NamedQuery(name = "RrhhControlPresupuesto.findByIdentificadorDocumentoApoyo", query = "SELECT r FROM RrhhControlPresupuesto r WHERE r.identificadorDocumentoApoyo = :identificadorDocumentoApoyo")})
public class RrhhControlPresupuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CONTROL_PRESUPUESTO")
    private BigDecimal idControlPresupuesto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_DOCUMENTO_APOYO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDocumentoApoyo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO")
    private BigDecimal monto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ANIO")
    private String anio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ESTADO")
    private String estado;
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
    @Size(max = 1000)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Size(max = 1000)
    @Column(name = "MOTIVO_ANULACION")
    private String motivoAnulacion;
    @Size(max = 500)
    @Column(name = "IDENTIFICADOR_DOCUMENTO_APOYO")
    private String identificadorDocumentoApoyo;
    @JoinColumn(name = "ID_FUENTE_FINANCIAMIENTO", referencedColumnName = "FUENTE")
    @ManyToOne(optional = false)
    private RrhhFuente idFuenteFinanciamiento;
    @JoinColumn(name = "ID_RENGLON_PRESUPUESTO", referencedColumnName = "RENGLON")
    @ManyToOne(optional = false)
    private RrhhRenglon idRenglonPresupuesto;

    public RrhhControlPresupuesto() {
    }

    public RrhhControlPresupuesto(BigDecimal idControlPresupuesto) {
        this.idControlPresupuesto = idControlPresupuesto;
    }

    public RrhhControlPresupuesto(BigDecimal idControlPresupuesto, Date fechaDocumentoApoyo, BigDecimal monto, String anio, String estado, String usuarioInsert, Date fechaInsert) {
        this.idControlPresupuesto = idControlPresupuesto;
        this.fechaDocumentoApoyo = fechaDocumentoApoyo;
        this.monto = monto;
        this.anio = anio;
        this.estado = estado;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public BigDecimal getIdControlPresupuesto() {
        return idControlPresupuesto;
    }

    public void setIdControlPresupuesto(BigDecimal idControlPresupuesto) {
        this.idControlPresupuesto = idControlPresupuesto;
    }

    public Date getFechaDocumentoApoyo() {
        return fechaDocumentoApoyo;
    }

    public void setFechaDocumentoApoyo(Date fechaDocumentoApoyo) {
        this.fechaDocumentoApoyo = fechaDocumentoApoyo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getMotivoAnulacion() {
        return motivoAnulacion;
    }

    public void setMotivoAnulacion(String motivoAnulacion) {
        this.motivoAnulacion = motivoAnulacion;
    }

    public String getIdentificadorDocumentoApoyo() {
        return identificadorDocumentoApoyo;
    }

    public void setIdentificadorDocumentoApoyo(String identificadorDocumentoApoyo) {
        this.identificadorDocumentoApoyo = identificadorDocumentoApoyo;
    }

    public RrhhFuente getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }

    public void setIdFuenteFinanciamiento(RrhhFuente idFuenteFinanciamiento) {
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
    }

    public RrhhRenglon getIdRenglonPresupuesto() {
        return idRenglonPresupuesto;
    }

    public void setIdRenglonPresupuesto(RrhhRenglon idRenglonPresupuesto) {
        this.idRenglonPresupuesto = idRenglonPresupuesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idControlPresupuesto != null ? idControlPresupuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhControlPresupuesto)) {
            return false;
        }
        RrhhControlPresupuesto other = (RrhhControlPresupuesto) object;
        if ((this.idControlPresupuesto == null && other.idControlPresupuesto != null) || (this.idControlPresupuesto != null && !this.idControlPresupuesto.equals(other.idControlPresupuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhControlPresupuesto[ idControlPresupuesto=" + idControlPresupuesto + " ]";
    }
    
}
