package com.intellij.task.rally;

import java.net.URI;

import com.rallydev.rest.RallyRestApi;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;

public final class PooledRallyRestApi extends RallyRestApi {
    /**
     * Creates a new instance for the specified server using the specified credentials.
     *
     * @param server The server to connect to, e.g. {@code new URI("https://rally1.rallydev.com")}
     * @param userName The username to be used for authentication.
     * @param password The password to be used for authentication.
     */
    public PooledRallyRestApi(URI server, String userName, String password) {
        super(server, userName, password);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(
                new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        schemeRegistry.register(
                new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

        PoolingClientConnectionManager cm = new PoolingClientConnectionManager(schemeRegistry);
// Increase max total connection to 200
        cm.setMaxTotal(10);
// Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(10);

        httpClient.getConnectionManager().shutdown();
        httpClient = new DefaultHttpClient(cm);
        UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials(userName, password);
        httpClient.getCredentialsProvider().setCredentials(
                new AuthScope(server.getHost(), server.getPort()),
                usernamePasswordCredentials);

    }
}