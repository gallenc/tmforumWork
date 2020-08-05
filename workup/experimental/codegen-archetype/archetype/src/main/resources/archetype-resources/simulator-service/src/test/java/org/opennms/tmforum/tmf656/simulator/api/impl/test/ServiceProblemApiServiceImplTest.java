#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package org.opennms.tmforum.${tmfSpecPackageName}.simulator.api.impl.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ${package}.${tmfSpecPackageName}.swagger.model.RelatedParty;
import org.opennms.tmforum.${tmfSpecPackageName}.simulator.dao.ServiceProblemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-service-test-context.xml" })
public class ServiceProblemApiServiceImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceProblemApiServiceImplTest.class);

    @Autowired
    private ServiceProblemRepository serviceProblemRepository = null;

    @Before
    public void before() {
        
        RelatedParty rp;
        LOG.debug("before test running");
        assertNotNull(serviceProblemRepository);
        LOG.debug("before test complete");
    }
    
    @Test
    public void test1() {
        
    }

}
