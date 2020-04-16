package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Error;
import io.swagger.model.EventSubscription;
import io.swagger.model.ServiceProblemAttributeValueChangeNotification;
import io.swagger.model.ServiceProblemCreateNotification;
import io.swagger.model.ServiceProblemInformationRequiredNotification;
import io.swagger.model.ServiceProblemStateChangeNotification;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public abstract class ListenerApiService {
    public abstract Response listenToServiceProblemAttributeValueChangeNotification(ServiceProblemAttributeValueChangeNotification data,SecurityContext securityContext) throws NotFoundException;
    public abstract Response listenToServiceProblemCreateNotification(ServiceProblemCreateNotification data,SecurityContext securityContext) throws NotFoundException;
    public abstract Response listenToServiceProblemInformationRequiredNotification(ServiceProblemInformationRequiredNotification data,SecurityContext securityContext) throws NotFoundException;
    public abstract Response listenToServiceProblemStateChangeNotification(ServiceProblemStateChangeNotification data,SecurityContext securityContext) throws NotFoundException;
}
