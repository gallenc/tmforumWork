package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.ListenerApiService;
import io.swagger.api.factories.ListenerApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Error;
import io.swagger.model.EventSubscription;
import io.swagger.model.ServiceProblemAttributeValueChangeNotification;
import io.swagger.model.ServiceProblemCreateNotification;
import io.swagger.model.ServiceProblemInformationRequiredNotification;
import io.swagger.model.ServiceProblemStateChangeNotification;

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

@Path("/listener")
@Consumes({ "application/json;charset=utf-8" })
@Produces({ "application/json;charset=utf-8" })
@io.swagger.annotations.Api(description = "the listener API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public class ListenerApi  {
   private final ListenerApiService delegate;

   public ListenerApi(@Context ServletConfig servletContext) {
      ListenerApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("ListenerApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (ListenerApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = ListenerApiServiceFactory.getListenerApi();
      }

      this.delegate = delegate;
   }

    @POST
    @Path("/serviceProblemAttributeValueChangeNotification")
    @Consumes({ "application/json;charset=utf-8" })
    @Produces({ "application/json;charset=utf-8" })
    @io.swagger.annotations.ApiOperation(value = "Client listener for entity ServiceProblemAttributeValueChangeNotification", notes = "Example of a client listener for receiving the notification ServiceProblemAttributeValueChangeNotification", response = EventSubscription.class, tags={ "notification listeners (client side)", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Notified", response = EventSubscription.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    public Response listenToServiceProblemAttributeValueChangeNotification(@ApiParam(value = "The event data" ,required=true) ServiceProblemAttributeValueChangeNotification data
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.listenToServiceProblemAttributeValueChangeNotification(data,securityContext);
    }
    @POST
    @Path("/serviceProblemCreateNotification")
    @Consumes({ "application/json;charset=utf-8" })
    @Produces({ "application/json;charset=utf-8" })
    @io.swagger.annotations.ApiOperation(value = "Client listener for entity ServiceProblemCreateNotification", notes = "Example of a client listener for receiving the notification ServiceProblemCreateNotification", response = EventSubscription.class, tags={ "notification listeners (client side)", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Notified", response = EventSubscription.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    public Response listenToServiceProblemCreateNotification(@ApiParam(value = "The event data" ,required=true) ServiceProblemCreateNotification data
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.listenToServiceProblemCreateNotification(data,securityContext);
    }
    @POST
    @Path("/serviceProblemInformationRequiredNotification")
    @Consumes({ "application/json;charset=utf-8" })
    @Produces({ "application/json;charset=utf-8" })
    @io.swagger.annotations.ApiOperation(value = "Client listener for entity ServiceProblemInformationRequiredNotification", notes = "Example of a client listener for receiving the notification ServiceProblemInformationRequiredNotification", response = EventSubscription.class, tags={ "notification listeners (client side)", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Notified", response = EventSubscription.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    public Response listenToServiceProblemInformationRequiredNotification(@ApiParam(value = "The event data" ,required=true) ServiceProblemInformationRequiredNotification data
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.listenToServiceProblemInformationRequiredNotification(data,securityContext);
    }
    @POST
    @Path("/serviceProblemStateChangeNotification")
    @Consumes({ "application/json;charset=utf-8" })
    @Produces({ "application/json;charset=utf-8" })
    @io.swagger.annotations.ApiOperation(value = "Client listener for entity ServiceProblemStateChangeNotification", notes = "Example of a client listener for receiving the notification ServiceProblemStateChangeNotification", response = EventSubscription.class, tags={ "notification listeners (client side)", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Notified", response = EventSubscription.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    public Response listenToServiceProblemStateChangeNotification(@ApiParam(value = "The event data" ,required=true) ServiceProblemStateChangeNotification data
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.listenToServiceProblemStateChangeNotification(data,securityContext);
    }
}
