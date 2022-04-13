package edu.pgs.rbator.upskill.http.server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
import edu.pgs.rbator.upskill.http.handlers.CallbackHttpHandler;
import edu.pgs.rbator.upskill.http.handlers.HomeHttpHandler;
import edu.pgs.rbator.upskill.http.handlers.LoginHttpHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class UpskillHttpServer {
    public static final String POST_HTTP_METHOD = "POST";
    public static final String GET_HTTP_METHOD = "GET";
    public static final State STATE = new State();

    public HttpServer build() throws IOException {
        HttpServer httpServer = HttpServerProvider.provider().createHttpServer(new InetSocketAddress("localhost", 8080), 1);
        httpServer.createContext("/auth/login", new LoginHttpHandler());
        httpServer.createContext("/auth/callback", new CallbackHttpHandler());
        httpServer.createContext("/home", new HomeHttpHandler());
        return httpServer;
    }
}
