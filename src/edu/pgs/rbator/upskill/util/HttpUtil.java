package edu.pgs.rbator.upskill.util;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_MOVED_PERM;
import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpUtil {
    public static Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), UTF_8), URLDecoder.decode(pair.substring(idx + 1), UTF_8));
        }
        return query_pairs;
    }
    public static void redirect(HttpExchange exchange, String location) throws IOException {
        exchange.getResponseHeaders().add("Location", location);
        exchange.sendResponseHeaders(HTTP_MOVED_PERM, 0);
        exchange.close();
        System.out.println("Redirected to: " + location);
    }
}
