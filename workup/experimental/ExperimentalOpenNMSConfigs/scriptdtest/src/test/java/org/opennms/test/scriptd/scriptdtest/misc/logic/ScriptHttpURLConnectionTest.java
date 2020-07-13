package org.opennms.test.scriptd.scriptdtest.misc.logic;

import static org.junit.Assert.*;

import org.junit.Test;
import org.opennms.test.scriptd.scriptdtest.misc.logic.ScriptdHttpURLConnectionExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScriptHttpURLConnectionTest {
    static final Logger log = LoggerFactory.getLogger(ScriptHttpURLConnectionTest.class);

    @Test
    public void test() {
        log.debug("start of test");
        ScriptdHttpURLConnectionExample scriptdExample = new ScriptdHttpURLConnectionExample();
        
        log.debug("test create service problem");
        scriptdExample.createServiceProblem();
        
        log.debug("initialise");
        scriptdExample.startScript();
        
        log.debug("send event ");
        scriptdExample.eventScript();
        
        log.debug("stop script");
        scriptdExample.stopScript();
        
        log.debug("end of test");
    }

}
