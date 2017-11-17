/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.pojo;

import cgc.rrhh.contratos.model.RrhhAcademico;

/**
 *
 * @author ejmorales
 */
public class PersistAcademico {
    
    private RrhhAcademico rrhhAcademico;
    private boolean create = false;
    private boolean update = false;

    public PersistAcademico() {
    }

    public PersistAcademico(RrhhAcademico rrhhAcademico) {
        this.rrhhAcademico = rrhhAcademico;
    }

    public RrhhAcademico getRrhhAcademico() {
        return rrhhAcademico;
    }

    public void setRrhhAcademico(RrhhAcademico rrhhAcademico) {
        this.rrhhAcademico = rrhhAcademico;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
    
    
    
}
