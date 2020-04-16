package io.swagger.api.factories;

import io.swagger.api.ProblemGroupApiService;
import io.swagger.api.impl.ProblemGroupApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public class ProblemGroupApiServiceFactory {
    private final static ProblemGroupApiService service = new ProblemGroupApiServiceImpl();

    public static ProblemGroupApiService getProblemGroupApi() {
        return service;
    }
}
