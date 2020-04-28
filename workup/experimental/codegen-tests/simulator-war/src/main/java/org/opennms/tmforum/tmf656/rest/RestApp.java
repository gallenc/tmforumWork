package org.opennms.tmforum.tmf656.rest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import org.springframework.stereotype.Component;

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


public class RestApp extends ResourceConfig {
	private Logger LOG = LoggerFactory.getLogger(RestApp.class);
	
	// should be injected but leave for now
	private String basePath = "/tmf656-spm-simulator-war/tmf-api/serviceProblemManagement/v3/";
	
	// produces http://localhost:8080/tmf-api/serviceProblemManagement/v3/swagger.json
	// see
	// https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Getting-started
	
	// setting package names programatically because we may refactor
	String packageApi = org.opennms.tmforum.swagger.tmf656.swagger.api.StringUtil.class.getPackage().getName();
	String packageModel = org.opennms.tmforum.swagger.tmf656.swagger.model.Any.class.getPackage().getName();
	
	public RestApp() {
		LOG.info("**************************** DEBUG STARTING INTERFACE REST APP ");

		LOG.info("**************************** Registering packages "+packageApi+" "+packageModel);
		
		packages(packageApi, packageModel, "org.opennms.tmforum.tmf656.simulator.api.impl");
		
		register(JacksonFeature.class);

	    register(JacksonObjectMapperProvider.class);

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
		// swagger is at http://localhost:8080/basePath/swagger.json
		config.setBasePath(basePath);
		//config.setResourcePackage("org.opennms.tmforum.swagger.tmf656.swagger.api");
		config.setResourcePackage(packageApi);
		config.setPrettyPrint(true);
		config.setScan(true);

		// http://localhost:8084/project-web/rest/swagger/v1.0/swagger.json
		// http://localhost:8080/swagger/v1.0/swagger.json
	}
}
