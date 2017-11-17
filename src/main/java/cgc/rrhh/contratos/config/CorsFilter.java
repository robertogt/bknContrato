/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgc.rrhh.contratos.config;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author ejmorales
 */
@Provider
public class CorsFilter implements ContainerResponseFilter{
     @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
 
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin,X-Requested-With,Content-Type,Authorization");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        
        if("OPTIONS".equalsIgnoreCase(requestContext.getMethod())){
            responseContext.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
