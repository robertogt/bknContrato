/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "RRHH_CATEGORIA_UBICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhCategoriaUbicacion.findAll", query = "SELECT r FROM RrhhCategoriaUbicacion r")
    , @NamedQuery(name = "RrhhCategoriaUbicacion.findByIdCategoriaUbicacion", query = "SELECT r FROM RrhhCategoriaUbicacion r WHERE r.idCategoriaUbicacion = :idCategoriaUbicacion")
    , @NamedQuery(name = "RrhhCategoriaUbicacion.findByNivelUbicacion", query = "SELECT r FROM RrhhCategoriaUbicacion r WHERE r.nivelUbicacion = :nivelUbicacion")
    , @NamedQuery(name = "RrhhCategoriaUbicacion.findByNombreCategoria", query = "SELECT r FROM RrhhCategoriaUbicacion r WHERE r.nombreCategoria = :nombreCategoria")})
public class RrhhCategoriaUbicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CATEGORIA_UBICACION")
    private BigDecimal idCategoriaUbicacion;
    @Column(name = "NIVEL_UBICACION")
    private BigInteger nivelUbicacion;
    @Size(max = 200)
    @Column(name = "NOMBRE_CATEGORIA")
    private String nombreCategoria;
    @OneToMany(mappedBy = "idCategoriaPadre")
    private List<RrhhCategoriaUbicacion> rrhhCategoriaUbicacionList;
    @JoinColumn(name = "ID_CATEGORIA_PADRE", referencedColumnName = "ID_CATEGORIA_UBICACION")
    @ManyToOne
    private RrhhCategoriaUbicacion idCategoriaPadre;
    @OneToMany(mappedBy = "idCategoriaUbicacion")
    private List<RrhhUbicacionFuncional> rrhhUbicacionFuncionalList;

    public RrhhCategoriaUbicacion() {
    }

    public RrhhCategoriaUbicacion(BigDecimal idCategoriaUbicacion) {
        this.idCategoriaUbicacion = idCategoriaUbicacion;
    }

    public BigDecimal getIdCategoriaUbicacion() {
        return idCategoriaUbicacion;
    }

    public void setIdCategoriaUbicacion(BigDecimal idCategoriaUbicacion) {
        this.idCategoriaUbicacion = idCategoriaUbicacion;
    }

    public BigInteger getNivelUbicacion() {
        return nivelUbicacion;
    }

    public void setNivelUbicacion(BigInteger nivelUbicacion) {
        this.nivelUbicacion = nivelUbicacion;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @XmlTransient
    public List<RrhhCategoriaUbicacion> getRrhhCategoriaUbicacionList() {
        return rrhhCategoriaUbicacionList;
    }

    public void setRrhhCategoriaUbicacionList(List<RrhhCategoriaUbicacion> rrhhCategoriaUbicacionList) {
        this.rrhhCategoriaUbicacionList = rrhhCategoriaUbicacionList;
    }

    public RrhhCategoriaUbicacion getIdCategoriaPadre() {
        return idCategoriaPadre;
    }

    public void setIdCategoriaPadre(RrhhCategoriaUbicacion idCategoriaPadre) {
        this.idCategoriaPadre = idCategoriaPadre;
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
        hash += (idCategoriaUbicacion != null ? idCategoriaUbicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhCategoriaUbicacion)) {
            return false;
        }
        RrhhCategoriaUbicacion other = (RrhhCategoriaUbicacion) object;
        if ((this.idCategoriaUbicacion == null && other.idCategoriaUbicacion != null) || (this.idCategoriaUbicacion != null && !this.idCategoriaUbicacion.equals(other.idCategoriaUbicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhCategoriaUbicacion[ idCategoriaUbicacion=" + idCategoriaUbicacion + " ]";
    }
    
}
