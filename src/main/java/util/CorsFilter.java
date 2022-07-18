package util;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, 
    	ContainerResponseContext responseContext) throws IOException {
          responseContext.getHeaders().add(
            "Access-Control-Allow-Origin", "*");
          responseContext.getHeaders().add(
            "Access-Control-Allow-Credentials", "true");
          responseContext.getHeaders().add(
           "Access-Control-Allow-Headers",
           "origin, content-type, accept, authorization, Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, access-control-allow-headers, Origin, X-Requested-With, Content-Type, Accept, Authorization");
          
          responseContext.getHeaders().add(
            "Access-Control-Allow-Methods", 
            "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}