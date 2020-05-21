package org.opennms.tmforum.tmf656.simulator.api.hub.impl;

import org.opennms.tmforum.swagger.tmf656.swagger.api.*;
import org.opennms.tmforum.swagger.tmf656.swagger.model.*;

import org.opennms.tmforum.swagger.tmf656.swagger.model.Error;
import org.opennms.tmforum.swagger.tmf656.swagger.model.EventSubscription;
import org.opennms.tmforum.swagger.tmf656.swagger.model.EventSubscriptionInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.opennms.tmforum.swagger.tmf656.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

// NOTE this class has been modified by maven-replacer-plugin from swagger generated class to allow injection
@javax.inject.Named
public class HubApiServiceImpl extends HubApiService {
    private static Logger LOG = LoggerFactory.getLogger(HubApiServiceImpl.class);

    // statically finds the fully qualificed name of this class
    private static String apiServiceImplClassName = java.lang.invoke.MethodHandles.lookup().lookupClass().getName();

    @Override
    public Response registerListener(EventSubscriptionInput data, SecurityContext securityContext, javax.ws.rs.core.UriInfo uriInfo) throws NotFoundException {
        LOG.debug("register listener called EventSubscriptionInput="+data);
        // do some magic!
        return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "this method not implemented in class="+apiServiceImplClassName)).build();
        // return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response unregisterListener(String id, SecurityContext securityContext, javax.ws.rs.core.UriInfo uriInfo) throws NotFoundException {
        LOG.debug("unregister listener called id="+id);
        // do some magic!
        return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "this method not implemented in class="+apiServiceImplClassName)).build();
        // return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
