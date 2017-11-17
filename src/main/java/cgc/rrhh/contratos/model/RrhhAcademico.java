/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "RRHH_ACADEMICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhAcademico.findAll", query = "SELECT r FROM RrhhAcademico r")
    , @NamedQuery(name = "RrhhAcademico.findByAcademico", query = "SELECT r FROM RrhhAcademico r WHERE r.academico = :academico")
    , @NamedQuery(name = "RrhhAcademico.findByUsuarioInsert", query = "SELECT r FROM RrhhAcademico r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhAcademico.findByFechaInsert", query = "SELECT r FROM RrhhAcademico r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhAcademico.findByUsuarioUpdate", query = "SELECT r FROM RrhhAcademico r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhAcademico.findByFechaUpdate", query = "SELECT r FROM RrhhAcademico r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhAcademico.findByNivelEducativo", query = "SELECT r FROM RrhhAcademico r WHERE r.nivelEducativo = :nivelEducativo")
    , @NamedQuery(name = "RrhhAcademico.findByDependencia", query = "SELECT r FROM RrhhAcademico r WHERE r.dependencia = :dependencia")
    , @NamedQuery(name = "RrhhAcademico.findByFechaTitulo", query = "SELECT r FROM RrhhAcademico r WHERE r.fechaTitulo = :fechaTitulo")
    , @NamedQuery(name = "RrhhAcademico.findByNumeroColegiado", query = "SELECT r FROM RrhhAcademico r WHERE r.numeroColegiado = :numeroColegiado")
    , @NamedQuery(name = "RrhhAcademico.findByFechaColegiacion", query = "SELECT r FROM RrhhAcademico r WHERE r.fechaColegiacion = :fechaColegiacion")
    , @NamedQuery(name = "RrhhAcademico.findByVenceColegiacion", query = "SELECT r FROM RrhhAcademico r WHERE r.venceColegiacion = :venceColegiacion")
    , @NamedQuery(name = "RrhhAcademico.findByNumeroConstancia", query = "SELECT r FROM RrhhAcademico r WHERE r.numeroConstancia = :numeroConstancia")
    , @NamedQuery(name = "RrhhAcademico.findByEstado", query = "SELECT r FROM RrhhAcademico r WHERE r.estado = :estado")
    , @NamedQuery(name = "RrhhAcademico.findByActivoPerpetuo", query = "SELECT r FROM RrhhAcademico r WHERE r.activoPerpetuo = :activoPerpetuo")
    , @NamedQuery(name = "RrhhAcademico.findByNumeroColegiado2", query = "SELECT r FROM RrhhAcademico r WHERE r.numeroColegiado2 = :numeroColegiado2")
    , @NamedQuery(name = "RrhhAcademico.findByFechaColegiacion2", query = "SELECT r FROM RrhhAcademico r WHERE r.fechaColegiacion2 = :fechaColegiacion2")
    , @NamedQuery(name = "RrhhAcademico.findByVenceColegiacion2", query = "SELECT r FROM RrhhAcademico r WHERE r.venceColegiacion2 = :venceColegiacion2")
    , @NamedQuery(name = "RrhhAcademico.findByNumeroConstancia2", query = "SELECT r FROM RrhhAcademico r WHERE r.numeroConstancia2 = :numeroConstancia2")
    , @NamedQuery(name = "RrhhAcademico.findByNivelAcademico", query = "SELECT r FROM RrhhAcademico r WHERE r.nivelAcademico = :nivelAcademico")
    , @NamedQuery(name = "RrhhAcademico.findByTituloRueColegio", query = "SELECT r FROM RrhhAcademico r WHERE r.titulo.titulo = :titulo AND r.colegioProfesional.colegioProfesional = :colegioProfesional AND r.idRue.idRue = :rue")
    , @NamedQuery(name = "RrhhAcademico.findByTituloRue", query = "SELECT r FROM RrhhAcademico r WHERE r.titulo.titulo = :titulo AND r.idRue.idRue = :rue")})
public class RrhhAcademico implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "aSequence", sequenceName = "SEQ_RRHH_ACADEMICO", allocationSize = 1)
    @GeneratedValue(generator = "aSequence",strategy = GenerationType.SEQUENCE)
    @Column(name = "ACADEMICO")
    private BigDecimal academico;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "NIVEL_EDUCATIVO")
    private String nivelEducativo;
    @Size(max = 100)
    @Column(name = "DEPENDENCIA")
    private String dependencia;
    @Column(name = "FECHA_TITULO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTitulo;
    @Size(max = 10)
    @Column(name = "NUMERO_COLEGIADO")
    private String numeroColegiado;
    @Column(name = "FECHA_COLEGIACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaColegiacion;
    @Column(name = "VENCE_COLEGIACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date venceColegiacion;
    @Size(max = 20)
    @Column(name = "NUMERO_CONSTANCIA")
    private String numeroConstancia;
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 1)
    @Column(name = "ACTIVO_PERPETUO")
    private String activoPerpetuo;
    @Size(max = 10)
    @Column(name = "NUMERO_COLEGIADO2")
    private String numeroColegiado2;
    @Column(name = "FECHA_COLEGIACION2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaColegiacion2;
    @Column(name = "VENCE_COLEGIACION2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date venceColegiacion2;
    @Size(max = 20)
    @Column(name = "NUMERO_CONSTANCIA2")
    private String numeroConstancia2;
    @Size(max = 1)
    @Column(name = "NIVEL_ACADEMICO")
    private String nivelAcademico;
    @JoinColumn(name = "COLEGIO_PROFESIONAL2", referencedColumnName = "COLEGIO_PROFESIONAL")
    @ManyToOne
    private RrhhColegioProfesional colegioProfesional2;
    @JoinColumn(name = "COLEGIO_PROFESIONAL", referencedColumnName = "COLEGIO_PROFESIONAL")
    @ManyToOne
    private RrhhColegioProfesional colegioProfesional;
    @JoinColumn(name = "GRADO_ACADEMICO", referencedColumnName = "GRADO_ACADEMICO")
    @ManyToOne
    private RrhhGradoAcademico gradoAcademico;
    @JoinColumn(name = "ID_RUE", referencedColumnName = "ID_RUE")
    @ManyToOne(optional = false)
    private RrhhRue idRue;
    @JoinColumn(name = "TITULO", referencedColumnName = "TITULO")
    @ManyToOne
    private RrhhTitulo titulo;

    public RrhhAcademico() {
    }

    public RrhhAcademico(BigDecimal academico) {
        this.academico = academico;
    }

    public RrhhAcademico(BigDecimal academico, String usuarioInsert, Date fechaInsert, String nivelEducativo) {
        this.academico = academico;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
        this.nivelEducativo = nivelEducativo;
    }

    public BigDecimal getAcademico() {
        return academico;
    }

    public void setAcademico(BigDecimal academico) {
        this.academico = academico;
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

    public String getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(String nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public Date getFechaTitulo() {
        return fechaTitulo;
    }

    public void setFechaTitulo(Date fechaTitulo) {
        this.fechaTitulo = fechaTitulo;
    }

    public String getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(String numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

    public Date getFechaColegiacion() {
        return fechaColegiacion;
    }

    public void setFechaColegiacion(Date fechaColegiacion) {
        this.fechaColegiacion = fechaColegiacion;
    }

    public Date getVenceColegiacion() {
        return venceColegiacion;
    }

    public void setVenceColegiacion(Date venceColegiacion) {
        this.venceColegiacion = venceColegiacion;
    }

    public String getNumeroConstancia() {
        return numeroConstancia;
    }

    public void setNumeroConstancia(String numeroConstancia) {
        this.numeroConstancia = numeroConstancia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getActivoPerpetuo() {
        return activoPerpetuo;
    }

    public void setActivoPerpetuo(String activoPerpetuo) {
        this.activoPerpetuo = activoPerpetuo;
    }

    public String getNumeroColegiado2() {
        return numeroColegiado2;
    }

    public void setNumeroColegiado2(String numeroColegiado2) {
        this.numeroColegiado2 = numeroColegiado2;
    }

    public Date getFechaColegiacion2() {
        return fechaColegiacion2;
    }

    public void setFechaColegiacion2(Date fechaColegiacion2) {
        this.fechaColegiacion2 = fechaColegiacion2;
    }

    public Date getVenceColegiacion2() {
        return venceColegiacion2;
    }

    public void setVenceColegiacion2(Date venceColegiacion2) {
        this.venceColegiacion2 = venceColegiacion2;
    }

    public String getNumeroConstancia2() {
        return numeroConstancia2;
    }

    public void setNumeroConstancia2(String numeroConstancia2) {
        this.numeroConstancia2 = numeroConstancia2;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public RrhhColegioProfesional getColegioProfesional2() {
        return colegioProfesional2;
    }

    public void setColegioProfesional2(RrhhColegioProfesional colegioProfesional2) {
        this.colegioProfesional2 = colegioProfesional2;
    }

    public RrhhColegioProfesional getColegioProfesional() {
        return colegioProfesional;
    }

    public void setColegioProfesional(RrhhColegioProfesional colegioProfesional) {
        this.colegioProfesional = colegioProfesional;
    }

    public RrhhGradoAcademico getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(RrhhGradoAcademico gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public RrhhRue getIdRue() {
        return idRue;
    }

    public void setIdRue(RrhhRue idRue) {
        this.idRue = idRue;
    }

    public RrhhTitulo getTitulo() {
        return titulo;
    }

    public void setTitulo(RrhhTitulo titulo) {
        this.titulo = titulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (academico != null ? academico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhAcademico)) {
            return false;
        }
        RrhhAcademico other = (RrhhAcademico) object;
        if ((this.academico == null && other.academico != null) || (this.academico != null && !this.academico.equals(other.academico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhAcademico[ academico=" + academico + " ]";
    }
    
}
