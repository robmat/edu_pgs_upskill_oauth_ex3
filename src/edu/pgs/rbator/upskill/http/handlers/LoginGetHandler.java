package edu.pgs.rbator.upskill.http.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import static edu.pgs.rbator.upskill.Main.*;
import static edu.pgs.rbator.upskill.http.server.UpskillHttpServer.GET_HTTP_METHOD;
import static edu.pgs.rbator.upskill.http.server.UpskillHttpServer.STATE;
import static edu.pgs.rbator.upskill.util.HttpUtil.redirect;
import static java.lang.String.format;
import static java.net.URLEncoder.encode;
import static java.nio.charset.StandardCharsets.UTF_8;

public class LoginGetHandler implements HttpHandler {

    public static final String AUTHORIZE_URL = "https://%s/authorize?response_type=code&%s&redirect_uri=%s&audience=%s&scope=%s";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equals(GET_HTTP_METHOD)) return;
        if (STATE.isNotLoggedIn()) {
            String location = format(AUTHORIZE_URL,
                    AUTH0_DOMAIN,
                    CLIENT_ID_QUERY_PARAM,
                    encode(CALLBACK_URL, UTF_8),
                    MANAGEMENT_API + "/",
                    encode("openid", UTF_8));
            redirect(exchange, location);
        } else {
            redirect(exchange, "/home");
        }
    }
}
