package org.opennms.tmforum.tmf656.simulator.dao.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.opennms.tmforum.tmf656.simulator.dao.ServiceProblemRepository;
import org.opennms.tmforum.tmf656.simulator.model.ServiceProblemImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * NOTE FOR DAO TEST IN ECLIPSE TO PASS YOU NEED TO TURN OFF PROJECT REFERENCING
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring.xml" })
public class ServiceProblemRepositoryTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(ServiceProblemRepositoryTest.class);

    @Autowired
    private ServiceProblemRepository serviceProblemRepository = null;

    @Before
    public void before() {
        LOG.debug("before test running");
        assertNotNull(serviceProblemRepository);
        LOG.debug("before test complete");
    }

    @Transactional
    @Test
    public void test1() {
        LOG.debug("start of test1");

        ServiceProblemImpl serviceProblem1 = new ServiceProblemImpl();
        serviceProblem1 = serviceProblemRepository.save(serviceProblem1);
        System.out.println("serviceProblem1=" + serviceProblem1);

        Long id = serviceProblem1.getID();
        ServiceProblemImpl serviceProblem2 = serviceProblemRepository.getOne(id);
        System.out.println("serviceProblem2=" + serviceProblem2);
        LOG.debug("end of test1");
    }
}