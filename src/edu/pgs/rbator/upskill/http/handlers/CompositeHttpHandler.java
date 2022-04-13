package edu.pgs.rbator.upskill.http.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Set;

public abstract class CompositeHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        for (HttpHandler handler : getHandlers()) {
            handler.handle(exchange);
        }
    }

    protected abstract Set<HttpHandler> getHandlers();
}
