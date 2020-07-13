package org.opennms.test.scriptd.scriptdtest.client.logic;

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

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.ssl.SSLContexts;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

public class ScriptedApacheHttpAsyncClient {
    static final Logger log = LoggerFactory.getLogger(ScriptedApacheHttpAsyncClient.class);

    // tutorials https://github.com/eugenp/tutorials/tree/master/httpclient#readme
    // Now – let's see how to use a SSL Certificate with HttpAsyncClient.
    // In the following example – we configure HttpAsyncClient to accept all
    // certificates:

    final int BOUND = 20;

    CloseableHttpAsyncClient m_client = null;

    BlockingQueue<JSONObject> m_jsonQueue = new LinkedBlockingQueue<>(BOUND);

    Thread m_listener = null;

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
                            handleMessage(msg);

                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("listener thread closed");
            }

            public void handleMessage(JSONObject msg) {
                log.debug("handling reply message: " + msg);

            }

        });

        m_listener.start();

    }

    /* asynchronous client code */

    public void getRequest(String url) {
        if (m_client == null) {
            log.error("Client not started. call startClient() first");
            return;
        }
        HttpGet request = new HttpGet(url);
        executeRequest(request);
    }

    public void deleteRequest(String url) {
        if (m_client == null) {
            log.error("Client not started. call startClient() first");
            return;
        }
        HttpDelete request = new HttpDelete(url);
        executeRequest(request);
    }

    public void postRequest(String url, String jsonMessage) {
        if (m_client == null) {
            log.error("Client not started. call startClient() first");
            return;
        }
        HttpPost request = new HttpPost(url);

        request.setHeader("Content-type", "application/json");
        request.setHeader("Accept", "application/json");
        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonMessage);
        } catch (UnsupportedEncodingException e1) {
            log.error("postRequest problem encoding post message", e1);
            return;
        }
        request.setEntity(entity);
        executeRequest(request);
    }

    public void putRequest(String url, String jsonMessage) {
        if (m_client == null) {
            log.error("Client not started. call startClient() first");
            return;
        }
        HttpPut request = new HttpPut(url);

        request.setHeader("Content-type", "application/json");
        request.setHeader("Accept", "application/json");
        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonMessage);
        } catch (UnsupportedEncodingException e1) {
            log.error("putRequest problem encoding post message", e1);
            return;
        }
        request.setEntity(entity);
        executeRequest(request);
    }

    public void patchRequest(String url, String jsonMessage) {
        if (m_client == null) {
            log.error("Client not started. call startClient() first");
            return;
        }
        HttpPatch request = new HttpPatch(url);

        request.setHeader("Content-type", "application/json");
        request.setHeader("Accept", "application/json");
        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonMessage);
        } catch (UnsupportedEncodingException e1) {
            log.error("patchRequest problem encoding post message", e1);
            return;
        }
        request.setEntity(entity);
        executeRequest(request);
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

    public void executeRequest(HttpRequestBase request) {

        m_client.execute(request, new FutureCallback<HttpResponse>() {

            public void completed(final HttpResponse response) {
                BufferedReader in = null;
                try {

                    int status = response.getStatusLine().getStatusCode();
                    log.debug(request.getRequestLine() + " response status: " + status);

                    InputStream responseBody = response.getEntity().getContent();

                    /* read the response of the request and place it in a content String */
                    in = new BufferedReader(new InputStreamReader(responseBody));
                    String inputLine;
                    StringBuffer contentbuff = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        contentbuff.append(inputLine);
                    }
                    String content = contentbuff.toString();

                    log.debug(request.getRequestLine() + " Server response : " + content);

                    JSONObject message = new JSONObject();
                    message.put("status", status);
                    message.put("jsonobject", null);
                    message.put("jsonarray", null);
                    message.put("poison", null);

                    if (!content.isEmpty()) {
                        try {
                            JSONParser parser = new JSONParser();
                            Object item = parser.parse(content.toString());
                            if (item instanceof JSONArray) {
                                message.put("jsonarray", (JSONArray) item);
                            } else {
                                message.put("jsonobject", (JSONObject) item);
                            }
                        } catch (Exception ex) {
                            log.warn(request.getRequestLine() + " cannot parse server response : " + content);
                        }
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
