package org.datayoo.botu.event;

import org.apache.commons.lang3.Validate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SerializationSchemaLoader {

  protected static final String SCHEMA_PATTERN = ".*\\.ss";

  public static List<SerializationSchema> loadFromDir(String dirPath)
      throws IOException {
    return loadFromDir(dirPath, SCHEMA_PATTERN);
  }

  public static List<SerializationSchema> loadFromDir(String dirPath,
      String fileNamePattern) throws IOException {
    Validate.notEmpty(dirPath, "dirPath is empty!");
    Validate.notEmpty(fileNamePattern, "fileNamePattern is empty!");
    File dir = new File(dirPath);
    Pattern pattern = Pattern.compile(fileNamePattern);
    List<SerializationSchema> schemas = new LinkedList<SerializationSchema>();
    loadFromDir(dir, pattern, schemas);
    return schemas;
  }

  protected static void loadFromDir(File dir, Pattern fileNamePattern,
      List<SerializationSchema> schemas) throws IOException {
    File[] files = dir.listFiles();
    if (files == null) {
      throw new FileNotFoundException(
          String.format("Invalid file path '%s'", dir.getAbsolutePath()));
    }
    for (int i = 0; i < files.length; i++) {
      if (files[i].isDirectory()) {
        loadFromDir(files[i], fileNamePattern, schemas);
      } else if (files[i].isFile()) {
        Matcher matcher = fileNamePattern.matcher(files[i].getAbsolutePath());
        if (matcher != null && matcher.find()) {
          SerializationSchema schema = new SerializationSchema(files[i]);
          schemas.add(schema);
        }
      }
    }
  }
}
