package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.ProblemUngroupApiService;
import io.swagger.api.factories.ProblemUngroupApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Error;
import io.swagger.model.ProblemUngroup;
import io.swagger.model.ProblemUngroupCreate;

import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/problemUngroup")
@Consumes({ "application/json;charset=utf-8" })
@Produces({ "application/json;charset=utf-8" })
@io.swagger.annotations.Api(description = "the problemUngroup API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public class ProblemUngroupApi  {
   private final ProblemUngroupApiService delegate;

   public ProblemUngroupApi(@Context ServletConfig servletContext) {
      ProblemUngroupApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("ProblemUngroupApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (ProblemUngroupApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = ProblemUngroupApiServiceFactory.getProblemUngroupApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json;charset=utf-8" })
    @Produces({ "application/json;charset=utf-8" })
    @io.swagger.annotations.ApiOperation(value = "Creates a ProblemUngroup", notes = "This operation creates a ProblemUngroup entity.", response = ProblemUngroup.class, tags={ "problemUngroup", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Created", response = ProblemUngroup.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    public Response createProblemUngroup(@ApiParam(value = "The ProblemUngroup to be created" ,required=true) ProblemUngroupCreate problemUngroup
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createProblemUngroup(problemUngroup,securityContext);
    }
}
