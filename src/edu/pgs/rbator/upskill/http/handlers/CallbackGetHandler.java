package edu.pgs.rbator.upskill.http.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import edu.pgs.rbator.upskill.util.IOUtils;
import edu.pgs.rbator.upskill.util.HttpUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import static edu.pgs.rbator.upskill.Main.*;
import static edu.pgs.rbator.upskill.http.server.UpskillHttpServer.*;
import static edu.pgs.rbator.upskill.util.HttpUtil.redirect;
import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;

public class CallbackGetHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equals(GET_HTTP_METHOD)) return;
        String code = HttpUtil.splitQuery(exchange.getRequestURI().getQuery()).get("code");
        String tokenJsonResponse = tokenRequest(code);
        STATE.saveAccessToken(tokenJsonResponse);
        redirect(exchange, "/home");
    }

    private String tokenRequest(String code) throws IOException {
        URL url = new URL(TOKEN_URL);

        String body = format("""
                {
                  "grant_type": "%s",
                  "client_id": "%s",
                  "client_secret": "%s",
                  "code": "%s",
                  "redirect_uri": "%s"
                }
                """, GRANT_TYPE, CLIENT_ID, CLIENT_SECRET, code, REDIRECT_URI);
        byte[] bodyBytes = body.getBytes(UTF_8);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        urlConnection.setRequestProperty("Content-Length", bodyBytes.length + "");
        urlConnection.setRequestProperty("Accept", "*/*");
        urlConnection.setRequestMethod(POST_HTTP_METHOD);
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        urlConnection.getOutputStream().write(bodyBytes);
        urlConnection.getOutputStream().flush();
        urlConnection.getOutputStream().close();
        System.out.println(url);
        System.out.println(body);
        System.out.println(urlConnection.getResponseCode());
        System.out.println(urlConnection.getResponseMessage());
        String tokenJsonResponse = "";
        System.out.println(tokenJsonResponse = Objects.nonNull(urlConnection.getErrorStream()) ?
                IOUtils.toString(urlConnection.getErrorStream()) :
                IOUtils.toString((InputStream) urlConnection.getContent()));
        return tokenJsonResponse;
    }
}
