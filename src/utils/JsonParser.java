package utils;

import java.util.HashMap;
import java.util.Map;

public class JsonParser {

    public static Map<String, String> parse(String json) {

        Map<String, String> map = new HashMap<>();

        json = json.trim();

        // remove { }
        json = json.substring(1, json.length() - 1);

        String[] pairs = json.split(",");

        for (String pair : pairs) {

            String[] keyValue = pair.split(":");

            String key = keyValue[0].trim().replace("\"", "");
            String value = keyValue[1].trim().replace("\"", "");

            map.put(key, value);
        }

        return map;
    }
}
