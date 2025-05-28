package org.datayoo.botu;

import org.datayoo.moql.EntityMap;

import java.util.List;

public interface BotuListener {

  void onData(Object[] data);

  void onData(List<Object[]> dataList);

  void onData(EntityMap event);
}
