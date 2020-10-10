/* Asynchronous Apache HTTP Client which is used as basis for bean shell script  */
/* Author: Craig Gallen */
/* Version : 1.8 */

package org.opennms.test.scriptd.scriptdtest.client.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
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
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.ssl.SSLContexts;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScriptedApacheHttpAsyncClient {
    static final Logger log = LoggerFactory.getLogger(ScriptedApacheHttpAsyncClient.class);

    /*
     * tutorials https://github.com/eugenp/tutorials/tree/master/httpclient#readme
     * Now – let's see how to use a SSL Certificate with HttpAsyncClient. In the
     * following example – we configure HttpAsyncClient to accept all certificates:
     * 
     * NOTE beanshell does not support generics or @Override !!! ( annotations ) or
     * | in Exceptions (basically limited to before java 5)
     */

    int BOUND = 20;
    
    /* Apache HttpAsync setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT).setConnectionRequestTimeout(TIMEOUT) */
    /* 5 seconds timeout */
    int TIMEOUT=5000;
    
    int m_timeout = TIMEOUT;

	CloseableHttpAsyncClient m_client = null;

    BlockingQueue m_jsonQueue = new LinkedBlockingQueue(BOUND);

    Thread m_listener = null;

    MessageHandler m_messageHandler = new MessageHandler();

    CredentialsProvider m_defaultCredentialsProvider = null;
    
    public void setTimeout(int timeout) {
    	m_timeout = timeout;
	}

    public void setDefaultCredentialsProvider(CredentialsProvider defaultCredentialsProvider) {
        this.m_defaultCredentialsProvider = defaultCredentialsProvider;
    }

    public void setMessageHandler(MessageHandler messageHandler) {
        log.debug("scriptedApacheHttpAsyncClient setMessageHandler " + messageHandler);
        m_messageHandler = messageHandler;
    }

    public BlockingQueue getjsonQueue() {
        return m_jsonQueue;
    }

    public synchronized void stopListener() {

        /* inject poison to finish listener and empty queue */
        JSONObject poison = new JSONObject();
        poison.put("poison", "true");

        /* Pause for 5 seconds to finish transactions */
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

            public void handleMessage(JSONObject msg) {
                try {
                    log.debug("method handling reply message: " + msg);
                    m_messageHandler.handleIncomingMessage(msg);
                } catch (Exception ex) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    ex.printStackTrace(pw);
                    log.warn("1st listener thread exception " + sw.toString());
                }
            }

            public void run() {
                log.debug("starting listener for responses");
                try {

                    /* consuming messages until poison message is received */
                    while (true) {
                        /* note not casting here because it causes null object in beanshell */
                        Object rxmsg = m_jsonQueue.take();
                        JSONObject msg = (JSONObject) rxmsg;
                        if (msg == null) {
                            log.warn("listener message is null");
                        } else if (msg.get("poison") != null) {
                            log.debug("listener stopping on poison message: " + msg);
                            throw new InterruptedException("interrupted on poison message");
                        } else {
                            log.debug("handling reply message: " + msg);
                            handleMessage(msg);
                        }
                    }
                } catch (InterruptedException ex) {
                    log.warn("listener thread interrupted " + ex.toString());
                }
                log.debug("listener thread closed");
            }

        });

        m_listener.start();

    }

    /* asynchronous client code */

    public void getRequest(String url, String username, String password) {
        if (m_client == null) {
            log.error("Client not started. call startClient() first");
            return;
        }
        HttpGet request = new HttpGet(url);
        executeRequest(request, username, password);
    }

    public void deleteRequest(String url, String username, String password) {
        if (m_client == null) {
            log.error("Client not started. call startClient() first");
            return;
        }
        HttpDelete request = new HttpDelete(url);
        executeRequest(request, username, password);
    }

    public void postRequest(String url, String jsonMessage, String username, String password) {
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
        executeRequest(request, username, password);
    }

    public void putRequest(String url, String jsonMessage, String username, String password) {
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
        executeRequest(request, username, password);
    }

    public void patchRequest(String url, String jsonMessage, String username, String password) {
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
        executeRequest(request, username, password);
    }

    public synchronized void startClient() {
        log.debug("Starting scriptedHttpAsyncClient socket-timeout="+m_timeout);

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
            /* set up ssl context */
            sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();

            /* default request config */
            RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT)
                    .setSocketTimeout(m_timeout).setConnectTimeout(m_timeout).setConnectionRequestTimeout(m_timeout).build();

            /* Set Up async client */
            HttpAsyncClientBuilder clientBuilder = HttpAsyncClients.custom()
                    .setSSLHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                    .setSSLContext(sslContext)
                    .setDefaultRequestConfig(requestConfig);

            /* set default credentials provider if present. */
            if (m_defaultCredentialsProvider != null) {
                clientBuilder.setDefaultCredentialsProvider(m_defaultCredentialsProvider);
            }

            m_client = clientBuilder.build();

            /* Start client */
            m_client.start();

        } catch (Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            log.error("Problem creating client:" + sw.toString());
        }

    }

    public synchronized void stopClient() {
        log.debug("Stopping scriptedHttpAsyncClient");

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

    public void executeRequest(HttpRequestBase request, String username, String password) {
        log.debug("executing scriptedHttpAsyncClient request");

        /* not using typed or anonymous callback because of beanshell */

        FutureCallback requestCallback = new FutureCallback() {

            public void completed(Object objectRresponse) {

                BufferedReader in = null;
                try {

                    HttpResponse response = (HttpResponse) objectRresponse;

                    int status = response.getStatusLine().getStatusCode();
                    log.debug(" response status: " + status + " request: " + request.getRequestLine());

                    StringBuffer contentbuff = new StringBuffer();

                    if (response.getEntity() != null) {
                        InputStream responseBody = response.getEntity().getContent();

                        /* read the response of the request and place it in a content String */
                        in = new BufferedReader(new InputStreamReader(responseBody));
                        String inputLine;

                        while ((inputLine = in.readLine()) != null) {
                            contentbuff.append(inputLine);
                        }
                    }
                    
                    String content = contentbuff.toString();

                    log.debug(" reply content status: " + status + " request: " + request.getRequestLine() + " content: " + content);

                    JSONObject message = new JSONObject();
                    message.put("messageSource", "asyncClient");
                    message.put("requestMethod", request.getMethod());
                    message.put("requestHost", request.getURI().getHost());
                    message.put("requestPath", request.getURI().getPath());
                    message.put("requestPort", request.getURI().getPort());
                    message.put("requestRawUrl", request.getURI().toURL().toExternalForm());
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
                            log.warn("cannot parse server response  status: " + status + " request: " + request.getRequestLine() + " content " + content);
                        }
                    }

                    log.debug(" Response message status: " + status + " request: " + request.getRequestLine() + " message " + message.toString());

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

            public void failed(Exception ex) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                log.error(" failed response. request: " + request.getRequestLine() + "error:" + sw.toString());
            }

            public void cancelled() {
                log.error(" cancelled response. request: " + request.getRequestLine());
            }

        };

        if (username != null && !username.isEmpty()) {

            /* set up basic authentication specifically for this request only if username specified */
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            UsernamePasswordCredentials creds = new UsernamePasswordCredentials(username, password);
            credentialsProvider.setCredentials(AuthScope.ANY, creds);
            HttpClientContext context = HttpClientContext.create();
            context.setCredentialsProvider(credentialsProvider);

            m_client.execute(request, context, requestCallback);

        } else {
            /* dont use basic authentication for this request if username is null */
            m_client.execute(request, requestCallback);
        }

    }

}
