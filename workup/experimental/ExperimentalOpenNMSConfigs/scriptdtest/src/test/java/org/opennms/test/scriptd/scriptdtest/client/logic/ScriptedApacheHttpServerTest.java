package org.opennms.test.scriptd.scriptdtest.client.logic;

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
import org.opennms.test.scriptd.scriptdtest.client.logic.ScriptedApacheHttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * this is based on https://hc.apache.org/httpcomponents-core-ga/examples.html
 * HttpFileServer.java Apache licenced code Embedded HTTP/1.1 file server based
 * on a classic (blocking) I/O model.
 */
public class ScriptedApacheHttpServerTest {
    static final Logger log = LoggerFactory.getLogger(ScriptedApacheHttpServerTest.class);


    @Test
    public void test() {
        log.debug("starting server test");
        int port = 8981;
        BlockingQueue jsonQueue = null;
        String keyStoreFileLocation = null;

        String[] allowedTargets = {"/",
                "/opennms/tmf-api/serviceProblemManagement/v3/listener/serviceProblemAttributeValueChangeNotification",
                "/opennms/tmf-api/serviceProblemManagement/v3/listener/serviceProblemCreateNotification",
                "/opennms/tmf-api/serviceProblemManagement/v3/listener/serviceProblemInformationRequiredNotification",
                "/opennms/tmf-api/serviceProblemManagement/v3//listener/serviceProblemStateChangeNotification",
                "/generic-listener/notification"
                };

        ScriptedApacheHttpServer server = new ScriptedApacheHttpServer(port, jsonQueue, allowedTargets, keyStoreFileLocation);

        log.debug("starting server   ");
        server.start();
        log.debug("server started waiting 30 secs for for requests  ");
        // Pause for 30 seconds
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            log.debug("sleep interupted");
        }

        log.debug("stopping server   ");
        server.stop();

        log.debug("Stopping server test");

    }

}
