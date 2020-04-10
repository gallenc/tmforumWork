package io.swagger.api.factories;

import io.swagger.api.ListenerApiService;
import io.swagger.api.impl.ListenerApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public class ListenerApiServiceFactory {
    private final static ListenerApiService service = new ListenerApiServiceImpl();

    public static ListenerApiService getListenerApi() {
        return service;
    }
}
