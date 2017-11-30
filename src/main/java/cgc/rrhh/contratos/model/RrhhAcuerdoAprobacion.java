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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jrroquel
 */
@Entity
@Table(name = "RRHH_ACUERDO_APROBACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhAcuerdoAprobacion.findAll", query = "SELECT r FROM RrhhAcuerdoAprobacion r")
    , @NamedQuery(name = "RrhhAcuerdoAprobacion.findByIdAcuerdoAprobacion", query = "SELECT r FROM RrhhAcuerdoAprobacion r WHERE r.idAcuerdoAprobacion = :idAcuerdoAprobacion")
    , @NamedQuery(name = "RrhhAcuerdoAprobacion.findByNumeroAcuerdo", query = "SELECT r FROM RrhhAcuerdoAprobacion r WHERE r.numeroAcuerdo = :numeroAcuerdo")
    , @NamedQuery(name = "RrhhAcuerdoAprobacion.findByIdentificadorAcuerdo", query = "SELECT r FROM RrhhAcuerdoAprobacion r WHERE r.identificadorAcuerdo = :identificadorAcuerdo")
    , @NamedQuery(name = "RrhhAcuerdoAprobacion.findByObservaciones", query = "SELECT r FROM RrhhAcuerdoAprobacion r WHERE r.observaciones = :observaciones")
    , @NamedQuery(name = "RrhhAcuerdoAprobacion.findByEstado", query = "SELECT r FROM RrhhAcuerdoAprobacion r WHERE r.estado = :estado")
    , @NamedQuery(name = "RrhhAcuerdoAprobacion.findByUsuarioInsert", query = "SELECT r FROM RrhhAcuerdoAprobacion r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhAcuerdoAprobacion.findByFechaInsert", query = "SELECT r FROM RrhhAcuerdoAprobacion r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhAcuerdoAprobacion.findByUsuarioUpdate", query = "SELECT r FROM RrhhAcuerdoAprobacion r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhAcuerdoAprobacion.findByFechaUpdate", query = "SELECT r FROM RrhhAcuerdoAprobacion r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhAcuerdoAprobacion.findByRenglon", query = "SELECT r FROM RrhhAcuerdoAprobacion r WHERE r.renglon = :renglon")
    , @NamedQuery(name = "RrhhAcuerdoAprobacion.findByTipoServicios", query = "SELECT r FROM RrhhAcuerdoAprobacion r WHERE r.tipoServicios = :tipoServicios")
    , @NamedQuery(name = "RrhhAcuerdoAprobacion.findByAnio", query = "SELECT r FROM RrhhAcuerdoAprobacion r WHERE r.anio = :anio")})
public class RrhhAcuerdoAprobacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "jSequence", sequenceName = "SEQ_RRHH_ACUERDO_APROBACION", allocationSize = 1)
    @GeneratedValue(generator = "jSequence",strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_ACUERDO_APROBACION")
    private BigDecimal idAcuerdoAprobacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_ACUERDO")
    private BigInteger numeroAcuerdo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "IDENTIFICADOR_ACUERDO")
    private String identificadorAcuerdo;
    @Size(max = 1000)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USUARIO_INSERT")
    private String usuarioInsert;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INSERT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInsert;
    @Size(max = 100)
    @Column(name = "USUARIO_UPDATE")
    private String usuarioUpdate;
    @Column(name = "FECHA_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUpdate;
    @JoinColumn(name = "RENGLON", referencedColumnName = "RENGLON")
    @ManyToOne
    private RrhhRenglon renglon;
    @Size(max = 2)
    @Column(name = "TIPO_SERVICIOS")
    private String tipoServicios;
    @Column(name = "ANIO")
    private BigInteger anio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAcuerdoAprobacion")
    private List<RrhhAcuerdoContrato> rrhhAcuerdoContratoList;
    @JoinColumn(name = "ID_PLANTILLA", referencedColumnName = "ID_PLANTILLA")
    @ManyToOne
    private RrhhPlantilla idPlantilla;

    public RrhhAcuerdoAprobacion() {
    }

    public RrhhAcuerdoAprobacion(BigDecimal idAcuerdoAprobacion) {
        this.idAcuerdoAprobacion = idAcuerdoAprobacion;
    }

    public RrhhAcuerdoAprobacion(BigDecimal idAcuerdoAprobacion, BigInteger numeroAcuerdo, String identificadorAcuerdo, String estado, String usuarioInsert, Date fechaInsert) {
        this.idAcuerdoAprobacion = idAcuerdoAprobacion;
        this.numeroAcuerdo = numeroAcuerdo;
        this.identificadorAcuerdo = identificadorAcuerdo;
        this.estado = estado;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public BigDecimal getIdAcuerdoAprobacion() {
        return idAcuerdoAprobacion;
    }

    public void setIdAcuerdoAprobacion(BigDecimal idAcuerdoAprobacion) {
        this.idAcuerdoAprobacion = idAcuerdoAprobacion;
    }

    public BigInteger getNumeroAcuerdo() {
        return numeroAcuerdo;
    }

    public void setNumeroAcuerdo(BigInteger numeroAcuerdo) {
        this.numeroAcuerdo = numeroAcuerdo;
    }

    public String getIdentificadorAcuerdo() {
        return identificadorAcuerdo;
    }

    public void setIdentificadorAcuerdo(String identificadorAcuerdo) {
        this.identificadorAcuerdo = identificadorAcuerdo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public String getTipoServicios() {
        return tipoServicios;
    }

    public void setTipoServicios(String tipoServicios) {
        this.tipoServicios = tipoServicios;
    }

    public BigInteger getAnio() {
        return anio;
    }

    public void setAnio(BigInteger anio) {
        this.anio = anio;
    }
    
    public RrhhRenglon getRenglon() {
        return renglon;
    }

    public void setRenglon(RrhhRenglon renglon) {
        this.renglon = renglon;
    }

    @XmlTransient
    public List<RrhhAcuerdoContrato> getRrhhAcuerdoContratoList() {
        return rrhhAcuerdoContratoList;
    }

    public void setRrhhAcuerdoContratoList(List<RrhhAcuerdoContrato> rrhhAcuerdoContratoList) {
        this.rrhhAcuerdoContratoList = rrhhAcuerdoContratoList;
    }

    public RrhhPlantilla getIdPlantilla() {
        return idPlantilla;
    }

    public void setIdPlantilla(RrhhPlantilla idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcuerdoAprobacion != null ? idAcuerdoAprobacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhAcuerdoAprobacion)) {
            return false;
        }
        RrhhAcuerdoAprobacion other = (RrhhAcuerdoAprobacion) object;
        if ((this.idAcuerdoAprobacion == null && other.idAcuerdoAprobacion != null) || (this.idAcuerdoAprobacion != null && !this.idAcuerdoAprobacion.equals(other.idAcuerdoAprobacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhAcuerdoAprobacion[ idAcuerdoAprobacion=" + idAcuerdoAprobacion + " ]";
    }
    
}
