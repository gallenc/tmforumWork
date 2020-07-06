package org.opennms.test.scriptd.scriptdtest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScriptTest {
    static final Logger log = LoggerFactory.getLogger(ScriptTest.class);

    @Test
    public void test() {
        log.debug("start of test");
        ScriptdExample scriptdExample = new ScriptdExample();
        
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
