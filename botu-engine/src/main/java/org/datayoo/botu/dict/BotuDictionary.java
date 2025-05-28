package org.datayoo.botu.dict;

import java.util.List;

public interface BotuDictionary {

  String getName();

  String getVersion();

  List<ItemMetadata> getItems();

  void refresh();

}
