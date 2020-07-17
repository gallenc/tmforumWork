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

    String SERVICE_PROBLEM_ALARM="org.opennms.uei.serviceProblemAlarm";
    String SERVICE_PROBLEM_ALARM_UPDATE="org.opennms.uei.serviceProblemAlarmUpdate";
    
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

    @Test
    public void testEventHasNoHref() throws BSFException {
        log.debug("start of testEventHasNoHref test script");

        Event event = new Event();
        event.setUei("uei.opennms.org/internal/alarms/AlarmRaised");
        event.setHost("127.0.0.1");
        event.setCreationTime(new Date());

        event.setDbid(10);
        event.setDescr("this description ");
        event.setUei(SERVICE_PROBLEM_ALARM);

        AlarmData alarmData = new AlarmData();
        alarmData.setReductionKey("reductionkey");
        alarmData.setAlarmType(0);
        event.setAlarmData(alarmData);
        
        IEvent ievent = ImmutableMapper.fromMutableEvent(event);

        mgr.registerBean("event", ievent);

        String onEventScript = "     log = bsf.lookupBean(\"log\"); \n" 
                + "   log.debug(\"running onEvent script\"); \n"
                + "   source(\"src/main/resources/scriptd-event-spm1.bsh\"); \n";

        mgr.exec("beanshell", "", 0, 0, onEventScript);

        log.debug("end of testEventHasNoHref test script");
    }
    
    @Test
    public void testEventHasHref() throws BSFException {
        log.debug("start of testEventHasHref test script");

        Event event = new Event();
        event.setHost("127.0.0.1");
        event.setCreationTime(new Date());
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

        mgr.registerBean("event", ievent);

        String onEventScript = "     log = bsf.lookupBean(\"log\"); \n" 
                + "   log.debug(\"running onEvent script\"); \n"
                + "   source(\"src/main/resources/scriptd-event-spm1.bsh\"); \n";

        mgr.exec("beanshell", "", 0, 0, onEventScript);

        log.debug("end of testEventHasHref test script");
    }
}
