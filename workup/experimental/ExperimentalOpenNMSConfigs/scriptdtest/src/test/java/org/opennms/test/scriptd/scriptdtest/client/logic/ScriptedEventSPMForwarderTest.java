package org.opennms.test.scriptd.scriptdtest.client.logic;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.opennms.netmgt.events.api.EventIpcManager;
import org.opennms.netmgt.events.api.EventIpcManagerFactory;
import org.opennms.netmgt.events.api.EventListener;
import org.opennms.netmgt.events.api.EventProxyException;
import org.opennms.netmgt.events.api.model.IEvent;
import org.opennms.netmgt.events.api.model.ImmutableMapper;
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
    String SERVICE_PROBLEM_REPLY = "uei.opennms.org/bsm/serviceProblemReply";

    ScriptedApacheHttpAsyncClient scriptedClient = null;
    ScriptedEventSPMForwarder spmForwarder = null;
    
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
        
        /* urls and credentials for spm hosts UrlCredential(String url, String username, String password) */
        List<UrlCredential> urlCredentials = Arrays.asList(new UrlCredential("http://tmf656-test1.centralus.cloudapp.azure.com:8080/tmf656-spm-simulator-war", "username", "password" ));
        spmForwarder.setUrlCredentials(urlCredentials);

        spmForwarder.setScriptedClient(scriptedClient);
        scriptedClient.setMessageHandler(spmForwarder);

        scriptedClient.startListener();
        scriptedClient.startClient();

    }

    @After
    public void after() {
        scriptedClient.stopClient();
        scriptedClient.stopListener();
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

        spmForwarder.handleEvent(ievent);
        
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

        spmForwarder.handleEvent(ievent);
        
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
