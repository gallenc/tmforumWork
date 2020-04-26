package org.opennms.tmforum.impl.common.experimental.test.manual;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.bridge.SLF4JBridgeHandler;

// see also https://www.baeldung.com/jersey-test Jersey Test Framework

//@Ignore
public class EndpointTest {

    public static final String BASE_URI = "http://localhost:8080/api/";
    private HttpServer server;

//    @Before
//    public void setUp() throws Exception {
//        final ResourceConfig rc = new ResourceConfig(Service.class);
//        server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);       
//    }

    @Before
    public void setUp() {

        try {
            System.out.println("started embedded server");
            startEmbeddedServer();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        if (server != null)
            server.shutdownNow();
    }

    @Test
    public void test() {
        System.out.println("server started - waiting");

        // Client client = ClientBuilder.newClient();
        // WebTarget target = client.target(BASE_URI).path("service");

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
        }
        System.out.println("test ended");
        // fail("Not yet implemented");
    }

    // see example https://github.com/janusdn/jersey2-spring4-grizzly2/
    public void startEmbeddedServer() throws IOException {
        
        ResourceConfig rc = new ResourceConfig ();
        rc.packages ("org.opennms.tmforum.tmf656.service");
        rc.register (LoggingFeature.class);

        rc.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, "INFO"); //INFO
        rc.property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_SERVER, LoggingFeature.Verbosity.HEADERS_ONLY);

       HttpServer server = GrizzlyHttpServerFactory.createHttpServer (URI.create ("http://localhost:3388"), rc);

        // Create test web application context.
        WebappContext webappContext = new WebappContext("Test Context");
//        webappContext.addContextInitParameter("contextConfigLocation", "classpath*:spring-test-context.xml");
        webappContext.addContextInitParameter("contextConfigLocation", "spring-test-context.xml");
      //  webappContext.addContextInitParameter("contextClass","org.springframework.web.context.support.XmlWebApplicationContext");
     //   webappContext.addListener("org.springframework.web.context.ContextLoaderListener");

        // Create a servlet registration for the web application in order to wire up
        // Spring managed collaborators to Jersey resources.
        ServletRegistration servletRegistration = webappContext.addServlet("jersey-servlet", ServletContainer.class);

        // The logging filters for server logging.
//        servletRegistration.setInitParameter("com.sun.jersey.spi.container.ContainerResponseFilters",
//                "com.sun.jersey.api.container.filter.LoggingFilter");
//        servletRegistration.setInitParameter("com.sun.jersey.spi.container.ContainerRequestFilters",
//                "com.sun.jersey.api.container.filter.LoggingFilter");

 //       servletRegistration.setInitParameter("jersey.config.server.provider.classnames",   "org.glassfish.jersey.filter.LoggingFilter");

 //       servletRegistration.setInitParameter("javax.ws.rs.Application", "org.opennms.tmforum.impl.common.ListenerApplication");

     //   servletRegistration.setInitParameter("com.sun.jersey.config.property.packages",
     //           "org.opennms.tmforum.tmf656.service");
        // servletRegistration.setInitParameter(
        // "com.sun.jersey.api.json.POJOMappingFeature", "true");

        servletRegistration.addMapping("/*");

       // HttpServer server = new HttpServer();
        // http:\\localhost:3388
       // NetworkListener listener = new NetworkListener("grizzly2", "localhost", 3388);
       // server.addListener(listener);

        webappContext.deploy(server);

        try {

            // https://stackoverflow.com/questions/20550028/jersey-loggingfilter-with-log4j
            // Jersey uses java.util.logging - bridge to slf4
            SLF4JBridgeHandler.removeHandlersForRootLogger();
            SLF4JBridgeHandler.install();

            server.start();

        } catch (Exception e) {
            throw new RuntimeException("failed to start test server ", e);
        }
    }

}
