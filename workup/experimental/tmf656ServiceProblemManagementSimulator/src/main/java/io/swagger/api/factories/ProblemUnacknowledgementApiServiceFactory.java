package io.swagger.api.factories;

import io.swagger.api.ProblemUnacknowledgementApiService;
import io.swagger.api.impl.ProblemUnacknowledgementApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public class ProblemUnacknowledgementApiServiceFactory {
    private final static ProblemUnacknowledgementApiService service = new ProblemUnacknowledgementApiServiceImpl();

    public static ProblemUnacknowledgementApiService getProblemUnacknowledgementApi() {
        return service;
    }
}
