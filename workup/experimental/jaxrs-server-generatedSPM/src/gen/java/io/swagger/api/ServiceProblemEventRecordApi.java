package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.ServiceProblemEventRecordApiService;
import io.swagger.api.factories.ServiceProblemEventRecordApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Error;
import io.swagger.model.ServiceProblemEventRecord;

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

@Path("/serviceProblemEventRecord")
@Consumes({ "application/json;charset=utf-8" })
@Produces({ "application/json;charset=utf-8" })
@io.swagger.annotations.Api(description = "the serviceProblemEventRecord API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public class ServiceProblemEventRecordApi  {
   private final ServiceProblemEventRecordApiService delegate;

   public ServiceProblemEventRecordApi(@Context ServletConfig servletContext) {
      ServiceProblemEventRecordApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("ServiceProblemEventRecordApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (ServiceProblemEventRecordApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = ServiceProblemEventRecordApiServiceFactory.getServiceProblemEventRecordApi();
      }

      this.delegate = delegate;
   }

    @GET
    
    @Consumes({ "application/json;charset=utf-8" })
    @Produces({ "application/json;charset=utf-8" })
    @io.swagger.annotations.ApiOperation(value = "List or find ServiceProblemEventRecord objects", notes = "This operation list or find ServiceProblemEventRecord entities", response = ServiceProblemEventRecord.class, responseContainer = "List", tags={ "serviceProblemEventRecord", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = ServiceProblemEventRecord.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    public Response listServiceProblemEventRecord(@ApiParam(value = "Comma-separated properties to be provided in response") @QueryParam("fields") String fields
,@ApiParam(value = "Requested index for start of resources to be provided in response") @QueryParam("offset") Integer offset
,@ApiParam(value = "Requested number of resources to be provided in response") @QueryParam("limit") Integer limit
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.listServiceProblemEventRecord(fields,offset,limit,securityContext);
    }
    @GET
    @Path("/{id}")
    @Consumes({ "application/json;charset=utf-8" })
    @Produces({ "application/json;charset=utf-8" })
    @io.swagger.annotations.ApiOperation(value = "Retrieves a ServiceProblemEventRecord by ID", notes = "This operation retrieves a ServiceProblemEventRecord entity. Attribute selection is enabled for all first level attributes.", response = ServiceProblemEventRecord.class, tags={ "serviceProblemEventRecord", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = ServiceProblemEventRecord.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    public Response retrieveServiceProblemEventRecord(@ApiParam(value = "Identifier of the ServiceProblemEventRecord",required=true) @PathParam("id") String id
,@ApiParam(value = "Comma-separated properties to provide in response") @QueryParam("fields") String fields
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.retrieveServiceProblemEventRecord(id,fields,securityContext);
    }
}
