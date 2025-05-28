package org.datayoo.botu.event;

import com.google.gson.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.Validate;
import org.datayoo.base.types.DataTypeName;
import org.datayoo.util.InputStreamLoader;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SerializationSchema {

  protected String name;

  protected int cursor = 0;

  protected List<FieldSerializationMetadata> metadataList = new LinkedList<FieldSerializationMetadata>();

  protected Map<String, FieldSerializationMetadata> metadataMap = new HashMap<String, FieldSerializationMetadata>();

  public SerializationSchema(String fileName) throws IOException {
    Validate.notEmpty(fileName, "fileName is empty!");
    name = FilenameUtils.getBaseName(fileName);
    JsonParser parser = new JsonParser();
    InputStreamReader reader = new InputStreamReader(
        InputStreamLoader.load(fileName));
    try {
      JsonElement jsonElement = parser.parse(reader);
      loadSchema(jsonElement);
    } finally {
      reader.close();
    }
  }

  public SerializationSchema(File file) throws IOException {
    Validate.notNull(file, "file is null!");
    name = FilenameUtils.getBaseName(file.getName());
    JsonParser parser = new JsonParser();
    FileReader fileReader = new FileReader(file);
    try {
      JsonElement jsonElement = parser.parse(fileReader);
      loadSchema(jsonElement);
    } finally {
      fileReader.close();
    }
  }

  public SerializationSchema(String name, InputStream inputStream)
      throws IOException {
    Validate.notEmpty(name, "name is empty!");
    Validate.notNull(inputStream, "inputStream is null!");
    this.name = name;
    JsonParser parser = new JsonParser();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    try {
      JsonElement jsonElement = parser.parse(inputStreamReader);
      loadSchema(jsonElement);
    } finally {
      inputStreamReader.close();
    }
  }

  public SerializationSchema(String name, String jsonSchema) {
    Validate.notEmpty(name, "name is empty!");
    Validate.notEmpty(jsonSchema, "jsonSchema is empty!");

    this.name = name;
    JsonParser parser = new JsonParser();
    JsonElement jsonElement = parser.parse(jsonSchema);
    loadSchema(jsonElement);
  }

  public SerializationSchema(String name,
      List<FieldSerializationMetadata> fieldMetadatas) {
    Validate.notEmpty(name, "name is empty!");
    Validate.notEmpty(fieldMetadatas, "fieldMetadatas is empty!");

    this.name = name;
    for (FieldSerializationMetadata fieldMetadata : fieldMetadatas) {
      metadataList.add(fieldMetadata);
      metadataMap.put(fieldMetadata.getName(), fieldMetadata);
    }
  }

  protected void loadSchema(JsonElement jsonElement) {
    if (!(jsonElement instanceof JsonObject)) {
      throw new IllegalArgumentException("Invalid schema format!");
    }
    JsonObject jsonObject = (JsonObject) jsonElement;
    loadJsonObject(null, jsonObject);
  }

  protected void loadJsonObject(String path, JsonObject jsonObject) {
    for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
      String tmpPath = entry.getKey();
      if (path != null) {
        tmpPath = String.format("%s.%s", path, entry.getKey());
      }
      if (entry.getValue() instanceof JsonObject) {
        loadJsonObject(tmpPath, (JsonObject) entry.getValue());
      } else if (entry.getValue() instanceof JsonArray) {
        JsonArray ja = (JsonArray) entry.getValue();
        FieldSerializationMetadata metadata = createFieldSerializationMetadata(
            tmpPath, ja);
        metadataList.add(metadata);
        metadataMap.put(tmpPath, metadata);
      } else {
        throw new IllegalArgumentException(
            String.format("Invalid schema format at '%s'!", tmpPath));
      }
    }
  }

  protected FieldSerializationMetadata createFieldSerializationMetadata(
      String name, JsonArray jsonArray) {
    FieldSerializationMetadata metadata = new FieldSerializationMetadata();
    metadata.setName(name);
    metadata.setIndex(cursor++);
    DataTypeName dataType = DataTypeName.valueOf(
        jsonArray.get(0).getAsString());
    metadata.setDataType(dataType);
    JsonElement je;
    if (jsonArray.size() > 1) {
      je = jsonArray.get(1);
      if (!(je instanceof JsonNull)) {
        metadata.setBizType(je.getAsString());
      }
      if (jsonArray.size() > 2) {
        je = jsonArray.get(2);
        if (!(je instanceof JsonNull)) {
          metadata.setDataFormat(je.getAsString());
        }
        if (jsonArray.size() > 3) {
          je = jsonArray.get(3);
          if (!(je instanceof JsonNull)) {
            metadata.setIgnore(je.getAsBoolean());
          }
        }
      }
    }
    if (metadata.getBizType() == null) {
      metadata.setBizType(metadata.getDataType().name());
    }
    return metadata;
  }

  public String getName() {
    return name;
  }

  public List<FieldSerializationMetadata> getMetadataList() {
    return metadataList;
  }

  public String[] getFieldNames() {
    String[] fieldNames = new String[metadataList.size()];
    int i = 0;
    for (FieldSerializationMetadata fieldSerializationMetadata : metadataList) {
      fieldNames[i++] = fieldSerializationMetadata.getName();
    }
    return fieldNames;
  }

  public Map<String, FieldSerializationMetadata> getMetadataMap() {
    return metadataMap;
  }

  public FieldSerializationMetadata getFieldSerializationMetadata(
      String field) {
    return metadataMap.get(field);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof SerializationSchema)) {
      return false;
    }
    SerializationSchema schema = (SerializationSchema) obj;
    return name.equals(schema.name);
  }
}
