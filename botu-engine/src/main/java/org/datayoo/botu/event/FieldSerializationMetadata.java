package org.datayoo.botu.event;

import org.datayoo.base.types.DataTypeName;

import java.io.Serializable;

public class FieldSerializationMetadata implements Serializable {

  protected String name;

  protected int index;

  protected DataTypeName dataType;

  protected String bizType;

  protected String dataFormat;
  //暂未使用
  protected boolean ignore = false;

  public String getName() {
    return name;
  }

  public FieldSerializationMetadata setName(String name) {
    this.name = name;
    return this;
  }

  public int getIndex() {
    return index;
  }

  public FieldSerializationMetadata setIndex(int index) {
    this.index = index;
    return this;
  }

  public DataTypeName getDataType() {
    return dataType;
  }

  public FieldSerializationMetadata setDataType(DataTypeName dataType) {
    this.dataType = dataType;
    return this;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getDataFormat() {
    return dataFormat;
  }

  public void setDataFormat(String dataFormat) {
    this.dataFormat = dataFormat;
  }

  public boolean isIgnore() {
    return ignore;
  }

  public void setIgnore(boolean ignore) {
    this.ignore = ignore;
  }
}
