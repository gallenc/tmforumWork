package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Error;
import io.swagger.model.ProblemAcknowledgement;
import io.swagger.model.ProblemAcknowledgementCreate;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public abstract class ProblemAcknowledgementApiService {
    public abstract Response createProblemAcknowledgement(ProblemAcknowledgementCreate problemAcknowledgement,SecurityContext securityContext) throws NotFoundException;
}
