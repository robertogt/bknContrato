/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import cgc.rrhh.contratos.pojo.ResultsActividad;
import cgc.rrhh.contratos.pojo.ResultsTexto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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
@Table(name = "RRHH_ACTIVIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhActividad.findAll", query = "SELECT r FROM RrhhActividad r ORDER BY r.idActividad DESC")
    , @NamedQuery(name = "RrhhActividad.findByIdActividad", query = "SELECT r FROM RrhhActividad r WHERE r.idActividad = :idActividad")
    , @NamedQuery(name = "RrhhActividad.findByNombre", query = "SELECT r FROM RrhhActividad r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RrhhActividad.findByDescripcion", query = "SELECT r FROM RrhhActividad r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "RrhhActividad.findByObservaciones", query = "SELECT r FROM RrhhActividad r WHERE r.observaciones = :observaciones")
    , @NamedQuery(name = "RrhhActividad.findByEstado", query = "SELECT r FROM RrhhActividad r WHERE r.estado = :estado ORDER BY r.idActividad DESC")
    , @NamedQuery(name = "RrhhActividad.findByUsuarioInsert", query = "SELECT r FROM RrhhActividad r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhActividad.findByUsuarioUpdate", query = "SELECT r FROM RrhhActividad r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhActividad.findByFechaInsert", query = "SELECT r FROM RrhhActividad r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhActividad.findByFechaUpdate", query = "SELECT r FROM RrhhActividad r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhActividad.findByIdUbicacion", query = "SELECT r FROM RrhhActividad r WHERE r.idUbicacion = :idUbicacion")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "RrhhActividad.NativeQueryActividad",
                      query = "SELECT A.ID_ACTIVIDAD,A.NOMBRE,A.DESCRIPCION, 1 SELECCIONADO " +
                            "FROM RRHH_PERFIL_ACTIVIDAD P " +
                            "INNER JOIN RRHH_ACTIVIDAD A ON P.ID_ACTIVIDAD = A.ID_ACTIVIDAD " +
                            "WHERE P.ID_PERFIL = ? "+
                            "AND A.ESTADO = 'A' " +
                            "AND P.ESTADO = 'A' " +  
                            "ORDER BY A.NOMBRE ASC ",
                      resultSetMapping = "ResultsActividad"),
    @NamedNativeQuery(name = "RrhhActividad.actividadByContrato",
                      query = "SELECT ROWNUM ID_ACTIVIDAD, A.DESCRIPCION FROM RRHH_ACTIVIDAD_CONTRATO C " +
"INNER JOIN RRHH_ACTIVIDAD A ON C.ID_ACTIVIDAD = A.ID_ACTIVIDAD " +
"WHERE C.ID_CONTRATO = ? AND C.ESTADO = 'A' ",
                      resultSetMapping = "ResultsActividad2"),
    @NamedNativeQuery(name = "RrhhActividad.ActividadContrato",
                      query = "SELECT ID_ACTIVIDAD,NOMBRE,DESCRIPCION,SELECCIONADO FROM ( " +
                            "SELECT A.ID_ACTIVIDAD,A.NOMBRE,A.DESCRIPCION, 1 SELECCIONADO " +
                            "FROM RRHH_PERFIL_ACTIVIDAD P " +
                            "INNER JOIN RRHH_ACTIVIDAD A ON P.ID_ACTIVIDAD = A.ID_ACTIVIDAD " +
                            "WHERE P.ID_PERFIL = ?perfil " +
                            "AND A.ESTADO = 'A' " +
                            "AND P.ESTADO = 'A' " + 
                            "AND EXISTS(SELECT 1 FROM RRHH_ACTIVIDAD_CONTRATO WHERE ID_ACTIVIDAD = A.ID_ACTIVIDAD AND ID_CONTRATO = ?contrato AND ESTADO = 'A') " +
                            "UNION ALL " +
                            "SELECT A.ID_ACTIVIDAD,A.NOMBRE,A.DESCRIPCION, 0 SELECCIONADO " +
                            "FROM RRHH_PERFIL_ACTIVIDAD P " +
                            "INNER JOIN RRHH_ACTIVIDAD A ON P.ID_ACTIVIDAD = A.ID_ACTIVIDAD " +
                            "WHERE P.ID_PERFIL = ?perfil " +
                            "AND A.ESTADO = 'A' " +
                            "AND P.ESTADO = 'A' "+  
                            "AND NOT EXISTS(SELECT 1 FROM RRHH_ACTIVIDAD_CONTRATO WHERE ID_ACTIVIDAD = A.ID_ACTIVIDAD AND ID_CONTRATO = ?contrato AND ESTADO = 'A') " +
                            ") ",
                            resultSetMapping = "ResultsActividad")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "ResultsActividad",
                         classes = {@ConstructorResult(targetClass = ResultsActividad.class,
                                 columns = {@ColumnResult(name = "ID_ACTIVIDAD", type = BigDecimal.class),
                                            @ColumnResult(name = "NOMBRE", type = String.class),
                                            @ColumnResult(name = "DESCRIPCION", type = String.class),
                                            @ColumnResult(name = "SELECCIONADO", type = boolean.class)
                                 })
                         }),
    @SqlResultSetMapping(name = "ResultsActividad2",
                         classes = {@ConstructorResult(targetClass = ResultsActividad.class,
                                 columns = {@ColumnResult(name = "ID_ACTIVIDAD", type = BigDecimal.class),
                                            @ColumnResult(name = "DESCRIPCION", type = String.class)
                                 })
                         })
})
public class RrhhActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ACTIVIDAD")
    private BigDecimal idActividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 500)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USUARIO_INSERT")
    private String usuarioInsert;
    @Size(max = 100)
    @Column(name = "USUARIO_UPDATE")
    private String usuarioUpdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INSERT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInsert;
    @Column(name = "FECHA_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUpdate;
    @JoinColumn(name = "UBICACION_FUNCIONAL", referencedColumnName = "UBICACION_FUNCIONAL")
    @ManyToOne(optional = false)
    private RrhhUbicacionFuncional idUbicacion;

    public RrhhActividad() {
    }

    public RrhhActividad(BigDecimal idActividad) {
        this.idActividad = idActividad;
    }

    public RrhhActividad(BigDecimal idActividad, String nombre, String descripcion, String estado, String usuarioInsert, Date fechaInsert) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public BigDecimal getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(BigDecimal idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getUsuarioUpdate() {
        return usuarioUpdate;
    }

    public void setUsuarioUpdate(String usuarioUpdate) {
        this.usuarioUpdate = usuarioUpdate;
    }

    public Date getFechaInsert() {
        return fechaInsert;
    }

    public void setFechaInsert(Date fechaInsert) {
        this.fechaInsert = fechaInsert;
    }

    public Date getFechaUpdate() {
        return fechaUpdate;
    }

    public void setFechaUpdate(Date fechaUpdate) {
        this.fechaUpdate = fechaUpdate;
    }

    public RrhhUbicacionFuncional getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(RrhhUbicacionFuncional idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividad != null ? idActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhActividad)) {
            return false;
        }
        RrhhActividad other = (RrhhActividad) object;
        if ((this.idActividad == null && other.idActividad != null) || (this.idActividad != null && !this.idActividad.equals(other.idActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhActividad[ idActividad=" + idActividad + " ]";
    }
    
}
