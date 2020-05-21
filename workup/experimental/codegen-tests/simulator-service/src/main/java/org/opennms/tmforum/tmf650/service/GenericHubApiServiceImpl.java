package org.opennms.tmforum.tmf650.service;

import org.opennms.tmforum.tmf650.api.*;
import org.opennms.tmforum.tmf650.model.*;

import org.opennms.tmforum.swagger.tmf656.swagger.model.Error;
import org.opennms.tmforum.tmf650.model.GenericEventSubscription;
import org.opennms.tmforum.tmf650.model.GenericEventSubscriptionInput;
import org.opennms.tmforum.tmf656.simulator.api.impl.HubApiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.opennms.tmforum.swagger.tmf656.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.inject.Named
public class GenericHubApiServiceImpl extends GenericHubApiService {
    private static Logger LOG = LoggerFactory.getLogger(GenericHubApiServiceImpl.class);

    // statically finds the fully qualified name of this class
    private static String apiServiceImplClassName = java.lang.invoke.MethodHandles.lookup().lookupClass().getName();

    @Override
    public Response registerListener(GenericEventSubscriptionInput data, SecurityContext securityContext, javax.ws.rs.core.UriInfo uriInfo) throws NotFoundException {
        LOG.debug("register listener called EventSubscriptionInput="+data);
        
        return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).build();
        // return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response unregisterListener(String id, SecurityContext securityContext, javax.ws.rs.core.UriInfo uriInfo) throws NotFoundException {
        LOG.debug("unregister listener called id="+id);
        
        return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).build();
        // return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
