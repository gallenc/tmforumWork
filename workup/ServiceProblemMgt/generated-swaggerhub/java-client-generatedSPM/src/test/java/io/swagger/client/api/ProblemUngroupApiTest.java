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
import io.swagger.client.model.ProblemUngroup;
import io.swagger.client.model.ProblemUngroupCreate;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for ProblemUngroupApi
 */
@Ignore
public class ProblemUngroupApiTest {

    private final ProblemUngroupApi api = new ProblemUngroupApi();

    
    /**
     * Creates a ProblemUngroup
     *
     * This operation creates a ProblemUngroup entity.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createProblemUngroupTest() throws ApiException {
        ProblemUngroupCreate problemUngroup = null;
        ProblemUngroup response = api.createProblemUngroup(problemUngroup);

        // TODO: test validations
    }
    
}
