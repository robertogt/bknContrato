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
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
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
@Table(name = "RRHH_LABORAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhLaboral.findAll", query = "SELECT r FROM RrhhLaboral r")
    , @NamedQuery(name = "RrhhLaboral.findByLaboral", query = "SELECT r FROM RrhhLaboral r WHERE r.laboral = :laboral")
    , @NamedQuery(name = "RrhhLaboral.findByUsuarioInsert", query = "SELECT r FROM RrhhLaboral r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhLaboral.findByFechaInsert", query = "SELECT r FROM RrhhLaboral r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhLaboral.findByUsuarioUpdate", query = "SELECT r FROM RrhhLaboral r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhLaboral.findByFechaUpdate", query = "SELECT r FROM RrhhLaboral r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhLaboral.findByFechaInicio", query = "SELECT r FROM RrhhLaboral r WHERE r.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "RrhhLaboral.findByFechaFin", query = "SELECT r FROM RrhhLaboral r WHERE r.fechaFin = :fechaFin")
    , @NamedQuery(name = "RrhhLaboral.findByObservacion", query = "SELECT r FROM RrhhLaboral r WHERE r.observacion = :observacion")
    , @NamedQuery(name = "RrhhLaboral.findByEstado", query = "SELECT r FROM RrhhLaboral r WHERE r.estado = :estado")
    , @NamedQuery(name = "RrhhLaboral.findByNumeroDocumento", query = "SELECT r FROM RrhhLaboral r WHERE r.numeroDocumento = :numeroDocumento")
    , @NamedQuery(name = "RrhhLaboral.findByNumeroContrato", query = "SELECT r FROM RrhhLaboral r WHERE r.numeroContrato = :numeroContrato")
    , @NamedQuery(name = "RrhhLaboral.findByTipoServicios", query = "SELECT r FROM RrhhLaboral r WHERE r.tipoServicios = :tipoServicios")
    , @NamedQuery(name = "RrhhLaboral.findByPlazoContrato", query = "SELECT r FROM RrhhLaboral r WHERE r.plazoContrato = :plazoContrato")
    , @NamedQuery(name = "RrhhLaboral.findByFechaDel", query = "SELECT r FROM RrhhLaboral r WHERE r.fechaDel = :fechaDel")
    , @NamedQuery(name = "RrhhLaboral.findByFechaAl", query = "SELECT r FROM RrhhLaboral r WHERE r.fechaAl = :fechaAl")
    , @NamedQuery(name = "RrhhLaboral.findByDocumentoMovimiento", query = "SELECT r FROM RrhhLaboral r WHERE r.documentoMovimiento = :documentoMovimiento")
    , @NamedQuery(name = "RrhhLaboral.findByFechaCambioTipoMovimiento", query = "SELECT r FROM RrhhLaboral r WHERE r.fechaCambioTipoMovimiento = :fechaCambioTipoMovimiento")
    , @NamedQuery(name = "RrhhLaboral.findBySueldoBase", query = "SELECT r FROM RrhhLaboral r WHERE r.sueldoBase = :sueldoBase")
    , @NamedQuery(name = "RrhhLaboral.findByBonoProfesional", query = "SELECT r FROM RrhhLaboral r WHERE r.bonoProfesional = :bonoProfesional")
    , @NamedQuery(name = "RrhhLaboral.findByHonorario", query = "SELECT r FROM RrhhLaboral r WHERE r.honorario = :honorario")
    , @NamedQuery(name = "RrhhLaboral.findByNumeroFianza", query = "SELECT r FROM RrhhLaboral r WHERE r.numeroFianza = :numeroFianza")
    , @NamedQuery(name = "RrhhLaboral.findByRue", query = "SELECT r FROM RrhhLaboral r WHERE r.idRue.idRue = :rue ORDER BY r.fechaInsert DESC ")})

public class RrhhLaboral implements Serializable {

    

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "dSequence", sequenceName = "SEQ_RRHH_LABORAL", allocationSize = 1)
    @GeneratedValue(generator = "dSequence",strategy = GenerationType.SEQUENCE)
    @Column(name = "LABORAL")
    private BigDecimal laboral;
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
    @JoinColumn(name = "FUENTE", referencedColumnName = "FUENTE")
    @ManyToOne(optional = false)
    private RrhhFuente fuente;
    @JoinColumn(name = "ID_NUMERO_PUESTO", referencedColumnName = "ID_NUMERO_PUESTO")
    @ManyToOne
    private RrhhGuatenominas idNumeroPuesto;
    @JoinColumn(name = "PUESTO_FUNCIONAL", referencedColumnName = "PUESTO_FUNCIONAL")
    @ManyToOne(optional = false)
    private RrhhPuestoFuncional puestoFuncional;
    @JoinColumn(name = "PUESTO_NOMINAL", referencedColumnName = "PUESTO_NOMINAL")
    @ManyToOne
    private RrhhPuestoNominalClass puestoNominal;
    @JoinColumn(name = "RENGLON", referencedColumnName = "RENGLON")
    @ManyToOne(optional = false)
    private RrhhRenglon renglon;
    @JoinColumn(name = "ID_RUE", referencedColumnName = "ID_RUE")
    @ManyToOne(optional = false)
    private RrhhRue idRue;
    @JoinColumn(name = "TIPO_MOVIMIENTO", referencedColumnName = "TIPO_MOVIMIENTO")
    @ManyToOne(optional = false)
    private RrhhTipoMovimiento tipoMovimiento;
    @JoinColumn(name = "UBICACION_FUNCIONAL", referencedColumnName = "UBICACION_FUNCIONAL")
    @ManyToOne(optional = false)
    private RrhhUbicacionFuncional ubicacionFuncional;
    @JoinColumn(name = "UBICACION_NOMINAL", referencedColumnName = "UBICACION_NOMINAL")
    @ManyToOne
    private RrhhUbicacionNominal ubicacionNominal;
    @JoinColumn(name = "ID_CONTRATO", referencedColumnName = "ID_CONTRATO")
    @ManyToOne
    private RrhhContrato idContrato;

    public RrhhLaboral() {
    }

    public RrhhLaboral(BigDecimal laboral) {
        this.laboral = laboral;
    }

    public RrhhLaboral(BigDecimal laboral, String usuarioInsert, Date fechaInsert, Date fechaInicio, String estado, String numeroDocumento) {
        this.laboral = laboral;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
        this.numeroDocumento = numeroDocumento;
    }

    public BigDecimal getLaboral() {
        return laboral;
    }

    public void setLaboral(BigDecimal laboral) {
        this.laboral = laboral;
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

    public RrhhFuente getFuente() {
        return fuente;
    }

    public void setFuente(RrhhFuente fuente) {
        this.fuente = fuente;
    }

    public RrhhGuatenominas getIdNumeroPuesto() {
        return idNumeroPuesto;
    }

    public void setIdNumeroPuesto(RrhhGuatenominas idNumeroPuesto) {
        this.idNumeroPuesto = idNumeroPuesto;
    }

    public RrhhPuestoFuncional getPuestoFuncional() {
        return puestoFuncional;
    }

    public void setPuestoFuncional(RrhhPuestoFuncional puestoFuncional) {
        this.puestoFuncional = puestoFuncional;
    }

    public RrhhPuestoNominalClass getPuestoNominal() {
        return puestoNominal;
    }

    public void setPuestoNominal(RrhhPuestoNominalClass puestoNominal) {
        this.puestoNominal = puestoNominal;
    }

    public RrhhRenglon getRenglon() {
        return renglon;
    }

    public void setRenglon(RrhhRenglon renglon) {
        this.renglon = renglon;
    }

    public RrhhRue getIdRue() {
        return idRue;
    }

    public void setIdRue(RrhhRue idRue) {
        this.idRue = idRue;
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
    
    public RrhhContrato getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(RrhhContrato idContrato) {
        this.idContrato = idContrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (laboral != null ? laboral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhLaboral)) {
            return false;
        }
        RrhhLaboral other = (RrhhLaboral) object;
        if ((this.laboral == null && other.laboral != null) || (this.laboral != null && !this.laboral.equals(other.laboral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhLaboral[ laboral=" + laboral + " ]";
    }


    
    
}
