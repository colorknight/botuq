package org.datayoo.botu.dict;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.Validate;
import org.datayoo.util.ClassResoureLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BotuDictionaryLoader {

  public static List<BotuDictionary> loadFromResource(String path,
      ClassLoader classLoader) {
    List<BotuDictionary> botuDictionaries = new LinkedList<>();
    ClassResoureLoader classResoureLoader = new ClassResoureLoader(classLoader);
    try {
      classResoureLoader.loadResources(path,
          new BiConsumer<JarFile, JarEntry>() {
            @Override
            public void accept(JarFile jarFile, JarEntry jarEntry) {
              try {
                botuDictionaries.add(new CommonBotuDictionary(
                    FilenameUtils.getBaseName(jarEntry.getName()),
                    jarFile.getInputStream(jarEntry)));
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            }
          });
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return botuDictionaries;
  }

  public static List<BotuDictionary> loadFromDir(String dirPath,
      String fileNamePattern) throws IOException {
    Validate.notEmpty(dirPath, "dirPath is empty!");
    Validate.notEmpty(fileNamePattern, "fileNamePattern is empty!");
    File dir = new File(dirPath);
    Pattern pattern = Pattern.compile(fileNamePattern);
    List<BotuDictionary> dictionaries = new LinkedList<BotuDictionary>();
    loadFromDir(dir, pattern, dictionaries);
    return dictionaries;
  }

  protected static void loadFromDir(File dir, Pattern fileNamePattern,
      List<BotuDictionary> dictionaries) throws IOException {
    File[] files = dir.listFiles();
    if (files == null) {
      throw new FileNotFoundException(
          String.format("Invalid file path '%s'", dir.getAbsolutePath()));
    }
    for (int i = 0; i < files.length; i++) {
      if (files[i].isDirectory()) {
        loadFromDir(files[i], fileNamePattern, dictionaries);
      } else if (files[i].isFile()) {
        Matcher matcher = fileNamePattern.matcher(files[i].getAbsolutePath());
        if (matcher != null && matcher.find()) {
          LazyBotuDictionary dict = new LazyBotuDictionary(files[i]);
          dictionaries.add(dict);
        }
      }
    }
  }
}
