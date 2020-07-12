package org.opennms.test.scriptd.scriptdtest.logic;

import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.cert.X509Certificate;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


// lots of random tests
public class HTTPAsyncClientSimpleGetTest2 {
    static final Logger log = LoggerFactory.getLogger(HTTPAsyncClientSimpleGetTest2.class);

    // tutorials https://github.com/eugenp/tutorials/tree/master/httpclient#readme
    //Now – let's see how to use a SSL Certificate with HttpAsyncClient.
   // In the following example – we configure HttpAsyncClient to accept all certificates:

        @Test
        public void whenUseSSLWithHttpAsyncClient_thenCorrect() throws Exception {
            
            /* Accept ALL SSL certificates */
            TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] certificate,  String authType) {
                    return true;
                }
            };
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
            
            /* set up basic authentication */
            CredentialsProvider provider = new BasicCredentialsProvider();
            UsernamePasswordCredentials creds = 
                new UsernamePasswordCredentials("username", "password");
            provider.setCredentials(AuthScope.ANY, creds);
            
            /* default request config */
            RequestConfig requestConfig = RequestConfig.custom()
                    .setCookieSpec(CookieSpecs.DEFAULT)
                    .setSocketTimeout(1000)
                    .setConnectTimeout(1000)
                    .setConnectionRequestTimeout(1000)
                    .build();
            
            /* Set Up async client */
            CloseableHttpAsyncClient client = HttpAsyncClients.custom()
              .setSSLHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
              .setSSLContext(sslContext)
              .setDefaultCredentialsProvider(provider)
              .setDefaultRequestConfig(requestConfig)
              .build();
            
            /* Start client */
            client.start();
            
            /* get request */
            HttpGet request = new HttpGet("https://tmf656-test1.centralus.cloudapp.azure.com:8443/tmf656-spm-simulator-war/tmf-api/serviceProblemManagement/v3/serviceProblem/2");
            Future<HttpResponse> future = client.execute(request, null);
            HttpResponse response = future.get();

            // this reads the status
            int status = response.getStatusLine().getStatusCode();
            
            log.debug("response status: "+status);


            BufferedReader in=null;
            try {
                InputStream responseBody = response.getEntity().getContent();
                
                // read the response of the request and place it in a content String:
                in = new BufferedReader(new InputStreamReader(responseBody));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                
                log.debug("Server response : "+content);
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(content.toString());
                log.debug("response object"+jsonObject.toString());
                
                log.debug("response ID: "+jsonObject.get("id"));


            } catch (Exception ex) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                log.error("problem in script"+ sw.toString());
            } finally {
               if(in!=null) in.close();
            }

            client.close();
        }

}
