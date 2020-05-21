package org.opennms.tmforum.tmf656.simulator.api.hub.impl;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.opennms.tmforum.swagger.tmf656.swagger.api.HubApi;
import org.opennms.tmforum.tmf650.api.rest.GenericHubApi;

//TODO FIX MAPPING BETWEEN CLASSES
@Mapper
public interface  ServiceProblemGenericHubApiMapper {
    
    ServiceProblemGenericHubApiMapper INSTANCE = Mappers.getMapper(ServiceProblemGenericHubApiMapper.class );

    GenericHubApi hubApiToGenericHubApi(@MappingTarget GenericHubApi genericHubApi,  HubApi hubApi);
    
    HubApi genericHubApiToHubApi(@MappingTarget HubApi hubApi, GenericHubApi genericHubApi);
    

}
