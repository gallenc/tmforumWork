package org.opennms.test.scriptd.scriptdtest.client.logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.opennms.netmgt.xml.event.AlarmData;
import org.opennms.netmgt.xml.event.Event;
import org.opennms.netmgt.xml.event.Parm;
import org.opennms.netmgt.xml.event.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScriptedEventSPMForwarderTest {
    static final Logger log = LoggerFactory.getLogger(ApacheHttpAsyncClientTest.class);

    ScriptedApacheHttpAsyncClient scriptedClient = null;
    ScriptedEventSPMForwarder spmForwarder = null;

    @Before
    public void before() {

        scriptedClient = new ScriptedApacheHttpAsyncClient();
        spmForwarder = new ScriptedEventSPMForwarder();

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
        event.setUei("ueistring");

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

        spmForwarder.updateServiceProblem(event);
        
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
        event.setUei("ueistring");

        AlarmData alarmData = new AlarmData();
        alarmData.setReductionKey("reductionkey");
        alarmData.setAlarmType(0);
        event.setAlarmData(alarmData);
        
        spmForwarder.updateServiceProblem(event);
        
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
