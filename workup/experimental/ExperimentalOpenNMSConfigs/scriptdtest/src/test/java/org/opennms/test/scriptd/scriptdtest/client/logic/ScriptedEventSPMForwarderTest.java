package org.opennms.test.scriptd.scriptdtest.client.logic;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.bsf.BSFException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.opennms.netmgt.events.api.EventIpcManager;
import org.opennms.netmgt.events.api.EventIpcManagerFactory;
import org.opennms.netmgt.events.api.EventListener;
import org.opennms.netmgt.events.api.EventProxyException;
import org.opennms.netmgt.events.api.model.IEvent;
import org.opennms.netmgt.events.api.model.ImmutableMapper;
import org.opennms.netmgt.model.OnmsNode;
import org.opennms.netmgt.model.events.EventBuilder;
import org.opennms.netmgt.xml.event.AlarmData;
import org.opennms.netmgt.xml.event.Event;
import org.opennms.netmgt.xml.event.Log;
import org.opennms.netmgt.xml.event.Parm;
import org.opennms.netmgt.xml.event.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScriptedEventSPMForwarderTest {
    static final Logger log = LoggerFactory.getLogger(ApacheHttpAsyncClientTest.class);

    /* Standard OpenNMS BSM events */
    String SERVICE_PROBLEM = "uei.opennms.org/bsm/serviceProblem";
    String SERVICE_OPERATIONAL_STATUS_CHANGED = "uei.opennms.org/bsm/serviceOperationalStatusChanged";
    String SERVICE_PROBLEM_RESOLVED = "uei.opennms.org/bsm/serviceProblemResolved";
            
    /* New Service Problem Reply Event */
    String SERVICE_PROBLEM_REPLY = "uei.opennms.org/tmf656spm/serviceProblemReply";

    ScriptedApacheHttpAsyncClient scriptedClient = null;
    ScriptedEventSPMForwarder spmForwarder = null;
    ScriptedApacheHttpServer server = null;
    
    EventIpcManagerFactory eventIpcManagerFactory = new EventIpcManagerFactory() {
    };
    
    static EventIpcManager ipcManager;

    @Before
    public void before() {
        
        ipcManager = new EventIpcManager() {

            @Override
            public void addEventListener(EventListener listener) {
            }

            @Override
            public void addEventListener(EventListener listener, Collection<String> ueis) {
            }

            @Override
            public void addEventListener(EventListener listener, String uei) {
            }

            @Override
            public void removeEventListener(EventListener listener) {
            }

            @Override
            public void removeEventListener(EventListener listener, Collection<String> ueis) {
            }

            @Override
            public void removeEventListener(EventListener listener, String uei) {
            }

            @Override
            public boolean hasEventListener(String uei) {
                return false;
            }

            @Override
            public void send(Event event) throws EventProxyException {
                log.debug("****** EventIpcManager spm forwarder sending: "+event);
            }

            @Override
            public void send(Log eventLog) throws EventProxyException {
            }

            @Override
            public void sendNow(Event event) {
            }

            @Override
            public void sendNow(Log eventLog) {
            }

            @Override
            public void sendNowSync(Event event) {
            }

            @Override
            public void sendNowSync(Log eventLog) {
            }
            
        };
        
        EventIpcManagerFactory.setIpcManager(ipcManager);

        scriptedClient = new ScriptedApacheHttpAsyncClient();
        spmForwarder = new ScriptedEventSPMForwarder();
        
        /* set name of this originating system in sent spm calls */
        spmForwarder.setThisOriginatingSystem("testOpenNMS");
        
        /* urls and credentials for spm hosts UrlCredential(String url, String username, String password) */
        List<UrlCredential> urlCredentials = Arrays.asList(new UrlCredential("http://tmf656-test1.centralus.cloudapp.azure.com:8080/tmf656-spm-simulator-war", "username", "password" ));
        spmForwarder.setUrlCredentials(urlCredentials);

        spmForwarder.setScriptedClient(scriptedClient);
        scriptedClient.setMessageHandler(spmForwarder);
        
        log.debug("configuraing scripted http server");
        int port = 8981;
        BlockingQueue jsonQueue = scriptedClient.getjsonQueue();
        String keyStoreFileLocation = null;

        String[] allowedTargets = {"/",
                "/opennms/tmf-api/serviceProblemManagement/v3/listener/serviceProblemAttributeValueChangeNotification",
                "/opennms/tmf-api/serviceProblemManagement/v3/listener/serviceProblemCreateNotification",
                "/opennms/tmf-api/serviceProblemManagement/v3/listener/serviceProblemInformationRequiredNotification",
                "/opennms/tmf-api/serviceProblemManagement/v3//listener/serviceProblemStateChangeNotification",
                "/generic-listener/notification"
                };

        server = new ScriptedApacheHttpServer(port, jsonQueue, allowedTargets, keyStoreFileLocation);

        log.debug("starting message listener");
        scriptedClient.startListener();
        
        log.debug("starting scripted http server");
        server.start();

        log.debug("starting async client");
        scriptedClient.startClient();

    }

    @After
    public void after() {
        log.debug("stopping scripted http server");
        server.stop();
        log.debug("stopping async client");
        scriptedClient.stopClient();
        log.debug("stopping message listener");
        scriptedClient.stopListener();
    }
    
    //@Test // uncomment if testing server only
    public void testEventServerSpm() throws BSFException {
        log.debug("run testEventServerSpm test");
        
        log.debug("server started waiting 30 secs for for requests  ");
        // Pause for 30 seconds
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            log.debug("sleep interupted");
        }
        
        log.debug("end run testEventServerSpm test");
    }

    @Test
    public void testEventHasHrefExistingSpm() {
        
        // build event with spm params

        log.debug("****** start of testEventHasHrefExistingSpm");
        
        String source = "service problem interface";
        String description = "this description";
        
        // cant use builder to set dbId
        Event event = new Event();
        event.setDbid(10);
        
        EventBuilder eventBuilder = new EventBuilder(event);
        eventBuilder.setDescription(description);
        eventBuilder.setSource(source);
        eventBuilder.setUei(SERVICE_PROBLEM);

        AlarmData alarmData = new AlarmData();
        alarmData.setReductionKey("reductionkey");
        alarmData.setAlarmType(1);
        eventBuilder.setAlarmData(alarmData);
        
        eventBuilder.addParam("businessServiceName", "business service 1");
        eventBuilder.addParam("businessServiceId", "1");
        eventBuilder.addParam("rootCause", "test Root cause");

        /* this will add the service problem id to the alarm */
        eventBuilder.addParam("spmID", "1");
        
        /* this will add the service problem href to the alarm */
        eventBuilder.addParam("spmHREF", "http://tmf656-test1.centralus.cloudapp.azure.com:8080/tmf656-spm-simulator-war/tmf-api/serviceProblemManagement/v3/serviceProblem/1");

        event = eventBuilder.getEvent();
        log.debug("Sending event:" +event.toString());
        
        IEvent ievent = ImmutableMapper.fromMutableEvent(event);

        OnmsNode node = null;
        spmForwarder.handleEvent(ievent, node);
        
        log.debug("Waiting for responses");
        // Pause for 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.debug("sleep interupted");
        }

        log.debug("Finished Waiting for responses");

        log.debug("end of ForExistingSpm()");
    }
    
    @Test
    public void testEventHasNoHrefNewSpm() {
        log.debug("****** start of testEventHasNoHrefNewSpm test script");
        
        // build event with no spm params
        
        String source = "service problem interface";
        String description = "this description";
        
        // cant use builder to set dbId
        Event event = new Event();
        event.setDbid(10);
        
        EventBuilder eventBuilder = new EventBuilder(event);
        eventBuilder.setDescription(description);
        eventBuilder.setSource(source);
        eventBuilder.setUei(SERVICE_PROBLEM);

        AlarmData alarmData = new AlarmData();
        alarmData.setReductionKey("reductionkey");
        alarmData.setAlarmType(1);
        eventBuilder.setAlarmData(alarmData);
        
        eventBuilder.addParam("businessServiceName", "business service 1");
        eventBuilder.addParam("businessServiceId", "1");
        eventBuilder.addParam("rootCause", "test Root cause");

        event = eventBuilder.getEvent();
        log.debug("Sending event:" +event.toString());
        
        IEvent ievent = ImmutableMapper.fromMutableEvent(event);

        OnmsNode node = null;
        spmForwarder.handleEvent(ievent, node);
        
        log.debug("Waiting for responses");
        // Pause for 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.debug("sleep interupted");
        }

        log.debug("Finished Waiting for responses");

        log.debug("****** end of testEventHasNoHrefNewSpm");
        
    }

}
