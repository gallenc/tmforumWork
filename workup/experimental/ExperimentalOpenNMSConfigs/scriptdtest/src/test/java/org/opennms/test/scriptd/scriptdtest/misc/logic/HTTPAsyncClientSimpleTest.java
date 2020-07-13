package org.opennms.test.scriptd.scriptdtest.misc.logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// lots of random tests
public class HTTPAsyncClientSimpleTest {
    static final Logger log = LoggerFactory.getLogger(HTTPAsyncClientSimpleTest.class);

    // tutorials https://github.com/eugenp/tutorials/tree/master/httpclient#readme
    // Now – let's see how to use a SSL Certificate with HttpAsyncClient.
    // In the following example – we configure HttpAsyncClient to accept all
    // certificates:

    final String baseHTTPSUrl = "https://tmf656-test1.centralus.cloudapp.azure.com:8443/tmf656-spm-simulator-war";

    final String baseHTTPUrl = "http://tmf656-test1.centralus.cloudapp.azure.com:8080/tmf656-spm-simulator-war";

    final int BOUND = 20;

    CloseableHttpAsyncClient m_client = null;

    BlockingQueue<JSONObject> m_jsonQueue = new LinkedBlockingQueue<>(BOUND);

    Thread m_listener = null;
    
    String basicServiceProblemTemplate = "{\n"+ 
            "  \"id\": \"string\",\n" + 
            "  \"href\": \"string\"" + 
            "  \"affectedNumberOfServices\": 0,\n" + 
            "  \"category\": \"string\",\n" + 
            "  \"correlationId\": \"string\",\n" + 
            "  \"description\": \"string\",\n" + 
            "  \"originatingSystem\": \"string\",\n" + 
            "  \"priority\": 0,\n" + 
            "  \"reason\": \"string\",\n" + 
            "  \"status\": \"string\",\n" + 
            "  \"affectedLocation\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"name\": \"string\",\n" + 
            "      \"role\": \"string\",\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"affectedResource\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"name\": \"string\",\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"affectedService\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"associatedSLAViolation\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"associatedTroubleTicket\": [\n" + 
            "    {\n" + 
            "      \"id\": \"string\",\n" + 
            "      \"href\": \"string\",\n" + 
            "      \"correlationId\": \"string\",\n" + 
            "      \"status\": \"string\",\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"comment\": [\n" + 
            "    {\n" + 
            "      \"author\": \"string\",\n" + 
            "      \"date\": \"2020-07-06T21:33:07.950Z\",\n" + 
            "      \"system\": \"string\",\n" + 
            "      \"text\": \"string\",\n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"originatorParty\": {\n" + 
            "    \"id\": \"string\",\n" + 
            "    \"href\": \"string\",\n" + 
            "    \"name\": \"string\",\n" + 
            "    \"role\": \"string\",\n" + 
            "  },\n" + 
            "}";

    @Before
    public void before() {

        startListener();
        startClient();

    }

    @After
    public void after() {
        stopClient();
        stopListener();
    }

    @Test
    public void getServiceProblemsTest() {
        log.debug("start of getServiceProblemsTest()");

        /* get http request */
        HttpRequestBase request = new HttpGet(baseHTTPUrl + "/tmf-api/serviceProblemManagement/v3/serviceProblem/2");

        executeRequest(m_client, request);

        /* get https request */
        request = new HttpGet(baseHTTPSUrl + "/tmf-api/serviceProblemManagement/v3/serviceProblem/2");

        executeRequest(m_client, request);

        /* get http ALL SERVICE PROBLEMS request */
        request = new HttpGet(baseHTTPUrl + "/tmf-api/serviceProblemManagement/v3/serviceProblem");

        executeRequest(m_client, request);

        log.debug("Waiting for responses");
        // Pause for 10 seconds
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            log.debug("sleep interupted");
        }

        log.debug("Finished Waiting for responses");

        log.debug("end of getServiceProblemsTest()");
    }
    
    
    @Test
    public void postServiceProblemsTest() {
        log.debug("start of postServiceProblemsTest()");

        /* get http request */
        HttpPost request = new HttpPost(baseHTTPUrl + "/tmf-api/serviceProblemManagement/v3/serviceProblem/");
        
        request.setHeader("Content-type", "application/json");
        request.setHeader("Accept", "application/json");

        JSONParser parser = new JSONParser();
        String jsonString =null ;
        try {
            JSONObject serviceProblem = (JSONObject) parser.parse(basicServiceProblemTemplate);
            serviceProblem.put("id", null);
            serviceProblem.put("href", null);
            jsonString=serviceProblem.toJSONString();
        } catch (ParseException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        
        StringEntity entity=null ;
        try {
            entity = new StringEntity(jsonString);
        } catch (UnsupportedEncodingException e1) {
            log.error("problem encoding post message",e1);
        }
        request.setEntity(entity);

        executeRequest(m_client, request);

        log.debug("Waiting for responses");
        /* Pause for 10 seconds */
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            log.debug("sleep interupted");
        }

        log.debug("Finished Waiting for responses");

        log.debug("end of postServiceProblemsTest()");
    }

    public synchronized void stopListener() {

        // inject poison to finish listener and empty queue
        JSONObject poison = new JSONObject();
        poison.put("poison", "true");

        // Pause for 5 seconds to finish transactions
        try {
            m_jsonQueue.offer(poison);
            Thread.sleep(5000);
            m_jsonQueue.offer(poison);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.debug("sleep interrupted");
        }

        m_listener.interrupt();
        m_listener = null;
    }

    public synchronized void startListener() {

        /* listening for replies */
        m_listener = new Thread(new Runnable() {

            @Override
            public void run() {
                log.debug("starting listener for responses");
                try {
                    JSONObject msg;
                    boolean stop = false;
                    /* consuming messages until poison message is received */
                    while (!stop) {
                        msg = m_jsonQueue.take();

                        if (msg.get("poison") != null) {
                            log.debug("listener stopping on poison message: " + msg);
                            stop = true;
                        } else {
                            log.debug("handling message: " + msg);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("listener thread closed");
            }

        });

        m_listener.start();

    }

    public synchronized void stopClient() {
        try {
            if (m_client != null)
                m_client.close();
            m_client = null;
            log.debug("Client closed");
        } catch (IOException ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            log.error("Problem stopping client" + sw.toString());
        }
    }

    public synchronized void startClient() {

        if (m_client != null) {
            log.error("Client already started. Stop client before starting a new one");
            return;
        }

        /* Accept ALL SSL certificates */
        TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
            public boolean isTrusted(X509Certificate[] certificate, String authType) {
                return true;
            }
        };
        SSLContext sslContext;
        try {
            sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();

            /* set up basic authentication */
            CredentialsProvider provider = new BasicCredentialsProvider();
            UsernamePasswordCredentials creds = new UsernamePasswordCredentials("username", "password");
            provider.setCredentials(AuthScope.ANY, creds);

            /* default request config */
            RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT)
                    .setSocketTimeout(1000).setConnectTimeout(1000).setConnectionRequestTimeout(1000).build();

            /* Set Up async client */
            m_client = HttpAsyncClients.custom()
                    .setSSLHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                    .setSSLContext(sslContext).setDefaultCredentialsProvider(provider)
                    .setDefaultRequestConfig(requestConfig).build();

            /* Start client */
            m_client.start();

        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            log.error("Problem creating client:" + sw.toString());
        }

    }

    public void executeRequest(CloseableHttpAsyncClient client, HttpRequestBase request) {

        client.execute(request, new FutureCallback<HttpResponse>() {

            public void completed(final HttpResponse response) {
                BufferedReader in = null;
                try {

                    int status = response.getStatusLine().getStatusCode();
                    log.debug(request.getRequestLine() + " response status: " + status);

                    InputStream responseBody = response.getEntity().getContent();

                    /* read the response of the request and place it in a content String */
                    in = new BufferedReader(new InputStreamReader(responseBody));
                    String inputLine;
                    StringBuffer content = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }

                    log.debug(request.getRequestLine() + " Server response : " + content);
                    JSONParser parser = new JSONParser();
                    Object item = parser.parse(content.toString());

                    JSONObject message = new JSONObject();
                    message.put("status", status);
                    message.put("jsonobject", null);
                    message.put("jsonarray", null);
                    message.put("poison", null);

                    if (item instanceof JSONArray) {
                        message.put("jsonarray", (JSONArray) item);
                    } else {
                        message.put("jsonobject", (JSONObject) item);
                    }

                    log.debug(request.getRequestLine() + " Response message" + message.toString());

                    boolean notFull = m_jsonQueue.offer(message);
                    if (!notFull) {
                        log.warn("m_jsonQueue is full so discarding message" + message.toString());
                    }

                } catch (Exception ex) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    ex.printStackTrace(pw);
                    log.error(request.getRequestLine() + " problem parsing response " + sw.toString());
                } finally {
                    if (in != null)
                        try {
                            in.close();
                        } catch (IOException e) {
                        }
                }

            }

            public void failed(final Exception ex) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                log.error(request.getRequestLine() + " failed response" + sw.toString());
            }

            public void cancelled() {
                log.error(request.getRequestLine() + " cancelled response");
            }

        });
    }

}
