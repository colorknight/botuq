package org.datayoo.botu.dict;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

public class BotuDictionaryTest extends TestCase {

  public void testBotuDictionary() {
    String dictFileName = "./resources/dictionaries/topsec-firewall-category.dict";

    try {
      CommonBotuDictionary dictionary = new CommonBotuDictionary(dictFileName);
      List<ItemMetadata> items = dictionary.getItems();
      int x = 1;
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
