/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.pojo;

import cgc.rrhh.contratos.model.RrhhAcuerdoAprobacion;
import cgc.rrhh.contratos.model.RrhhAcuerdoContrato;
import cgc.rrhh.contratos.model.RrhhContratoEstado;
import cgc.rrhh.contratos.model.RrhhLaboral;
import java.util.List;

/**
 *
 * @author ejmorales
 */
public class PersistAcuerdoAprobacion {
    
    private List<RrhhLaboral> laboral;
    private List<RrhhAcuerdoContrato> acuerdo;
    private List<RrhhContratoEstado> update;
    private List<RrhhContratoEstado> create;
    private RrhhAcuerdoAprobacion aprobacion;

    public PersistAcuerdoAprobacion() {
    }

    public RrhhAcuerdoAprobacion getAprobacion() {
        return aprobacion;
    }

    public void setAprobacion(RrhhAcuerdoAprobacion aprobacion) {
        this.aprobacion = aprobacion;
    }
    
    

    public List<RrhhLaboral> getLaboral() {
        return laboral;
    }

    public void setLaboral(List<RrhhLaboral> laboral) {
        this.laboral = laboral;
    }

    public List<RrhhAcuerdoContrato> getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(List<RrhhAcuerdoContrato> acuerdo) {
        this.acuerdo = acuerdo;
    }

    public List<RrhhContratoEstado> getUpdate() {
        return update;
    }

    public void setUpdate(List<RrhhContratoEstado> update) {
        this.update = update;
    }

    public List<RrhhContratoEstado> getCreate() {
        return create;
    }

    public void setCreate(List<RrhhContratoEstado> create) {
        this.create = create;
    }
    
    
}
