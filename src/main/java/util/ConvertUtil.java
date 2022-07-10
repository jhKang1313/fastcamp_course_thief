package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Map;

public class ConvertUtil {
    public static Map jsonToMap(String str) throws Exception{
        JsonParser parser = new JsonParser();
        JsonElement ele = parser.parse(str);
        JsonObject jsonObject = ele.getAsJsonObject().get("data").getAsJsonObject();
        Map<String, String> map = new ObjectMapper().readValue(jsonObject.toString(), Map.class);
        return map;
    }
}
