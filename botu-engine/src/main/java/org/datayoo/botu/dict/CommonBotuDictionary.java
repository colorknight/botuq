package org.datayoo.botu.dict;

import com.google.gson.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.Validate;
import org.datayoo.botu.exception.BotuRuntimeException;
import org.datayoo.util.InputStreamLoader;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CommonBotuDictionary implements BotuDictionary, Serializable {

  public static final String DICT_VERSION = "version";

  protected String fileName;

  protected String name;

  protected String version = "";

  protected List<ItemMetadata> items = new LinkedList<ItemMetadata>();

  public CommonBotuDictionary(String fileName) throws IOException {
    Validate.notEmpty(fileName, "fileName is empty!");
    name = FilenameUtils.getBaseName(fileName);
    this.fileName = fileName;
    load();
  }

  public CommonBotuDictionary(File file) throws IOException {
    this(file.getCanonicalPath());
  }

  public CommonBotuDictionary(String name, InputStream inputStream)
      throws IOException {
    Validate.notEmpty(name, "name is empty!");
    Validate.notNull(inputStream, "inputStream is null!");
    this.name = name;
    load(inputStream);
  }

  protected void load() throws IOException {
    if (fileName == null)
      return;
    load(InputStreamLoader.load(fileName));
  }

  protected void load(InputStream inputStream) throws IOException {
    InputStreamReader reader = new InputStreamReader(inputStream);
    JsonParser parser = new JsonParser();
    try {
      JsonElement jsonElement = parser.parse(reader);
      parseDictionary((JsonObject) jsonElement);
    } finally {
      if (reader != null)
        reader.close();
    }
  }

  public void refresh() {
    try {
      load();
    } catch (IOException e) {
      throw new BotuRuntimeException(
          String.format("Load dictionary '%s' failed!", name));
    }
  }

  protected void parseDictionary(JsonObject root) {
    version = root.get("version").getAsString();
    JsonArray ja = root.getAsJsonArray("items");
    parseItems(ja);
  }

  protected void parseItems(JsonArray jsonArray) {
    for (int i = 0; i < jsonArray.size(); i++) {
      if (jsonArray.get(i) instanceof JsonNull)
        continue;
      JsonObject jo = (JsonObject) jsonArray.get(i);
      JsonArray ka = jo.getAsJsonArray("keys");
      if (ka.size() == 0)
        throw new IllegalArgumentException("keys field is empty!");
      JsonPrimitive vp = jo.getAsJsonPrimitive("value");
      ItemMetadata im = createItemMetadata(ka, vp);
      items.add(im);
    }
  }

  protected ItemMetadata createItemMetadata(JsonArray ka, JsonPrimitive vp) {
    Object[] keys = new Object[ka.size()];
    for (int i = 0; i < ka.size(); i++) {
      JsonPrimitive jp = (JsonPrimitive) ka.get(i);
      keys[i] = getValue(jp);
    }
    return new ItemMetadata(keys, getValue(vp));
  }

  protected Object getValue(JsonPrimitive jp) {
    if (jp == null) {
      return null;
    }
    if (jp.isString()) {
      return jp.getAsString();
    } else if (jp.isNumber()) {
      Number number = jp.getAsNumber();
      double d = number.doubleValue();
      //判断是否是浮点数
      long mod = (long) (d * 1000000) % 1000000;
      if (mod > 0)
        return d;
      long l = number.longValue();
      //判断是否是整数
      if (l > Integer.MAX_VALUE || l < Integer.MIN_VALUE)
        return l;
      return number.intValue();
    } else if (jp.isBoolean()) {
      return jp.getAsBoolean();
    }
    throw new IllegalArgumentException("Invalid json primitive!");
  }

  public String getName() {
    return name;
  }

  public String getVersion() {
    return version;
  }

  public List<ItemMetadata> getItems() {
    return items;
  }

  public void setItems(List<ItemMetadata> items) {
    if (items != null)
      this.items = items;
  }

}
