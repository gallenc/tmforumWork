package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.ServiceProblemApiService;
import io.swagger.api.factories.ServiceProblemApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Error;
import io.swagger.model.ServiceProblem;
import io.swagger.model.ServiceProblemCreate;
import io.swagger.model.ServiceProblemUpdate;

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

@Path("/serviceProblem")
@Consumes({ "application/json;charset=utf-8" })
@Produces({ "application/json;charset=utf-8" })
@io.swagger.annotations.Api(description = "the serviceProblem API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public class ServiceProblemApi  {
   private final ServiceProblemApiService delegate;

   public ServiceProblemApi(@Context ServletConfig servletContext) {
      ServiceProblemApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("ServiceProblemApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (ServiceProblemApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = ServiceProblemApiServiceFactory.getServiceProblemApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json;charset=utf-8" })
    @Produces({ "application/json;charset=utf-8" })
    @io.swagger.annotations.ApiOperation(value = "Creates a ServiceProblem", notes = "This operation creates a ServiceProblem entity.", response = ServiceProblem.class, tags={ "serviceProblem", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Created", response = ServiceProblem.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    public Response createServiceProblem(@ApiParam(value = "The ServiceProblem to be created" ,required=true) ServiceProblemCreate serviceProblem
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createServiceProblem(serviceProblem,securityContext);
    }
    @DELETE
    @Path("/{id}")
    @Consumes({ "application/json;charset=utf-8" })
    @Produces({ "application/json;charset=utf-8" })
    @io.swagger.annotations.ApiOperation(value = "Deletes a ServiceProblem", notes = "This operation deletes a ServiceProblem entity.", response = Void.class, tags={ "serviceProblem", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Deleted", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    public Response deleteServiceProblem(@ApiParam(value = "Identifier of the ServiceProblem",required=true) @PathParam("id") String id
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteServiceProblem(id,securityContext);
    }
    @GET
    
    @Consumes({ "application/json;charset=utf-8" })
    @Produces({ "application/json;charset=utf-8" })
    @io.swagger.annotations.ApiOperation(value = "List or find ServiceProblem objects", notes = "This operation list or find ServiceProblem entities", response = ServiceProblem.class, responseContainer = "List", tags={ "serviceProblem", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = ServiceProblem.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    public Response listServiceProblem(@ApiParam(value = "Comma-separated properties to be provided in response") @QueryParam("fields") String fields
,@ApiParam(value = "Requested index for start of resources to be provided in response") @QueryParam("offset") Integer offset
,@ApiParam(value = "Requested number of resources to be provided in response") @QueryParam("limit") Integer limit
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.listServiceProblem(fields,offset,limit,securityContext);
    }
    @PATCH
    @Path("/{id}")
    @Consumes({ "application/json;charset=utf-8" })
    @Produces({ "application/json;charset=utf-8" })
    @io.swagger.annotations.ApiOperation(value = "Updates partially a ServiceProblem", notes = "This operation updates partially a ServiceProblem entity.", response = ServiceProblem.class, tags={ "serviceProblem", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Updated", response = ServiceProblem.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    public Response patchServiceProblem(@ApiParam(value = "Identifier of the ServiceProblem",required=true) @PathParam("id") String id
,@ApiParam(value = "The ServiceProblem to be updated" ,required=true) ServiceProblemUpdate serviceProblem
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.patchServiceProblem(id,serviceProblem,securityContext);
    }
    @GET
    @Path("/{id}")
    @Consumes({ "application/json;charset=utf-8" })
    @Produces({ "application/json;charset=utf-8" })
    @io.swagger.annotations.ApiOperation(value = "Retrieves a ServiceProblem by ID", notes = "This operation retrieves a ServiceProblem entity. Attribute selection is enabled for all first level attributes.", response = ServiceProblem.class, tags={ "serviceProblem", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = ServiceProblem.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    public Response retrieveServiceProblem(@ApiParam(value = "Identifier of the ServiceProblem",required=true) @PathParam("id") String id
,@ApiParam(value = "Comma-separated properties to provide in response") @QueryParam("fields") String fields
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.retrieveServiceProblem(id,fields,securityContext);
    }
}
