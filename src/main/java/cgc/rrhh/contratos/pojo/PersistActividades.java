/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.pojo;

import cgc.rrhh.contratos.model.RrhhActividadContrato;
import java.util.List;

/**
 *
 * @author ejmorales
 */
public class PersistActividades {
    
    private List<RrhhActividadContrato> update;
    private List<RrhhActividadContrato> crear;

    public PersistActividades() {
    }

    public PersistActividades(List<RrhhActividadContrato> update, List<RrhhActividadContrato> crear) {
        this.update = update;
        this.crear = crear;
    }

    public List<RrhhActividadContrato> getUpdate() {
        return update;
    }

    public void setUpdate(List<RrhhActividadContrato> update) {
        this.update = update;
    }

    public List<RrhhActividadContrato> getCrear() {
        return crear;
    }

    public void setCrear(List<RrhhActividadContrato> crear) {
        this.crear = crear;
    }
    
    
    
}
