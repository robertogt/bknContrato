/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import cgc.rrhh.contratos.pojo.ResultsAcademico;
import cgc.rrhh.contratos.pojo.ResultsContrato;
import cgc.rrhh.contratos.pojo.ResultsFuncionario;
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
@Table(name = "RRHH_CONTRATO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhContrato.findAll", query = "SELECT r FROM RrhhContrato r")
    , @NamedQuery(name = "RrhhContrato.findByIdContrato", query = "SELECT r FROM RrhhContrato r WHERE r.idContrato = :idContrato")
    , @NamedQuery(name = "RrhhContrato.findByIdentificadorContrato", query = "SELECT r FROM RrhhContrato r WHERE r.correlativoContrato = :correlativoContrato")    
    , @NamedQuery(name = "RrhhContrato.findByAnio", query = "SELECT r FROM RrhhContrato r WHERE r.anio = :anio")
    , @NamedQuery(name = "RrhhContrato.findByUsuarioInsert", query = "SELECT r FROM RrhhContrato r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhContrato.findByFechaInsert", query = "SELECT r FROM RrhhContrato r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhContrato.findByUsuarioUpdate", query = "SELECT r FROM RrhhContrato r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhContrato.findByFechaUpdate", query = "SELECT r FROM RrhhContrato r WHERE r.fechaUpdate = :fechaUpdate")})
@NamedNativeQueries({
    @NamedNativeQuery(name="RrhhContrato.findByContrato",
                        query="SELECT RUE.ID_RUE, RUE.CUI, TO_CHAR(LAB.FECHA_DEL,'dd/mm/yyyy') FECHA_DEL, TO_CHAR(LAB.FECHA_AL,'dd/mm/yyyy') FECHA_AL, "
                                + "TO_CHAR(LAB.FECHA_CAMBIO_TIPO_MOVIMIENTO,'dd/mm/yyyy') FECHA_CAMBIO_TIPO_MOVIMIENTO, " +
"LAB.RENGLON, LAB.TIPO_SERVICIOS, LAB.UBICACION_FUNCIONAL, UF.NOMBRE NOMBRE_UBICACION, " +
"(SELECT MAX(ID_PERFIL) ID_PERFIL FROM RRHH_ACTIVIDAD_CONTRATO WHERE ID_CONTRATO = ?idContrato) ID_PERFIL, LAB.HONORARIO "+
"FROM RRHH_RUE RUE " +
"INNER JOIN RRHH_LABORAL LAB ON RUE.ID_RUE = LAB.ID_RUE " +
"INNER JOIN RRHH_UBICACION_FUNCIONAL UF ON LAB.UBICACION_FUNCIONAL = UF.UBICACION_FUNCIONAL " +
"INNER JOIN RRHH_CONTRATO C ON LAB.ID_CONTRATO = C.ID_CONTRATO " +
"WHERE LAB.ESTADO = 'A' AND C.ID_CONTRATO = ?idContrato ",
                        resultSetMapping = "ResultFuncionario"),
    @NamedNativeQuery(name="RrhhContrato.listAllContrato",
                        query="SELECT L.ID_CONTRATO, " +
"R.PRIMER_NOMBRE||' '||R.SEGUNDO_NOMBRE||' '||R.PRIMER_APELLIDO||' '||R.SEGUNDO_APELLIDO NOMBRE_COMPLETO, " +
"L.NUMERO_CONTRATO, " +
"TO_CHAR(L.FECHA_DEL,'DD/MM/YYYY') FECHA_DEL, " +
"TO_CHAR(L.FECHA_AL,'DD/MM/YYYY') FECHA_AL, " +
"TO_CHAR(L.FECHA_CAMBIO_TIPO_MOVIMIENTO,'DD/MM/YYYY') FECHA_CAMBIO_TIPO_MOVIMIENTO, " +
"U.NOMBRE NOMBRE_UBICACION, " +
"RE.NOMBRE NOMBRE_RENGLON, " +
"CASE R.ESTADO_CIVIL WHEN 'C' THEN 'CASADO/A'  " +
"WHEN 'S' THEN 'SOLTERO/A' WHEN 'V' THEN 'VIUDO/A' WHEN 'D' THEN 'DIVORCIADO/A'  " +
"WHEN 'U' THEN 'UNIDO/A' END ESTADO_CIVIL_LETRAS, " +
"R.NIT, " +
"UPPER(R.DIRECCION||' '||M.NOMBRE||', '||D.NOMBRE) DIRECCION, " +
"REPLACE(REPLACE(R.CUI,'-',''),' ','') DPI, " +
"L.TIPO_SERVICIOS, " +
"T.NOMBRE TITULO, " +
"CP.NOMBRE_COLEGIO_PROFESIONAL, " +
"A.NUMERO_COLEGIADO, " +
"L.HONORARIO, " +
"CE.ID_CATALOGO_ESTADO, " +
"L.USUARIO_INSERT, " +
"L.FECHA_INSERT, " +
"L.USUARIO_UPDATE, " +
"L.FECHA_UPDATE, " +
"C.OBSERVACIONES "+
"FROM RRHH_LABORAL L " +
"INNER JOIN RRHH_RENGLON RE ON L.RENGLON = RE.RENGLON " +
"INNER JOIN RRHH_RUE R ON L.ID_RUE = R.ID_RUE " +
"INNER JOIN RRHH_CONTRATO C ON L.ID_CONTRATO = C.ID_CONTRATO " +
"INNER JOIN RRHH_UBICACION_FUNCIONAL U ON L.UBICACION_FUNCIONAL = U.UBICACION_FUNCIONAL " +
"INNER JOIN RRHH_ACADEMICO A ON C.ACADEMICO = A.ACADEMICO " +
"INNER JOIN RRHH_TITULO T ON A.TITULO = T.TITULO " +
"INNER JOIN RRHH_MUNICIPIO M ON R.MUNICIPIO_VIVIENDA = M.MUNICIPIO AND R.DEPARTAMENTO_VIVIENDA = M.DEPARTAMENTO  " +
"INNER JOIN RRHH_DEPARTAMENTO D ON M.DEPARTAMENTO = D.DEPARTAMENTO " +
"LEFT JOIN RRHH_COLEGIO_PROFESIONAL CP ON A.COLEGIO_PROFESIONAL = CP.COLEGIO_PROFESIONAL " +
"INNER JOIN ( SELECT C.ID_CONTRATO, C.ID_CATALOGO_ESTADO FROM RRHH_CONTRATO_ESTADO C WHERE ESTADO = 'A' AND ID_CATALOGO_ESTADO = DECODE(?,1,1, ID_CATALOGO_ESTADO )) CE ON C.ID_CONTRATO = CE.ID_CONTRATO " +
"WHERE L.ESTADO = ? ORDER BY L.FECHA_INSERT ASC",
                        resultSetMapping = "ResultsContrato")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "ResultsContrato",
                         classes = {@ConstructorResult(targetClass = ResultsContrato.class,
                                   columns = {
                                              @ColumnResult(name = "ID_CONTRATO", type = BigDecimal.class),
                                              @ColumnResult(name = "NOMBRE_COMPLETO", type = String.class),                                              
                                              @ColumnResult(name = "NUMERO_CONTRATO", type = String.class),
                                              @ColumnResult(name = "FECHA_DEL", type = String.class),
                                              @ColumnResult(name = "FECHA_AL", type = String.class),
                                              @ColumnResult(name = "FECHA_CAMBIO_TIPO_MOVIMIENTO", type = String.class),
                                              @ColumnResult(name = "NOMBRE_UBICACION", type = String.class),
                                              @ColumnResult(name = "NOMBRE_RENGLON", type = String.class),
                                              @ColumnResult(name = "ESTADO_CIVIL_LETRAS", type = String.class),
                                              @ColumnResult(name = "NIT", type = String.class),
                                              @ColumnResult(name = "DIRECCION", type = String.class),
                                              @ColumnResult(name = "DPI", type = String.class),
                                              @ColumnResult(name = "TIPO_SERVICIOS", type = String.class),
                                              @ColumnResult(name = "TITULO", type = String.class),
                                              @ColumnResult(name = "NOMBRE_COLEGIO_PROFESIONAL", type = String.class),
                                              @ColumnResult(name = "NUMERO_COLEGIADO", type = BigDecimal.class),
                                              @ColumnResult(name = "HONORARIO", type = BigDecimal.class),
                                              @ColumnResult(name = "ID_CATALOGO_ESTADO", type = BigDecimal.class),
                                              @ColumnResult(name = "USUARIO_INSERT", type = String.class),
                                              @ColumnResult(name = "FECHA_INSERT", type = Date.class),
                                              @ColumnResult(name = "USUARIO_UPDATE", type = String.class),
                                              @ColumnResult(name = "FECHA_UPDATE", type = Date.class),
                                              @ColumnResult(name = "OBSERVACIONES", type = String.class)
                                              
                                   })
                         }),
    @SqlResultSetMapping(name = "ResultFuncionario",
                         classes = {@ConstructorResult(targetClass = ResultsFuncionario.class,
                                   columns = {
                                              @ColumnResult(name = "ID_RUE", type = BigDecimal.class),
                                              @ColumnResult(name = "CUI", type = String.class),                                              
                                              @ColumnResult(name = "FECHA_DEL", type = String.class),
                                              @ColumnResult(name = "FECHA_AL", type = String.class),
                                              @ColumnResult(name = "FECHA_CAMBIO_TIPO_MOVIMIENTO", type = String.class),
                                              @ColumnResult(name = "RENGLON", type = String.class),
                                              @ColumnResult(name = "TIPO_SERVICIOS", type = String.class),
                                              @ColumnResult(name = "UBICACION_FUNCIONAL", type = BigDecimal.class),
                                              @ColumnResult(name = "NOMBRE_UBICACION", type = String.class),
                                              @ColumnResult(name = "ID_PERFIL", type = BigDecimal.class),
                                              @ColumnResult(name = "HONORARIO", type = Double.class)
                                   })
                         })
})

public class RrhhContrato implements Serializable {

  

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "cSequence", sequenceName = "SEQ_RRHH_CONTRATO", allocationSize = 1)
    @GeneratedValue(generator = "cSequence",strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_CONTRATO")
    private BigDecimal idContrato;
    @Column(name = "CORRELATIVO_CONTRATO")
    private BigInteger correlativoContrato;
    @Column(name = "ANIO")
    private BigInteger anio;
    @Size(max = 50)
    @Column(name = "USUARIO_INSERT")
    private String usuarioInsert;
    @Column(name = "FECHA_INSERT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInsert;
    @Size(max = 50)
    @Column(name = "USUARIO_UPDATE")
    private String usuarioUpdate;
    @Column(name = "FECHA_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUpdate;    
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "ID_PLANTILLA", referencedColumnName = "ID_PLANTILLA")
    @ManyToOne
    private RrhhPlantilla idPlantilla;
    @JoinColumn(name = "ACADEMICO", referencedColumnName = "ACADEMICO")
    @ManyToOne
    private RrhhAcademico academico;

    public RrhhContrato() {
    }

    public RrhhContrato(BigDecimal idContrato) {
        this.idContrato = idContrato;
    }

    public BigDecimal getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(BigDecimal idContrato) {
        this.idContrato = idContrato;
    }

    public BigInteger getAnio() {
        return anio;
    }

    public void setAnio(BigInteger anio) {
        this.anio = anio;
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

    public RrhhPlantilla getIdPlantilla() {
        return idPlantilla;
    }

    public void setIdPlantilla(RrhhPlantilla idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public RrhhAcademico getAcademico() {
        return academico;
    }

    public void setAcademico(RrhhAcademico academico) {
        this.academico = academico;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContrato != null ? idContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhContrato)) {
            return false;
        }
        RrhhContrato other = (RrhhContrato) object;
        if ((this.idContrato == null && other.idContrato != null) || (this.idContrato != null && !this.idContrato.equals(other.idContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhContrato[ idContrato=" + idContrato + " ]";
    }

    public BigInteger getCorrelativoContrato() {
        return correlativoContrato;
    }

    public void setCorrelativoContrato(BigInteger correlativoContrato) {
        this.correlativoContrato = correlativoContrato;
    }
    
}
