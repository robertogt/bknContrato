/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import cgc.rrhh.contratos.pojo.ResultsUbicacion;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "RRHH_UBICACION_FUNCIONAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhUbicacionFuncional.findAll", query = "SELECT r FROM RrhhUbicacionFuncional r")
    , @NamedQuery(name = "RrhhUbicacionFuncional.findByUbicacionFuncional", query = "SELECT r FROM RrhhUbicacionFuncional r WHERE r.ubicacionFuncional = :ubicacionFuncional")
    , @NamedQuery(name = "RrhhUbicacionFuncional.findByNombre", query = "SELECT r FROM RrhhUbicacionFuncional r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhUbicacionFuncional.findByCodigoUbicacion", query = "SELECT r FROM RrhhUbicacionFuncional r WHERE r.codigoUbicacion = :codigoUbicacion")
    , @NamedQuery(name = "RrhhUbicacionFuncional.findByUsuarioInsert", query = "SELECT r FROM RrhhUbicacionFuncional r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhUbicacionFuncional.findByFechaInsert", query = "SELECT r FROM RrhhUbicacionFuncional r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhUbicacionFuncional.findByUsuarioUpdate", query = "SELECT r FROM RrhhUbicacionFuncional r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhUbicacionFuncional.findByFechaUpdate", query = "SELECT r FROM RrhhUbicacionFuncional r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhUbicacionFuncional.findByEstado", query = "SELECT r FROM RrhhUbicacionFuncional r WHERE r.estado = :estado")
    , @NamedQuery(name = "RrhhUbicacionFuncional.findByCodigoComisionSag", query = "SELECT r FROM RrhhUbicacionFuncional r WHERE r.codigoComisionSag = :codigoComisionSag")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "RrhhUbicacionFuncional.NativeQueryUbicaciones",
                      query = "SELECT U.UBICACION_FUNCIONAL, " +
                              "U.NOMBRE " +
                              "FROM RRHH_LABORAL L " +
                              "INNER JOIN RRHH_UBICACION_FUNCIONAL U ON L.UBICACION_FUNCIONAL = U.UBICACION_FUNCIONAL " +
                              "AND TRANSLATE(UPPER(U.NOMBRE),'ÁÉÍÓÚÑ','AEIOUN') LIKE REPLACE('%'|| TRANSLATE(UPPER(?),'ÁÉÍÓÚÑ','AEIOUN')||'%',' ','%') " +
                              "ORDER BY U.NOMBRE ",
                      resultSetMapping = "ResultsUbicacion")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "ResultsUbicacion",
                         classes = {@ConstructorResult(targetClass = ResultsUbicacion.class,
                                 columns = {@ColumnResult(name = "UBICACION_FUNCIONAL", type = Integer.class),
                                            @ColumnResult(name = "NOMBRE", type = String.class)
                                 })
                         })
})
public class RrhhUbicacionFuncional implements Serializable {

    @JoinColumn(name = "ID_CATEGORIA_UBICACION", referencedColumnName = "ID_CATEGORIA_UBICACION")
    @ManyToOne
    private RrhhCategoriaUbicacion idCategoriaUbicacion;
    @JoinColumn(name = "UBICACION_FUNCIONAL_PADRE", referencedColumnName = "UBICACION_FUNCIONAL")
    @ManyToOne
    private RrhhUbicacionFuncional ubicacionFuncionalPadre;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "UBICACION_FUNCIONAL")
    private BigDecimal ubicacionFuncional;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 40)
    @Column(name = "CODIGO_UBICACION")
    private String codigoUbicacion;
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
    @Column(name = "CODIGO_COMISION_SAG")
    private Long codigoComisionSag;
    @JoinColumn(name = "EDIFICIO", referencedColumnName = "EDIFICIO")
    @ManyToOne
    private RrhhEdificio edificio;
    @JoinColumn(name = "TIPO_UBICACION", referencedColumnName = "TIPO_UBICACION")
    @ManyToOne
    private RrhhTipoUbicacion tipoUbicacion;

    public RrhhUbicacionFuncional() {
    }

    public RrhhUbicacionFuncional(BigDecimal ubicacionFuncional) {
        this.ubicacionFuncional = ubicacionFuncional;
    }

    public RrhhUbicacionFuncional(BigDecimal ubicacionFuncional, String nombre, String usuarioInsert, Date fechaInsert) {
        this.ubicacionFuncional = ubicacionFuncional;
        this.nombre = nombre;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public BigDecimal getUbicacionFuncional() {
        return ubicacionFuncional;
    }

    public void setUbicacionFuncional(BigDecimal ubicacionFuncional) {
        this.ubicacionFuncional = ubicacionFuncional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoUbicacion() {
        return codigoUbicacion;
    }

    public void setCodigoUbicacion(String codigoUbicacion) {
        this.codigoUbicacion = codigoUbicacion;
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

    public Long getCodigoComisionSag() {
        return codigoComisionSag;
    }

    public void setCodigoComisionSag(Long codigoComisionSag) {
        this.codigoComisionSag = codigoComisionSag;
    }

    public RrhhEdificio getEdificio() {
        return edificio;
    }

    public void setEdificio(RrhhEdificio edificio) {
        this.edificio = edificio;
    }

    public RrhhTipoUbicacion getTipoUbicacion() {
        return tipoUbicacion;
    }

    public void setTipoUbicacion(RrhhTipoUbicacion tipoUbicacion) {
        this.tipoUbicacion = tipoUbicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ubicacionFuncional != null ? ubicacionFuncional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhUbicacionFuncional)) {
            return false;
        }
        RrhhUbicacionFuncional other = (RrhhUbicacionFuncional) object;
        if ((this.ubicacionFuncional == null && other.ubicacionFuncional != null) || (this.ubicacionFuncional != null && !this.ubicacionFuncional.equals(other.ubicacionFuncional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhUbicacionFuncional[ ubicacionFuncional=" + ubicacionFuncional + " ]";
    }

    public RrhhCategoriaUbicacion getIdCategoriaUbicacion() {
        return idCategoriaUbicacion;
    }

    public void setIdCategoriaUbicacion(RrhhCategoriaUbicacion idCategoriaUbicacion) {
        this.idCategoriaUbicacion = idCategoriaUbicacion;
    }

    public RrhhUbicacionFuncional getUbicacionFuncionalPadre() {
        return ubicacionFuncionalPadre;
    }

    public void setUbicacionFuncionalPadre(RrhhUbicacionFuncional ubicacionFuncionalPadre) {
        this.ubicacionFuncionalPadre = ubicacionFuncionalPadre;
    }
    
}
