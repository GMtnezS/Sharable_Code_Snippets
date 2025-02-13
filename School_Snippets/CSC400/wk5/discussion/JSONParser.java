package wk5.discussion;

import java.util.*;

public class JSONParser {
    // Simple method to parse JSON string into a nested Map manually
    public static Map<String, Object> parseJson(String json) {
        Map<String, Object> jsonMap = new HashMap<>();

        json = json.replaceAll("[{}\"]", "");
        String[] pairs = json.split(",");

        for (String pair : pairs) {
            String[] keyValue = pair.split(":");

            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                if (value.matches("\\d+")) {
                    jsonMap.put(key, Integer.parseInt(value));
                } else {
                    jsonMap.put(key, value);
                }
            }
        }
        return jsonMap;
    }
}
