/*
 * Service Problem Management
 * ## TMF API Reference: TMF656 - Service Problem Management  ### Release : 18.5 - December 2018  Service Problem Management API goal is to provide the ability to manage problems in the Service domain.   ### Resource  - serviceProblem  ### Operations Service Problem Management API performs the following operations on the resource : - Retrieve an entity or a collection of entities depending on filter criteria - Partial update of an entity (including updating rules) - Create an entity (including default values and creation rules) - Delete an entity (for administration purposes) - Manage notification of events
 *
 * OpenAPI spec version: 3.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.model.Error;
import io.swagger.client.model.ServiceProblem;
import io.swagger.client.model.ServiceProblemCreate;
import io.swagger.client.model.ServiceProblemUpdate;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for ServiceProblemApi
 */
@Ignore
public class ServiceProblemApiTest {

    private final ServiceProblemApi api = new ServiceProblemApi();

    
    /**
     * Creates a ServiceProblem
     *
     * This operation creates a ServiceProblem entity.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createServiceProblemTest() throws ApiException {
        ServiceProblemCreate serviceProblem = null;
        ServiceProblem response = api.createServiceProblem(serviceProblem);

        // TODO: test validations
    }
    
    /**
     * Deletes a ServiceProblem
     *
     * This operation deletes a ServiceProblem entity.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteServiceProblemTest() throws ApiException {
        String id = null;
        api.deleteServiceProblem(id);

        // TODO: test validations
    }
    
    /**
     * List or find ServiceProblem objects
     *
     * This operation list or find ServiceProblem entities
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void listServiceProblemTest() throws ApiException {
        String fields = null;
        Integer offset = null;
        Integer limit = null;
        List<ServiceProblem> response = api.listServiceProblem(fields, offset, limit);

        // TODO: test validations
    }
    
    /**
     * Updates partially a ServiceProblem
     *
     * This operation updates partially a ServiceProblem entity.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void patchServiceProblemTest() throws ApiException {
        String id = null;
        ServiceProblemUpdate serviceProblem = null;
        ServiceProblem response = api.patchServiceProblem(id, serviceProblem);

        // TODO: test validations
    }
    
    /**
     * Retrieves a ServiceProblem by ID
     *
     * This operation retrieves a ServiceProblem entity. Attribute selection is enabled for all first level attributes.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void retrieveServiceProblemTest() throws ApiException {
        String id = null;
        String fields = null;
        ServiceProblem response = api.retrieveServiceProblem(id, fields);

        // TODO: test validations
    }
    
}