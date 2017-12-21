/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.pojo;

import cgc.rrhh.contratos.model.RrhhAcuerdoContrato;
import java.util.List;

/**
 *
 * @author ejmorales
 */
public class ResponseAcuerdoContrato {
    
    private List<ResultsContrato> target;
    private List<ResultsContrato> source;

    public ResponseAcuerdoContrato() {
    }

    public ResponseAcuerdoContrato(List<ResultsContrato> target, List<ResultsContrato> source) {
        this.target = target;
        this.source = source;
    }

    public List<ResultsContrato> getTarget() {
        return target;
    }

    public void setTarget(List<ResultsContrato> target) {
        this.target = target;
    }

    public List<ResultsContrato> getSource() {
        return source;
    }

    public void setSource(List<ResultsContrato> source) {
        this.source = source;
    }
    
    
    
}
