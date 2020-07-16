package org.opennms.test.scriptd.scriptdtest;

import java.util.Date;

import org.apache.bsf.BSFEngine;
import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.opennms.netmgt.xml.event.Event;

//https://javasourcequery.com/example/org.apache.bsf.lookupBean
public class TestRunBshSpm1Script {
    static final Logger log = LoggerFactory.getLogger(TestRunBshSpm1Script.class);

    private static BSFManager mgr = new BSFManager();

    private BSFEngine beanshellEngine;

    @Before
    public void before() throws BSFException {
        log.debug("executing start script");
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
    public void test() throws BSFException {
        log.debug("start of onevent test script");

        Event event = new Event();
        event.setUei("uei.opennms.org/internal/alarms/AlarmRaised");
        event.setHost("127.0.0.1");
        event.setCreationTime(new Date());

        mgr.registerBean("event", event);

        String onEventScript = "     log = bsf.lookupBean(\"log\"); \n" 
                + "   log.debug(\"running onEvent script\"); \n"
                + "   source(\"src/main/resources/scriptd-event-spm1.bsh\"); \n";

        mgr.exec("beanshell", "", 0, 0, onEventScript);

        log.debug("end of onevent test script");
    }
}
