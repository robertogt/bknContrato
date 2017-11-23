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
@Table(name = "RRHH_HISTORICO_LABORAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhHistoricoLaboral.findAll", query = "SELECT r FROM RrhhHistoricoLaboral r")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByHistoricoLaboral", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.historicoLaboral = :historicoLaboral")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByUsuarioInsert", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByFechaInsert", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByUsuarioUpdate", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByFechaUpdate", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByPuestoNominal", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.puestoNominal = :puestoNominal")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByFechaInicio", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByFechaFin", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.fechaFin = :fechaFin")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByObservacion", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.observacion = :observacion")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByEstado", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.estado = :estado")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByNumeroDocumento", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.numeroDocumento = :numeroDocumento")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByNumeroContrato", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.numeroContrato = :numeroContrato")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByTipoServicios", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.tipoServicios = :tipoServicios")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByPlazoContrato", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.plazoContrato = :plazoContrato")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByFechaDel", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.fechaDel = :fechaDel")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByFechaAl", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.fechaAl = :fechaAl")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByDocumentoMovimiento", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.documentoMovimiento = :documentoMovimiento")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByFechaCambioTipoMovimiento", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.fechaCambioTipoMovimiento = :fechaCambioTipoMovimiento")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findBySueldoBase", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.sueldoBase = :sueldoBase")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByFechaInsertHistorico", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.fechaInsertHistorico = :fechaInsertHistorico")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByUsuarioInsertHistorico", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.usuarioInsertHistorico = :usuarioInsertHistorico")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByBonoProfesional", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.bonoProfesional = :bonoProfesional")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByHonorario", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.honorario = :honorario")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByNumeroFianza", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.numeroFianza = :numeroFianza")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByEdificio", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.edificio = :edificio")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByEstadoHistorico", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.estadoHistorico = :estadoHistorico")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByUsuarioUpdateHistorico", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.usuarioUpdateHistorico = :usuarioUpdateHistorico")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByFechaUpdateHistorico", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.fechaUpdateHistorico = :fechaUpdateHistorico")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByIdContrato", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.idContrato = :idContrato")
    , @NamedQuery(name = "RrhhHistoricoLaboral.findByColumn2", query = "SELECT r FROM RrhhHistoricoLaboral r WHERE r.column2 = :column2")})
public class RrhhHistoricoLaboral implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "HISTORICO_LABORAL")
    private BigDecimal historicoLaboral;
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
    @JoinColumn(name = "PUESTO_NOMINAL", referencedColumnName = "PUESTO_NOMINAL")
    @ManyToOne(optional = false)
    private RrhhPuestoNominalClass puestoNominal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Size(max = 4000)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;
    @Size(max = 100)
    @Column(name = "NUMERO_CONTRATO")
    private String numeroContrato;
    @Size(max = 1)
    @Column(name = "TIPO_SERVICIOS")
    private String tipoServicios;
    @Size(max = 20)
    @Column(name = "PLAZO_CONTRATO")
    private String plazoContrato;
    @Column(name = "FECHA_DEL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDel;
    @Column(name = "FECHA_AL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAl;
    @Size(max = 25)
    @Column(name = "DOCUMENTO_MOVIMIENTO")
    private String documentoMovimiento;
    @Column(name = "FECHA_CAMBIO_TIPO_MOVIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambioTipoMovimiento;
    @Column(name = "SUELDO_BASE")
    private BigDecimal sueldoBase;
    @Column(name = "FECHA_INSERT_HISTORICO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInsertHistorico;
    @Size(max = 50)
    @Column(name = "USUARIO_INSERT_HISTORICO")
    private String usuarioInsertHistorico;
    @Size(max = 1)
    @Column(name = "BONO_PROFESIONAL")
    private String bonoProfesional;
    @Column(name = "HONORARIO")
    private BigDecimal honorario;
    @Size(max = 30)
    @Column(name = "NUMERO_FIANZA")
    private String numeroFianza;
    @JoinColumn(name = "EDIFICIO", referencedColumnName = "EDIFICIO")
    @ManyToOne
    private RrhhEdificio edificio;
    @Size(max = 1)
    @Column(name = "ESTADO_HISTORICO")
    private String estadoHistorico;
    @Size(max = 50)
    @Column(name = "USUARIO_UPDATE_HISTORICO")
    private String usuarioUpdateHistorico;
    @Column(name = "FECHA_UPDATE_HISTORICO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUpdateHistorico;
    @JoinColumn(name = "ID_CONTRATO", referencedColumnName = "ID_CONTRATO")
    @ManyToOne
    private RrhhContrato idContrato;
    
    @JoinColumn(name = "ID_RUE", referencedColumnName = "ID_RUE")
    @ManyToOne(optional = false)
    private RrhhRue idRue;
    
    @JoinColumn(name = "FUENTE", referencedColumnName = "FUENTE")
    @ManyToOne(optional = false)
    private RrhhFuente fuente;
    
    @JoinColumn(name = "LABORAL", referencedColumnName = "LABORAL")
    @ManyToOne(optional = false)
    private RrhhLaboral laboral;
    
    @JoinColumn(name = "PUESTO_FUNCIONAL", referencedColumnName = "PUESTO_FUNCIONAL")
    @ManyToOne(optional = false)
    private RrhhPuestoFuncional puestoFuncional;
    
    @JoinColumn(name = "RENGLON", referencedColumnName = "RENGLON")
    @ManyToOne(optional = false)
    private RrhhRenglon renglon;
    
    @JoinColumn(name = "TIPO_MOVIMIENTO", referencedColumnName = "TIPO_MOVIMIENTO")
    @ManyToOne(optional = false)
    private RrhhTipoMovimiento tipoMovimiento;
    
    @JoinColumn(name = "UBICACION_FUNCIONAL", referencedColumnName = "UBICACION_FUNCIONAL")
    @ManyToOne(optional = false)
    private RrhhUbicacionFuncional ubicacionFuncional;
    
    @JoinColumn(name = "UBICACION_NOMINAL", referencedColumnName = "UBICACION_NOMINAL")
    @ManyToOne(optional = false)
    private RrhhUbicacionNominal ubicacionNominal;
    
    @JoinColumn(name = "ID_NUMERO_PUESTO", referencedColumnName = "ID_NUMERO_PUESTO")
    @ManyToOne
    private RrhhGuatenominas idNumeroPuesto;

    public RrhhHistoricoLaboral() {
    }

    public RrhhHistoricoLaboral(BigDecimal historicoLaboral) {
        this.historicoLaboral = historicoLaboral;
    }

    public RrhhHistoricoLaboral(BigDecimal historicoLaboral, String usuarioInsert, Date fechaInsert, Date fechaInicio, String estado, String numeroDocumento) {
        this.historicoLaboral = historicoLaboral;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
        this.numeroDocumento = numeroDocumento;
    }

    public BigDecimal getHistoricoLaboral() {
        return historicoLaboral;
    }

    public void setHistoricoLaboral(BigDecimal historicoLaboral) {
        this.historicoLaboral = historicoLaboral;
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

    public RrhhPuestoNominalClass getPuestoNominal() {
        return puestoNominal;
    }

    public void setPuestoNominal(RrhhPuestoNominalClass puestoNominal) {
        this.puestoNominal = puestoNominal;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getTipoServicios() {
        return tipoServicios;
    }

    public void setTipoServicios(String tipoServicios) {
        this.tipoServicios = tipoServicios;
    }

    public String getPlazoContrato() {
        return plazoContrato;
    }

    public void setPlazoContrato(String plazoContrato) {
        this.plazoContrato = plazoContrato;
    }

    public Date getFechaDel() {
        return fechaDel;
    }

    public void setFechaDel(Date fechaDel) {
        this.fechaDel = fechaDel;
    }

    public Date getFechaAl() {
        return fechaAl;
    }

    public void setFechaAl(Date fechaAl) {
        this.fechaAl = fechaAl;
    }

    public String getDocumentoMovimiento() {
        return documentoMovimiento;
    }

    public void setDocumentoMovimiento(String documentoMovimiento) {
        this.documentoMovimiento = documentoMovimiento;
    }

    public Date getFechaCambioTipoMovimiento() {
        return fechaCambioTipoMovimiento;
    }

    public void setFechaCambioTipoMovimiento(Date fechaCambioTipoMovimiento) {
        this.fechaCambioTipoMovimiento = fechaCambioTipoMovimiento;
    }

    public BigDecimal getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(BigDecimal sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public Date getFechaInsertHistorico() {
        return fechaInsertHistorico;
    }

    public void setFechaInsertHistorico(Date fechaInsertHistorico) {
        this.fechaInsertHistorico = fechaInsertHistorico;
    }

    public String getUsuarioInsertHistorico() {
        return usuarioInsertHistorico;
    }

    public void setUsuarioInsertHistorico(String usuarioInsertHistorico) {
        this.usuarioInsertHistorico = usuarioInsertHistorico;
    }

    public String getBonoProfesional() {
        return bonoProfesional;
    }

    public void setBonoProfesional(String bonoProfesional) {
        this.bonoProfesional = bonoProfesional;
    }

    public BigDecimal getHonorario() {
        return honorario;
    }

    public void setHonorario(BigDecimal honorario) {
        this.honorario = honorario;
    }

    public String getNumeroFianza() {
        return numeroFianza;
    }

    public void setNumeroFianza(String numeroFianza) {
        this.numeroFianza = numeroFianza;
    }

    public RrhhEdificio getEdificio() {
        return edificio;
    }

    public void setEdificio(RrhhEdificio edificio) {
        this.edificio = edificio;
    }

    public String getEstadoHistorico() {
        return estadoHistorico;
    }

    public void setEstadoHistorico(String estadoHistorico) {
        this.estadoHistorico = estadoHistorico;
    }

    public String getUsuarioUpdateHistorico() {
        return usuarioUpdateHistorico;
    }

    public void setUsuarioUpdateHistorico(String usuarioUpdateHistorico) {
        this.usuarioUpdateHistorico = usuarioUpdateHistorico;
    }

    public Date getFechaUpdateHistorico() {
        return fechaUpdateHistorico;
    }

    public void setFechaUpdateHistorico(Date fechaUpdateHistorico) {
        this.fechaUpdateHistorico = fechaUpdateHistorico;
    }

    public RrhhContrato getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(RrhhContrato idContrato) {
        this.idContrato = idContrato;
    }

    public RrhhRue getIdRue() {
        return idRue;
    }

    public void setIdRue(RrhhRue idRue) {
        this.idRue = idRue;
    }

    public RrhhFuente getFuente() {
        return fuente;
    }

    public void setFuente(RrhhFuente fuente) {
        this.fuente = fuente;
    }

    public RrhhLaboral getLaboral() {
        return laboral;
    }

    public void setLaboral(RrhhLaboral laboral) {
        this.laboral = laboral;
    }

    public RrhhPuestoFuncional getPuestoFuncional() {
        return puestoFuncional;
    }

    public void setPuestoFuncional(RrhhPuestoFuncional puestoFuncional) {
        this.puestoFuncional = puestoFuncional;
    }

    public RrhhRenglon getRenglon() {
        return renglon;
    }

    public void setRenglon(RrhhRenglon renglon) {
        this.renglon = renglon;
    }

    public RrhhTipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(RrhhTipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public RrhhUbicacionFuncional getUbicacionFuncional() {
        return ubicacionFuncional;
    }

    public void setUbicacionFuncional(RrhhUbicacionFuncional ubicacionFuncional) {
        this.ubicacionFuncional = ubicacionFuncional;
    }

    public RrhhUbicacionNominal getUbicacionNominal() {
        return ubicacionNominal;
    }

    public void setUbicacionNominal(RrhhUbicacionNominal ubicacionNominal) {
        this.ubicacionNominal = ubicacionNominal;
    }

    public RrhhGuatenominas getIdNumeroPuesto() {
        return idNumeroPuesto;
    }

    public void setIdNumeroPuesto(RrhhGuatenominas idNumeroPuesto) {
        this.idNumeroPuesto = idNumeroPuesto;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historicoLaboral != null ? historicoLaboral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhHistoricoLaboral)) {
            return false;
        }
        RrhhHistoricoLaboral other = (RrhhHistoricoLaboral) object;
        if ((this.historicoLaboral == null && other.historicoLaboral != null) || (this.historicoLaboral != null && !this.historicoLaboral.equals(other.historicoLaboral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhHistoricoLaboral[ historicoLaboral=" + historicoLaboral + " ]";
    }
    
}
