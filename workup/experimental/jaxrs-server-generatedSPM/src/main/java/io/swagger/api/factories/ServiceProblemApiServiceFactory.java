package io.swagger.api.factories;

import io.swagger.api.ServiceProblemApiService;
import io.swagger.api.impl.ServiceProblemApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public class ServiceProblemApiServiceFactory {
    private final static ServiceProblemApiService service = new ServiceProblemApiServiceImpl();

    public static ServiceProblemApiService getServiceProblemApi() {
        return service;
    }
}
