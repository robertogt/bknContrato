/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import cgc.rrhh.contratos.pojo.ResultsMunicipio;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ejmorales
 */
@Entity
@Table(name = "RRHH_MUNICIPIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhMunicipio.findAll", query = "SELECT r FROM RrhhMunicipio r")
    , @NamedQuery(name = "RrhhMunicipio.findByDepartamento", query = "SELECT r FROM RrhhMunicipio r WHERE r.rrhhMunicipioPK.departamento = :departamento")
    , @NamedQuery(name = "RrhhMunicipio.findByMunicipio", query = "SELECT r FROM RrhhMunicipio r WHERE r.rrhhMunicipioPK.municipio = :municipio")
    , @NamedQuery(name = "RrhhMunicipio.findByNombre", query = "SELECT r FROM RrhhMunicipio r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhMunicipio.findByOrden", query = "SELECT r FROM RrhhMunicipio r WHERE r.orden = :orden")
    , @NamedQuery(name = "RrhhMunicipio.findByMunicipioDepto", query = "SELECT r FROM RrhhMunicipio r WHERE r.rrhhMunicipioPK.municipio = :municipio AND r.rrhhMunicipioPK.departamento = :departamento")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "RrhhMunicipio.NativeMunicipiosDepto",
                      query = "SELECT M.DEPARTAMENTO,M.MUNICIPIO,D.NOMBRE||' - '||M.NOMBRE NOMBRE FROM RRHH_MUNICIPIO M " +
                            "INNER JOIN RRHH_DEPARTAMENTO D ON M.DEPARTAMENTO = D.DEPARTAMENTO " +
                            "ORDER BY M.ORDEN ",
                      resultSetMapping = "ResultsMunicipio")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "ResultsMunicipio",
                        classes = {@ConstructorResult(targetClass = ResultsMunicipio.class,
                                columns = {@ColumnResult(name = "DEPARTAMENTO",type = String.class),
                                           @ColumnResult(name = "MUNICIPIO",type = String.class),
                                           @ColumnResult(name = "NOMBRE",type = String.class)
                                })
                        })
})
public class RrhhMunicipio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RrhhMunicipioPK rrhhMunicipioPK;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 4)
    @Column(name = "ORDEN")
    private String orden;
    @OneToMany(mappedBy = "rrhhMunicipio")
    private List<RrhhRue> rrhhRueList;
    @OneToMany(mappedBy = "rrhhMunicipio")
    private List<RrhhEdificio> rrhhEdificioList;
    @JoinColumn(name = "DEPARTAMENTO", referencedColumnName = "DEPARTAMENTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private RrhhDepartamento rrhhDepartamento;

    public RrhhMunicipio() {
    }

    public RrhhMunicipio(RrhhMunicipioPK rrhhMunicipioPK) {
        this.rrhhMunicipioPK = rrhhMunicipioPK;
    }

    public RrhhMunicipio(String departamento, String municipio) {
        this.rrhhMunicipioPK = new RrhhMunicipioPK(departamento, municipio);
    }

    public RrhhMunicipioPK getRrhhMunicipioPK() {
        return rrhhMunicipioPK;
    }

    public void setRrhhMunicipioPK(RrhhMunicipioPK rrhhMunicipioPK) {
        this.rrhhMunicipioPK = rrhhMunicipioPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    @XmlTransient
    public List<RrhhRue> getRrhhRueList() {
        return rrhhRueList;
    }

    public void setRrhhRueList(List<RrhhRue> rrhhRueList) {
        this.rrhhRueList = rrhhRueList;
    }

    @XmlTransient
    public List<RrhhEdificio> getRrhhEdificioList() {
        return rrhhEdificioList;
    }

    public void setRrhhEdificioList(List<RrhhEdificio> rrhhEdificioList) {
        this.rrhhEdificioList = rrhhEdificioList;
    }

    public RrhhDepartamento getRrhhDepartamento() {
        return rrhhDepartamento;
    }

    public void setRrhhDepartamento(RrhhDepartamento rrhhDepartamento) {
        this.rrhhDepartamento = rrhhDepartamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rrhhMunicipioPK != null ? rrhhMunicipioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhMunicipio)) {
            return false;
        }
        RrhhMunicipio other = (RrhhMunicipio) object;
        if ((this.rrhhMunicipioPK == null && other.rrhhMunicipioPK != null) || (this.rrhhMunicipioPK != null && !this.rrhhMunicipioPK.equals(other.rrhhMunicipioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhMunicipio[ rrhhMunicipioPK=" + rrhhMunicipioPK + " ]";
    }
    
}
