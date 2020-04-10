package io.swagger.api.factories;

import io.swagger.api.ProblemUngroupApiService;
import io.swagger.api.impl.ProblemUngroupApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public class ProblemUngroupApiServiceFactory {
    private final static ProblemUngroupApiService service = new ProblemUngroupApiServiceImpl();

    public static ProblemUngroupApiService getProblemUngroupApi() {
        return service;
    }
}
