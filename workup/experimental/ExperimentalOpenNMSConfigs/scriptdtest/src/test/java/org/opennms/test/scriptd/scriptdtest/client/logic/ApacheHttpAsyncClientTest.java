package org.opennms.test.scriptd.scriptdtest.client.logic;

import static org.junit.Assert.*;

import org.opennms.test.scriptd.scriptdtest.client.logic.ScriptedApacheHttpAsyncClient;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApacheHttpAsyncClientTest {
    static final Logger log = LoggerFactory.getLogger(ApacheHttpAsyncClientTest.class);
    
    final String TEST_USERNAME = "username";
    
    final String TEST_PASSWORD = "password";

    final String baseHTTPSUrl = "https://tmf656-test1.centralus.cloudapp.azure.com:8443/tmf656-simulator-war";

    final String baseHTTPUrl = "http://tmf656-test1.centralus.cloudapp.azure.com:8080/tmf656-simulator-war";

    ScriptedApacheHttpAsyncClient scriptedClient = new ScriptedApacheHttpAsyncClient();
    
    String minimalServiceProblemTemplate = "{\n"+ 
            "  \"id\": \"string\",\n" + 
            "  \"href\": \"string\"" + 
            "  \"affectedNumberOfServices\": 0,\n" + 
            "  \"category\": \"string\",\n" + 
            "  \"correlationId\": \"string\",\n" + 
            "  \"description\": \"string\",\n" + 
            "  \"originatingSystem\": \"string\",\n" + 
            "  \"priority\": 0,\n" + 
            "  \"reason\": \"string\",\n" + 
            "  \"status\": \"string\",\n" + 
            "  \"affectedService\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "    }\n" + 
            "  ]\n" + 
            "}";
    
    String basicServiceProblemTemplate = "{\n"+ 
            "  \"id\": \"string\",\n" + 
            "  \"href\": \"string\"" + 
            "  \"affectedNumberOfServices\": 0,\n" + 
            "  \"category\": \"string\",\n" + 
            "  \"correlationId\": \"string\",\n" + 
            "  \"description\": \"string\",\n" + 
            "  \"originatingSystem\": \"string\",\n" + 
            "  \"priority\": 0,\n" + 
            "  \"reason\": \"string\",\n" + 
            "  \"status\": \"string\",\n" + 
            "  \"affectedLocation\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"name\": \"string\",\n" + 
            "      \"role\": \"string\",\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"affectedResource\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"name\": \"string\",\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"affectedService\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"associatedSLAViolation\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"associatedTroubleTicket\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"correlationId\": \"string\",\n" + 
            "      \"status\": \"string\",\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"comment\": [\n" + 
            "    {\n" + 
            "      \"author\": \"string\",\n" + 
            "      \"date\": \"2020-07-06T21:33:07.950Z\",\n" + 
            "      \"system\": \"string\",\n" + 
            "      \"text\": \"string\",\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"originatorParty\": {\n" + 
            "    \"id\": \"string\",\n" + 
            "    \"href\": \"string\",\n" + 
            "    \"name\": \"string\",\n" + 
            "    \"role\": \"string\",\n" + 
            "  },\n" + 
            "}";

    @Before
    public void before() {

        scriptedClient.startListener();
        scriptedClient.startClient();

    }

    @After
    public void after() {
        scriptedClient.stopClient();
        scriptedClient.stopListener();
    }
    


    @Test
    public void getServiceProblemsTest() {
        log.debug("start of getServiceProblemsTest()");

        /* get http request */
        scriptedClient.getRequest(baseHTTPUrl + "/tmf-api/serviceProblemManagement/v3/serviceProblem/2", TEST_USERNAME, TEST_PASSWORD);

        /* get https request */
        scriptedClient.getRequest(baseHTTPSUrl  + "/tmf-api/serviceProblemManagement/v3/serviceProblem/2", TEST_USERNAME, TEST_PASSWORD);

        /* get http ALL SERVICE PROBLEMS request */
        scriptedClient.getRequest(baseHTTPSUrl  + "/tmf-api/serviceProblemManagement/v3/serviceProblem", TEST_USERNAME, TEST_PASSWORD);

        log.debug("Waiting for responses");
        // Pause for 10 seconds
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            log.debug("sleep interupted");
        }

        log.debug("Finished Waiting for responses");

        log.debug("end of getServiceProblemsTest()");
    }
    
    public void deleteServiceProblemsTest() {
        log.debug("start of deleteServiceProblemsTest()");

        /* get http request */
        scriptedClient.deleteRequest(baseHTTPUrl + "/tmf-api/serviceProblemManagement/v3/serviceProblem/1", TEST_USERNAME, TEST_PASSWORD);

        log.debug("Waiting for responses");
        // Pause for 10 seconds
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            log.debug("sleep interupted");
        }

        log.debug("Finished Waiting for responses");

        log.debug("end of deleteServiceProblemsTest()");
    }
    
    
    
    @Test
    public void postServiceProblemsTest() {
        log.debug("start of postServiceProblemsTest()");
        
        JSONParser parser = new JSONParser();
        String jsonString =null ;
        try {
            JSONObject serviceProblem = (JSONObject) parser.parse(basicServiceProblemTemplate);
            serviceProblem.remove("id");
            serviceProblem.remove("href");
            jsonString=serviceProblem.toJSONString();
        } catch (ParseException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        String url = baseHTTPUrl + "/tmf-api/serviceProblemManagement/v3/serviceProblem/";
        
        /* post http request */
        scriptedClient.postRequest(url, jsonString, TEST_USERNAME, TEST_PASSWORD);
        
        log.debug("Waiting for responses");
        /* Pause for 10 seconds */
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            log.debug("sleep interupted");
        }

        log.debug("Finished Waiting for responses");

        log.debug("end of postServiceProblemsTest()");
    }
    
    @Test
    public void patchServiceProblemsTest() {
        log.debug("start of patchServiceProblemsTest()");
        
        JSONParser parser = new JSONParser();
        String jsonString =null ;
        try {
            JSONObject serviceProblem = (JSONObject) parser.parse(minimalServiceProblemTemplate);
            serviceProblem.remove("id");
            serviceProblem.remove("href");
            serviceProblem.remove("affectedNumberOfServices");
            serviceProblem.remove("category");
            serviceProblem.remove("correlationId");
            serviceProblem.remove("description");
            serviceProblem.remove("originatingSystem");
            serviceProblem.remove("reason");
            serviceProblem.remove("status");
            serviceProblem.remove("affectedService");
            
            serviceProblem.put("priority", 10);
            serviceProblem.put("reason", "new Priority 10");

            jsonString=serviceProblem.toJSONString();
            
        } catch (ParseException e2) {
            // TODO Auto-generated catch block
            log.error("start of patchServiceProblemsTest()",e2);
            
        }
        
        String url = baseHTTPUrl + "/tmf-api/serviceProblemManagement/v3/serviceProblem/2";
        log.debug("sending patch to url "+url
                + " jsonString :"+jsonString);

        /* post http request */
        scriptedClient.patchRequest(url, jsonString, TEST_USERNAME, TEST_PASSWORD);
        
        log.debug("Waiting for responses");
        /* Pause for 10 seconds */
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            log.debug("sleep interupted");
        }

        log.debug("Finished Waiting for responses");

        log.debug("end of patchServiceProblemsTest()");
    }


}
