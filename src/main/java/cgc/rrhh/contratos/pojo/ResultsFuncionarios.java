/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.pojo;

/**
 *
 * @author ejmorales
 */
public class ResultsFuncionarios {
    
    private String dpi;
    private String nombreCompleto;
    
    public ResultsFuncionarios(){
        
    }

    public ResultsFuncionarios(String dpi, String nombreCompleto) {
        this.dpi = dpi;
        this.nombreCompleto = nombreCompleto;
    }
    
       
    public String getDpi(){
        return dpi;
    }
    
    public void setDpi(String dpi){
        this.dpi = dpi;
    }
    
    public String getNombreCompleto(){
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    
}
