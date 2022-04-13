package edu.pgs.rbator.upskill.http.handlers;

import com.sun.net.httpserver.HttpHandler;

import java.util.Set;

public class HomeHttpHandler extends CompositeHttpHandler {

    Set<HttpHandler> handlers = Set.of(new HomeGetHandler());

    @Override
    public Set<HttpHandler> getHandlers() {
        return handlers;
    }
}
