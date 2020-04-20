package io.swagger.api.factories;

import io.swagger.api.ServiceProblemEventRecordApiService;
import io.swagger.api.impl.ServiceProblemEventRecordApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public class ServiceProblemEventRecordApiServiceFactory {
    private final static ServiceProblemEventRecordApiService service = new ServiceProblemEventRecordApiServiceImpl();

    public static ServiceProblemEventRecordApiService getServiceProblemEventRecordApi() {
        return service;
    }
}
