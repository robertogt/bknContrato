/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.pojo;

import cgc.rrhh.contratos.model.RrhhAcuerdoContrato;
import cgc.rrhh.contratos.model.RrhhContratoEstado;
import cgc.rrhh.contratos.model.RrhhLaboral;
import java.util.List;

/**
 *
 * @author ejmorales
 */
public class PersistAcuerdoContrato {
    
    private List<RrhhAcuerdoContrato> update;
    private List<RrhhAcuerdoContrato> create;
    private List<RrhhLaboral> laboral;
    private List<RrhhContratoEstado> modificarEstado;
    private List<RrhhContratoEstado> crearEstado;

    public PersistAcuerdoContrato() {
    }

    public PersistAcuerdoContrato(List<RrhhAcuerdoContrato> update, List<RrhhAcuerdoContrato> create, List<RrhhLaboral> laboral, List<RrhhContratoEstado> modificarEstado, List<RrhhContratoEstado> crearEstado) {
        this.update = update;
        this.create = create;
        this.laboral = laboral;
        this.modificarEstado = modificarEstado;
        this.crearEstado = crearEstado;
    }

    public List<RrhhContratoEstado> getCrearEstado() {
        return crearEstado;
    }

    public void setCrearEstado(List<RrhhContratoEstado> crearEstado) {
        this.crearEstado = crearEstado;
    }

    public List<RrhhLaboral> getLaboral() {
        return laboral;
    }

    public void setLaboral(List<RrhhLaboral> laboral) {
        this.laboral = laboral;
    }

    public List<RrhhContratoEstado> getModificarEstado() {
        return modificarEstado;
    }

    public void setModificarEstado(List<RrhhContratoEstado> modificarEstado) {
        this.modificarEstado = modificarEstado;
    }

    

    public List<RrhhAcuerdoContrato> getCreate() {
        return create;
    }

    public void setCreate(List<RrhhAcuerdoContrato> create) {
        this.create = create;
    }

    public List<RrhhAcuerdoContrato> getUpdate() {
        return update;
    }

    public void setUpdate(List<RrhhAcuerdoContrato> update) {
        this.update = update;
    }
    
    
    
}
