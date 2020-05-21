package org.opennms.tmforum.tmf650.api.test;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.SpringLifecycleListener;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.RequestContextFilter;

@Ignore
public class JerseyGenericHubTest extends JerseyTest {
    private static Logger LOG = LoggerFactory.getLogger(JerseyGenericHubTest.class);

    @Override
    protected Application configure() {
        LOG.debug("starting jetty for test");
        ResourceConfig rc = new ResourceConfig();

        rc.register(SpringLifecycleListener.class);
        rc.register(RequestContextFilter.class);
        rc.packages("org.opennms.tmforum.tmf650.api", "org.opennms.tmforum.tmf650.api.model",
                "org.opennms.tmforum.tmf650.api.rest", "org.opennms.tmforum.tmf650.api.impl");

        rc.property("contextConfigLocation", "classpath:spring-simple-rest-test-context.xml");

        return rc;
    }

    @Test
    public void test() {
        LOG.debug("starting generic hub test  - waiting");

        try {
            Thread.sleep(120000); // 2 minute
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        LOG.debug("end of generic hub test");
    }
}