package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Error;
import io.swagger.model.ProblemUngroup;
import io.swagger.model.ProblemUngroupCreate;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public abstract class ProblemUngroupApiService {
    public abstract Response createProblemUngroup(ProblemUngroupCreate problemUngroup,SecurityContext securityContext) throws NotFoundException;
}
