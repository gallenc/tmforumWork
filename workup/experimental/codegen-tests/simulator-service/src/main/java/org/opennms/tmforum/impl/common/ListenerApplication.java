package org.opennms.tmforum.impl.common;


import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import org.opennms.tmforum.impl.common.TestResource;

/**
 * MyDemoApplication
 * @author Janus Dam Nielsen
 */
public class ListenerApplication extends ResourceConfig {

    /**
     * Register JAX-RS application components.
     */
    public ListenerApplication() {
        this.register(RequestContextFilter.class);
        this.register(TestResource.class);
        this.register(JacksonFeature.class);
        // Use this for registering a full set of resources.
        // this.packages("org.foo.rest;org.bar.rest");
    }
}