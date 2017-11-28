/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ejmorales
 */
public class ResultsFuncionario {
    
    private BigDecimal idRue;
    private BigDecimal idContrato;
    private String fechaNacimiento;
    private String estadoCivil;
    private String estadoCivilLetras;
    private BigDecimal pais;
    private String nacionalidad;
    private String dpi;
    private String nit;
    private String direccion;
    private String renglon;
    private String tipoServicios;
    private Double honorario;
    private String estado;
    private Date fechaInsert;
    private BigDecimal ubicacionFuncional;
    private String nombreUbicacion;
    private String fechaDel;
    private String fechaAl;
    private String fechaCambioTipoMovimiento;
    private Integer edad;
    private String numeroColegiado;
    private BigDecimal idPerfil;
    private List<ResultsActividad> actividades;
    private ResultsAcademico academico; 
    private String observaciones;
    
    public ResultsFuncionario() {
    }

    public ResultsFuncionario(BigDecimal idRue,BigDecimal idContrato,String fechaNacimiento, String estadoCivil, 
            String estadoCivilLetras, BigDecimal pais, String nacionalidad, 
            String dpi, String nit, String direccion,
            String renglon, String tipoServicios, Double honorario, 
            String estado, Date fechaInsert,BigDecimal ubicacionFuncional,String nombreUbicacion) {
        this.idRue = idRue;
        this.idContrato = idContrato;
        this.fechaNacimiento = fechaNacimiento;
        this.estadoCivil = estadoCivil;
        this.estadoCivilLetras = estadoCivilLetras;
        this.pais = pais;
        this.nacionalidad = nacionalidad;
        this.dpi = dpi;
        this.nit = nit;
        this.direccion = direccion;
        this.renglon = renglon;
        this.tipoServicios = tipoServicios;
        this.honorario = honorario;
        this.estado = estado;
        this.fechaInsert = fechaInsert;
        this.ubicacionFuncional = ubicacionFuncional;
        this.nombreUbicacion = nombreUbicacion;
    }

    public ResultsFuncionario(BigDecimal idRue,BigDecimal idContrato,
            String fechaNacimiento, String estadoCivil, String estadoCivilLetras, 
            BigDecimal pais, String nacionalidad, String dpi, 
            String nit, String direccion, String renglon, 
            String tipoServicios, Double honorario, String estado, Date fechaInsert,
            BigDecimal ubicacionFuncional,String nombreUbicacion,
            String fechaDel, String fechaAl,String fechaCambioTipoMovimiento,List<ResultsActividad> actividades,
            ResultsAcademico academico,String observaciones) {
        this.idRue = idRue;
        this.idContrato = idContrato;
        this.fechaNacimiento = fechaNacimiento;
        this.estadoCivil = estadoCivil;
        this.estadoCivilLetras = estadoCivilLetras;
        this.pais = pais;
        this.nacionalidad = nacionalidad;
        this.dpi = dpi;
        this.nit = nit;
        this.direccion = direccion;
        this.renglon = renglon;
        this.tipoServicios = tipoServicios;
        this.honorario = honorario;
        this.estado = estado;
        this.fechaInsert = fechaInsert;
        this.ubicacionFuncional = ubicacionFuncional;
        this.nombreUbicacion = nombreUbicacion;
        this.fechaDel = fechaDel;
        this.fechaAl = fechaAl;
        this.fechaCambioTipoMovimiento = fechaCambioTipoMovimiento;
        this.actividades = actividades;
        this.academico = academico;
        this.observaciones = observaciones;
    }
    
    

    public ResultsFuncionario(String fechaNacimiento, String estadoCivil, String estadoCivilLetras,
            String nacionalidad, String dpi,
            String nit, String direccion, String  numeroColegiado) {
        this.fechaNacimiento = fechaNacimiento;
        this.estadoCivil = estadoCivil;
        this.estadoCivilLetras = estadoCivilLetras;
        this.nacionalidad = nacionalidad;
        this.dpi = dpi;
        this.nit = nit;
        this.direccion = direccion;
        this.numeroColegiado =  numeroColegiado;
    }
    
    public ResultsFuncionario(String fechaNacimiento, String estadoCivil, String estadoCivilLetras,
            String nacionalidad, String dpi,
            String nit, String direccion, String  numeroColegiado,
            BigDecimal ubicacionFuncional,String nombreUbicacion, String fechaDel, String fechaAl,
            String fechaCambioTipoMovimiento,List<ResultsActividad> actividades,
            ResultsAcademico academico, String observaciones) {
        this.fechaNacimiento = fechaNacimiento;
        this.estadoCivil = estadoCivil;
        this.estadoCivilLetras = estadoCivilLetras;
        this.nacionalidad = nacionalidad;
        this.dpi = dpi;
        this.nit = nit;
        this.direccion = direccion;
        this.numeroColegiado =  numeroColegiado;
        this.fechaDel = fechaDel;
        this.fechaAl = fechaAl;
        this.fechaCambioTipoMovimiento = fechaCambioTipoMovimiento;
        this.actividades = actividades;
        this.academico = academico;
        this.observaciones = observaciones;
        this.ubicacionFuncional = ubicacionFuncional;
        this.nombreUbicacion = nombreUbicacion;
    }
    
    public ResultsFuncionario(BigDecimal idContrato, BigDecimal idRue, String dpi, String fechaDel, 
            String fechaAl, String fechaCambioTipoMovimiento, 
            String renglon, String tipoServicios, BigDecimal ubicacionFuncional,
            String nombreUbicacion, BigDecimal idPerfil, Double honorario){        
        this.idContrato = idContrato;
        this.idRue = idRue;
        this.dpi = dpi;
        this.fechaDel = fechaDel;
        this.fechaAl = fechaAl;
        this.fechaCambioTipoMovimiento = fechaCambioTipoMovimiento;
        this.renglon = renglon;
        this.tipoServicios = tipoServicios;
        this.ubicacionFuncional = ubicacionFuncional;
        this.nombreUbicacion = nombreUbicacion;
        this.idPerfil = idPerfil;
        this.honorario = honorario;
    }

    public BigDecimal getIdRue() {
        return idRue;
    }

    public void setIdRue(BigDecimal idRue) {
        this.idRue = idRue;
    }

    public BigDecimal getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(BigDecimal idContrato) {
        this.idContrato = idContrato;
    }
           
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEstadoCivilLetras() {
        return estadoCivilLetras;
    }

    public void setEstadoCivilLetras(String estadoCivilLetras) {
        this.estadoCivilLetras = estadoCivilLetras;
    }

    public BigDecimal getPais() {
        return pais;
    }

    public void setPais(BigDecimal pais) {
        this.pais = pais;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
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

    public String getRenglon() {
        return renglon;
    }

    public void setRenglon(String renglon) {
        this.renglon = renglon;
    }

    public String getTipoServicios() {
        return tipoServicios;
    }

    public void setTipoServicios(String tipoServicios) {
        this.tipoServicios = tipoServicios;
    }

    public Double getHonorario() {
        return honorario;
    }

    public void setHonorario(Double honorario) {
        this.honorario = honorario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaInsert() {
        return fechaInsert;
    }

    public void setFechaInsert(Date fechaInsert) {
        this.fechaInsert = fechaInsert;
    }

    public String getFechaAl() {
        return fechaAl;
    }

    public void setFechaAl(String fechaAl) {
        this.fechaAl = fechaAl;
    }

    public String getFechaDel() {
        return fechaDel;
    }
    
    public void setFechaDel(String fechaDel) {
        this.fechaDel = fechaDel;
    }

    public String getNumeroColegiado() {
        return  numeroColegiado;
    }

    public void setNumeroColegiado(String  numeroColegiado) {
        this. numeroColegiado =  numeroColegiado;
    }

    public BigDecimal getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(BigDecimal idPerfil) {
        this.idPerfil = idPerfil;
    }

    public List<ResultsActividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<ResultsActividad> actividades) {
        this.actividades = actividades;
    }

    public ResultsAcademico getAcademico() {
        return academico;
    }

    public void setAcademico(ResultsAcademico academico) {
        this.academico = academico;
    }

   

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getFechaCambioTipoMovimiento() {
        return fechaCambioTipoMovimiento;
    }

    public void setFechaCambioTipoMovimiento(String fechaCambioTipoMovimiento) {
        this.fechaCambioTipoMovimiento = fechaCambioTipoMovimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getUbicacionFuncional() {
        return ubicacionFuncional;
    }

    public void setUbicacionFuncional(BigDecimal ubicacionFuncional) {
        this.ubicacionFuncional = ubicacionFuncional;
    }

    public String getNombreUbicacion() {
        return nombreUbicacion;
    }

    public void setNombreUbicacion(String nombreUbicacion) {
        this.nombreUbicacion = nombreUbicacion;
    }

   
    
    
    
}
