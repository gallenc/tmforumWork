package org.opennms.tmforum.tmf650.api;

import org.opennms.tmforum.tmf650.model.GenericEventSubscriptionInput;

import org.opennms.tmforum.swagger.tmf656.swagger.api.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public abstract class GenericHubApiService {
    public abstract Response registerListener(GenericEventSubscriptionInput data,SecurityContext securityContext, javax.ws.rs.core.UriInfo uriInfo) throws NotFoundException;
    public abstract Response unregisterListener(String id,SecurityContext securityContext, javax.ws.rs.core.UriInfo uriInfo) throws NotFoundException;
}
