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
import javax.persistence.JoinColumns;
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
@Table(name = "RRHH_EDIFICIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhEdificio.findAll", query = "SELECT r FROM RrhhEdificio r")
    , @NamedQuery(name = "RrhhEdificio.findByEdificio", query = "SELECT r FROM RrhhEdificio r WHERE r.edificio = :edificio")
    , @NamedQuery(name = "RrhhEdificio.findByNombre", query = "SELECT r FROM RrhhEdificio r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhEdificio.findByDireccion", query = "SELECT r FROM RrhhEdificio r WHERE r.direccion = :direccion")
    , @NamedQuery(name = "RrhhEdificio.findByZona", query = "SELECT r FROM RrhhEdificio r WHERE r.zona = :zona")})
public class RrhhEdificio implements Serializable {

    @Size(max = 250)
    @Column(name = "DIRECCION_COMPLETA")
    private String direccionCompleta;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EDIFICIO")
    private BigDecimal edificio;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 100)
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "ZONA")
    private BigInteger zona;
    @OneToMany(mappedBy = "edificio")
    private List<RrhhUbicacionFuncional> rrhhUbicacionFuncionalList;
    @OneToMany(mappedBy = "edificio")
    private List<RrhhLaboral> rrhhLaboralList;
    @JoinColumns({
        @JoinColumn(name = "DEPARTAMENTO", referencedColumnName = "DEPARTAMENTO")
        , @JoinColumn(name = "MUNICIPIO", referencedColumnName = "MUNICIPIO")})
    @ManyToOne
    private RrhhMunicipio rrhhMunicipio;

    public RrhhEdificio() {
    }

    public RrhhEdificio(BigDecimal edificio) {
        this.edificio = edificio;
    }

    public BigDecimal getEdificio() {
        return edificio;
    }

    public void setEdificio(BigDecimal edificio) {
        this.edificio = edificio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public BigInteger getZona() {
        return zona;
    }

    public void setZona(BigInteger zona) {
        this.zona = zona;
    }

    @XmlTransient
    public List<RrhhUbicacionFuncional> getRrhhUbicacionFuncionalList() {
        return rrhhUbicacionFuncionalList;
    }

    public void setRrhhUbicacionFuncionalList(List<RrhhUbicacionFuncional> rrhhUbicacionFuncionalList) {
        this.rrhhUbicacionFuncionalList = rrhhUbicacionFuncionalList;
    }

    @XmlTransient
    public List<RrhhLaboral> getRrhhLaboralList() {
        return rrhhLaboralList;
    }

    public void setRrhhLaboralList(List<RrhhLaboral> rrhhLaboralList) {
        this.rrhhLaboralList = rrhhLaboralList;
    }

    public RrhhMunicipio getRrhhMunicipio() {
        return rrhhMunicipio;
    }

    public void setRrhhMunicipio(RrhhMunicipio rrhhMunicipio) {
        this.rrhhMunicipio = rrhhMunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (edificio != null ? edificio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhEdificio)) {
            return false;
        }
        RrhhEdificio other = (RrhhEdificio) object;
        if ((this.edificio == null && other.edificio != null) || (this.edificio != null && !this.edificio.equals(other.edificio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhEdificio[ edificio=" + edificio + " ]";
    }

    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }
    
}
