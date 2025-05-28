package org.datayoo.botu.dict;

import org.apache.commons.lang3.Validate;

import java.io.Serializable;

public class ItemMetadata implements Serializable {

  protected Object[] keys;

  protected Object value;

  public ItemMetadata(Object[] keys, Object value) {
    Validate.notEmpty(keys, "keys is empty!");
    Validate.notNull(value, "value is null");

    this.keys = keys;
    this.value = value;
  }

  public Object[] getKeys() {
    return keys;
  }

  public Object getValue() {
    return value;
  }
}
