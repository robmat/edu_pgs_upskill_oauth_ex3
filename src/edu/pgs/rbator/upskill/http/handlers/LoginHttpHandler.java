package edu.pgs.rbator.upskill.http.handlers;

import com.sun.net.httpserver.HttpHandler;

import java.util.Set;

public class LoginHttpHandler extends CompositeHttpHandler {

    Set<HttpHandler> handlers = Set.of(new LoginGetHandler());

    @Override
    public Set<HttpHandler> getHandlers() {
        return handlers;
    }
}
