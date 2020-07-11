package org.opennms.test.scriptd.scriptdtest.logic;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
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


// lots of random tests
public class HTTPAsyncClientTest2 {

    // tutorials https://github.com/eugenp/tutorials/tree/master/httpclient#readme
    //Now – let's see how to use a SSL Certificate with HttpAsyncClient.
   // In the following example – we configure HttpAsyncClient to accept all certificates:

        @Test
        public void whenUseSSLWithHttpAsyncClient_thenCorrect() throws Exception {
            
            /* Accept ALL ssl certificates */
            TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] certificate,  String authType) {
                    return true;
                }
            };
            SSLContext sslContext = SSLContexts.custom()
              .loadTrustMaterial(null, acceptingTrustStrategy).build();
         
            /* Set Up async client */
            CloseableHttpAsyncClient client = HttpAsyncClients.custom()
              .setSSLHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
              .setSSLContext(sslContext).build();
            
            
            
            
            client.start();
            
            HttpGet request = new HttpGet("https://mms.nw.ru/");
            Future<HttpResponse> future = client.execute(request, null);
            HttpResponse response = future.get();
            
            assertEquals(response.getStatusLine().getStatusCode(), 200);
            client.close();
        }

}
