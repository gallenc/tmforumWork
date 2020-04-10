package io.swagger.api.factories;

import io.swagger.api.ProblemAcknowledgementApiService;
import io.swagger.api.impl.ProblemAcknowledgementApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public class ProblemAcknowledgementApiServiceFactory {
    private final static ProblemAcknowledgementApiService service = new ProblemAcknowledgementApiServiceImpl();

    public static ProblemAcknowledgementApiService getProblemAcknowledgementApi() {
        return service;
    }
}
