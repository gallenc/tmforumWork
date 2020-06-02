package org.opennms.tmforum.tmf650.listener.impl;

import org.opennms.tmforum.tmf650.api.*;
import org.opennms.tmforum.tmf650.model.*;

import org.opennms.tmforum.swagger.tmf656.swagger.model.Error;
import org.opennms.tmforum.tmf656.simulator.api.impl.HubApiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

import org.opennms.tmforum.swagger.tmf656.swagger.api.ApiResponseMessage;
import org.opennms.tmforum.swagger.tmf656.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.inject.Inject;
import javax.validation.constraints.*;

@javax.inject.Named
public class GenericListenerApiServiceImpl extends GenericListenerApiService {
    private static Logger LOG = LoggerFactory.getLogger(GenericListenerApiServiceImpl.class);

    @Inject
    GenericNotificationSubscriber genericNotificationSubscriber;

    @Override
    public Response listenToGenericNotification(GenericNotification data, SecurityContext securityContext,
            UriInfo uriInfo) {
        try {
            LOG.debug("listenToGenericNotification called for GenericNotification=" + data);

            if (genericNotificationSubscriber != null) {
                LOG.debug("sending notification to listeners=");
                genericNotificationSubscriber.onNotification(data);
            }

            return Response.ok().build();
            
        } catch (Exception ex) {
            LOG.error("POST /registerListener registerListener ", ex);
            ApiResponseMessage apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "POST /registerListener registerListener error: " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiResponseMessage).build();
        }
    }

}
