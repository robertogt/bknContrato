/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.config;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author ejmorales
 */

@ApplicationPath("rest")
public class ApplicationConfig extends Application{
     @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(cgc.rrhh.contratos.config.CorsFilter.class);
        resources.add(cgc.rrhh.contratos.rest.ActividadPerfilREST.class);
        resources.add(cgc.rrhh.contratos.rest.AcuerdoAprobacionREST.class);
        resources.add(cgc.rrhh.contratos.rest.AddendumREST.class);
        resources.add(cgc.rrhh.contratos.rest.AsesorREST.class);
        resources.add(cgc.rrhh.contratos.rest.BusquedaREST.class);
        resources.add(cgc.rrhh.contratos.rest.ColegiosRest.class);
        resources.add(cgc.rrhh.contratos.rest.ConsultaREST.class);
        resources.add(cgc.rrhh.contratos.rest.ContratoEditarREST.class);
        resources.add(cgc.rrhh.contratos.rest.ContratoREST.class);
        resources.add(cgc.rrhh.contratos.rest.FianzaREST.class);
        resources.add(cgc.rrhh.contratos.rest.FuncionariosREST.class);
        resources.add(cgc.rrhh.contratos.rest.MantenimientoREST.class);
        resources.add(cgc.rrhh.contratos.rest.RescindirREST.class);
        resources.add(cgc.rrhh.contratos.rest.RueREST.class);
        resources.add(cgc.rrhh.contratos.rest.TitulosRest.class);
    }
}
