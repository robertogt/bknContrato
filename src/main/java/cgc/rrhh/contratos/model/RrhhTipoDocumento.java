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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "RRHH_TIPO_DOCUMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhTipoDocumento.findAll", query = "SELECT r FROM RrhhTipoDocumento r")
    , @NamedQuery(name = "RrhhTipoDocumento.findByIdTipoDocumento", query = "SELECT r FROM RrhhTipoDocumento r WHERE r.idTipoDocumento = :idTipoDocumento")
    , @NamedQuery(name = "RrhhTipoDocumento.findByNombre", query = "SELECT r FROM RrhhTipoDocumento r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhTipoDocumento.findByEstado", query = "SELECT r FROM RrhhTipoDocumento r WHERE r.estado = :estado")
    , @NamedQuery(name = "RrhhTipoDocumento.findByUsuarioInsert", query = "SELECT r FROM RrhhTipoDocumento r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhTipoDocumento.findByFechaInsert", query = "SELECT r FROM RrhhTipoDocumento r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhTipoDocumento.findByUsuarioUpdate", query = "SELECT r FROM RrhhTipoDocumento r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhTipoDocumento.findByFechaUpdate", query = "SELECT r FROM RrhhTipoDocumento r WHERE r.fechaUpdate = :fechaUpdate")})
public class RrhhTipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_DOCUMENTO")
    private BigDecimal idTipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDocumento")
    private List<RrhhPlantilla> rrhhPlantillaList;

    public RrhhTipoDocumento() {
    }

    public RrhhTipoDocumento(BigDecimal idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public RrhhTipoDocumento(BigDecimal idTipoDocumento, String nombre, String estado, String usuarioInsert, Date fechaInsert) {
        this.idTipoDocumento = idTipoDocumento;
        this.nombre = nombre;
        this.estado = estado;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public BigDecimal getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(BigDecimal idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public List<RrhhPlantilla> getRrhhPlantillaList() {
        return rrhhPlantillaList;
    }

    public void setRrhhPlantillaList(List<RrhhPlantilla> rrhhPlantillaList) {
        this.rrhhPlantillaList = rrhhPlantillaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDocumento != null ? idTipoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhTipoDocumento)) {
            return false;
        }
        RrhhTipoDocumento other = (RrhhTipoDocumento) object;
        if ((this.idTipoDocumento == null && other.idTipoDocumento != null) || (this.idTipoDocumento != null && !this.idTipoDocumento.equals(other.idTipoDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhTipoDocumento[ idTipoDocumento=" + idTipoDocumento + " ]";
    }
    
}
