package org.opennms.test.scriptd.scriptdtest.misc.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.opennms.netmgt.xml.event.Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bsh.util.BeanShellBSFEngine;

// see https://www.baeldung.com/java-http-request
// see https://github.com/eugenp/tutorials/tree/master/core-java-modules/core-java-networking-2
// https://mkyong.com/java/json-simple-example-read-and-write-json/
// https://www.baeldung.com/httpurlconnection-post
// https://beanshell.github.io/manual/bshmanual.html#Scripted_Methods
// http://192.168.92.131:8980/opennms/rest/events?limit=0
// https://docs.opennms.org/opennms/releases/latest/guide-development/guide-development.html#_events
// https://wiki.opennms.org/wiki/InsEventProxy
// http://192.168.92.131:8980/opennms/event/list
// https://beanshell.github.io/manual/bshmanual.html#Scripted_Methods
// http://tmf656-test1.centralus.cloudapp.azure.com:8080/tmf656-simulator-war/swaggerUI

//<?xml version="1.0"?>

//<scriptd-configuration>
//  <engine language="beanshell" className="bsh.util.BeanShellBSFEngine" extensions="bsh"/>

//import org.opennms.netmgt.scriptd.ins.events.InsServerListener;

//import org.opennms.netmgt.config.DataSourceFactory;

public class ScriptdHttpURLConnectionExample {

    static final Logger log = LoggerFactory.getLogger(ScriptdHttpURLConnectionExample.class);
    

    
    
    String baseUrl = "http://tmf656-test1.centralus.cloudapp.azure.com:8080/tmf656-simulator-war";
    String username= "admin";
    String password= "admin";
    
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
    


    public void startScript() {

        // <start-script language="beanshell">

        // log = bsf.lookupBean("log");
        log.debug("Starting Script");
        log.debug("set up connection");
        


        // InsServerListener isl = new InsServerListener();

        // optional (if not setted, default port (8154) is used)
        // isl.setListeningPort(8152);

        // optional (if not setted, no authentication is required)
        // isl.setSharedASCIIString("1234567890");

        // required properties
        // isl.setCriteriaRestriction(
        // "eventuei = 'uei.opennms.org/internal/alarms/AlarmRaised' and EXISTS (select
        // 1 from alarms where alarmtype = 1 and severity > 3 and eventoperinstruct =
        // alarmid and eventtime > lasteventtime)");
        // isl.start();

        // </start-script>

    }

    public void stopScript() {
        // <stop-script language="beanshell">

        // isl.interrupt();
        log.debug("executing a stop script");

        // </stop-script>
    }

    public void eventScript() {

        // <event-script language="beanshell">

        log.debug("executing event script");

       // Event event = bsf.lookupBean("event");

        // if ((event.uei.equals("uei.opennms.org/internal/alarms/NotificationAlarm"))
        // || (event.uei.equals("uei.opennms.org/internal/alarms/AlarmCleared"))
        // || (event.uei.equals("uei.opennms.org/internal/alarms/AlarmRaised"))) {

        // isl.flushEvent(event);
        // }

        // </event-script>

    }

    // </scriptd-configuration>
    
    public JSONObject createServiceProblem() {
        JSONParser parser = new JSONParser();

        URL url;
        try {
            
            JSONObject serviceProblemCreate = (JSONObject) parser.parse(basicServiceProblemTemplate);
            
            // curl -X POST "http://tmf656-test1.centralus.cloudapp.azure.com:8080/tmf656-simulator-war/tmf-api/serviceProblemManagement/v3/serviceProblem" -H  "accept: application/json;charset=utf-8"
            url = new URL(baseUrl+"/tmf-api/serviceProblemManagement/v3/serviceProblem/");

            HttpURLConnection con;

            con = (HttpURLConnection) url.openConnection();
            
            // set request content type
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            
            // set 5 seconds timeout
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setRequestMethod("POST");
            con.connect();
            
            // send json string
            String jsonInputString = serviceProblemCreate.toString();
            
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);           
            }
            
            // this reads the status
            int status = con.getResponseCode();
            log.debug("response status: "+status);

            // read the response of the request and place it in a content String:
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            
            in.close();
            con.disconnect();
            
            String jsonString = content.toString();

            log.debug("response messge: "+jsonString);
            
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            log.debug("response object"+jsonObject.toString());
            
            log.debug("response ID: "+jsonObject.get("id"));
            
            return jsonObject;

        } catch (IOException | ParseException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            log.error("problem in script"+ sw.toString());
        }
        
        return null;
    }
    

    
    public JSONObject getServiceProblem(Integer id) {
        JSONParser parser = new JSONParser();

        URL url;
        try {
            
            // curl -X GET "http://tmf656-test1.centralus.cloudapp.azure.com:8080/tmf656-simulator-war/tmf-api/serviceProblemManagement/v3/serviceProblem" -H  "accept: application/json;charset=utf-8"
            url = new URL(baseUrl+"/tmf-api/serviceProblemManagement/v3/serviceProblem/"+id.toString());

            HttpURLConnection con;

            con = (HttpURLConnection) url.openConnection();
            
            // set request content type
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            
            // set 5 seconds timeout
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setRequestMethod("GET");
            con.connect();
            
            // this reads the status
            int status = con.getResponseCode();
            log.debug("response status: "+status);

            // read the response of the request and place it in a content String:
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            
            in.close();
            con.disconnect();
            
            String jsonString = content.toString();

            log.debug("response messge: "+jsonString);
            
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            log.debug("response object"+jsonObject.toString());
            
            return jsonObject;

        } catch (IOException | ParseException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            log.error("problem in script"+ sw.toString());
        }
        
        return null;
    }
    
    String fullServiceProblemTemplate ="{\n"
            + "\"id\": \"string\",\n" + 
            "  \"href\": \"string\"" + 
            "  \"affectedNumberOfServices\": 0,\n" + 
            "  \"category\": \"string\",\n" + 
            "  \"correlationId\": \"string\",\n" + 
            "  \"description\": \"string\",\n" + 
            "  \"impactImportanceFactor\": \"string\",\n" + 
            "  \"originatingSystem\": \"string\",\n" + 
            "  \"priority\": 0,\n" + 
            "  \"problemEscalation\": \"string\",\n" + 
            "  \"reason\": \"string\",\n" + 
            "  \"resolutionDate\": \"2020-07-06T21:33:07.948Z\",\n" + 
            "  \"status\": \"string\",\n" + 
            "  \"statusChangeDate\": \"2020-07-06T21:33:07.949Z\",\n" + 
            "  \"statusChangeReason\": \"string\",\n" + 
            "  \"timeChanged\": \"2020-07-06T21:33:07.949Z\",\n" + 
            "  \"timeRaised\": \"2020-07-06T21:33:07.949Z\",\n" + 
            "  \"affectedLocation\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"name\": \"string\",\n" + 
            "      \"role\": \"string\",\n" + 
            "      \"@baseType\": \"string\",\n" + 
            "      \"@schemaLocation\": \"string\",\n" + 
            "      \"@type\": \"string\"\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"affectedResource\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"name\": \"string\",\n" + 
            "      \"@baseType\": \"string\",\n" + 
            "      \"@schemaLocation\": \"string\",\n" + 
            "      \"@type\": \"string\",\n" + 
            "      \"@referredType\": \"string\"\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"affectedService\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"@baseType\": \"string\",\n" + 
            "      \"@schemaLocation\": \"string\",\n" + 
            "      \"@type\": \"string\",\n" + 
            "      \"@referredType\": \"string\"\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"associatedSLAViolation\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"@baseType\": \"string\",\n" + 
            "      \"@schemaLocation\": \"string\",\n" + 
            "      \"@type\": \"string\",\n" + 
            "      \"@referredType\": \"string\"\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"associatedTroubleTicket\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"correlationId\": \"string\",\n" + 
            "      \"status\": \"string\",\n" + 
            "      \"@baseType\": \"string\",\n" + 
            "      \"@schemaLocation\": \"string\",\n" + 
            "      \"@type\": \"string\",\n" + 
            "      \"@referredType\": \"string\"\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"comment\": [\n" + 
            "    {\n" + 
            "      \"author\": \"string\",\n" + 
            "      \"date\": \"2020-07-06T21:33:07.950Z\",\n" + 
            "      \"system\": \"string\",\n" + 
            "      \"text\": \"string\",\n" + 
            "      \"@baseType\": \"string\",\n" + 
            "      \"@schemaLocation\": \"string\",\n" + 
            "      \"@type\": \"string\"\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"extensionInfo\": [\n" + 
            "    {\n" + 
            "      \"name\": \"string\",\n" + 
            "      \"valueType\": \"string\",\n" + 
            "      \"value\": {\n" + 
            "        \"anyStr\": \"string\"\n" + 
            "      },\n" + 
            "      \"@baseType\": \"string\",\n" + 
            "      \"@schemaLocation\": \"string\",\n" + 
            "      \"@type\": \"string\"\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"firstAlert\": {\n" + 
            "    \"id\": \"string\",\n" + 
            "    \"href\": \"string\",\n" + 
            "    \"name\": \"string\",\n" + 
            "    \"@baseType\": \"string\",\n" + 
            "    \"@schemaLocation\": \"string\",\n" + 
            "    \"@type\": \"string\",\n" + 
            "    \"@referredType\": \"string\"\n" + 
            "  },\n" + 
            "  \"impactPatterns\": {\n" + 
            "    \"description\": \"string\",\n" + 
            "    \"extensionInfo\": [\n" + 
            "      {\n" + 
            "        \"name\": \"string\",\n" + 
            "        \"valueType\": \"string\",\n" + 
            "        \"value\": {\n" + 
            "          \"anyStr\": \"string\"\n" + 
            "        },\n" + 
            "        \"@baseType\": \"string\",\n" + 
            "        \"@schemaLocation\": \"string\",\n" + 
            "        \"@type\": \"string\"\n" + 
            "      }\n" + 
            "    ],\n" + 
            "    \"@baseType\": \"string\",\n" + 
            "    \"@schemaLocation\": \"string\",\n" + 
            "    \"@type\": \"string\"\n" + 
            "  },\n" + 
            "  \"originatorParty\": {\n" + 
            "    \"id\": \"string\",\n" + 
            "    \"href\": \"string\",\n" + 
            "    \"name\": \"string\",\n" + 
            "    \"role\": \"string\",\n" + 
            "    \"@baseType\": \"string\",\n" + 
            "    \"@schemaLocation\": \"string\",\n" + 
            "    \"@type\": \"string\",\n" + 
            "    \"@referredType\": \"string\"\n" + 
            "  },\n" + 
            "  \"parentProblem\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"correlationId\": \"string\",\n" + 
            "      \"@baseType\": \"string\",\n" + 
            "      \"@schemaLocation\": \"string\",\n" + 
            "      \"@type\": \"string\",\n" + 
            "      \"@referredType\": \"string\"\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"relatedEvent\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"eventTime\": \"2020-07-06T21:33:07.951Z\",\n" + 
            "      \"eventType\": \"string\",\n" + 
            "      \"@baseType\": \"string\",\n" + 
            "      \"@schemaLocation\": \"string\",\n" + 
            "      \"@type\": \"string\",\n" + 
            "      \"@referredType\": \"string\"\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"relatedObject\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"name\": \"string\",\n" + 
            "      \"@baseType\": \"string\",\n" + 
            "      \"@schemaLocation\": \"string\",\n" + 
            "      \"@type\": \"string\",\n" + 
            "      \"@referredType\": \"string\"\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"relatedParty\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"name\": \"string\",\n" + 
            "      \"role\": \"string\",\n" + 
            "      \"@baseType\": \"string\",\n" + 
            "      \"@schemaLocation\": \"string\",\n" + 
            "      \"@type\": \"string\",\n" + 
            "      \"@referredType\": \"string\"\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"responsibleParty\": {\n" + 
            "    \"id\": \"string\",\n" + 
            "    \"href\": \"string\",\n" + 
            "    \"name\": \"string\",\n" + 
            "    \"role\": \"string\",\n" + 
            "    \"@baseType\": \"string\",\n" + 
            "    \"@schemaLocation\": \"string\",\n" + 
            "    \"@type\": \"string\",\n" + 
            "    \"@referredType\": \"string\"\n" + 
            "  },\n" + 
            "  \"rootCauseResource\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"name\": \"string\",\n" + 
            "      \"@baseType\": \"string\",\n" + 
            "      \"@schemaLocation\": \"string\",\n" + 
            "      \"@type\": \"string\",\n" + 
            "      \"@referredType\": \"string\"\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"rootCauseService\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"@baseType\": \"string\",\n" + 
            "      \"@schemaLocation\": \"string\",\n" + 
            "      \"@type\": \"string\",\n" + 
            "      \"@referredType\": \"string\"\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"trackingRecord\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"description\": \"string\",\n" + 
            "      \"systemId\": \"string\",\n" + 
            "      \"time\": \"2020-07-06T21:33:07.953Z\",\n" + 
            "      \"user\": \"string\",\n" + 
            "      \"extensionInfo\": [\n" + 
            "        {\n" + 
            "          \"name\": \"string\",\n" + 
            "          \"valueType\": \"string\",\n" + 
            "          \"value\": {\n" + 
            "            \"anyStr\": \"string\"\n" + 
            "          },\n" + 
            "          \"@baseType\": \"string\",\n" + 
            "          \"@schemaLocation\": \"string\",\n" + 
            "          \"@type\": \"string\"\n" + 
            "        }\n" + 
            "      ],\n" + 
            "      \"@baseType\": \"string\",\n" + 
            "      \"@schemaLocation\": \"string\",\n" + 
            "      \"@type\": \"string\"\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"underlyingAlarm\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"changeRequest\": {\n" + 
            "        \"id\": \"string\",\n" + 
            "        \"href\": \"string\",\n" + 
            "        \"@baseType\": \"string\",\n" + 
            "        \"@schemaLocation\": \"string\",\n" + 
            "        \"@type\": \"string\",\n" + 
            "        \"@referredType\": \"string\"\n" + 
            "      },\n" + 
            "      \"@baseType\": \"string\",\n" + 
            "      \"@schemaLocation\": \"string\",\n" + 
            "      \"@type\": \"string\",\n" + 
            "      \"@referredType\": \"string\"\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"underlyingProblem\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"correlationId\": \"string\",\n" + 
            "      \"@baseType\": \"string\",\n" + 
            "      \"@schemaLocation\": \"string\",\n" + 
            "      \"@type\": \"string\",\n" + 
            "      \"@referredType\": \"string\"\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"@baseType\": \"string\",\n" + 
            "  \"@schemaLocation\": \"string\",\n" + 
            "  \"@type\": \"string\"\n" + 
            "}";

}
