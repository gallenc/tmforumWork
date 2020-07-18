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
import org.opennms.netmgt.xml.event.AlarmData;
import org.opennms.netmgt.xml.event.Event;
import org.opennms.netmgt.xml.event.Log;
import org.opennms.netmgt.xml.event.Parm;
import org.opennms.netmgt.xml.event.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScriptedEventSPMForwarderTest {
    static final Logger log = LoggerFactory.getLogger(ApacheHttpAsyncClientTest.class);

    String SERVICE_PROBLEM_ALARM="org.opennms.uei.serviceProblemAlarm";
    String SERVICE_PROBLEM_ALARM_UPDATE="org.opennms.uei.serviceProblemAlarmUpdate";

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
    public void sendEventForExistingSpm() {

        log.debug("start of ForExistingSpm()");
        Event event = new Event();

        event.setDbid(10);
        event.setDescr("this description ");
        event.setUei(SERVICE_PROBLEM_ALARM);

        AlarmData alarmData = new AlarmData();
        alarmData.setReductionKey("reductionkey");
        alarmData.setAlarmType(0);
        event.setAlarmData(alarmData);

        /* this will add the service problem id to the alarm */
        Parm parm1 = new Parm();
        parm1.setParmName("spmID");
        Value value1 = new Value();
        value1.setContent("1");
        parm1.setValue(value1);
        event.addParm(parm1);

        /* this will add the service problem href to the alarm */
        Parm parm2 = new Parm();
        parm2.setParmName("spmHREF");
        Value value2 = new Value();
        value2.setContent("href");
        parm2.setValue(value2);
        event.addParm(parm2);
        
        IEvent ievent = ImmutableMapper.fromMutableEvent(event);

        spmForwarder.updateServiceProblem(ievent);
        
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
    public void sendEventforNewSPM() {
        // no params
        log.debug("start of sendEventforNewSPM()");
        Event event = new Event();

        event.setDbid(10);
        event.setDescr("this description ");
        event.setUei(SERVICE_PROBLEM_ALARM);

        AlarmData alarmData = new AlarmData();
        alarmData.setReductionKey("reductionkey");
        alarmData.setAlarmType(0);
        event.setAlarmData(alarmData);
        
        IEvent ievent = ImmutableMapper.fromMutableEvent(event);
        
        spmForwarder.updateServiceProblem(ievent);
        
        log.debug("Waiting for responses");
        // Pause for 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.debug("sleep interupted");
        }

        log.debug("Finished Waiting for responses");

        log.debug("end of sendEventforNewSPM()");
    }

}
