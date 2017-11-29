/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.pojo;

import cgc.rrhh.contratos.model.RrhhAcuerdoContrato;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jrroquel
 */
public class ResultAcuerdoAprobacion {
    private BigDecimal idAcuerdoAprobacion;
    private BigInteger numeroAcuerdo;
    private String identificadorAcuerdo;
    private String renglon;
    private String tipoServicios;
    private String observaciones;
    private String estado;
    private String usuarioInsert;
    private Date fechaInsert;
    private String usuarioUpdate;
    private Date fechaUpdate;
    private List<RrhhAcuerdoContrato> rrhhAcuerdoContratoList;
    
    
    
    
    
}
