package org.datayoo.util;

import com.google.gson.*;

import java.util.HashMap;
import java.util.Map;

public abstract class JsonConvertor {

  public static Object fromJson(String json) {
    return fromJson(JsonParser.parseString(json));
  }

  public static Object fromJson(JsonElement jsonElement) {
    return fromJsonElement(jsonElement);
  }

  public static Object[] fromJsonArray(JsonArray jsonArray) {
    Object[] ary = new Object[jsonArray.size()];
    for (int i = 0; i < ary.length; i++) {
      ary[i] = fromJsonElement(jsonArray.get(i));
    }
    return ary;
  }

  protected static Object fromJsonElement(JsonElement jsonElement) {
    if (jsonElement instanceof JsonPrimitive)
      return getJsonValue((JsonPrimitive) jsonElement);
    else if (jsonElement instanceof JsonObject) {
      return fromJsonObject((JsonObject) jsonElement);
    } else if (jsonElement instanceof JsonArray) {
      return fromJsonArray((JsonArray) jsonElement);
    } else {
      return null;
    }
  }

  public static Object fromJsonObject(JsonObject jsonObject) {
    Map<String, Object> dataMap = new HashMap<String, Object>();
    for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
      dataMap.put(entry.getKey(), fromJsonElement(entry.getValue()));
    }
    return dataMap;
  }

  public static Object getJsonValue(JsonPrimitive jsonPrimitive) {
    if (jsonPrimitive.isNumber()) {
      String number = jsonPrimitive.getAsString();
      int index = number.indexOf('.');
      if (index == -1)
        return jsonPrimitive.getAsLong();
      else
        return jsonPrimitive.getAsDouble();
    } else if (jsonPrimitive.isBoolean()) {
      return jsonPrimitive.getAsBoolean();
    } else {
      return jsonPrimitive.getAsString();
    }
  }
}
