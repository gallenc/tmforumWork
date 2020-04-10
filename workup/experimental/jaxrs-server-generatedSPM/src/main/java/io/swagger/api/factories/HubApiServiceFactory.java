package io.swagger.api.factories;

import io.swagger.api.HubApiService;
import io.swagger.api.impl.HubApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public class HubApiServiceFactory {
    private final static HubApiService service = new HubApiServiceImpl();

    public static HubApiService getHubApi() {
        return service;
    }
}
