package org.example;
import java.util.Map;

public abstract class AbstractTest {
    private static final String APP_ID = "cb51431559cc1cb6b8105606a5a2caf3";
    private static final String ENDPOINT_URL = "http://api.openweathermap.org/data/2.5/weather";

    protected static String buildUrl(Map<String, String> parameters, boolean withId) {
        StringBuilder urlBuilder = new StringBuilder(ENDPOINT_URL + (withId ? ("?appid=" + APP_ID) : ""));
        boolean needsQ = !withId;
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            urlBuilder.append(needsQ ? "?" : "&").append(entry.getKey()).append("=").append(entry.getValue());
            needsQ = false;
        }
        return urlBuilder.toString();
    }

    protected static String buildUrl(Map<String, String> parameters) {
        return buildUrl(parameters, true);
    }
}
