package edu.pgs.rbator.upskill.http.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import edu.pgs.rbator.upskill.util.HttpUtil;
import edu.pgs.rbator.upskill.util.IOUtils;
import edu.pgs.rbator.upskill.util.PatternUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static edu.pgs.rbator.upskill.Main.USER_INFO_URL;
import static edu.pgs.rbator.upskill.http.server.UpskillHttpServer.GET_HTTP_METHOD;
import static edu.pgs.rbator.upskill.http.server.UpskillHttpServer.STATE;
import static edu.pgs.rbator.upskill.util.PatternUtil.extractJsonProperty;
import static java.lang.String.format;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.nio.charset.StandardCharsets.UTF_8;

public class HomeGetHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equals(GET_HTTP_METHOD)) return;
        if (STATE.isNotLoggedIn()) HttpUtil.redirect(exchange, "/auth/login");

        System.out.println(USER_INFO_URL);
        HttpURLConnection connection = (HttpURLConnection) new URL(USER_INFO_URL).openConnection();
        String bearerToken = "Bearer " + STATE.accessToken();
        connection.setRequestProperty("Authorization", bearerToken);
        connection.connect();
        System.out.println(bearerToken);
        System.out.println(connection.getResponseCode());
        System.out.println(connection.getResponseMessage());
        String userDetailsJson = Objects.nonNull(connection.getErrorStream()) ?
                IOUtils.toString(connection.getErrorStream()) :
                IOUtils.toString((InputStream) connection.getContent());
        System.out.println(userDetailsJson);
        String responseBody = response(userDetailsJson);
        byte[] responseBodyBytes = responseBody.getBytes(UTF_8);
        exchange.sendResponseHeaders(HTTP_OK, responseBodyBytes.length);
        exchange.getResponseBody().write(responseBodyBytes);
        exchange.close();
    }

    private String response(String userDetailsJson) {
        String username = extractJsonProperty(userDetailsJson, "name");
        String pictureUrl = extractJsonProperty(userDetailsJson, "picture");
        return format("""
                <html>
                    <body>
                        <div>
                            Hello %s!
                        </div>
                        <img src="%s" />
                    </body>
                </html>
                """, username, pictureUrl);
    }
}
