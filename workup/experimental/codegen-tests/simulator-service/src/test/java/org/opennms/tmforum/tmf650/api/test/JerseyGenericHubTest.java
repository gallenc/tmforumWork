package org.opennms.tmforum.tmf650.api.test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.SpringLifecycleListener;
import org.glassfish.jersey.test.JerseyTest;
import org.opennms.tmforum.impl.common.NewJacksonFeature;
import org.opennms.tmforum.tmf650.model.GenericEventSubscription;
import org.opennms.tmforum.tmf650.model.GenericEventSubscriptionInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.RequestContextFilter;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class JerseyGenericHubTest extends JerseyTest {
    private static Logger LOG = LoggerFactory.getLogger(JerseyGenericHubTest.class);

    @Override
    protected Application configure() {
        LOG.debug("starting jetty for test");
        ResourceConfig rc = new ResourceConfig();

        rc.register(SpringLifecycleListener.class);
        // rc.register(RequestContextFilter.class);
        rc.packages("org.opennms.tmforum.tmf650.api", "org.opennms.tmforum.tmf650.api.model",
                "org.opennms.tmforum.tmf650.api.rest", "org.opennms.tmforum.tmf650.api.impl");

        rc.property("contextConfigLocation", "classpath:spring-simple-rest-test-context.xml");

        // configures jackson data binding
        rc.register(NewJacksonFeature.class);

        return rc;
    }

    @Override
    public void configureClient(ClientConfig config) {
        // configures jackson data binding
        config.register(NewJacksonFeature.class);
    }

    @Test
    public void testget() {
        LOG.debug("start of test get");
        GenericEventSubscriptionInput subscriptionRequest = new GenericEventSubscriptionInput();
        LOG.debug("subscriptionRequest=" + subscriptionRequest);

        Response response = target("/generic-hub").request().get();
        assertEquals("Should return status 200", 200, response.getStatus());

        String subscriptionReply = response.readEntity(String.class);
        LOG.debug("subscriptionReply=" + subscriptionReply);

        LOG.debug("end of test get");
    }

    @Test
    public void testAdd() {
        LOG.debug("start of test add subscription");
        GenericEventSubscriptionInput subscriptionRequest = new GenericEventSubscriptionInput();
        LOG.debug("subscriptionRequest=" + subscriptionRequest);

        Response response = target("/generic-hub").request()
                .post(Entity.entity(subscriptionRequest, MediaType.APPLICATION_JSON));
        assertEquals("Should return status 200", 200, response.getStatus());

        GenericEventSubscription subscriptionReply = response.readEntity(GenericEventSubscription.class);
        LOG.debug("subscriptionReply=" + subscriptionReply);

        LOG.debug("end of test add subscription");
    }

    @Test
    public void testDelete() {
        LOG.debug("start of test delete subscription");
        GenericEventSubscriptionInput subscriptionRequest = new GenericEventSubscriptionInput();
        LOG.debug("subscriptionRequest=" + subscriptionRequest);

        Response response = target("/generic-hub/5").request().delete();
        assertEquals("Should return status 204", 204, response.getStatus());

        LOG.debug("end of test delete subscription");
    }

}