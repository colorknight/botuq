package org.datayoo.util;

import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.function.BiConsumer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassResoureLoader {
  protected ClassLoader classLoader;

  public ClassResoureLoader() {
    this.classLoader = this.getClass().getClassLoader();
  }

  public ClassResoureLoader(ClassLoader classLoader) {
    Validate.notNull(classLoader, "classLoader is null!");
    this.classLoader = classLoader;
  }

  public void loadResources(String path,
      BiConsumer<JarFile, JarEntry> predicate) throws IOException {
    Validate.notEmpty(path, "path is empty!");

    URL dirUrl = classLoader.getResource(path);
    if (dirUrl == null)
      return;
    // JAR 文件中的目录
    String jarPath = dirUrl.getPath()
        .substring(0, dirUrl.getPath().indexOf("!"));
    int index = jarPath.indexOf(":");
    if (index != -1) {
      jarPath = jarPath.substring(index + 1);
    }
    JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
    Enumeration<JarEntry> entries = jar.entries();
    while (entries.hasMoreElements()) {
      JarEntry entry = entries.nextElement();
      if (entry.getName().startsWith(path) && !entry.isDirectory()) {
        predicate.accept(jar, entry);
      }
    }
  }
}
