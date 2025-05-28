package org.datayoo.botu.dict;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.Validate;

import java.io.File;
import java.util.List;

public class LazyBotuDictionary implements BotuDictionary {

  protected String fileName;

  protected File file;

  protected CommonBotuDictionary botuDictionary;

  protected String name;

  public LazyBotuDictionary(String fileName) {
    Validate.notEmpty(fileName, "fileName is empty!");
    name = FilenameUtils.getBaseName(fileName);
    this.fileName = fileName;
  }

  public LazyBotuDictionary(File file) {
    Validate.notNull(file, "file is null!");
    name = FilenameUtils.getBaseName(file.getName());
    this.file = file;
  }

  @Override
  public void refresh() {
    if (botuDictionary != null)
      botuDictionary.refresh();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public synchronized String getVersion() {
    if (botuDictionary == null)
      loadBotuDictionary();
    return botuDictionary.getVersion();
  }

  @Override
  public synchronized List<ItemMetadata> getItems() {
    if (botuDictionary == null)
      loadBotuDictionary();
    return botuDictionary.getItems();
  }

  protected void loadBotuDictionary() {
    try {
      if (fileName != null)
        botuDictionary = new CommonBotuDictionary(fileName);
      else
        botuDictionary = new CommonBotuDictionary(file);
    } catch (Throwable t) {
      throw new RuntimeException(t.getMessage(), t);
    }
  }
}
