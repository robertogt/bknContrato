/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import cgc.rrhh.contratos.pojo.ResultsContrato;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author jrroquel
 */
@Entity
@Table(name = "RRHH_ACUERDO_CONTRATO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhAcuerdoContrato.findAll", query = "SELECT r FROM RrhhAcuerdoContrato r")
    , @NamedQuery(name = "RrhhAcuerdoContrato.findByIdAcuerdoContrato", query = "SELECT r FROM RrhhAcuerdoContrato r WHERE r.idAcuerdoContrato = :idAcuerdoContrato")
    , @NamedQuery(name = "RrhhAcuerdoContrato.findByEstado", query = "SELECT r FROM RrhhAcuerdoContrato r WHERE r.estado = :estado")
    , @NamedQuery(name = "RrhhAcuerdoContrato.findByUsuarioInsert", query = "SELECT r FROM RrhhAcuerdoContrato r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhAcuerdoContrato.findByFechaInsert", query = "SELECT r FROM RrhhAcuerdoContrato r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhAcuerdoContrato.findByUsuarioUpdate", query = "SELECT r FROM RrhhAcuerdoContrato r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhAcuerdoContrato.findByFechaUpdate", query = "SELECT r FROM RrhhAcuerdoContrato r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhAcuerdoContrato.findByAcuerdoAprobacion", query = "SELECT r FROM RrhhAcuerdoContrato r WHERE r.idAcuerdoAprobacion.idAcuerdoAprobacion = :acuerdoAprobacion AND r.estado = 'A'")
    , @NamedQuery(name = "RrhhAcuerdoContrato.findByAcuerdoByContrato", query = "SELECT r FROM RrhhAcuerdoContrato r WHERE r.idAcuerdoAprobacion.idAcuerdoAprobacion = :acuerdoAprobacion AND r.idContrato.idContrato = :contrato")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "RrhhAcuerdoContrato.NativeQueryAcuerdoContratos",
                      query = "SELECT ROWNUM CORRELATIVO,ID_CONTRATO,NOMBRE_COMPLETO,NUMERO_CONTRATO,FECHA_CAMBIO_TIPO_MOVIMIENTO,NUMERO_FIANZA FROM ( "+
"SELECT R.PRIMER_NOMBRE||' '||R.SEGUNDO_NOMBRE||' '||R.PRIMER_APELLIDO||' '||R.SEGUNDO_APELLIDO NOMBRE_COMPLETO, " +
"L.ID_CONTRATO, "+                              
"L.NUMERO_CONTRATO, " +
"TO_CHAR(L.FECHA_CAMBIO_TIPO_MOVIMIENTO, 'DD/MM/YYYY') FECHA_CAMBIO_TIPO_MOVIMIENTO, " +
"L.NUMERO_FIANZA, " +
"L.ESTADO " +
"FROM RRHH_CONTRATO C  " +
"INNER JOIN RRHH_LABORAL L ON C.ID_CONTRATO = L.ID_CONTRATO " +
"INNER JOIN RRHH_RUE R ON L.ID_RUE = R.ID_RUE " +
"INNER JOIN RRHH_ACUERDO_CONTRATO A ON C.ID_CONTRATO = A.ID_CONTRATO  " +
"WHERE A.ID_ACUERDO_APROBACION = ?acuerdo " +
"AND A.ESTADO = 'A' "+
"UNION ALL  " +
"SELECT R.PRIMER_NOMBRE||' '||R.SEGUNDO_NOMBRE||' '||R.PRIMER_APELLIDO||' '||R.SEGUNDO_APELLIDO NOMBRE_COMPLETO, " +
"L.ID_CONTRATO, "+ 
"L.NUMERO_CONTRATO, " +
"TO_CHAR(L.FECHA_CAMBIO_TIPO_MOVIMIENTO, 'DD/MM/YYYY') FECHA_CAMBIO_TIPO_MOVIMIENTO, " +
"L.NUMERO_FIANZA, " +
"L.ESTADO " +
"FROM RRHH_CONTRATO C  " +
"INNER JOIN RRHH_LABORAL L ON C.ID_CONTRATO = L.ID_CONTRATO  " +
"INNER JOIN RRHH_RUE R ON L.ID_RUE = R.ID_RUE " +
"INNER JOIN RRHH_CONTRATO_ESTADO CE ON C.ID_CONTRATO = CE.ID_CONTRATO " +
"WHERE L.TIPO_SERVICIOS = ?tipo " +
"AND RENGLON = ?renglon  " +
"AND C.ANIO = ?anio " +
"AND CE.ID_CATALOGO_ESTADO = 4 " +
"AND CE.ESTADO = 'A' "+
"AND L.ESTADO = 'I') ",
                      resultSetMapping = "ResultsContratoAcuerdo"),
    @NamedNativeQuery(name = "RrhhAcuerdoContrato.QueryContrato",
                      query = "SELECT ROWNUM CORRELATIVO, "+
"L.ID_CONTRATO, "+ 
"R.PRIMER_NOMBRE||' '||R.SEGUNDO_NOMBRE||' '||R.PRIMER_APELLIDO||' '||R.SEGUNDO_APELLIDO NOMBRE_COMPLETO, " +
"L.NUMERO_CONTRATO, " +
"TO_CHAR(L.FECHA_CAMBIO_TIPO_MOVIMIENTO, 'DD/MM/YYYY') FECHA_CAMBIO_TIPO_MOVIMIENTO, " +
"L.NUMERO_FIANZA " +
"FROM RRHH_CONTRATO C  " +
"INNER JOIN RRHH_LABORAL L ON C.ID_CONTRATO = L.ID_CONTRATO " +
"INNER JOIN RRHH_RUE R ON L.ID_RUE = R.ID_RUE " +
"INNER JOIN RRHH_ACUERDO_CONTRATO A ON C.ID_CONTRATO = A.ID_CONTRATO  " +
"WHERE A.ID_ACUERDO_APROBACION = ?acuerdo "+
"AND A.ESTADO = 'A' ",
                      resultSetMapping = "ResultsContratoAcuerdo")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "ResultsContratoAcuerdo",
                        classes = {@ConstructorResult(targetClass = ResultsContrato.class,
                                columns = {@ColumnResult(name = "CORRELATIVO", type = BigDecimal.class ),
                                           @ColumnResult(name = "ID_CONTRATO", type = BigDecimal.class ),
                                           @ColumnResult(name = "NOMBRE_COMPLETO", type = String.class ),
                                           @ColumnResult(name = "NUMERO_CONTRATO", type = String.class ),
                                           @ColumnResult(name = "FECHA_CAMBIO_TIPO_MOVIMIENTO", type = String.class ),
                                           @ColumnResult(name = "NUMERO_FIANZA", type = String.class )
                                })
                        })
})
public class RrhhAcuerdoContrato implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "iSequence", sequenceName = "SEQ_RRHH_ACUERDO_CONTRATO", allocationSize = 1)
    @GeneratedValue(generator = "iSequence",strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_ACUERDO_CONTRATO")
    private BigDecimal idAcuerdoContrato;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INSERT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInsert;
    @Size(max = 100)
    @Column(name = "USUARIO_UPDATE")
    private String usuarioUpdate;
    @Column(name = "FECHA_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUpdate;
    @JoinColumn(name = "ID_ACUERDO_APROBACION", referencedColumnName = "ID_ACUERDO_APROBACION")
    @ManyToOne(optional = false)
    private RrhhAcuerdoAprobacion idAcuerdoAprobacion;
    @JoinColumn(name = "ID_CONTRATO", referencedColumnName = "ID_CONTRATO")
    @ManyToOne(optional = false)
    private RrhhContrato idContrato;

    public RrhhAcuerdoContrato() {
    }

    public RrhhAcuerdoContrato(BigDecimal idAcuerdoContrato) {
        this.idAcuerdoContrato = idAcuerdoContrato;
    }

    public RrhhAcuerdoContrato(BigDecimal idAcuerdoContrato, String estado, String usuarioInsert, Date fechaInsert) {
        this.idAcuerdoContrato = idAcuerdoContrato;
        this.estado = estado;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
    }

    public BigDecimal getIdAcuerdoContrato() {
        return idAcuerdoContrato;
    }

    public void setIdAcuerdoContrato(BigDecimal idAcuerdoContrato) {
        this.idAcuerdoContrato = idAcuerdoContrato;
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

    public RrhhAcuerdoAprobacion getIdAcuerdoAprobacion() {
        return idAcuerdoAprobacion;
    }

    public void setIdAcuerdoAprobacion(RrhhAcuerdoAprobacion idAcuerdoAprobacion) {
        this.idAcuerdoAprobacion = idAcuerdoAprobacion;
    }

    public RrhhContrato getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(RrhhContrato idContrato) {
        this.idContrato = idContrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcuerdoContrato != null ? idAcuerdoContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhAcuerdoContrato)) {
            return false;
        }
        RrhhAcuerdoContrato other = (RrhhAcuerdoContrato) object;
        if ((this.idAcuerdoContrato == null && other.idAcuerdoContrato != null) || (this.idAcuerdoContrato != null && !this.idAcuerdoContrato.equals(other.idAcuerdoContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhAcuerdoContrato[ idAcuerdoContrato=" + idAcuerdoContrato + " ]";
    }
    
}
