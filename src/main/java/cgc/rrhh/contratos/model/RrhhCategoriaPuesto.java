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
@Table(name = "RRHH_CATEGORIA_PUESTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhCategoriaPuesto.findAll", query = "SELECT r FROM RrhhCategoriaPuesto r")
    , @NamedQuery(name = "RrhhCategoriaPuesto.findByIdCategoriaPuesto", query = "SELECT r FROM RrhhCategoriaPuesto r WHERE r.idCategoriaPuesto = :idCategoriaPuesto")
    , @NamedQuery(name = "RrhhCategoriaPuesto.findByNivelPuesto", query = "SELECT r FROM RrhhCategoriaPuesto r WHERE r.nivelPuesto = :nivelPuesto")
    , @NamedQuery(name = "RrhhCategoriaPuesto.findByIdPuestoPadre", query = "SELECT r FROM RrhhCategoriaPuesto r WHERE r.idPuestoPadre = :idPuestoPadre")
    , @NamedQuery(name = "RrhhCategoriaPuesto.findByNombreCategoria", query = "SELECT r FROM RrhhCategoriaPuesto r WHERE r.nombreCategoria = :nombreCategoria")})
public class RrhhCategoriaPuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CATEGORIA_PUESTO")
    private BigDecimal idCategoriaPuesto;
    @Column(name = "NIVEL_PUESTO")
    private BigInteger nivelPuesto;
    @Column(name = "ID_PUESTO_PADRE")
    private BigInteger idPuestoPadre;
    @Size(max = 100)
    @Column(name = "NOMBRE_CATEGORIA")
    private String nombreCategoria;
    @OneToMany(mappedBy = "idCategoriaPuesto")
    private List<RrhhPuestoFuncional> rrhhPuestoFuncionalList;

    public RrhhCategoriaPuesto() {
    }

    public RrhhCategoriaPuesto(BigDecimal idCategoriaPuesto) {
        this.idCategoriaPuesto = idCategoriaPuesto;
    }

    public BigDecimal getIdCategoriaPuesto() {
        return idCategoriaPuesto;
    }

    public void setIdCategoriaPuesto(BigDecimal idCategoriaPuesto) {
        this.idCategoriaPuesto = idCategoriaPuesto;
    }

    public BigInteger getNivelPuesto() {
        return nivelPuesto;
    }

    public void setNivelPuesto(BigInteger nivelPuesto) {
        this.nivelPuesto = nivelPuesto;
    }

    public BigInteger getIdPuestoPadre() {
        return idPuestoPadre;
    }

    public void setIdPuestoPadre(BigInteger idPuestoPadre) {
        this.idPuestoPadre = idPuestoPadre;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @XmlTransient
    public List<RrhhPuestoFuncional> getRrhhPuestoFuncionalList() {
        return rrhhPuestoFuncionalList;
    }

    public void setRrhhPuestoFuncionalList(List<RrhhPuestoFuncional> rrhhPuestoFuncionalList) {
        this.rrhhPuestoFuncionalList = rrhhPuestoFuncionalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriaPuesto != null ? idCategoriaPuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhCategoriaPuesto)) {
            return false;
        }
        RrhhCategoriaPuesto other = (RrhhCategoriaPuesto) object;
        if ((this.idCategoriaPuesto == null && other.idCategoriaPuesto != null) || (this.idCategoriaPuesto != null && !this.idCategoriaPuesto.equals(other.idCategoriaPuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhCategoriaPuesto[ idCategoriaPuesto=" + idCategoriaPuesto + " ]";
    }
    
}
