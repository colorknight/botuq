package org.datayoo.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class InputStreamLoader {

  public static final String PREFIX_SEPARATOR = ":";

  public static final String CLASSPATH_URL_PREFIX = "classpath:";
  public static final String URL_PROTOCOL_FILE = "file";

  public static InputStream load(String location) throws IOException {
    return load(location, InputStreamLoader.class.getClassLoader());
  }

  public static InputStream load(String location, ClassLoader classLoader)
      throws IOException {
    int index = location.indexOf(PREFIX_SEPARATOR);
    if (index == -1 || index == 1) { // 为1时为盘符
      return new FileInputStream(location);
    }
    if (location.startsWith(CLASSPATH_URL_PREFIX)) {
      return classLoader.getResourceAsStream(
          location.substring(CLASSPATH_URL_PREFIX.length()));
    } else {
      URL url = new URL(location);
      if (url.getProtocol().equals(URL_PROTOCOL_FILE)) {
        return new FileInputStream(url.getPath());
      }
    }
    throw new IllegalArgumentException("Invalid location!");
  }

}
