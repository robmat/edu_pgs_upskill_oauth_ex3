package edu.pgs.rbator.upskill.http.server;

import java.util.HashMap;
import java.util.Map;

import static edu.pgs.rbator.upskill.util.PatternUtil.extractJsonProperty;

public class State {
    static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    Map<String, String> state = new HashMap<>();

    public boolean isNotLoggedIn() {
        return !state.containsKey(ACCESS_TOKEN);
    }

    public void saveAccessToken(String tokenJsonResponse) {
        String propertyValue = extractJsonProperty(tokenJsonResponse, "access_token");
        state.put(ACCESS_TOKEN, propertyValue);
    }

    public String accessToken() {
        return state.get(ACCESS_TOKEN);
    }
}
