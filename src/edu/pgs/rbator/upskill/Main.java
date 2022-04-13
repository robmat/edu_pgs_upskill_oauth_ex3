package edu.pgs.rbator.upskill;

import edu.pgs.rbator.upskill.http.server.UpskillHttpServer;

import java.io.IOException;

import static java.lang.String.format;
import static java.net.URLEncoder.encode;

public class Main {

    public static final String AUTH0_DOMAIN = "dev-kz68re2s.us.auth0.com";
    public static final String CLIENT_ID = "dlFg3sDFCQg3H5jIWcpEHEhN9qsGZveW";
    public static final String CLIENT_SECRET = "PB0XHACaTX99-gXeXDJ6hafRmSWnnaVpjJpWX64t3fdbbnJQVuoMXZgG3g4cF-eB";
    public static final String CALLBACK_URL = "http://kubernetes.docker.internal:8080/auth/callback";
    public static final String TOKEN_URL = format("https://%s/oauth/token", AUTH0_DOMAIN);
    public static final String CLIENT_ID_QUERY_PARAM = format("client_id=%s", CLIENT_ID);
    public static final String GRANT_TYPE = "authorization_code";
    public static final String REDIRECT_URI = "http://kubernetes.docker.internal:8080";
    public static final String MANAGEMENT_API = format("https://%s/api/v2", AUTH0_DOMAIN);
    public static final String USER_INFO_URL = MANAGEMENT_API + "/users/auth0|624e9519bf5af7006f6c2b86";

    public static void main(String[] args) throws IOException {
        new Main().start();
    }

    private void start() throws IOException {
        new UpskillHttpServer().build().start();
    }
}
