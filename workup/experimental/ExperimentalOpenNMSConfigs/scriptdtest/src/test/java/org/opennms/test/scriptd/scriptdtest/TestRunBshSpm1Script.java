package org.opennms.test.scriptd.scriptdtest;

import java.util.Collection;
import java.util.Date;

import org.apache.bsf.BSFEngine;
import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;

import static org.junit.Assert.*;

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

//https://javasourcequery.com/example/org.apache.bsf.lookupBean
public class TestRunBshSpm1Script {
    static final Logger log = LoggerFactory.getLogger(TestRunBshSpm1Script.class);

    /* Standard OpenNMS BSM events */
    String SERVICE_PROBLEM = "uei.opennms.org/bsm/serviceProblem";
    String SERVICE_OPERATIONAL_STATUS_CHANGED = "uei.opennms.org/bsm/serviceOperationalStatusChanged";
    String SERVICE_PROBLEM_RESOLVED = "uei.opennms.org/bsm/serviceProblemResolved";
            
    /* New Service Problem Reply Event */
    String SERVICE_PROBLEM_REPLY = "uei.opennms.org/tmf656spm/serviceProblemReply";
    
    private static BSFManager mgr = new BSFManager();

    private BSFEngine beanshellEngine;
    
    EventIpcManagerFactory eventIpcManagerFactory = new EventIpcManagerFactory() {
    };
    
    EventIpcManager ipcManager = new EventIpcManager() {

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
    

    @Before
    public void before() throws BSFException {
        log.debug("executing start script");
        
        eventIpcManagerFactory.setIpcManager(ipcManager);
        
        // register beanshell with the BSF framework
        String[] extensions = { "bsh" };
        BSFManager.registerScriptingEngine("beanshell", "bsh.util.BeanShellBSFEngine", extensions);
        beanshellEngine = mgr.loadScriptingEngine("beanshell");
        mgr.registerBean("log", log);
        
        OnmsNode node = null;
        mgr.registerBean("node", node);

        String startScript = "     log = bsf.lookupBean(\"log\"); \n"
                + "   log.debug(\"start script logging enabled before importing source\"); \n"
                + "   source(\"src/main/resources/scriptd-start-spm1.bsh\"); \n";

        mgr.exec("beanshell", "", 0, 0, startScript);

        log.debug("finished executing start script");

    }

    @After
    public void after() throws BSFException {
        log.debug("executing stop script");

        String stopScript = "     log = bsf.lookupBean(\"log\"); \n" 
                + "   log.debug(\"running stop script\"); \n"
                + "   source(\"src/main/resources/scriptd-stop-spm1.bsh\"); \n";

        mgr.exec("beanshell", "", 0, 0, stopScript);
        
        log.debug("executed stop script");

    }
    
    // uncomment if you just want to run a server test @Test
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
    public void testEventHasHrefExistingSpm() throws BSFException {
        
        // build event with spm params

        log.debug("****** start of testEventHasHrefExistingSpm test script");
        
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

        mgr.registerBean("event", ievent);

        String onEventScript = "     log = bsf.lookupBean(\"log\"); \n" 
                + "   log.debug(\"running onEvent script\"); \n"
                + "   source(\"src/main/resources/scriptd-event-spm1.bsh\"); \n";

        mgr.exec("beanshell", "", 0, 0, onEventScript);

        log.debug("****** end of  testEventHasHrefExistingSpm test script");
    }
    
    @Test
    public void testEventHasNoHrefNewSpm() throws BSFException {
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

        mgr.registerBean("event", ievent);

        String onEventScript = "     log = bsf.lookupBean(\"log\"); \n" 
                + "   log.debug(\"running onEvent script\"); \n"
                + "   source(\"src/main/resources/scriptd-event-spm1.bsh\"); \n";

        mgr.exec("beanshell", "", 0, 0, onEventScript);

        log.debug("****** end of testEventHasNoHrefNewSpm test script");
        
    }

}
