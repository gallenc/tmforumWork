package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Error;
import io.swagger.model.ServiceProblem;
import io.swagger.model.ServiceProblemCreate;
import io.swagger.model.ServiceProblemUpdate;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public abstract class ServiceProblemApiService {
    public abstract Response createServiceProblem(ServiceProblemCreate serviceProblem,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteServiceProblem(String id,SecurityContext securityContext) throws NotFoundException;
    public abstract Response listServiceProblem( String fields, Integer offset, Integer limit,SecurityContext securityContext) throws NotFoundException;
    public abstract Response patchServiceProblem(String id,ServiceProblemUpdate serviceProblem,SecurityContext securityContext) throws NotFoundException;
    public abstract Response retrieveServiceProblem(String id, String fields,SecurityContext securityContext) throws NotFoundException;
}
