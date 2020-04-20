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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

    


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
	Logger LOG = LoggerFactory.getLogger(RestApp.class);

	// produces http://localhost:8080/tmf-api/serviceProblemManagement/v3/swagger.json
	// see
	// https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Getting-started
	
	String packageApi = org.opennms.tmforum.swagger.tmf656.swagger.api.StringUtil.class.getPackage().getName();
	String packageModel = org.opennms.tmforum.swagger.tmf656.swagger.model.Any.class.getPackage().getName();
	
	public RestApp() {
		LOG.info("**************************** DEBUG STARTING INTERFACE REST APP ");
		
		//ObjectMapper mapper = new ObjectMapper();
		//mapper.registerModule(new JavaTimeModule());
		
		// setting package names programatically because we may refactor

		LOG.info("**************************** Registering packages "+packageApi+" "+packageModel);
		
		packages(packageApi, packageModel);

		configureSwagger();
	}

	// swagger 1.5
	// see
	// https://stackoverflow.com/questions/40480131/how-to-use-swagger-with-resourceconfig-in-jersey
	private void configureSwagger() {
		this.register(ApiListingResource.class);
		this.register(SwaggerSerializers.class);
		BeanConfig config = new BeanConfig();
		config.setConfigId("spring-jaxrs-swagger");
		config.setTitle("Swagger Server");
		config.setVersion("1.0.0");
		// swagger is at http://localhost:8080/tmf-api/serviceProblemManagement/v3/swagger.json
		config.setBasePath("/tmf-api/serviceProblemManagement/v3/");
		//config.setResourcePackage("org.opennms.tmforum.swagger.tmf656.swagger.api");
		config.setResourcePackage(packageApi);
		config.setPrettyPrint(true);
		config.setScan(true);

		// http://localhost:8084/project-web/rest/swagger/v1.0/swagger.json
		// http://localhost:8080/swagger/v1.0/swagger.json
	}
}
