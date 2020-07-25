package org.opennms.test.scriptd.scriptdtest.misc.logic;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import com.sun.net.httpserver.HttpServer;

// just using capability in java 8 oracle 
// see https://dzone.com/articles/simple-http-server-in-java
public class SimpleHttpServerTest {
    static final Logger log = LoggerFactory.getLogger(SimpleHttpServerTest.class);

    @Test
    public void test() {

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            server.createContext("/test", new MyHttpHandler());

            server.setExecutor(threadPoolExecutor);

            server.start();


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        log.info(" Server started on port 8001  http://localhost:8001/test?name=sam");
        
        // Pause for 20 seconds
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            log.debug("sleep interupted");
        }

        log.info(" Server closed");
    }

    private class MyHttpHandler implements HttpHandler {

        @Override

        public void handle(HttpExchange httpExchange) throws IOException {

            String requestParamValue = null;
            if ("GET".equals(httpExchange.getRequestMethod())) {
                requestParamValue = handleGetRequest(httpExchange);
            } else if ("POST".equals(httpExchange)) {
               // requestParamValue = handlePostRequest(httpExchange);
            }
            handleResponse(httpExchange, requestParamValue);

        }

        private String handleGetRequest(HttpExchange httpExchange) {
            return httpExchange.getRequestURI()
                    .toString()
                    .split("\\?")[1]
                            .split("=")[1];
        }

        private void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
            OutputStream outputStream = httpExchange.getResponseBody();
            StringBuilder htmlBuilder = new StringBuilder();

            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("Hello ")
                    .append(requestParamValue)
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");

            // encode HTML content

           // String htmlResponse = StringEscapeUtils.escapeHtml4(htmlBuilder.toString());
            String htmlResponse = htmlBuilder.toString();

            // this line is a must

            httpExchange.sendResponseHeaders(200, htmlResponse.length());

            outputStream.write(htmlResponse.getBytes());

            outputStream.flush();

            outputStream.close();

        }

    }

}
