package org.opennms.tmforum.impl.common.test.manual;

import static org.junit.Assert.*;

import java.io.IOException;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.servlet.ServletContainer;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

//@Ignore
public class EndpointTest {

    @Before
    public void init() {

        try {
            System.out.println("started embedded server");
            startEmbeddedServer();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        System.out.println("test ended");
        // fail("Not yet implemented");
    }

    // see example https://github.com/janusdn/jersey2-spring4-grizzly2/

    public void startEmbeddedServer() throws IOException {

        // Create test web application context.
        WebappContext webappContext = new WebappContext("Test Context");
        webappContext.addContextInitParameter("contextClass",
                "org.springframework.web.context.support.XmlWebApplicationContext");
        webappContext.addContextInitParameter("contextConfigLocation", "classpath*:spring-test-context.xml");
        webappContext.addListener("org.springframework.web.context.ContextLoaderListener");

        // Create a servlet registration for the web application in order to wire up
        // Spring managed collaborators to Jersey resources.
        ServletRegistration servletRegistration = webappContext.addServlet("jersey-servlet", ServletContainer.class);

        // The logging filters for server logging.
        servletRegistration.setInitParameter("com.sun.jersey.spi.container.ContainerResponseFilters",
                "com.sun.jersey.api.container.filter.LoggingFilter");
        servletRegistration.setInitParameter("com.sun.jersey.spi.container.ContainerRequestFilters",
                "com.sun.jersey.api.container.filter.LoggingFilter");

        servletRegistration.setInitParameter("javax.ws.rs.Application",
                "org.opennms.tmforum.impl.common.ListenerApplication");

        servletRegistration.setInitParameter("com.sun.jersey.config.property.packages",
                "org.opennms.tmforum.tmf656.service");
        // servletRegistration.setInitParameter(
        // "com.sun.jersey.api.json.POJOMappingFeature", "true");

        servletRegistration.addMapping("/*");

        HttpServer server = new HttpServer();
        NetworkListener listener = new NetworkListener("grizzly2", "localhost", 3388);
        server.addListener(listener);

        webappContext.deploy(server);

        try {
            server.start();
            System.out.println("Waiting for server stop");
            Thread.sleep(30000);
            // System.in.read();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            server.shutdownNow();
        }
    }

}
