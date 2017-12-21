/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import cgc.rrhh.contratos.pojo.ResultsAcademico;
import cgc.rrhh.contratos.pojo.ResultsFuncionario;
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "RRHH_RUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhRue.findAll", query = "SELECT r FROM RrhhRue r")
    , @NamedQuery(name = "RrhhRue.findByRue", query = "SELECT r FROM RrhhRue r WHERE r.rue = :rue")
    , @NamedQuery(name = "RrhhRue.findByUsuarioInsert", query = "SELECT r FROM RrhhRue r WHERE r.usuarioInsert = :usuarioInsert")
    , @NamedQuery(name = "RrhhRue.findByFechaInsert", query = "SELECT r FROM RrhhRue r WHERE r.fechaInsert = :fechaInsert")
    , @NamedQuery(name = "RrhhRue.findByUsuarioUpdate", query = "SELECT r FROM RrhhRue r WHERE r.usuarioUpdate = :usuarioUpdate")
    , @NamedQuery(name = "RrhhRue.findByFechaUpdate", query = "SELECT r FROM RrhhRue r WHERE r.fechaUpdate = :fechaUpdate")
    , @NamedQuery(name = "RrhhRue.findByGuatenomina", query = "SELECT r FROM RrhhRue r WHERE r.guatenomina = :guatenomina")
    , @NamedQuery(name = "RrhhRue.findByPrimerApellido", query = "SELECT r FROM RrhhRue r WHERE r.primerApellido = :primerApellido")
    , @NamedQuery(name = "RrhhRue.findBySegundoApellido", query = "SELECT r FROM RrhhRue r WHERE r.segundoApellido = :segundoApellido")
    , @NamedQuery(name = "RrhhRue.findByApellidoCasada", query = "SELECT r FROM RrhhRue r WHERE r.apellidoCasada = :apellidoCasada")
    , @NamedQuery(name = "RrhhRue.findByPrimerNombre", query = "SELECT r FROM RrhhRue r WHERE r.primerNombre = :primerNombre")
    , @NamedQuery(name = "RrhhRue.findBySegundoNombre", query = "SELECT r FROM RrhhRue r WHERE r.segundoNombre = :segundoNombre")
    , @NamedQuery(name = "RrhhRue.findByTercerNombre", query = "SELECT r FROM RrhhRue r WHERE r.tercerNombre = :tercerNombre")
    , @NamedQuery(name = "RrhhRue.findByFechaNacimiento", query = "SELECT r FROM RrhhRue r WHERE r.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "RrhhRue.findByGenero", query = "SELECT r FROM RrhhRue r WHERE r.genero = :genero")
    , @NamedQuery(name = "RrhhRue.findByEstadoCivil", query = "SELECT r FROM RrhhRue r WHERE r.estadoCivil = :estadoCivil")
    , @NamedQuery(name = "RrhhRue.findByTipoIdentificacion", query = "SELECT r FROM RrhhRue r WHERE r.tipoIdentificacion = :tipoIdentificacion")
    , @NamedQuery(name = "RrhhRue.findByNumeroRegistro", query = "SELECT r FROM RrhhRue r WHERE r.numeroRegistro = :numeroRegistro")
    , @NamedQuery(name = "RrhhRue.findByCui", query = "SELECT r FROM RrhhRue r WHERE r.cui = :cui")
    , @NamedQuery(name = "RrhhRue.findByPasaporte", query = "SELECT r FROM RrhhRue r WHERE r.pasaporte = :pasaporte")
    , @NamedQuery(name = "RrhhRue.findByNit", query = "SELECT r FROM RrhhRue r WHERE r.nit = :nit")
    , @NamedQuery(name = "RrhhRue.findByDireccion", query = "SELECT r FROM RrhhRue r WHERE r.direccion = :direccion")
    , @NamedQuery(name = "RrhhRue.findByDepartamentoVivienda", query = "SELECT r FROM RrhhRue r WHERE r.departamentoVivienda = :departamentoVivienda")
    , @NamedQuery(name = "RrhhRue.findByMunicipioVivienda", query = "SELECT r FROM RrhhRue r WHERE r.municipioVivienda = :municipioVivienda")
    , @NamedQuery(name = "RrhhRue.findByTelefonoCasa", query = "SELECT r FROM RrhhRue r WHERE r.telefonoCasa = :telefonoCasa")
    , @NamedQuery(name = "RrhhRue.findByTelefonoCelular", query = "SELECT r FROM RrhhRue r WHERE r.telefonoCelular = :telefonoCelular")
    , @NamedQuery(name = "RrhhRue.findByEmail", query = "SELECT r FROM RrhhRue r WHERE r.email = :email")
    , @NamedQuery(name = "RrhhRue.findByRuap", query = "SELECT r FROM RrhhRue r WHERE r.ruap = :ruap")
    , @NamedQuery(name = "RrhhRue.findByCuentaBancaria", query = "SELECT r FROM RrhhRue r WHERE r.cuentaBancaria = :cuentaBancaria")
    , @NamedQuery(name = "RrhhRue.findByTipoSangre", query = "SELECT r FROM RrhhRue r WHERE r.tipoSangre = :tipoSangre")
    , @NamedQuery(name = "RrhhRue.findByAlergias", query = "SELECT r FROM RrhhRue r WHERE r.alergias = :alergias")
    , @NamedQuery(name = "RrhhRue.findByContactoEmergencia", query = "SELECT r FROM RrhhRue r WHERE r.contactoEmergencia = :contactoEmergencia")
    , @NamedQuery(name = "RrhhRue.findByLicenciaConducir", query = "SELECT r FROM RrhhRue r WHERE r.licenciaConducir = :licenciaConducir")
    , @NamedQuery(name = "RrhhRue.findByTipoLicenciaConducir", query = "SELECT r FROM RrhhRue r WHERE r.tipoLicenciaConducir = :tipoLicenciaConducir")
    , @NamedQuery(name = "RrhhRue.findByNumeroLicenciaConducir", query = "SELECT r FROM RrhhRue r WHERE r.numeroLicenciaConducir = :numeroLicenciaConducir")
    , @NamedQuery(name = "RrhhRue.findByVenceLicenciaConducir", query = "SELECT r FROM RrhhRue r WHERE r.venceLicenciaConducir = :venceLicenciaConducir")
    , @NamedQuery(name = "RrhhRue.findByLicenciaArmas", query = "SELECT r FROM RrhhRue r WHERE r.licenciaArmas = :licenciaArmas")
    , @NamedQuery(name = "RrhhRue.findByNumeroLicenciaArmas", query = "SELECT r FROM RrhhRue r WHERE r.numeroLicenciaArmas = :numeroLicenciaArmas")
    , @NamedQuery(name = "RrhhRue.findByVenceLicenciaArmas", query = "SELECT r FROM RrhhRue r WHERE r.venceLicenciaArmas = :venceLicenciaArmas")
    , @NamedQuery(name = "RrhhRue.findByNombrePadre", query = "SELECT r FROM RrhhRue r WHERE r.nombrePadre = :nombrePadre")
    , @NamedQuery(name = "RrhhRue.findByNombreMadre", query = "SELECT r FROM RrhhRue r WHERE r.nombreMadre = :nombreMadre")
    , @NamedQuery(name = "RrhhRue.findByNombreConyuge", query = "SELECT r FROM RrhhRue r WHERE r.nombreConyuge = :nombreConyuge")
    , @NamedQuery(name = "RrhhRue.findByFoto", query = "SELECT r FROM RrhhRue r WHERE r.foto = :foto")
    , @NamedQuery(name = "RrhhRue.findByLoginRed", query = "SELECT r FROM RrhhRue r WHERE r.loginRed = :loginRed")
    , @NamedQuery(name = "RrhhRue.findByLoginBd", query = "SELECT r FROM RrhhRue r WHERE r.loginBd = :loginBd")
    , @NamedQuery(name = "RrhhRue.findByLoginApp", query = "SELECT r FROM RrhhRue r WHERE r.loginApp = :loginApp")
    , @NamedQuery(name = "RrhhRue.findByFechaFicha", query = "SELECT r FROM RrhhRue r WHERE r.fechaFicha = :fechaFicha")
    , @NamedQuery(name = "RrhhRue.findByUsuarioFicha", query = "SELECT r FROM RrhhRue r WHERE r.usuarioFicha = :usuarioFicha")
    , @NamedQuery(name = "RrhhRue.findByNumeroProbidad", query = "SELECT r FROM RrhhRue r WHERE r.numeroProbidad = :numeroProbidad")
    , @NamedQuery(name = "RrhhRue.findByTelefonoEmergencia", query = "SELECT r FROM RrhhRue r WHERE r.telefonoEmergencia = :telefonoEmergencia")
    , @NamedQuery(name = "RrhhRue.findByEstado", query = "SELECT r FROM RrhhRue r WHERE r.estado = :estado")
    , @NamedQuery(name = "RrhhRue.findByIdRue", query = "SELECT r FROM RrhhRue r WHERE r.idRue = :idRue")
    , @NamedQuery(name = "RrhhRue.findByFechaFallecimientoPadre", query = "SELECT r FROM RrhhRue r WHERE r.fechaFallecimientoPadre = :fechaFallecimientoPadre")
    , @NamedQuery(name = "RrhhRue.findByFechaFallecimientoMadre", query = "SELECT r FROM RrhhRue r WHERE r.fechaFallecimientoMadre = :fechaFallecimientoMadre")
    , @NamedQuery(name = "RrhhRue.findByFechaFallecimientoConyuge", query = "SELECT r FROM RrhhRue r WHERE r.fechaFallecimientoConyuge = :fechaFallecimientoConyuge")
    , @NamedQuery(name = "RrhhRue.findByCodigoEmpleado", query = "SELECT r FROM RrhhRue r WHERE r.codigoEmpleado = :codigoEmpleado")
    , @NamedQuery(name = "RrhhRue.findByObservaciones", query = "SELECT r FROM RrhhRue r WHERE r.observaciones = :observaciones")
    , @NamedQuery(name = "RrhhRue.findByCantidadHijos", query = "SELECT r FROM RrhhRue r WHERE r.cantidadHijos = :cantidadHijos")
    , @NamedQuery(name = "RrhhRue.findByCodigoEmpleadoSag", query = "SELECT r FROM RrhhRue r WHERE r.codigoEmpleadoSag = :codigoEmpleadoSag")
    , @NamedQuery(name = "RrhhRue.findByEmailInstitucional", query = "SELECT r FROM RrhhRue r WHERE r.emailInstitucional = :emailInstitucional")
    , @NamedQuery(name = "RrhhRue.findByZonaVivienda", query = "SELECT r FROM RrhhRue r WHERE r.zonaVivienda = :zonaVivienda")
    , @NamedQuery(name = "RrhhRue.findByDireccion2", query = "SELECT r FROM RrhhRue r WHERE r.direccion2 = :direccion2")
    , @NamedQuery(name = "RrhhRue.findByDepartamentoVivienda2", query = "SELECT r FROM RrhhRue r WHERE r.departamentoVivienda2 = :departamentoVivienda2")
    , @NamedQuery(name = "RrhhRue.findByMunicipioVivienda2", query = "SELECT r FROM RrhhRue r WHERE r.municipioVivienda2 = :municipioVivienda2")
    , @NamedQuery(name = "RrhhRue.findByZonaVivienda2", query = "SELECT r FROM RrhhRue r WHERE r.zonaVivienda2 = :zonaVivienda2")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "RrhhRue.findByDpi",
                      query = "SELECT * FROM ( " +
"SELECT R.ID_RUE,L.ID_CONTRATO,TO_CHAR(R.FECHA_NACIMIENTO,'DD/MM/YYYY') FECHA_NACIMIENTO, " +
"R.ESTADO_CIVIL, CASE R.ESTADO_CIVIL WHEN 'C' THEN 'CASADO/A' " +
"WHEN 'S' THEN 'SOLTERO/A' WHEN 'V' THEN 'VIUDO/A' WHEN 'D' THEN 'DIVORCIADO/A' " +
"WHEN 'U' THEN 'UNIDO/A' END ESTADO_CIVIL_LETRAS, " +
"P.PAIS,  " +
"P.NACIONALIDAD,  " +
"REPLACE(REPLACE(R.CUI,'-',''),' ','') DPI,R.NIT, " +
"UPPER(R.DIRECCION||' '||M.NOMBRE||', '||D.NOMBRE) DIRECCION, " +
"L.RENGLON, " +
"L.TIPO_SERVICIOS, " +
"L.HONORARIO, " +
"L.ESTADO, " +
"L.FECHA_INSERT, " +
"U.UBICACION_FUNCIONAL, "+
"U.NOMBRE NOMBRE_UBICACION "+                              
"FROM RRHH_RUE R  " +
"INNER JOIN RRHH_LABORAL L ON R.ID_RUE = L.ID_RUE " +
"INNER JOIN RRHH_PAIS P ON R.PAIS = P.PAIS " +
"INNER JOIN RRHH_MUNICIPIO M ON R.MUNICIPIO_VIVIENDA = M.MUNICIPIO AND R.DEPARTAMENTO_VIVIENDA = M.DEPARTAMENTO " +
"INNER JOIN RRHH_DEPARTAMENTO D ON M.DEPARTAMENTO = D.DEPARTAMENTO " +
"INNER JOIN RRHH_UBICACION_FUNCIONAL U ON L.UBICACION_FUNCIONAL = U.UBICACION_FUNCIONAL "+
"WHERE REPLACE(REPLACE(R.CUI,'-',''),' ','') = ? ) TEMP  " +
"GROUP BY TEMP.ID_RUE,TEMP.ID_CONTRATO,TEMP.FECHA_NACIMIENTO, TEMP.ESTADO_CIVIL, " +
"TEMP.ESTADO_CIVIL_LETRAS,TEMP.PAIS, TEMP.NACIONALIDAD, " +
"TEMP.DPI,TEMP.NIT,TEMP.DIRECCION,TEMP.RENGLON,TEMP.TIPO_SERVICIOS,TEMP.HONORARIO, " +
"TEMP.ESTADO,TEMP.FECHA_INSERT,TEMP.UBICACION_FUNCIONAL,TEMP.NOMBRE_UBICACION "+
"ORDER BY FECHA_INSERT DESC ",
                      resultSetMapping = "ResultsRue"),
    @NamedNativeQuery(name = "RrhhRue.findAcademicoTecnico",
                      query = "SELECT A.ACADEMICO,A.TITULO,T.NOMBRE NOMBRE_TITULO,A.NUMERO_COLEGIADO,A.COLEGIO_PROFESIONAL FROM RRHH_ACADEMICO A INNER JOIN RRHH_TITULO T ON A.TITULO = T.TITULO WHERE A.ID_RUE = ? AND A.NIVEL_EDUCATIVO = 4 AND A.GRADO_ACADEMICO = 39 AND A.ESTADO = 'A' ORDER BY A.FECHA_INSERT DESC ",
                      resultSetMapping = "ResultsAcademico"),
    @NamedNativeQuery(name = "RrhhRue.findAcademicoProfesional",
                      query = "SELECT A.ACADEMICO,A.TITULO,T.NOMBRE NOMBRE_TITULO,A.NUMERO_COLEGIADO,A.COLEGIO_PROFESIONAL FROM RRHH_ACADEMICO A INNER JOIN RRHH_TITULO T ON A.TITULO = T.TITULO WHERE A.ID_RUE = ? AND A.GRADO_ACADEMICO = 39 AND A.NIVEL_EDUCATIVO = 5 AND NUMERO_COLEGIADO IS NOT NULL AND COLEGIO_PROFESIONAL IS NOT NULL AND A.ESTADO = 'A' ORDER BY A.FECHA_INSERT DESC ",
                      resultSetMapping = "ResultsAcademico"),
    @NamedNativeQuery(name = "RrhhRue.findActividadByContrato",
                      query = "SELECT A.ACADEMICO,A.TITULO,T.NOMBRE NOMBRE_TITULO,A.NUMERO_COLEGIADO,A.COLEGIO_PROFESIONAL FROM RRHH_ACADEMICO A INNER JOIN RRHH_TITULO T ON A.TITULO = T.TITULO INNER JOIN RRHH_CONTRATO C ON A.ACADEMICO = C.ACADEMICO WHERE C.ID_CONTRATO = ? ",
                      resultSetMapping = "ResultsAcademico")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "ResultsRue",
                         classes = {@ConstructorResult(targetClass = ResultsFuncionario.class,
                                   columns = {@ColumnResult(name = "ID_RUE", type = BigDecimal.class),
                                              @ColumnResult(name = "ID_CONTRATO", type = BigDecimal.class),
                                              @ColumnResult(name = "FECHA_NACIMIENTO", type = String.class),
                                              @ColumnResult(name = "ESTADO_CIVIL", type = String.class),
                                              @ColumnResult(name = "ESTADO_CIVIL_LETRAS", type = String.class),
                                              @ColumnResult(name = "PAIS", type = BigDecimal.class),
                                              @ColumnResult(name = "NACIONALIDAD", type = String.class),
                                              @ColumnResult(name = "DPI", type = String.class),
                                              @ColumnResult(name = "NIT", type = String.class),
                                              @ColumnResult(name = "DIRECCION", type = String.class),
                                              @ColumnResult(name = "RENGLON", type = String.class),
                                              @ColumnResult(name = "TIPO_SERVICIOS", type = String.class),
                                              @ColumnResult(name = "HONORARIO", type = Double.class),
                                              @ColumnResult(name = "ESTADO", type = String.class),
                                              @ColumnResult(name = "FECHA_INSERT", type = Date.class),
                                              @ColumnResult(name = "UBICACION_FUNCIONAL", type = BigDecimal.class),
                                              @ColumnResult(name = "NOMBRE_UBICACION", type = String.class)
                                   })
                         }),
    @SqlResultSetMapping(name = "ResultsAcademico",
                         classes = {@ConstructorResult(targetClass = ResultsAcademico.class,
                                   columns = {@ColumnResult(name = "ACADEMICO", type = BigDecimal.class),
                                              @ColumnResult(name = "TITULO", type = BigDecimal.class),
                                              @ColumnResult(name = "NOMBRE_TITULO", type = String.class),
                                              @ColumnResult(name = "NUMERO_COLEGIADO", type = String.class),
                                              @ColumnResult(name = "COLEGIO_PROFESIONAL", type = BigDecimal.class)                                              
                                   })
                         })
})
public class RrhhRue implements Serializable {


    /*private static final long serialVersionUID = 1L;
    
    
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "fSequence", sequenceName = "SEQ_RRHH_RUE", allocationSize = 1)
    @GeneratedValue(generator = "fSequence",strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_RUE")
    private Long idRue;*/
     private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "RUE")
    private String rue;
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
    @Size(max = 50)
    @Column(name = "GUATENOMINA")
    private String guatenomina;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PRIMER_APELLIDO")
    private String primerApellido;
    @Size(max = 20)
    @Column(name = "SEGUNDO_APELLIDO")
    private String segundoApellido;
    @Size(max = 20)
    @Column(name = "APELLIDO_CASADA")
    private String apellidoCasada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PRIMER_NOMBRE")
    private String primerNombre;
    @Size(max = 20)
    @Column(name = "SEGUNDO_NOMBRE")
    private String segundoNombre;
    @Size(max = 20)
    @Column(name = "TERCER_NOMBRE")
    private String tercerNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "GENERO")
    private String genero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO_CIVIL")
    private String estadoCivil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_IDENTIFICACION")
    private String tipoIdentificacion;
    @Size(max = 7)
    @Column(name = "NUMERO_REGISTRO")
    private String numeroRegistro;
    @Size(max = 50)
    @Column(name = "CUI")
    private String cui;
    @Size(max = 20)
    @Column(name = "PASAPORTE")
    private String pasaporte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 19)
    @Column(name = "NIT")
    private String nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DIRECCION")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "DEPARTAMENTO_VIVIENDA")
    private String departamentoVivienda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "MUNICIPIO_VIVIENDA")
    private String municipioVivienda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TELEFONO_CASA")
    private String telefonoCasa;
    @Size(max = 50)
    @Column(name = "TELEFONO_CELULAR")
    private String telefonoCelular;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Correo electrónico no válido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 50)
    @Column(name = "RUAP")
    private String ruap;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "CUENTA_BANCARIA")
    private String cuentaBancaria;
    @Size(max = 1)
    @Column(name = "TIPO_SANGRE")
    private String tipoSangre;
    @Size(max = 50)
    @Column(name = "ALERGIAS")
    private String alergias;
    @Size(max = 100)
    @Column(name = "CONTACTO_EMERGENCIA")
    private String contactoEmergencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "LICENCIA_CONDUCIR")
    private String licenciaConducir;
    @Size(max = 1)
    @Column(name = "TIPO_LICENCIA_CONDUCIR")
    private String tipoLicenciaConducir;
    @Size(max = 20)
    @Column(name = "NUMERO_LICENCIA_CONDUCIR")
    private String numeroLicenciaConducir;
    @Column(name = "VENCE_LICENCIA_CONDUCIR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date venceLicenciaConducir;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "LICENCIA_ARMAS")
    private String licenciaArmas;
    @Size(max = 20)
    @Column(name = "NUMERO_LICENCIA_ARMAS")
    private String numeroLicenciaArmas;
    @Column(name = "VENCE_LICENCIA_ARMAS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date venceLicenciaArmas;
    @Size(max = 100)
    @Column(name = "NOMBRE_PADRE")
    private String nombrePadre;
    @Size(max = 100)
    @Column(name = "NOMBRE_MADRE")
    private String nombreMadre;
    @Size(max = 100)
    @Column(name = "NOMBRE_CONYUGE")
    private String nombreConyuge;
    @Size(max = 100)
    @Column(name = "FOTO")
    private String foto;
    @Size(max = 50)
    @Column(name = "LOGIN_RED")
    private String loginRed;
    @Size(max = 50)
    @Column(name = "LOGIN_BD")
    private String loginBd;
    @Size(max = 50)
    @Column(name = "LOGIN_APP")
    private String loginApp;
    @Column(name = "FECHA_FICHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFicha;
    @Size(max = 50)
    @Column(name = "USUARIO_FICHA")
    private String usuarioFicha;
    @Size(max = 15)
    @Column(name = "NUMERO_PROBIDAD")
    private String numeroProbidad;
    @Size(max = 50)
    @Column(name = "TELEFONO_EMERGENCIA")
    private String telefonoEmergencia;
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Id
   // @Basic(optional = false)
    @SequenceGenerator(name = "fSequence", sequenceName = "SEQ_RRHH_RUE", allocationSize = 1)
    @GeneratedValue(generator = "fSequence",strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_RUE")
    private Long idRue;
    @Column(name = "FECHA_FALLECIMIENTO_PADRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFallecimientoPadre;
    @Column(name = "FECHA_FALLECIMIENTO_MADRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFallecimientoMadre;
    @Column(name = "FECHA_FALLECIMIENTO_CONYUGE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFallecimientoConyuge;
    @Column(name = "CODIGO_EMPLEADO")
    private Long codigoEmpleado;
    @Lob
    @Column(name = "HUELLA_DIGITAL")
    private Serializable huellaDigital;
    @Size(max = 4000)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "CANTIDAD_HIJOS")
    private Short cantidadHijos;
    @Size(max = 100)
    @Column(name = "CODIGO_EMPLEADO_SAG")
    private String codigoEmpleadoSag;
    @Size(max = 100)
    @Column(name = "EMAIL_INSTITUCIONAL")
    private String emailInstitucional;
    @Column(name = "ZONA_VIVIENDA")
    private Short zonaVivienda;
    @Size(max = 200)
    @Column(name = "DIRECCION2")
    private String direccion2;
    @Size(max = 2)
    @Column(name = "DEPARTAMENTO_VIVIENDA2")
    private String departamentoVivienda2;
    @Size(max = 2)
    @Column(name = "MUNICIPIO_VIVIENDA2")
    private String municipioVivienda2;
    @Column(name = "ZONA_VIVIENDA2")
    private Short zonaVivienda2;
    @JoinColumns({
        @JoinColumn(name = "DEPARTAMENTO", referencedColumnName = "DEPARTAMENTO")
        , @JoinColumn(name = "MUNICIPIO", referencedColumnName = "MUNICIPIO")})
    @ManyToOne
    private RrhhMunicipio rrhhMunicipio;
    @JoinColumn(name = "PAIS", referencedColumnName = "PAIS")
    @ManyToOne
    private RrhhPais pais;

    public RrhhRue() {
    }

    public RrhhRue(Long idRue) {
        this.idRue = idRue;
    }

    public RrhhRue(Long idRue, String rue, String usuarioInsert, Date fechaInsert, String primerApellido, String primerNombre, Date fechaNacimiento, String genero, String estadoCivil, String tipoIdentificacion, String nit, String direccion, String departamentoVivienda, String municipioVivienda, String telefonoCasa, String cuentaBancaria, String licenciaConducir, String licenciaArmas) {
        this.idRue = idRue;
        this.rue = rue;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
        this.primerApellido = primerApellido;
        this.primerNombre = primerNombre;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.estadoCivil = estadoCivil;
        this.tipoIdentificacion = tipoIdentificacion;
        this.nit = nit;
        this.direccion = direccion;
        this.departamentoVivienda = departamentoVivienda;
        this.municipioVivienda = municipioVivienda;
        this.telefonoCasa = telefonoCasa;
        this.cuentaBancaria = cuentaBancaria;
        this.licenciaConducir = licenciaConducir;
        this.licenciaArmas = licenciaArmas;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
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

    public String getGuatenomina() {
        return guatenomina;
    }

    public void setGuatenomina(String guatenomina) {
        this.guatenomina = guatenomina;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getApellidoCasada() {
        return apellidoCasada;
    }

    public void setApellidoCasada(String apellidoCasada) {
        this.apellidoCasada = apellidoCasada;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getTercerNombre() {
        return tercerNombre;
    }

    public void setTercerNombre(String tercerNombre) {
        this.tercerNombre = tercerNombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDepartamentoVivienda() {
        return departamentoVivienda;
    }

    public void setDepartamentoVivienda(String departamentoVivienda) {
        this.departamentoVivienda = departamentoVivienda;
    }

    public String getMunicipioVivienda() {
        return municipioVivienda;
    }

    public void setMunicipioVivienda(String municipioVivienda) {
        this.municipioVivienda = municipioVivienda;
    }

    public String getTelefonoCasa() {
        return telefonoCasa;
    }

    public void setTelefonoCasa(String telefonoCasa) {
        this.telefonoCasa = telefonoCasa;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRuap() {
        return ruap;
    }

    public void setRuap(String ruap) {
        this.ruap = ruap;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getContactoEmergencia() {
        return contactoEmergencia;
    }

    public void setContactoEmergencia(String contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public String getLicenciaConducir() {
        return licenciaConducir;
    }

    public void setLicenciaConducir(String licenciaConducir) {
        this.licenciaConducir = licenciaConducir;
    }

    public String getTipoLicenciaConducir() {
        return tipoLicenciaConducir;
    }

    public void setTipoLicenciaConducir(String tipoLicenciaConducir) {
        this.tipoLicenciaConducir = tipoLicenciaConducir;
    }

    public String getNumeroLicenciaConducir() {
        return numeroLicenciaConducir;
    }

    public void setNumeroLicenciaConducir(String numeroLicenciaConducir) {
        this.numeroLicenciaConducir = numeroLicenciaConducir;
    }

    public Date getVenceLicenciaConducir() {
        return venceLicenciaConducir;
    }

    public void setVenceLicenciaConducir(Date venceLicenciaConducir) {
        this.venceLicenciaConducir = venceLicenciaConducir;
    }

    public String getLicenciaArmas() {
        return licenciaArmas;
    }

    public void setLicenciaArmas(String licenciaArmas) {
        this.licenciaArmas = licenciaArmas;
    }

    public String getNumeroLicenciaArmas() {
        return numeroLicenciaArmas;
    }

    public void setNumeroLicenciaArmas(String numeroLicenciaArmas) {
        this.numeroLicenciaArmas = numeroLicenciaArmas;
    }

    public Date getVenceLicenciaArmas() {
        return venceLicenciaArmas;
    }

    public void setVenceLicenciaArmas(Date venceLicenciaArmas) {
        this.venceLicenciaArmas = venceLicenciaArmas;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public String getNombreMadre() {
        return nombreMadre;
    }

    public void setNombreMadre(String nombreMadre) {
        this.nombreMadre = nombreMadre;
    }

    public String getNombreConyuge() {
        return nombreConyuge;
    }

    public void setNombreConyuge(String nombreConyuge) {
        this.nombreConyuge = nombreConyuge;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLoginRed() {
        return loginRed;
    }

    public void setLoginRed(String loginRed) {
        this.loginRed = loginRed;
    }

    public String getLoginBd() {
        return loginBd;
    }

    public void setLoginBd(String loginBd) {
        this.loginBd = loginBd;
    }

    public String getLoginApp() {
        return loginApp;
    }

    public void setLoginApp(String loginApp) {
        this.loginApp = loginApp;
    }

    public Date getFechaFicha() {
        return fechaFicha;
    }

    public void setFechaFicha(Date fechaFicha) {
        this.fechaFicha = fechaFicha;
    }

    public String getUsuarioFicha() {
        return usuarioFicha;
    }

    public void setUsuarioFicha(String usuarioFicha) {
        this.usuarioFicha = usuarioFicha;
    }

    public String getNumeroProbidad() {
        return numeroProbidad;
    }

    public void setNumeroProbidad(String numeroProbidad) {
        this.numeroProbidad = numeroProbidad;
    }

    public String getTelefonoEmergencia() {
        return telefonoEmergencia;
    }

    public void setTelefonoEmergencia(String telefonoEmergencia) {
        this.telefonoEmergencia = telefonoEmergencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdRue() {
        return idRue;
    }

    public void setIdRue(Long idRue) {
        this.idRue = idRue;
    }

    public Date getFechaFallecimientoPadre() {
        return fechaFallecimientoPadre;
    }

    public void setFechaFallecimientoPadre(Date fechaFallecimientoPadre) {
        this.fechaFallecimientoPadre = fechaFallecimientoPadre;
    }

    public Date getFechaFallecimientoMadre() {
        return fechaFallecimientoMadre;
    }

    public void setFechaFallecimientoMadre(Date fechaFallecimientoMadre) {
        this.fechaFallecimientoMadre = fechaFallecimientoMadre;
    }

    public Date getFechaFallecimientoConyuge() {
        return fechaFallecimientoConyuge;
    }

    public void setFechaFallecimientoConyuge(Date fechaFallecimientoConyuge) {
        this.fechaFallecimientoConyuge = fechaFallecimientoConyuge;
    }

    public Long getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(Long codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public Serializable getHuellaDigital() {
        return huellaDigital;
    }

    public void setHuellaDigital(Serializable huellaDigital) {
        this.huellaDigital = huellaDigital;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Short getCantidadHijos() {
        return cantidadHijos;
    }

    public void setCantidadHijos(Short cantidadHijos) {
        this.cantidadHijos = cantidadHijos;
    }

    public String getCodigoEmpleadoSag() {
        return codigoEmpleadoSag;
    }

    public void setCodigoEmpleadoSag(String codigoEmpleadoSag) {
        this.codigoEmpleadoSag = codigoEmpleadoSag;
    }

    public String getEmailInstitucional() {
        return emailInstitucional;
    }

    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional;
    }

    public Short getZonaVivienda() {
        return zonaVivienda;
    }

    public void setZonaVivienda(Short zonaVivienda) {
        this.zonaVivienda = zonaVivienda;
    }

    public String getDireccion2() {
        return direccion2;
    }

    public void setDireccion2(String direccion2) {
        this.direccion2 = direccion2;
    }

    public String getDepartamentoVivienda2() {
        return departamentoVivienda2;
    }

    public void setDepartamentoVivienda2(String departamentoVivienda2) {
        this.departamentoVivienda2 = departamentoVivienda2;
    }

    public String getMunicipioVivienda2() {
        return municipioVivienda2;
    }

    public void setMunicipioVivienda2(String municipioVivienda2) {
        this.municipioVivienda2 = municipioVivienda2;
    }

    public Short getZonaVivienda2() {
        return zonaVivienda2;
    }

    public void setZonaVivienda2(Short zonaVivienda2) {
        this.zonaVivienda2 = zonaVivienda2;
    }

    public RrhhMunicipio getRrhhMunicipio() {
        return rrhhMunicipio;
    }

    public void setRrhhMunicipio(RrhhMunicipio rrhhMunicipio) {
        this.rrhhMunicipio = rrhhMunicipio;
    }

    public RrhhPais getPais() {
        return pais;
    }

    public void setPais(RrhhPais pais) {
        this.pais = pais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRue != null ? idRue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhRue)) {
            return false;
        }
        RrhhRue other = (RrhhRue) object;
        if ((this.idRue == null && other.idRue != null) || (this.idRue != null && !this.idRue.equals(other.idRue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cgc.general.model.RrhhRue[ idRue=" + idRue + " ]";
    }
    
   
    
}
