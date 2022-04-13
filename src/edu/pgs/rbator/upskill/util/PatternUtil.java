package edu.pgs.rbator.upskill.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {
    public static String extractJsonProperty(String jsonResponse, String jsonProperty) {
        Matcher matcher = Pattern.compile("\"" + jsonProperty + "\":\"(.*?)\"").matcher(jsonResponse);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}
