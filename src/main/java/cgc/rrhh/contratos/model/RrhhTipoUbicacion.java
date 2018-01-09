/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ejmorales
 */
@Entity
@Table(name = "RRHH_TIPO_UBICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhTipoUbicacion.findAll", query = "SELECT r FROM RrhhTipoUbicacion r")
    , @NamedQuery(name = "RrhhTipoUbicacion.findByTipoUbicacion", query = "SELECT r FROM RrhhTipoUbicacion r WHERE r.tipoUbicacion = :tipoUbicacion")
    , @NamedQuery(name = "RrhhTipoUbicacion.findByDescripcion", query = "SELECT r FROM RrhhTipoUbicacion r WHERE r.descripcion = :descripcion")})
public class RrhhTipoUbicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_UBICACION")
    private Long tipoUbicacion;
    @Size(max = 75)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "PREFIJO")
    private String prefijo;
    @OneToMany(mappedBy = "tipoUbicacion")
    private List<RrhhUbicacionFuncional> rrhhUbicacionFuncionalList;

    public RrhhTipoUbicacion() {
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }
    
    

    public RrhhTipoUbicacion(Long tipoUbicacion) {
        this.tipoUbicacion = tipoUbicacion;
    }

    public Long getTipoUbicacion() {
        return tipoUbicacion;
    }

    public void setTipoUbicacion(Long tipoUbicacion) {
        this.tipoUbicacion = tipoUbicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<RrhhUbicacionFuncional> getRrhhUbicacionFuncionalList() {
        return rrhhUbicacionFuncionalList;
    }

    public void setRrhhUbicacionFuncionalList(List<RrhhUbicacionFuncional> rrhhUbicacionFuncionalList) {
        this.rrhhUbicacionFuncionalList = rrhhUbicacionFuncionalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoUbicacion != null ? tipoUbicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhTipoUbicacion)) {
            return false;
        }
        RrhhTipoUbicacion other = (RrhhTipoUbicacion) object;
        if ((this.tipoUbicacion == null && other.tipoUbicacion != null) || (this.tipoUbicacion != null && !this.tipoUbicacion.equals(other.tipoUbicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhTipoUbicacion[ tipoUbicacion=" + tipoUbicacion + " ]";
    }
    
}
