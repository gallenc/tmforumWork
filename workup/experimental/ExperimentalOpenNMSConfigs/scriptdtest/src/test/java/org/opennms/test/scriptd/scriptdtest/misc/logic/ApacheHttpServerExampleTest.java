package org.opennms.test.scriptd.scriptdtest.misc.logic;

import java.io.BufferedReader;

/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import org.apache.http.ConnectionClosedException;
import org.apache.http.ExceptionLogger;
import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * this is based on https://hc.apache.org/httpcomponents-core-ga/examples.html
 * HttpFileServer.java Apache licenced code Embedded HTTP/1.1 file server based
 * on a classic (blocking) I/O model.
 */
public class ApacheHttpServerExampleTest {
    static final Logger log = LoggerFactory.getLogger(ApacheHttpServerExampleTest.class);

    class ScriptedHttpServer {

        private int port = 8081;
        // String baseUrl;
        private String m_keyStoreFileLocation;
        private SSLContext m_sslContext = null;
        private HttpServer m_server;
        private BlockingQueue m_jsonQueue = null;
        private String[] m_allowedTargets = null;

        public ScriptedHttpServer(int port, BlockingQueue jsonQueue, String[] allowedTargets, String keyStoreFileLocation) {
            super();
            this.port = port;
            this.m_keyStoreFileLocation = m_keyStoreFileLocation;
            this.m_jsonQueue = jsonQueue;
            this.m_allowedTargets = allowedTargets;
        }

        public void start() {

            try {

                if (m_keyStoreFileLocation != null) {

                    /* Initialize SSL context only if there is a keyStoreFile */
                    URL url = ApacheHttpServerExampleTest.class.getResource("/my.keystore");
                    if (url == null) {
                        System.out.println("Keystore not found");
                        System.exit(1);
                    }
                    m_sslContext = SSLContexts.custom()
                            .loadKeyMaterial(url, "secret".toCharArray(), "secret".toCharArray()).build();
                }

                SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(15000).setTcpNoDelay(true).build();

                m_server = ServerBootstrap.bootstrap().setListenerPort(port).setServerInfo("Test/1.1")
                        .setSocketConfig(socketConfig).setSslContext(m_sslContext)
                        .setExceptionLogger(new ExceptionLogger() {

                            public void log(final Exception ex) {
                                if (ex instanceof SocketTimeoutException) {
                                    log.error("Connection timed out", ex);
                                } else if (ex instanceof ConnectionClosedException) {
                                    log.error(ex.getMessage());
                                } else {
                                    log.error("exception in http server" + ex);
                                }
                            }

                        }).registerHandler("*", new JsonRequestHandler(m_jsonQueue, m_allowedTargets)).create();

                m_server.start();

            } catch (Exception ex) {
                log.error("problem starting server", ex);
            }
        }

        public void stop() {
            if (m_server != null) {
                m_server.shutdown(5, TimeUnit.SECONDS);
            }
        }

    }

    class JsonRequestHandler implements HttpRequestHandler {

        List allowedTargetsList = new ArrayList();

        private BlockingQueue m_jsonQueue = null;

        public JsonRequestHandler(BlockingQueue jsonQueue, String[] allowedTargets) {
            super();
            m_jsonQueue = jsonQueue;
            allowedTargetsList = Arrays.asList(allowedTargets);
        }

        public void handle(final HttpRequest request, final HttpResponse response, final HttpContext context)
                throws HttpException, IOException {

            BufferedReader in = null;
            try {
                String method = request.getRequestLine().getMethod().toUpperCase(Locale.ROOT);
                String target = request.getRequestLine().getUri();
                String content = null;

                if (!allowedTargetsList.contains(target)) {
                    String reason = "target "+target + " not in allowedTargetsList - http server request: " + request.getRequestLine() + "";
                    log.warn(reason);
                    response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
                    String status = Integer.toString(HttpStatus.SC_BAD_REQUEST);

                    String jsonError = jsonError(status, reason, null, status);

                    StringEntity entity = new StringEntity(jsonError, ContentType.create("application/json", "UTF-8"));
                    response.setEntity(entity);
                    return;
                }

                if (request instanceof HttpEntityEnclosingRequest) {
                    HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
                    InputStream responseBody = entity.getContent();
                    /* read the body of the request and place it in a content String */
                    in = new BufferedReader(new InputStreamReader(responseBody));
                    String inputLine;
                    StringBuffer contentbuff = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        contentbuff.append(inputLine);
                    }
                    content = contentbuff.toString();
                }

                log.debug("http server request: " + request.getRequestLine() + " content: " + content);

                JSONObject message = new JSONObject();
                message.put("requestMethod", method);
                message.put("requestHost", null);
                message.put("requestPath", target);
                message.put("status", null);
                message.put("jsonobject", null);
                message.put("jsonarray", null);
                message.put("poison", null);

                if (content != null && !content.isEmpty()) {
                    try {
                        JSONParser parser = new JSONParser();
                        Object item = parser.parse(content.toString());
                        if (item instanceof JSONArray) {
                            message.put("jsonarray", (JSONArray) item);
                        } else {
                            message.put("jsonobject", (JSONObject) item);
                        }
                    } catch (Exception ex) {
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        ex.printStackTrace(pw);

                        String reason = "cannot parse server request: " + request.getRequestLine() + "\n content "
                                + content + "\n" + sw.toString();
                        log.warn(reason);
                        response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
                        String status = Integer.toString(HttpStatus.SC_BAD_REQUEST);

                        String jsonError = jsonError(status, reason, null, status);

                        StringEntity entity = new StringEntity(jsonError,
                                ContentType.create("application/json", "UTF-8"));
                        response.setEntity(entity);
                        return;
                    }
                }

                response.setStatusCode(HttpStatus.SC_OK);

                log.debug(" queuing message:  request: " + request.getRequestLine() + " message " + message.toString());

                if (m_jsonQueue == null) {
                    log.warn("m_jsonQueue is null so discarding message" + message.toString());
                } else {
                    boolean notFull = m_jsonQueue.offer(message);
                    if (!notFull) {
                        log.warn("m_jsonQueue is full so discarding message" + message.toString());
                    }
                }

            } catch (Exception ex) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);

                String reason = "problem handling request: " + request.getRequestLine() + "\n error " + sw.toString();
                log.error(reason);
                response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
                String status = Integer.toString(HttpStatus.SC_BAD_REQUEST);
                String jsonError = jsonError(status, reason, null, status);

                StringEntity entity = new StringEntity(jsonError, ContentType.create("application/json", "UTF-8"));
                response.setEntity(entity);
            } finally {
                if (in != null)
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
            }

        }

    }

    private String jsonError(String code, String reason, String message, String status) {
        JSONObject errorMessage = new JSONObject();
        if (code != null)
            errorMessage.put("code", code);
        if (reason != null)
            errorMessage.put("reason", reason);
        if (message != null)
            errorMessage.put("message", message);
        if (status != null)
            errorMessage.put("status", status);
        return errorMessage.toString();
    }

    @Test
    public void test() {
        log.debug("starting test");
        int port = 8081;
        BlockingQueue jsonQueue = null;
        String keyStoreFileLocation = null;

        String[] allowedTargets = {"/",
                "/opennms/tmf-api/serviceProblemManagement/v3/listener/serviceProblemAttributeValueChangeNotification",
                "/opennms/tmf-api/serviceProblemManagement/v3/listener/serviceProblemCreateNotification",
                "/opennms/tmf-api/serviceProblemManagement/v3/listener/serviceProblemInformationRequiredNotification",
                "/opennms/tmf-api/serviceProblemManagement/v3//listener/serviceProblemStateChangeNotification",
                "/generic-listener/notification"
                };

        ScriptedHttpServer server = new ScriptedHttpServer(port, jsonQueue, allowedTargets, keyStoreFileLocation);

        log.debug("starting server   ");
        server.start();
        log.debug("server started waiting 20 secs for for requests  ");
        // Pause for 30 seconds
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            log.debug("sleep interupted");
        }

        log.debug("stopping server   ");
        server.stop();

        log.debug("Finished Waiting for responses");

    }

}
