package edu.pgs.rbator.upskill.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class IOUtils {
    public static String toString(InputStream content) throws IOException {
        return new String(content.readAllBytes(), StandardCharsets.UTF_8);
    }

}
