/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import cgc.rrhh.contratos.pojo.ResultsTexto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
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
@Table(name = "RRHH_TITULO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhTitulo.findAll", query = "SELECT r FROM RrhhTitulo r ")
    , @NamedQuery(name = "RrhhTitulo.findByTitulo", query = "SELECT r FROM RrhhTitulo r WHERE r.titulo = :titulo")
    , @NamedQuery(name = "RrhhTitulo.findByNombre", query = "SELECT r FROM RrhhTitulo r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhTitulo.findByGrado", query = "SELECT r FROM RrhhTitulo r WHERE r.grado = :grado")
    , @NamedQuery(name = "RrhhTitulo.findByUsuarioInsert", query = "SELECT r FROM RrhhTitulo r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhTitulo.findByFechaInsert", query = "SELECT r FROM RrhhTitulo r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhTitulo.findByUsuarioUpdate", query = "SELECT r FROM RrhhTitulo r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhTitulo.findByFechaUpdate", query = "SELECT r FROM RrhhTitulo r WHERE r.fechaUpdate = :fechaUpdate")})

@NamedNativeQueries({
    @NamedNativeQuery(name = "RrhhTitulo.NativeQueryTitulos",
                      query = "SELECT T.TITULO, " +
                              "T.NOMBRE " +
                              "FROM RRHH_TITULO T " +                              
                              "WHERE TRANSLATE(UPPER(T.NOMBRE),'ÁÉÍÓÚÑ','AEIOUN') LIKE REPLACE('%'|| TRANSLATE(UPPER(?),'ÁÉÍÓÚÑ','AEIOUN')||'%',' ','%') " +
                              "ORDER BY T.NOMBRE ASC",
                      resultSetMapping = "ResultsTexto")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "ResultsTexto",
                         classes = {@ConstructorResult(targetClass = ResultsTexto.class,
                                 columns = {@ColumnResult(name = "TITULO", type = BigDecimal.class),
                                            @ColumnResult(name = "NOMBRE", type = String.class)
                                 })
                         })
})
public class RrhhTitulo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TITULO")
    private BigDecimal titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 10)
    @Column(name = "GRADO")
    private String grado;
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

    public RrhhTitulo() {
    }

    public RrhhTitulo(BigDecimal titulo) {
        this.titulo = titulo;
    }

    public RrhhTitulo(BigDecimal titulo, String nombre, String usuarioInsert, Date fechaInsert) {
        this.titulo = titulo;
        this.nombre = nombre;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public BigDecimal getTitulo() {
        return titulo;
    }

    public void setTitulo(BigDecimal titulo) {
        this.titulo = titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (titulo != null ? titulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhTitulo)) {
            return false;
        }
        RrhhTitulo other = (RrhhTitulo) object;
        if ((this.titulo == null && other.titulo != null) || (this.titulo != null && !this.titulo.equals(other.titulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhTitulo[ titulo=" + titulo + " ]";
    }
    
}
