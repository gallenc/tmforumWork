package org.opennms.tmforum.tmf656.simulator.api.impl;

import org.opennms.tmforum.swagger.tmf656.swagger.api.*;
import org.opennms.tmforum.swagger.tmf656.swagger.model.*;

import org.opennms.tmforum.swagger.tmf656.swagger.model.Error;
import org.opennms.tmforum.swagger.tmf656.swagger.model.ServiceProblem;
import org.opennms.tmforum.swagger.tmf656.swagger.model.ServiceProblemCreate;
import org.opennms.tmforum.swagger.tmf656.swagger.model.ServiceProblemUpdate;
import org.opennms.tmforum.tmf656.service.FieldFilter;
import org.opennms.tmforum.tmf656.simulator.dao.ServiceProblemRepository;
import org.opennms.tmforum.tmf656.simulator.mapper.ServiceProblemCreateMapper;
import org.opennms.tmforum.tmf656.simulator.mapper.ServiceProblemMapper;
import org.opennms.tmforum.tmf656.simulator.model.ServiceProblemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.opennms.tmforum.swagger.tmf656.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "org.opennms.tmforum.swagger.patch.JavaJerseyServerCodegen", date = "2020-04-27T17:02:50.919+01:00")

@Named
public class ServiceProblemApiServiceImpl extends ServiceProblemApiService {
    final static Logger LOG = LoggerFactory.getLogger(ServiceProblemApiServiceImpl.class);

    // TODO MAKE CONFIG OPTION
    public static final Integer DEFAULT_MAX_PAGE_LIMIT = 100; // maximum number of alarms returned if limit not
                                                              // specified

    @Inject
    ServiceProblemRepository serviceProblemRepository;

    @Override
    @Transactional
    public Response createServiceProblem(ServiceProblemCreate serviceProblemCreate, SecurityContext securityContext)
            throws NotFoundException {
        try {
            LOG.debug("POST /serviceProblem createServiceProblem called");

            // map swagger dto to jpa entity
            LOG.debug("serviceProblemCreate:" + serviceProblemCreate);

            ServiceProblemEntity serviceProblemEntity = ServiceProblemCreateMapper.INSTANCE
                    .serviceProblemCreateToServiceProblemEntity(serviceProblemCreate);

            LOG.debug("persisting serviceProblemEntity:" + serviceProblemEntity);

            // persist jpa entity
            serviceProblemEntity = serviceProblemRepository.save(serviceProblemEntity);

            // map jpa entity to swagger dto
            ServiceProblem serviceProblem = ServiceProblemMapper.INSTANCE
                    .serviceProblemEntityToServiceProblem(serviceProblemEntity);

            LOG.debug("created service problem returning serviceProblem:" + serviceProblem);

            return Response.status(Response.Status.CREATED).entity(serviceProblem).build();

        } catch (Exception ex) {
            LOG.error("POST /serviceProblem createServiceProblem ", ex);
            ApiResponseMessage apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "POST /serviceProblem createServiceProblem error: " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiResponseMessage).build();
        }

    }

    @Override
    @Transactional
    public Response deleteServiceProblem(String idStr, SecurityContext securityContext) throws NotFoundException {

        try {
            LOG.debug("DELETE /deleteServiceProblem/{id} called id=" + idStr);

            Long id = Long.parseLong(idStr);
            ApiResponseMessage apiResponseMessage;

            if (!serviceProblemRepository.existsById(id)) {
                LOG.debug("DELETE /deleteServiceProblem/{id} entity does not exist id=" + idStr);
                apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR,
                        "DELETE /deleteServiceProblem/{id} not found id=" + idStr);
                return Response.status(Response.Status.NOT_FOUND).entity(apiResponseMessage).build();
            }

            // map jpa entity to swagger dto
            serviceProblemRepository.deleteById(id);

            return Response.status(Response.Status.NO_CONTENT).build(); // NO_CONTENT = 204

        } catch (Exception ex) {
            LOG.error("POST /serviceProblem createServiceProblem ", ex);
            ApiResponseMessage apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "POST /serviceProblem createServiceProblem error: " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiResponseMessage).build();
        }
    }

    @Override
    public Response listServiceProblem(String fields, Integer offset, Integer limit, SecurityContext securityContext)
            throws NotFoundException {

        try {
            LOG.debug("GET /serviceProblem/ listServiceProblem called offset=" + offset + " limit=" + limit + " fields="
                    + fields);

            Pageable pageRequest;

            if (offset == null && limit == null) {
                pageRequest = PageRequest.of(0, DEFAULT_MAX_PAGE_LIMIT, Sort.by("id").ascending());
            } else {
                if (offset < 0 || limit < 1)
                    throw new IllegalArgumentException("offset (" + offset + ") must not be negative, limit(" + limit
                            + ")  must be greater than 0.");
                pageRequest = PageRequest.of(offset, limit, Sort.by("id").ascending());
            }

            // retrieve page of Entity objects
            Page<ServiceProblemEntity> serviceProblemEntities = serviceProblemRepository.findAll(pageRequest);

            // map to dto objects
            List<ServiceProblem> serviceProblems = new ArrayList<ServiceProblem>();
            for (ServiceProblemEntity serviceProblemEntity : serviceProblemEntities) {
                ServiceProblem serviceProblem = ServiceProblemMapper.INSTANCE
                        .serviceProblemEntityToServiceProblem(serviceProblemEntity);
                
                FieldFilter<ServiceProblem> fieldFilter = new FieldFilter<ServiceProblem>();

                serviceProblem = fieldFilter.filter(serviceProblem, fields, null);
                
                serviceProblems.add(serviceProblem);
            }

            return Response.status(Response.Status.OK).entity(serviceProblems).build();

        } catch (Exception ex) {
            LOG.error("GET /serviceProblem/id retrieveServiceProblem ", ex);
            ApiResponseMessage apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "GET /serviceProblem/id retrieveServiceProblem: " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiResponseMessage).build();
        }

    }

    @Override
    @Transactional
    public Response patchServiceProblem(String id, ServiceProblemUpdate serviceProblem, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!
        return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "x4 method not implemented")).build();
        // return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK,
        // "magic!")).build();
    }

    @Override
    public Response retrieveServiceProblem(String idStr, String fields, SecurityContext securityContext)
            throws NotFoundException {

        try {
            LOG.debug("GET /serviceProblem/id retrieveServiceProblem called id=" + idStr + " fields=" + fields);

            Long id = Long.parseLong(idStr);
            ApiResponseMessage apiResponseMessage;

            Optional<ServiceProblemEntity> spOptional = serviceProblemRepository.findById(id);
            if (!spOptional.isPresent()) {
                LOG.debug("GET /serviceProblem/id retrieveServiceProblem entity not found id=" + idStr);
                apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR,
                        "GET /serviceProblem/id retrieveServiceProblem entity not found id=" + idStr);
                return Response.status(Response.Status.NOT_FOUND).entity(apiResponseMessage).build();
            }

            ServiceProblemEntity serviceProblemEntity = spOptional.get();

            LOG.debug("retreived serviceProblemEntity:" + serviceProblemEntity);

            // map jpa entity to swagger dto
            ServiceProblem serviceProblem = ServiceProblemMapper.INSTANCE
                    .serviceProblemEntityToServiceProblem(serviceProblemEntity);

            LOG.debug("mapped serviceProblem:" + serviceProblem);

            FieldFilter<ServiceProblem> fieldFilter = new FieldFilter<ServiceProblem>();

            serviceProblem = fieldFilter.filter(serviceProblem, fields, null);
            
            LOG.debug("field filtered serviceProblem:" + serviceProblem);

            return Response.status(Response.Status.CREATED).entity(serviceProblem).build();

        } catch (Exception ex) {
            LOG.error("GET /serviceProblem/id retrieveServiceProblem ", ex);
            ApiResponseMessage apiResponseMessage = new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "GET /serviceProblem/id retrieveServiceProblem: " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiResponseMessage).build();
        }

    }
}
