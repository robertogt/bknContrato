/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import java.io.Serializable;
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
@Table(name = "RRHH_RENGLON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhRenglon.findAll", query = "SELECT r FROM RrhhRenglon r")
    , @NamedQuery(name = "RrhhRenglon.findByRenglon", query = "SELECT r FROM RrhhRenglon r WHERE r.renglon = :renglon")
    , @NamedQuery(name = "RrhhRenglon.findByNombre", query = "SELECT r FROM RrhhRenglon r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhRenglon.findByUsuarioInsert", query = "SELECT r FROM RrhhRenglon r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhRenglon.findByFechaInsert", query = "SELECT r FROM RrhhRenglon r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhRenglon.findByUsuarioUpdate", query = "SELECT r FROM RrhhRenglon r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhRenglon.findByFechaUpdate", query = "SELECT r FROM RrhhRenglon r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhRenglon.findByEstado", query = "SELECT r FROM RrhhRenglon r WHERE r.estado = :estado AND r.tipo = 'T' ")
    , @NamedQuery(name = "RrhhRenglon.findByTipo", query = "SELECT r FROM RrhhRenglon r WHERE r.tipo = :tipo")})
public class RrhhRenglon implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRenglon")
    private List<RrhhPlantilla> rrhhPlantillaList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "RENGLON")
    private String renglon;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
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
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 1)
    @Column(name = "TIPO")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "renglon")
    private List<RrhhLaboral> rrhhLaboralList;

    public RrhhRenglon() {
    }

    public RrhhRenglon(String renglon) {
        this.renglon = renglon;
    }

    public RrhhRenglon(String renglon, String usuarioInsert, Date fechaInsert) {
        this.renglon = renglon;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public String getRenglon() {
        return renglon;
    }

    public void setRenglon(String renglon) {
        this.renglon = renglon;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<RrhhLaboral> getRrhhLaboralList() {
        return rrhhLaboralList;
    }

    public void setRrhhLaboralList(List<RrhhLaboral> rrhhLaboralList) {
        this.rrhhLaboralList = rrhhLaboralList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (renglon != null ? renglon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhRenglon)) {
            return false;
        }
        RrhhRenglon other = (RrhhRenglon) object;
        if ((this.renglon == null && other.renglon != null) || (this.renglon != null && !this.renglon.equals(other.renglon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhRenglon[ renglon=" + renglon + " ]";
    }

    @XmlTransient
    public List<RrhhPlantilla> getRrhhPlantillaList() {
        return rrhhPlantillaList;
    }

    public void setRrhhPlantillaList(List<RrhhPlantilla> rrhhPlantillaList) {
        this.rrhhPlantillaList = rrhhPlantillaList;
    }
    
}
