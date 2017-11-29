/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import cgc.rrhh.contratos.pojo.ResultsHistorial;
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
import javax.persistence.Lob;
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
@Table(name = "RRHH_CONTRATO_ESTADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhContratoEstado.findAll", query = "SELECT r FROM RrhhContratoEstado r")
    , @NamedQuery(name = "RrhhContratoEstado.findByIdContratoEstado", query = "SELECT r FROM RrhhContratoEstado r WHERE r.idContratoEstado = :idContratoEstado")
    , @NamedQuery(name = "RrhhContratoEstado.findByIdContrato", query = "SELECT r FROM RrhhContratoEstado r WHERE r.idContrato.idContrato = :idContrato AND r.estado = 'A' AND r.idCatalogoEstado.idCatalogoEstado = :catalogo ")
    , @NamedQuery(name = "RrhhContratoEstado.findByObservacion", query = "SELECT r FROM RrhhContratoEstado r WHERE r.observacion = :observacion")
    , @NamedQuery(name = "RrhhContratoEstado.findByUsuarioInsert", query = "SELECT r FROM RrhhContratoEstado r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhContratoEstado.findByFechaInsert", query = "SELECT r FROM RrhhContratoEstado r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhContratoEstado.findByUsuarioUpdate", query = "SELECT r FROM RrhhContratoEstado r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhContratoEstado.findByFechaUpdate", query = "SELECT r FROM RrhhContratoEstado r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhContratoEstado.findActiveByContrato", query = "SELECT r FROM RrhhContratoEstado r WHERE r.idContrato.idContrato = :idContrato AND r.estado = 'A' ")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "RrhhContratoEstado.findByContrato",
                      query = "SELECT CAT.NOMBRE NOMBRE_ESTADO,C.OBSERVACION,C.USUARIO_INSERT,C.FECHA_INSERT FROM RRHH_CONTRATO_ESTADO C  " +
                        "INNER JOIN RRHH_CATALOGO_ESTADO CAT ON C.ID_CATALOGO_ESTADO = CAT.ID_CATALOGO_ESTADO " +
                        "WHERE C.ID_CONTRATO = ? " +
                        "ORDER BY FECHA_INSERT ASC ",
                        resultSetMapping = "ResultsHistorial"),
    @NamedNativeQuery(name = "RrhhContratoEstado.findHistorialContrato",
                      query = "SELECT CE.ID_CONTRATO_ESTADO, CE.ID_CATALOGO_ESTADO,CE.ID_CONTRATO,CAT.NOMBRE NOMBRE_ESTADO,CE.OBSERVACION,CE.ESTADO,CE.FECHA_INSERT,CE.USUARIO_INSERT, " +
"CASE WHEN DOCUMENTO IS NULL THEN 0 ELSE 1 END DOCUMENTO " +
"FROM RRHH_CONTRATO_ESTADO CE " +
"INNER JOIN RRHH_CATALOGO_ESTADO CAT ON CE.ID_CATALOGO_ESTADO = CAT.ID_CATALOGO_ESTADO " +
"WHERE CE.ID_CONTRATO = ? AND CE.ID_CATALOGO_ESTADO IN (2,3) ORDER BY CE.FECHA_INSERT DESC",
                      resultSetMapping = "ResultsHistorial2")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "ResultsHistorial",
                         classes = {@ConstructorResult(targetClass = ResultsHistorial.class,
                                 columns = {@ColumnResult(name = "NOMBRE_ESTADO", type = String.class),
                                            @ColumnResult(name = "OBSERVACION", type = String.class),
                                            @ColumnResult(name = "USUARIO_INSERT", type = String.class),
                                            @ColumnResult(name = "FECHA_INSERT", type = Date.class)
                                 })
                         }),
    @SqlResultSetMapping(name = "ResultsHistorial2",
                         classes = {@ConstructorResult(targetClass = ResultsHistorial.class,
                                 columns = {@ColumnResult(name = "ID_CONTRATO_ESTADO", type = BigDecimal.class),
                                            @ColumnResult(name = "ID_CATALOGO_ESTADO", type = BigDecimal.class),
                                            @ColumnResult(name = "ID_CONTRATO", type = BigDecimal.class),
                                            @ColumnResult(name = "NOMBRE_ESTADO", type = String.class),
                                            @ColumnResult(name = "OBSERVACION", type = String.class),
                                            @ColumnResult(name = "ESTADO", type = String.class),
                                            @ColumnResult(name = "FECHA_INSERT", type = Date.class),
                                            @ColumnResult(name = "USUARIO_INSERT", type = String.class),
                                            @ColumnResult(name = "DOCUMENTO", type = boolean.class)
                                 })
                         })
})
public class RrhhContratoEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "gSequence", sequenceName = "SEQ_RRHH_CONTRATO_ESTADO", allocationSize = 1)
    @GeneratedValue(generator = "gSequence",strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_CONTRATO_ESTADO")
    private BigDecimal idContratoEstado;
    @Basic(optional = true)
    @Size(min = 1, max = 500)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Basic(optional = true)
    @Lob
    @Column(name = "DOCUMENTO")
    private byte[] documento;
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
    @JoinColumn(name = "ID_CATALOGO_ESTADO", referencedColumnName = "ID_CATALOGO_ESTADO")
    @ManyToOne(optional = false)
    private RrhhCatalogoEstado idCatalogoEstado;    
    @JoinColumn(name = "ID_CONTRATO", referencedColumnName = "ID_CONTRATO")
    @ManyToOne(optional = false)
    private RrhhContrato idContrato;
    @Column(name = "ESTADO")
    private String estado;

    public RrhhContratoEstado() {
    }

    public RrhhContratoEstado(BigDecimal idContratoEstado) {
        this.idContratoEstado = idContratoEstado;
    }

    public RrhhContratoEstado(BigDecimal idContratoEstado, String observacion, byte[] documento, String usuarioInsert, Date fechaInsert, String estado) {
        this.idContratoEstado = idContratoEstado;
        this.observacion = observacion;
        this.documento = documento;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
   
    public BigDecimal getIdContratoEstado() {
        return idContratoEstado;
    }

    public void setIdContratoEstado(BigDecimal idContratoEstado) {
        this.idContratoEstado = idContratoEstado;
    }

    public RrhhContrato getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(RrhhContrato idContrato) {
        this.idContrato = idContrato;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
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

    public RrhhCatalogoEstado getIdCatalogoEstado() {
        return idCatalogoEstado;
    }

    public void setIdCatalogoEstado(RrhhCatalogoEstado idCatalogoEstado) {
        this.idCatalogoEstado = idCatalogoEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContratoEstado != null ? idContratoEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhContratoEstado)) {
            return false;
        }
        RrhhContratoEstado other = (RrhhContratoEstado) object;
        if ((this.idContratoEstado == null && other.idContratoEstado != null) || (this.idContratoEstado != null && !this.idContratoEstado.equals(other.idContratoEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhContratoEstado[ idContratoEstado=" + idContratoEstado + " ]";
    }
    
}
