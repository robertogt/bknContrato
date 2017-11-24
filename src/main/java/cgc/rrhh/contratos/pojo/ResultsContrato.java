/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.pojo;

import cgc.rrhh.contratos.model.RrhhActividad;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ejmorales
 */
public class ResultsContrato {
    
    private BigDecimal idContrato;
    private String nombreCompleto;
    private String numeroContrato;
    private String fechaDel;
    private String fechaAl;
    private String fechaCambioTipoMovimiento;
    private String nombreUbicacion;
    private String nombreRenglon; 
    private String estadoCivilLetras;
    private String nit;
    private String direccion;
    private String dpi;    
    private String tipoServicios;
    private String titulo;
    private String nombreColegioProfesional;
    private BigDecimal numeroColegiado; 
    private BigDecimal honorario;
    private BigDecimal idCatalogoEstado;        
    private String usuarioInsert;
    private Date fechaInsert;
    private String usuarioUpdate;
    private Date fechaUpdate;
    private String observaciones;
    private List<RrhhActividad> actividades;

    public ResultsContrato() {
    }

    public ResultsContrato(BigDecimal idContrato, String nombreCompleto, String numeroContrato,
            String fechaDel, String fechaAl, String fechaCambioTipoMovimiento,
            String nombreUbicacion, String nombreRenglon, String estadoCivilLetras,
            String nit, String direccion, String dpi, String tipoServicios, String titulo,
            String nombreColegioProfesional, BigDecimal numeroColegiado, BigDecimal honorario,
            BigDecimal idCatalogoEstado, String usuarioInsert, Date fechaInsert, String usuarioUpdate,
            Date fechaUpdate, String observaciones) {
        this.idContrato = idContrato;
        this.nombreCompleto = nombreCompleto;
        this.numeroContrato = numeroContrato;
        this.fechaDel = fechaDel;
        this.fechaAl = fechaAl;
        this.fechaCambioTipoMovimiento = fechaCambioTipoMovimiento;
        this.nombreUbicacion = nombreUbicacion;
        this.nombreRenglon = nombreRenglon;
        this.estadoCivilLetras = estadoCivilLetras;
        this.nit = nit;
        this.direccion = direccion;
        this.dpi = dpi;
        this.tipoServicios = tipoServicios;
        this.titulo = titulo;
        this.nombreColegioProfesional = nombreColegioProfesional;
        this.numeroColegiado = numeroColegiado;
        this.honorario = honorario;
        this.idCatalogoEstado = idCatalogoEstado;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
        this.usuarioUpdate = usuarioUpdate;
        this.fechaUpdate = fechaUpdate;
        this.observaciones = observaciones;
    }

    public ResultsContrato(BigDecimal idContrato, String nombreCompleto, String numeroContrato, String fechaDel, String fechaAl, String fechaCambioTipoMovimiento, String nombreUbicacion, String nombreRenglon, String estadoCivilLetras, String nit, String direccion, String dpi, String tipoServicios, String titulo, String nombreColegioProfesional, BigDecimal numeroColegiado, BigDecimal honorario, BigDecimal idCatalogoEstado, String usuarioInsert, Date fechaInsert, String usuarioUpdate, Date fechaUpdate, String observaciones, List<RrhhActividad> actividades) {
        this.idContrato = idContrato;
        this.nombreCompleto = nombreCompleto;
        this.numeroContrato = numeroContrato;
        this.fechaDel = fechaDel;
        this.fechaAl = fechaAl;
        this.fechaCambioTipoMovimiento = fechaCambioTipoMovimiento;
        this.nombreUbicacion = nombreUbicacion;
        this.nombreRenglon = nombreRenglon;
        this.estadoCivilLetras = estadoCivilLetras;
        this.nit = nit;
        this.direccion = direccion;
        this.dpi = dpi;
        this.tipoServicios = tipoServicios;
        this.titulo = titulo;
        this.nombreColegioProfesional = nombreColegioProfesional;
        this.numeroColegiado = numeroColegiado;
        this.honorario = honorario;
        this.idCatalogoEstado = idCatalogoEstado;
        this.usuarioInsert = usuarioInsert;
        this.fechaInsert = fechaInsert;
        this.usuarioUpdate = usuarioUpdate;
        this.fechaUpdate = fechaUpdate;
        this.observaciones = observaciones;
        this.actividades = actividades;
    }

    public List<RrhhActividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<RrhhActividad> actividades) {
        this.actividades = actividades;
    }
    
    

    public BigDecimal getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(BigDecimal idContrato) {
        this.idContrato = idContrato;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getFechaDel() {
        return fechaDel;
    }

    public void setFechaDel(String fechaDel) {
        this.fechaDel = fechaDel;
    }

    public String getFechaAl() {
        return fechaAl;
    }

    public void setFechaAl(String fechaAl) {
        this.fechaAl = fechaAl;
    }

    public String getFechaCambioTipoMovimiento() {
        return fechaCambioTipoMovimiento;
    }

    public void setFechaCambioTipoMovimiento(String fechaCambioTipoMovimiento) {
        this.fechaCambioTipoMovimiento = fechaCambioTipoMovimiento;
    }

    public String getNombreUbicacion() {
        return nombreUbicacion;
    }

    public void setNombreUbicacion(String nombreUbicacion) {
        this.nombreUbicacion = nombreUbicacion;
    }

    public String getNombreRenglon() {
        return nombreRenglon;
    }

    public void setNombreRenglon(String nombreRenglon) {
        this.nombreRenglon = nombreRenglon;
    }

    public String getEstadoCivilLetras() {
        return estadoCivilLetras;
    }

    public void setEstadoCivilLetras(String estadoCivilLetras) {
        this.estadoCivilLetras = estadoCivilLetras;
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

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getTipoServicios() {
        return tipoServicios;
    }

    public void setTipoServicios(String tipoServicios) {
        this.tipoServicios = tipoServicios;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombreColegioProfesional() {
        return nombreColegioProfesional;
    }

    public void setNombreColegioProfesional(String nombreColegioProfesional) {
        this.nombreColegioProfesional = nombreColegioProfesional;
    }

    public BigDecimal getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(BigDecimal numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

    public BigDecimal getIdCatalogoEstado() {
        return idCatalogoEstado;
    }

    public void setIdCatalogoEstado(BigDecimal idCatalogoEstado) {
        this.idCatalogoEstado = idCatalogoEstado;
    }

    public BigDecimal getHonorario() {
        return honorario;
    }

    public void setHonorario(BigDecimal honorario) {
        this.honorario = honorario;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
   
    
    
}
