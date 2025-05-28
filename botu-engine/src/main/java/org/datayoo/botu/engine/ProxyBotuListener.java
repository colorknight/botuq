package org.datayoo.botu.engine;

import org.apache.commons.lang3.Validate;
import org.datayoo.botu.BotuListener;
import org.datayoo.moql.EntityMap;

import java.util.LinkedList;
import java.util.List;

public class ProxyBotuListener implements BotuListener {

  protected List<BotuListener> botuListeners = new LinkedList<>();

  @Override
  public void onData(Object[] event) {
    for (BotuListener botuListener : botuListeners) {
      botuListener.onData(event);
    }
  }

  @Override
  public void onData(List<Object[]> events) {
    for (BotuListener botuListener : botuListeners) {
      botuListener.onData(events);
    }
  }

  @Override
  public void onData(EntityMap event) {
    for (BotuListener botuListener : botuListeners) {
      botuListener.onData(event);
    }
  }

  public void addBotuListener(BotuListener botuListener) {
    Validate.notNull(botuListener, "botuListener is null!", new Object[0]);
    this.botuListeners.add(botuListener);
  }

  public void removeBotuListener(BotuListener botuListener) {
    Validate.notNull(botuListener, "botuListener is null!", new Object[0]);
    this.botuListeners.remove(botuListener);
  }
}
