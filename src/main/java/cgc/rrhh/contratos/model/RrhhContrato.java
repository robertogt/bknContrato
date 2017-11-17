/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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
