/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rhh.contratos.util;

import cgc.rrhh.contratos.pojo.ResultsFuncionario;


/**
 *
 * @author ejmorales
 */
public class ResponseData {
    
    private Integer code;
    private ResultsFuncionario data;
    private String message;    

    public ResponseData() {
    }

    public ResponseData(Integer code, ResultsFuncionario data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ResultsFuncionario getData() {
        return data;
    }

    public void setData(ResultsFuncionario data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
