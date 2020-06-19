package org.opennms.tmforum.tmf656.simulator.api.impl;

import org.opennms.tmforum.swagger.tmf656.swagger.api.*;
import org.opennms.tmforum.swagger.tmf656.swagger.model.*;

import org.opennms.tmforum.swagger.tmf656.swagger.model.Error;
import org.opennms.tmforum.swagger.tmf656.swagger.model.ProblemUnacknowledgement;
import org.opennms.tmforum.swagger.tmf656.swagger.model.ProblemUnacknowledgementCreate;

import java.util.List;
import org.opennms.tmforum.swagger.tmf656.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.opennms.tmforum.swagger.patch.JavaJerseyServerCodegen", date = "2020-06-19T09:47:32.097+01:00")

// NOTE this class has been modified by maven-replacer-plugin from swagger generated class to allow injection
@javax.inject.Named
public class ProblemUnacknowledgementApiServiceImpl extends ProblemUnacknowledgementApiService {

    // statically finds the fully qualified name of this class
    private static String apiServiceImplClassName = java.lang.invoke.MethodHandles.lookup().lookupClass().getName();

    @Override
    public Response createProblemUnacknowledgement(ProblemUnacknowledgementCreate problemUnacknowledgement, SecurityContext securityContext, javax.ws.rs.core.UriInfo uriInfo) throws NotFoundException {
        // do some magic!
        return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "XXX this method not implemented in class="+apiServiceImplClassName)).build();
        // return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
