package org.opennms.tmforum.tmf256.simulator.mapper;

import org.mapstruct.Mapper;
import org.opennms.tmforum.swagger.tmf656.swagger.model.ServiceProblem;
import org.opennms.tmforum.tmf656.simulator.model.ServiceProblemEntity;

@Mapper
public interface ServiceProblemMapper {

    ServiceProblemEntity serviceProblemToServiceProblemEntity(ServiceProblem dto);

    ServiceProblem serviceProblemEntityToServiceProblem(ServiceProblemEntity entity);
    
}
