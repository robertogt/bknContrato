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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "RRHH_PLANTILLA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhPlantilla.findAll", query = "SELECT r FROM RrhhPlantilla r")
    , @NamedQuery(name = "RrhhPlantilla.findByIdPlantilla", query = "SELECT r FROM RrhhPlantilla r WHERE r.idPlantilla = :idPlantilla")
    , @NamedQuery(name = "RrhhPlantilla.findByNombre", query = "SELECT r FROM RrhhPlantilla r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhPlantilla.findByAnio", query = "SELECT r FROM RrhhPlantilla r WHERE r.anio = :anio")
    , @NamedQuery(name = "RrhhPlantilla.findByTipoServicio", query = "SELECT r FROM RrhhPlantilla r WHERE r.tipoServicio = :tipoServicio")
    , @NamedQuery(name = "RrhhPlantilla.findByObservaciones", query = "SELECT r FROM RrhhPlantilla r WHERE r.observaciones = :observaciones")
    , @NamedQuery(name = "RrhhPlantilla.findByEstado", query = "SELECT r FROM RrhhPlantilla r WHERE r.estado = :estado")
    , @NamedQuery(name = "RrhhPlantilla.findByUsuarioInsert", query = "SELECT r FROM RrhhPlantilla r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhPlantilla.findByFechaInsert", query = "SELECT r FROM RrhhPlantilla r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhPlantilla.findByUsuarioUpdate", query = "SELECT r FROM RrhhPlantilla r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhPlantilla.findByFechaUpdate", query = "SELECT r FROM RrhhPlantilla r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhPlantilla.findByRenglonAnioTipo", query = "SELECT r FROM RrhhPlantilla r WHERE r.anio = :anio AND r.idTipoDocumento.idTipoDocumento = :tipoDocumento AND r.idRenglon.renglon = COALESCE(:renglon,r.idRenglon.renglon) AND r.tipoServicio = COALESCE(:tipoServicio,r.tipoServicio) AND r.estado = 'A' ")})
public class RrhhPlantilla implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PLANTILLA")
    private BigDecimal idPlantilla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "ARCHIVO")
    private byte[] archivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ANIO")
    private String anio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_SERVICIO")
    private String tipoServicio;
    @Size(max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
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
    @OneToMany(mappedBy = "idPlantilla")
    private List<RrhhContrato> rrhhContratoList;
    @JoinColumn(name = "ID_RENGLON", referencedColumnName = "RENGLON")
    @ManyToOne(optional = false)
    private RrhhRenglon idRenglon;
    @JoinColumn(name = "ID_TIPO_DOCUMENTO", referencedColumnName = "ID_TIPO_DOCUMENTO")
    @ManyToOne(optional = false)
    private RrhhTipoDocumento idTipoDocumento;

    public RrhhPlantilla() {
    }

    public RrhhPlantilla(BigDecimal idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    public RrhhPlantilla(BigDecimal idPlantilla, String nombre, byte[] archivo, String anio, String tipoServicio, String estado) {
        this.idPlantilla = idPlantilla;
        this.nombre = nombre;
        this.archivo = archivo;
        this.anio = anio;
        this.tipoServicio = tipoServicio;
        this.estado = estado;
    }

    public BigDecimal getIdPlantilla() {
        return idPlantilla;
    }

    public void setIdPlantilla(BigDecimal idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
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

    @XmlTransient
    public List<RrhhContrato> getRrhhContratoList() {
        return rrhhContratoList;
    }

    public void setRrhhContratoList(List<RrhhContrato> rrhhContratoList) {
        this.rrhhContratoList = rrhhContratoList;
    }

    public RrhhRenglon getIdRenglon() {
        return idRenglon;
    }

    public void setIdRenglon(RrhhRenglon idRenglon) {
        this.idRenglon = idRenglon;
    }

    public RrhhTipoDocumento getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(RrhhTipoDocumento idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlantilla != null ? idPlantilla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhPlantilla)) {
            return false;
        }
        RrhhPlantilla other = (RrhhPlantilla) object;
        if ((this.idPlantilla == null && other.idPlantilla != null) || (this.idPlantilla != null && !this.idPlantilla.equals(other.idPlantilla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhPlantilla[ idPlantilla=" + idPlantilla + " ]";
    }
    
}
