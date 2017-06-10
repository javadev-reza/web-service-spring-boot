package com.service.library;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Reza
 */
public class JsonUtil {
    
    public static Map<String, Object> JsonToMap(String value) {
        Map<String, Object> result = new HashMap<>();
        try {
            Gson gson = new Gson();
            result = (Map)gson.fromJson(value, Map.class);
        }
        catch (JsonSyntaxException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static String MapToJson(Map<String, Object> value) {
        String result = null;
        try {
            Gson gson = new Gson();
            result = gson.toJson(value);
        }
        catch (JsonSyntaxException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}