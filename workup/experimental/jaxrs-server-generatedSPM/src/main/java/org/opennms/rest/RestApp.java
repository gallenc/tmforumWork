package org.opennms.rest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

//public class ApiApplication extends Application {
//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> resources = new HashSet();
//
//        //resources.add(FirstResource.class);
//        //resources.add(SecondResource.class);
//        //...
//
//        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
//        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
//        
//        return resources;
//    }
//}


// @ApplicationPath("/rest")
public class RestApp extends ResourceConfig {

    // produces http://localhost:8084/project-web/rest/openapi.json 
    // see https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Getting-started
    public RestApp() {
    	  System.out.println("**************************** DEBUG STARTING REST APP");

    	
        packages("io.swagger.api",
                "io.swagger.model"
        );
       // configureSwagger();
    }

    // swagger 1.5
    // see https://stackoverflow.com/questions/40480131/how-to-use-swagger-with-resourceconfig-in-jersey
    private void configureSwagger() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);
        BeanConfig config = new BeanConfig();
        config.setConfigId("spring-jaxrs-swagger");
        config.setTitle("Spring Jersey jaxrs swagger integration");
        config.setVersion("v1.0");
        config.setBasePath("/swagger");
        config.setResourcePackage("io.swagger.api");
        config.setPrettyPrint(true);
        config.setScan(true);
    // http://localhost:8084/project-web/rest/swagger/v1.0/swagger.json
    // http://localhost:8080/swagger/v1.0/swagger.json
    }
}

