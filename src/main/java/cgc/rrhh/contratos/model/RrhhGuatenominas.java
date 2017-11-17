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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "RRHH_GUATENOMINAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhGuatenominas.findAll", query = "SELECT r FROM RrhhGuatenominas r")
    , @NamedQuery(name = "RrhhGuatenominas.findByNumeroPuesto", query = "SELECT r FROM RrhhGuatenominas r WHERE r.numeroPuesto = :numeroPuesto")
    , @NamedQuery(name = "RrhhGuatenominas.findBySector", query = "SELECT r FROM RrhhGuatenominas r WHERE r.sector = :sector")
    , @NamedQuery(name = "RrhhGuatenominas.findBySubsector", query = "SELECT r FROM RrhhGuatenominas r WHERE r.subsector = :subsector")
    , @NamedQuery(name = "RrhhGuatenominas.findByGrupo", query = "SELECT r FROM RrhhGuatenominas r WHERE r.grupo = :grupo")
    , @NamedQuery(name = "RrhhGuatenominas.findBySubgrupo", query = "SELECT r FROM RrhhGuatenominas r WHERE r.subgrupo = :subgrupo")
    , @NamedQuery(name = "RrhhGuatenominas.findByInstitucionEntidad", query = "SELECT r FROM RrhhGuatenominas r WHERE r.institucionEntidad = :institucionEntidad")
    , @NamedQuery(name = "RrhhGuatenominas.findByN1", query = "SELECT r FROM RrhhGuatenominas r WHERE r.n1 = :n1")
    , @NamedQuery(name = "RrhhGuatenominas.findByN2", query = "SELECT r FROM RrhhGuatenominas r WHERE r.n2 = :n2")
    , @NamedQuery(name = "RrhhGuatenominas.findByUbicacionGeografica", query = "SELECT r FROM RrhhGuatenominas r WHERE r.ubicacionGeografica = :ubicacionGeografica")
    , @NamedQuery(name = "RrhhGuatenominas.findByControlInterno", query = "SELECT r FROM RrhhGuatenominas r WHERE r.controlInterno = :controlInterno")
    , @NamedQuery(name = "RrhhGuatenominas.findByN3", query = "SELECT r FROM RrhhGuatenominas r WHERE r.n3 = :n3")
    , @NamedQuery(name = "RrhhGuatenominas.findByPrograma", query = "SELECT r FROM RrhhGuatenominas r WHERE r.programa = :programa")
    , @NamedQuery(name = "RrhhGuatenominas.findBySubprograma", query = "SELECT r FROM RrhhGuatenominas r WHERE r.subprograma = :subprograma")
    , @NamedQuery(name = "RrhhGuatenominas.findByProyecto", query = "SELECT r FROM RrhhGuatenominas r WHERE r.proyecto = :proyecto")
    , @NamedQuery(name = "RrhhGuatenominas.findByActividad", query = "SELECT r FROM RrhhGuatenominas r WHERE r.actividad = :actividad")
    , @NamedQuery(name = "RrhhGuatenominas.findByObra", query = "SELECT r FROM RrhhGuatenominas r WHERE r.obra = :obra")
    , @NamedQuery(name = "RrhhGuatenominas.findByRenglon", query = "SELECT r FROM RrhhGuatenominas r WHERE r.renglon = :renglon")
    , @NamedQuery(name = "RrhhGuatenominas.findByIndividual", query = "SELECT r FROM RrhhGuatenominas r WHERE r.individual = :individual")
    , @NamedQuery(name = "RrhhGuatenominas.findByUsuarioInsert", query = "SELECT r FROM RrhhGuatenominas r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhGuatenominas.findByFechaInsert", query = "SELECT r FROM RrhhGuatenominas r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhGuatenominas.findByEstado", query = "SELECT r FROM RrhhGuatenominas r WHERE r.estado = :estado")
    , @NamedQuery(name = "RrhhGuatenominas.findByPartidaPresupuestaria", query = "SELECT r FROM RrhhGuatenominas r WHERE r.partidaPresupuestaria = :partidaPresupuestaria")
    , @NamedQuery(name = "RrhhGuatenominas.findByUsuarioUpdate", query = "SELECT r FROM RrhhGuatenominas r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhGuatenominas.findByFechaUpdate", query = "SELECT r FROM RrhhGuatenominas r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhGuatenominas.findBySueldoBase", query = "SELECT r FROM RrhhGuatenominas r WHERE r.sueldoBase = :sueldoBase")
    , @NamedQuery(name = "RrhhGuatenominas.findByFechaInicioVigencia", query = "SELECT r FROM RrhhGuatenominas r WHERE r.fechaInicioVigencia = :fechaInicioVigencia")
    , @NamedQuery(name = "RrhhGuatenominas.findByFechaFinVigencia", query = "SELECT r FROM RrhhGuatenominas r WHERE r.fechaFinVigencia = :fechaFinVigencia")
    , @NamedQuery(name = "RrhhGuatenominas.findByIdNumeroPuesto", query = "SELECT r FROM RrhhGuatenominas r WHERE r.idNumeroPuesto = :idNumeroPuesto")})
public class RrhhGuatenominas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "NUMERO_PUESTO")
    private BigInteger numeroPuesto;
    @Column(name = "SECTOR")
    private Short sector;
    @Column(name = "SUBSECTOR")
    private Short subsector;
    @Column(name = "GRUPO")
    private Short grupo;
    @Column(name = "SUBGRUPO")
    private Short subgrupo;
    @Size(max = 4)
    @Column(name = "INSTITUCION_ENTIDAD")
    private String institucionEntidad;
    @Size(max = 4)
    @Column(name = "N1")
    private String n1;
    @Size(max = 2)
    @Column(name = "N2")
    private String n2;
    @Size(max = 4)
    @Column(name = "UBICACION_GEOGRAFICA")
    private String ubicacionGeografica;
    @Size(max = 4)
    @Column(name = "CONTROL_INTERNO")
    private String controlInterno;
    @Size(max = 2)
    @Column(name = "N3")
    private String n3;
    @Size(max = 2)
    @Column(name = "PROGRAMA")
    private String programa;
    @Size(max = 2)
    @Column(name = "SUBPROGRAMA")
    private String subprograma;
    @Size(max = 3)
    @Column(name = "PROYECTO")
    private String proyecto;
    @Size(max = 3)
    @Column(name = "ACTIVIDAD")
    private String actividad;
    @Size(max = 3)
    @Column(name = "OBRA")
    private String obra;
    @Size(max = 3)
    @Column(name = "RENGLON")
    private String renglon;
    @Size(max = 5)
    @Column(name = "INDIVIDUAL")
    private String individual;
    @Size(max = 50)
    @Column(name = "USUARIO_INSERT")
    private String usuarioInsert;
    @Column(name = "FECHA_INSERT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInsert;
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 100)
    @Column(name = "PARTIDA_PRESUPUESTARIA")
    private String partidaPresupuestaria;
    @Size(max = 50)
    @Column(name = "USUARIO_UPDATE")
    private String usuarioUpdate;
    @Column(name = "FECHA_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUpdate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SUELDO_BASE")
    private BigDecimal sueldoBase;
    @Column(name = "FECHA_INICIO_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioVigencia;
    @Column(name = "FECHA_FIN_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinVigencia;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_NUMERO_PUESTO")
    private Long idNumeroPuesto;
    @JoinColumn(name = "ESPECIALIDAD", referencedColumnName = "ESPECIALIDAD")
    @ManyToOne
    private RrhhEspecialidad especialidad;
    @JoinColumn(name = "PUESTO_NOMINAL", referencedColumnName = "PUESTO_NOMINAL")
    @ManyToOne
    private RrhhPuestoNominalClass puestoNominal;
    @JoinColumn(name = "UBICACION_NOMINAL", referencedColumnName = "UBICACION_NOMINAL")
    @ManyToOne
    private RrhhUbicacionNominal ubicacionNominal;
    @OneToMany(mappedBy = "idNumeroPuesto")
    private List<RrhhLaboral> rrhhLaboralList;

    public RrhhGuatenominas() {
    }

    public RrhhGuatenominas(Long idNumeroPuesto) {
        this.idNumeroPuesto = idNumeroPuesto;
    }

    public BigInteger getNumeroPuesto() {
        return numeroPuesto;
    }

    public void setNumeroPuesto(BigInteger numeroPuesto) {
        this.numeroPuesto = numeroPuesto;
    }

    public Short getSector() {
        return sector;
    }

    public void setSector(Short sector) {
        this.sector = sector;
    }

    public Short getSubsector() {
        return subsector;
    }

    public void setSubsector(Short subsector) {
        this.subsector = subsector;
    }

    public Short getGrupo() {
        return grupo;
    }

    public void setGrupo(Short grupo) {
        this.grupo = grupo;
    }

    public Short getSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(Short subgrupo) {
        this.subgrupo = subgrupo;
    }

    public String getInstitucionEntidad() {
        return institucionEntidad;
    }

    public void setInstitucionEntidad(String institucionEntidad) {
        this.institucionEntidad = institucionEntidad;
    }

    public String getN1() {
        return n1;
    }

    public void setN1(String n1) {
        this.n1 = n1;
    }

    public String getN2() {
        return n2;
    }

    public void setN2(String n2) {
        this.n2 = n2;
    }

    public String getUbicacionGeografica() {
        return ubicacionGeografica;
    }

    public void setUbicacionGeografica(String ubicacionGeografica) {
        this.ubicacionGeografica = ubicacionGeografica;
    }

    public String getControlInterno() {
        return controlInterno;
    }

    public void setControlInterno(String controlInterno) {
        this.controlInterno = controlInterno;
    }

    public String getN3() {
        return n3;
    }

    public void setN3(String n3) {
        this.n3 = n3;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getSubprograma() {
        return subprograma;
    }

    public void setSubprograma(String subprograma) {
        this.subprograma = subprograma;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }

    public String getRenglon() {
        return renglon;
    }

    public void setRenglon(String renglon) {
        this.renglon = renglon;
    }

    public String getIndividual() {
        return individual;
    }

    public void setIndividual(String individual) {
        this.individual = individual;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPartidaPresupuestaria() {
        return partidaPresupuestaria;
    }

    public void setPartidaPresupuestaria(String partidaPresupuestaria) {
        this.partidaPresupuestaria = partidaPresupuestaria;
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

    public BigDecimal getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(BigDecimal sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public Long getIdNumeroPuesto() {
        return idNumeroPuesto;
    }

    public void setIdNumeroPuesto(Long idNumeroPuesto) {
        this.idNumeroPuesto = idNumeroPuesto;
    }

    public RrhhEspecialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(RrhhEspecialidad especialidad) {
        this.especialidad = especialidad;
    }

    public RrhhPuestoNominalClass getPuestoNominal() {
        return puestoNominal;
    }

    public void setPuestoNominal(RrhhPuestoNominalClass puestoNominal) {
        this.puestoNominal = puestoNominal;
    }

    public RrhhUbicacionNominal getUbicacionNominal() {
        return ubicacionNominal;
    }

    public void setUbicacionNominal(RrhhUbicacionNominal ubicacionNominal) {
        this.ubicacionNominal = ubicacionNominal;
    }

    @XmlTransient
    public List<RrhhLaboral> getRrhhLaboralList() {
        return rrhhLaboralList;
    }

    public void setRrhhLaboralList(List<RrhhLaboral> rrhhLaboralList) {
        this.rrhhLaboralList = rrhhLaboralList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNumeroPuesto != null ? idNumeroPuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhGuatenominas)) {
            return false;
        }
        RrhhGuatenominas other = (RrhhGuatenominas) object;
        if ((this.idNumeroPuesto == null && other.idNumeroPuesto != null) || (this.idNumeroPuesto != null && !this.idNumeroPuesto.equals(other.idNumeroPuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.rrhh.contratos.model.RrhhGuatenominas[ idNumeroPuesto=" + idNumeroPuesto + " ]";
    }
    
}
