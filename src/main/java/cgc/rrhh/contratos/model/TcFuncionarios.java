/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.model;

import cgc.rrhh.contratos.pojo.ResultsFuncionario;
import cgc.rrhh.contratos.pojo.ResultsFuncionarios;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
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

/**
 *
 * @author ejmorales
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "TcFuncionarios.findAll",
                query = "SELECT t FROM TcFuncionarios t "),
     @NamedQuery(name = "TcFuncionarios.findByDPI",
                query = "SELECT t FROM TcFuncionarios t WHERE t.dpi = :dpi ")
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "TcFuncionarios.findFuncionarioByDpi",
                      query = "SELECT * FROM APP_NOTIFICACION.TC_FUNCIONARIOS WHERE TREPLACE(REPLACE(DPI,'-',''),' ','')  = ? ",
                      resultClass = TcFuncionarios.class),
    @NamedNativeQuery(name = "TcFuncionarios.NativeQueryDPIAllTable",
                      query = "SELECT * FROM APP_NOTIFICACION.TC_FUNCIONARIOS WHERE TRIM(REPLACE(DPI,'-',''))  LIKE REPLACE('%'|| ? ||'%',' ','%')",
                      resultClass = TcFuncionarios.class),
    @NamedNativeQuery(name = "TcFuncionarios.NativeQueryDPI",
                      query = "SELECT DPI,NOMBRE_COMPLETO FROM ( "+  
                              "SELECT DPI, NOMBRE_COMPLETO, ROW_NUMBER() OVER (PARTITION BY DPI ORDER BY NOMBRE_COMPLETO ASC) R FROM ( " +
                                "SELECT REPLACE(REPLACE(DPI,'-',''),' ','') AS DPI,DPI|| ' - ' ||PRIMER_NOMBRE||' '||SEGUNDO_NOMBRE||' '||PRIMER_APELLIDO||' '||SEGUNDO_APELLIDO AS NOMBRE_COMPLETO " +
                                "FROM APP_NOTIFICACION.TC_FUNCIONARIOS " +
                                "WHERE REPLACE(REPLACE(DPI,'-',''),' ','')  LIKE REPLACE('%'|| ?dpi ||'%',' ','%') " +
                                "UNION ALL " +
                                "SELECT REPLACE(REPLACE(CUI,'-',''),' ','') AS DPI,REPLACE(REPLACE(CUI,'-',''),' ','')|| ' - '  ||PRIMER_NOMBRE||' '||SEGUNDO_NOMBRE||' '||PRIMER_APELLIDO||' '||SEGUNDO_APELLIDO AS NOMBRE_COMPLETO " +
                                "FROM SAG_RRHH.RRHH_RUE " +
                                "WHERE REPLACE(REPLACE(CUI,'-',''),' ','')  LIKE REPLACE('%'|| ?dpi ||'%',' ','%') " +
                                ") GROUP BY DPI,NOMBRE_COMPLETO ) WHERE R = 1 ",
                      resultSetMapping = "ResultsFuncionario"),
    @NamedNativeQuery(name = "TcFuncionarios.NativefindByDpi",
                      query = "SELECT * FROM ( " +
"SELECT TO_CHAR(F.FECHA_NACIMIENTO,'DD/MM/YYYY') FECHA_NACIMIENTO, " +
"F.ESTADO_CIVIL, CASE F.ESTADO_CIVIL WHEN 'C' THEN 'CASADO/A' " +
"WHEN 'S' THEN 'SOLTERO/A' WHEN 'V' THEN 'VIUDO/A' WHEN 'D' THEN 'DIVORCIADO/A' " +
"WHEN 'U' THEN 'UNIDO/A' END ESTADO_CIVIL_LETRAS, " +
"P.NACIONALIDAD,  " +
"REPLACE(REPLACE(F.DPI,'-',''),' ','') DPI,F.NIT, " +
"UPPER(F.CALLE_AVENIDA_RESIDENCIA||' '||F.NUMERO_CASA_RESIDENCIA||' '||F.COLONIA_BARRIO_RESIDENCIA||' ZONA '||F.ZONA_RESIDENCIA||' '||M.NOMBRE||', '||D.NOMBRE) DIRECCION, " +
"F.NUMERO_COLEGIADO " +
"FROM APP_NOTIFICACION.TC_FUNCIONARIOS F  " +
"INNER JOIN SAG_RRHH.RRHH_MUNICIPIO M ON F.MUNICIPIO_RESIDENCIA = M.MUNICIPIO AND F.DEPARTAMENTO_RESIDENCIA = M.DEPARTAMENTO " +
"INNER JOIN SAG_RRHH.RRHH_DEPARTAMENTO D ON M.DEPARTAMENTO = D.DEPARTAMENTO " +
"INNER JOIN CLASIFICADORES.TC_PAISES P ON F.NACIONALIDAD = P.CODIGO_PAIS "+                              
"WHERE REPLACE(REPLACE(F.DPI,'-',''),' ','') = ? ) TEMP  " +
"GROUP BY TEMP.FECHA_NACIMIENTO, TEMP.ESTADO_CIVIL, " +
"TEMP.ESTADO_CIVIL_LETRAS, TEMP.NACIONALIDAD, " +
"TEMP.DPI,TEMP.NIT,TEMP.DIRECCION,TEMP.NUMERO_COLEGIADO ",
                      resultSetMapping = "ResultsInfoFuncionario")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "ResultsFuncionario",
                         classes = {@ConstructorResult(targetClass = ResultsFuncionarios.class,
                                        columns = {@ColumnResult(name = "DPI", type = String.class),
                                                   @ColumnResult(name = "NOMBRE_COMPLETO", type = String.class)
                                        })
                         
                         }),
    @SqlResultSetMapping(name = "ResultsInfoFuncionario",
                         classes = {@ConstructorResult(targetClass = ResultsFuncionario.class,
                                   columns = {@ColumnResult(name = "FECHA_NACIMIENTO", type = String.class),
                                              @ColumnResult(name = "ESTADO_CIVIL", type = String.class),
                                              @ColumnResult(name = "ESTADO_CIVIL_LETRAS", type = String.class),                                             
                                              @ColumnResult(name = "NACIONALIDAD", type = String.class),
                                              @ColumnResult(name = "DPI", type = String.class),
                                              @ColumnResult(name = "NIT", type = String.class),
                                              @ColumnResult(name = "DIRECCION", type = String.class),
                                              @ColumnResult(name = "NUMERO_COLEGIADO", type = String.class)
                                   })
                         })
})
@Table(name = "APP_NOTIFICACION.TC_FUNCIONARIOS")
public class TcFuncionarios implements Serializable{
    
@Id
@Column(name = "NIT")                       
private String nit;
@Column(name = "PRIMER_NOMBRE")
private String primerNombre;
@Column(name = "SEGUNDO_NOMBRE")            
private String segundoNombre;
@Column(name = "TERCER_NOMBRE")             
private String tercerNombre;
@Column(name = "PRIMER_APELLIDO")           
private String primerApellido;
@Column(name = "SEGUNDO_APELLIDO")          
private String segundoApellido;
@Column(name = "APELLIDO_CASADA")           
private String apellidoCasada;
@Column(name = "SEXO")       
private String sexo;
@Column(name = "ESTADO_CIVIL")              
private String estadoCivil;
@Column(name = "FECHA_NACIMIENTO")          
private Date fechaNacimiento;
@Column(name = "PROFESION_OFICIO")          
private String profesionOficio;
@Column(name = "CALLE_AVENIDA_RESIDENCIA")  
private String calleAvenidaResidencia;
@Column(name = "NUMERO_CASA_RESIDENCIA")    
private String numeroCasaResidencia;
@Column(name = "APARTAMENTO_RESIDENCIA")    
private String apartamentoResidencia;
@Column(name = "ZONA_RESIDENCIA")           
private BigInteger zonaResidencia;
@Column(name = "COLONIA_BARRIO_RESIDENCIA") 
private String coloniaBarrioResidencia;
@Column(name = "APARTADO_POSTAL_RESIDENCIA")
private String apartadoPostalResidencia;
@Column(name = "DEPARTAMENTO_RESIDENCIA")   
private String departamentoResidencia;
@Column(name = "MUNICIPIO_RESIDENCIA")      
private String municipioResidencia;
@Column(name = "DEPARTAMENTO_CEDULA")       
private String departamentoCedula;
@Column(name = "MUNICIPIO_CEDULA")          
private String municipioCedula;
@Column(name = "ORDEN_CEDULA")
private String ordenCedula;
@Column(name = "REGISTRO_CEDULA")           
private String registroCedula;
@Column(name = "DPI")                       
private String dpi;
@Column(name = "AFILIACION_IGSS")          
private String afiliacionIgss;
@Column(name = "NACIONALIDAD")              
private BigDecimal nacionalidad;
@Column(name = "NUMERO_PASAPORTE")          
private String numeroPasaporte;
@Column(name = "FECHA_EMISION_PASAPORTE")   
private Date fechaEmisionPasaporte;
@Column(name = "PAIS_EMISION_PASAPORTE")   
private BigDecimal paisEmisionPasaporte;
@Column(name = "NUMERO_COLEGIADO")          
private String numeroColegiado;
@Column(name = "COLEGIO")                   
private String colegio;
@Column(name = "TELEFONO_RESIDENCIA")       
private BigDecimal telefonoResidencia;
@Column(name = "CELULAR_1")                 
private BigDecimal celular1;
@Column(name = "CELULAR_2")                 
private BigDecimal celular2;
@Column(name = "CELULAR_3")                 
private BigDecimal celular3;
@Column(name = "EMAIL_PERSONAL")            
private String emailPersonal;
@Column(name = "IDIOMA_NOTIFICACION")       
private String idiomaNotificacion;
@Column(name = "ENTIDAD_LABORA_OLD")        
private BigDecimal entidadLaboraOld;
@Column(name = "AREA_DEPENDENCIA")          
private String areaDependencia;
@Column(name = "PUESTO")                    
private String puesto;
@Column(name = "NOMBRE_CARGO")              
private String nombreCargo;
@Column(name = "RENGLON_PRESUPUESTARIO")    
private String renglonPresupuestario;
@Column(name = "EMAIL_EN_ENTIDAD")          
private String emailEnEntidad;
@Column(name = "PERIODO_DEL")               
private Date periodoDel;
@Column(name = "PERIODO_AL")                
private Date periodoAl;
@Column(name = "OBSERVACIONES")             
private String observaciones;
@Column(name = "CODIGO_HASHDM5")            
private String codigoHashdm5;
@Column(name = "CODIGO_CLIENTE_SAG")        
private BigDecimal codigoClienteSag;
@Column(name = "ESTATUS")    
private String estatus;
@Column(name = "USUARIO_INGRESO")           
private String usuarioIngreso;
@Column(name = "FECHA_INGRESO")             
private Date fechaIngreso;
@Column(name = "USUARIO_ACTUALIZACION")     
private String usuarioActualizacion;
@Column(name = "FECHA_ACTUALIZACION")       
private Date fechaActualizacion;
@Column(name = "CORRELATIVO")               
private BigDecimal correlativo;
@Column(name = "CALLE_AVENIDA_SAT")         
private String calleAvenidaSat;
@Column(name = "NUMERO_CASA_SAT")           
private String numeroCasaSat;
@Column(name = "APARTAMENTO_SAT")           
private String apartamentoSat;
@Column(name = "ZONA_SAT")     
private BigDecimal zonaSat;
@Column(name = "COLONIA_BARRIO_SAT")        
private String coloniaBarrioSat;
@Column(name = "APARTADO_POSTAL_SAT")       
private String apartadoPostalSat;
@Column(name = "DEPARTAMENTO_SAT")          
private String departamentoSat;
@Column(name = "MUNICIPIO_SAT")             
private String municipioSat;
@Column(name = "TELEFONO_SAT")              
private BigDecimal telefonoSat;
@Column(name = "ENTIDAD_LABORA")            
private BigDecimal entidadLabora;
@Column(name = "FUNCIONARIO_ID")           
private BigDecimal funcionarioId;
@Column(name = "USUARIO_ID")    
private BigDecimal usuarioId;
@Column(name = "CODIGO_CUENTADANTE")
private String codigoCuentadante;
@Column(name = "IRTRA")
private String irtra;    

    public TcFuncionarios() {
    }

    public TcFuncionarios(String nit, String primerNombre, String segundoNombre, String tercerNombre, String primerApellido, String segundoApellido, String apellidoCasada, String sexo, String estadoCivil, Date fechaNacimiento, String profesionOficio, String calleAvenidaResidencia, String numeroCasaResidencia, String apartamentoResidencia, BigInteger zonaResidencia, String coloniaBarrioResidencia, String apartadoPostalResidencia, String departamentoResidencia, String municipioResidencia, String departamentoCedula, String municipioCedula, String ordenCedula, String registroCedula, String dpi, String afiliacionIgss, BigDecimal nacionalidad, String numeroPasaporte, Date fechaEmisionPasaporte, BigDecimal paisEmisionPasaporte, String numeroColegiado, String colegio, BigDecimal telefonoResidencia, BigDecimal celular1, BigDecimal celular2, BigDecimal celular3, String emailPersonal, String idiomaNotificacion, BigDecimal entidadLaboraOld, String areaDependencia, String puesto, String nombreCargo, String renglonPresupuestario, String emailEnEntidad, Date periodoDel, Date periodoAl, String observaciones, String codigoHashdm5, BigDecimal codigoClienteSag, String estatus, String usuarioIngreso, Date fechaIngreso, String usuarioActualizacion, Date fechaActualizacion, BigDecimal correlativo, String calleAvenidaSat, String numeroCasaSat, String apartamentoSat, BigDecimal zonaSat, String coloniaBarrioSat, String apartadoPostalSat, String departamentoSat, String municipioSat, BigDecimal telefonoSat, BigDecimal entidadLabora, BigDecimal funcionarioId, BigDecimal usuarioId, String codigoCuentadante, String irtra) {
        this.nit = nit;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.tercerNombre = tercerNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.apellidoCasada = apellidoCasada;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.fechaNacimiento = fechaNacimiento;
        this.profesionOficio = profesionOficio;
        this.calleAvenidaResidencia = calleAvenidaResidencia;
        this.numeroCasaResidencia = numeroCasaResidencia;
        this.apartamentoResidencia = apartamentoResidencia;
        this.zonaResidencia = zonaResidencia;
        this.coloniaBarrioResidencia = coloniaBarrioResidencia;
        this.apartadoPostalResidencia = apartadoPostalResidencia;
        this.departamentoResidencia = departamentoResidencia;
        this.municipioResidencia = municipioResidencia;
        this.departamentoCedula = departamentoCedula;
        this.municipioCedula = municipioCedula;
        this.ordenCedula = ordenCedula;
        this.registroCedula = registroCedula;
        this.dpi = dpi;
        this.afiliacionIgss = afiliacionIgss;
        this.nacionalidad = nacionalidad;
        this.numeroPasaporte = numeroPasaporte;
        this.fechaEmisionPasaporte = fechaEmisionPasaporte;
        this.paisEmisionPasaporte = paisEmisionPasaporte;
        this.numeroColegiado = numeroColegiado;
        this.colegio = colegio;
        this.telefonoResidencia = telefonoResidencia;
        this.celular1 = celular1;
        this.celular2 = celular2;
        this.celular3 = celular3;
        this.emailPersonal = emailPersonal;
        this.idiomaNotificacion = idiomaNotificacion;
        this.entidadLaboraOld = entidadLaboraOld;
        this.areaDependencia = areaDependencia;
        this.puesto = puesto;
        this.nombreCargo = nombreCargo;
        this.renglonPresupuestario = renglonPresupuestario;
        this.emailEnEntidad = emailEnEntidad;
        this.periodoDel = periodoDel;
        this.periodoAl = periodoAl;
        this.observaciones = observaciones;
        this.codigoHashdm5 = codigoHashdm5;
        this.codigoClienteSag = codigoClienteSag;
        this.estatus = estatus;
        this.usuarioIngreso = usuarioIngreso;
        this.fechaIngreso = fechaIngreso;
        this.usuarioActualizacion = usuarioActualizacion;
        this.fechaActualizacion = fechaActualizacion;
        this.correlativo = correlativo;
        this.calleAvenidaSat = calleAvenidaSat;
        this.numeroCasaSat = numeroCasaSat;
        this.apartamentoSat = apartamentoSat;
        this.zonaSat = zonaSat;
        this.coloniaBarrioSat = coloniaBarrioSat;
        this.apartadoPostalSat = apartadoPostalSat;
        this.departamentoSat = departamentoSat;
        this.municipioSat = municipioSat;
        this.telefonoSat = telefonoSat;
        this.entidadLabora = entidadLabora;
        this.funcionarioId = funcionarioId;
        this.usuarioId = usuarioId;
        this.codigoCuentadante = codigoCuentadante;
        this.irtra = irtra;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getProfesionOficio() {
        return profesionOficio;
    }

    public void setProfesionOficio(String profesionOficio) {
        this.profesionOficio = profesionOficio;
    }

    public String getCalleAvenidaResidencia() {
        return calleAvenidaResidencia;
    }

    public void setCalleAvenidaResidencia(String calleAvenidaResidencia) {
        this.calleAvenidaResidencia = calleAvenidaResidencia;
    }

    public String getNumeroCasaResidencia() {
        return numeroCasaResidencia;
    }

    public void setNumeroCasaResidencia(String numeroCasaResidencia) {
        this.numeroCasaResidencia = numeroCasaResidencia;
    }

    public String getApartamentoResidencia() {
        return apartamentoResidencia;
    }

    public void setApartamentoResidencia(String apartamentoResidencia) {
        this.apartamentoResidencia = apartamentoResidencia;
    }

    public BigInteger getZonaResidencia() {
        return zonaResidencia;
    }

    public void setZonaResidencia(BigInteger zonaResidencia) {
        this.zonaResidencia = zonaResidencia;
    }

    public String getColoniaBarrioResidencia() {
        return coloniaBarrioResidencia;
    }

    public void setColoniaBarrioResidencia(String coloniaBarrioResidencia) {
        this.coloniaBarrioResidencia = coloniaBarrioResidencia;
    }

    public String getApartadoPostalResidencia() {
        return apartadoPostalResidencia;
    }

    public void setApartadoPostalResidencia(String apartadoPostalResidencia) {
        this.apartadoPostalResidencia = apartadoPostalResidencia;
    }

    public String getDepartamentoResidencia() {
        return departamentoResidencia;
    }

    public void setDepartamentoResidencia(String departamentoResidencia) {
        this.departamentoResidencia = departamentoResidencia;
    }

    public String getMunicipioResidencia() {
        return municipioResidencia;
    }

    public void setMunicipioResidencia(String municipioResidencia) {
        this.municipioResidencia = municipioResidencia;
    }

    public String getDepartamentoCedula() {
        return departamentoCedula;
    }

    public void setDepartamentoCedula(String departamentoCedula) {
        this.departamentoCedula = departamentoCedula;
    }

    public String getMunicipioCedula() {
        return municipioCedula;
    }

    public void setMunicipioCedula(String municipioCedula) {
        this.municipioCedula = municipioCedula;
    }

    public String getOrdenCedula() {
        return ordenCedula;
    }

    public void setOrdenCedula(String ordenCedula) {
        this.ordenCedula = ordenCedula;
    }

    public String getRegistroCedula() {
        return registroCedula;
    }

    public void setRegistroCedula(String registroCedula) {
        this.registroCedula = registroCedula;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getAfiliacionIgss() {
        return afiliacionIgss;
    }

    public void setAfiliacionIgss(String afiliacionIgss) {
        this.afiliacionIgss = afiliacionIgss;
    }

    public BigDecimal getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(BigDecimal nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNumeroPasaporte() {
        return numeroPasaporte;
    }

    public void setNumeroPasaporte(String numeroPasaporte) {
        this.numeroPasaporte = numeroPasaporte;
    }

    public Date getFechaEmisionPasaporte() {
        return fechaEmisionPasaporte;
    }

    public void setFechaEmisionPasaporte(Date fechaEmisionPasaporte) {
        this.fechaEmisionPasaporte = fechaEmisionPasaporte;
    }

    public BigDecimal getPaisEmisionPasaporte() {
        return paisEmisionPasaporte;
    }

    public void setPaisEmisionPasaporte(BigDecimal paisEmisionPasaporte) {
        this.paisEmisionPasaporte = paisEmisionPasaporte;
    }

    public String getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(String numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    public BigDecimal getTelefonoResidencia() {
        return telefonoResidencia;
    }

    public void setTelefonoResidencia(BigDecimal telefonoResidencia) {
        this.telefonoResidencia = telefonoResidencia;
    }

    public BigDecimal getCelular1() {
        return celular1;
    }

    public void setCelular1(BigDecimal celular1) {
        this.celular1 = celular1;
    }

    public BigDecimal getCelular2() {
        return celular2;
    }

    public void setCelular2(BigDecimal celular2) {
        this.celular2 = celular2;
    }

    public BigDecimal getCelular3() {
        return celular3;
    }

    public void setCelular3(BigDecimal celular3) {
        this.celular3 = celular3;
    }

    public String getEmailPersonal() {
        return emailPersonal;
    }

    public void setEmailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal;
    }

    public String getIdiomaNotificacion() {
        return idiomaNotificacion;
    }

    public void setIdiomaNotificacion(String idiomaNotificacion) {
        this.idiomaNotificacion = idiomaNotificacion;
    }

    public BigDecimal getEntidadLaboraOld() {
        return entidadLaboraOld;
    }

    public void setEntidadLaboraOld(BigDecimal entidadLaboraOld) {
        this.entidadLaboraOld = entidadLaboraOld;
    }

    public String getAreaDependencia() {
        return areaDependencia;
    }

    public void setAreaDependencia(String areaDependencia) {
        this.areaDependencia = areaDependencia;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    public String getRenglonPresupuestario() {
        return renglonPresupuestario;
    }

    public void setRenglonPresupuestario(String renglonPresupuestario) {
        this.renglonPresupuestario = renglonPresupuestario;
    }

    public String getEmailEnEntidad() {
        return emailEnEntidad;
    }

    public void setEmailEnEntidad(String emailEnEntidad) {
        this.emailEnEntidad = emailEnEntidad;
    }

    public Date getPeriodoDel() {
        return periodoDel;
    }

    public void setPeriodoDel(Date periodoDel) {
        this.periodoDel = periodoDel;
    }

    public Date getPeriodoAl() {
        return periodoAl;
    }

    public void setPeriodoAl(Date periodoAl) {
        this.periodoAl = periodoAl;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCodigoHashdm5() {
        return codigoHashdm5;
    }

    public void setCodigoHashdm5(String codigoHashdm5) {
        this.codigoHashdm5 = codigoHashdm5;
    }

    public BigDecimal getCodigoClienteSag() {
        return codigoClienteSag;
    }

    public void setCodigoClienteSag(BigDecimal codigoClienteSag) {
        this.codigoClienteSag = codigoClienteSag;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getUsuarioIngreso() {
        return usuarioIngreso;
    }

    public void setUsuarioIngreso(String usuarioIngreso) {
        this.usuarioIngreso = usuarioIngreso;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public BigDecimal getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(BigDecimal correlativo) {
        this.correlativo = correlativo;
    }

    public String getCalleAvenidaSat() {
        return calleAvenidaSat;
    }

    public void setCalleAvenidaSat(String calleAvenidaSat) {
        this.calleAvenidaSat = calleAvenidaSat;
    }

    public String getNumeroCasaSat() {
        return numeroCasaSat;
    }

    public void setNumeroCasaSat(String numeroCasaSat) {
        this.numeroCasaSat = numeroCasaSat;
    }

    public String getApartamentoSat() {
        return apartamentoSat;
    }

    public void setApartamentoSat(String apartamentoSat) {
        this.apartamentoSat = apartamentoSat;
    }

    public BigDecimal getZonaSat() {
        return zonaSat;
    }

    public void setZonaSat(BigDecimal zonaSat) {
        this.zonaSat = zonaSat;
    }

    public String getColoniaBarrioSat() {
        return coloniaBarrioSat;
    }

    public void setColoniaBarrioSat(String coloniaBarrioSat) {
        this.coloniaBarrioSat = coloniaBarrioSat;
    }

    public String getApartadoPostalSat() {
        return apartadoPostalSat;
    }

    public void setApartadoPostalSat(String apartadoPostalSat) {
        this.apartadoPostalSat = apartadoPostalSat;
    }

    public String getDepartamentoSat() {
        return departamentoSat;
    }

    public void setDepartamentoSat(String departamentoSat) {
        this.departamentoSat = departamentoSat;
    }

    public String getMunicipioSat() {
        return municipioSat;
    }

    public void setMunicipioSat(String municipioSat) {
        this.municipioSat = municipioSat;
    }

    public BigDecimal getTelefonoSat() {
        return telefonoSat;
    }

    public void setTelefonoSat(BigDecimal telefonoSat) {
        this.telefonoSat = telefonoSat;
    }

    public BigDecimal getEntidadLabora() {
        return entidadLabora;
    }

    public void setEntidadLabora(BigDecimal entidadLabora) {
        this.entidadLabora = entidadLabora;
    }

    public BigDecimal getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(BigDecimal funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public BigDecimal getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(BigDecimal usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getCodigoCuentadante() {
        return codigoCuentadante;
    }

    public void setCodigoCuentadante(String codigoCuentadante) {
        this.codigoCuentadante = codigoCuentadante;
    }

    public String getIrtra() {
        return irtra;
    }

    public void setIrtra(String irtra) {
        this.irtra = irtra;
    }


}
