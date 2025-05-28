package org.datayoo.botu.types;

import org.datayoo.base.types.DataType;

import java.util.Objects;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2021/11/26 11:51 AM
 */
public abstract class AbstractTypeWrapper implements TypeWrapper {

  protected DataType dataType;

  protected String bizType;

  protected boolean first = true;

  protected boolean isRightType = false;

  @Override
  public DataType getDataType() {
    return dataType;
  }

  @Override
  public String getBizType() {
    return bizType;
  }

  @Override
  public Object wrapper(Object o) {
    if (o == null)
      return o;
    if (first) {
      isRightType = isRightType(o);
      first = false;
    }
    if (isRightType)
      return o;
    return convert(o);
  }

  protected boolean isIgnoreString(String str) {
    if (str.length() == 0)
      return true;
    if (str.length() == 1) {
      char ch = str.charAt(0);
      if (ch == ' ' || ch == '-')
        return true;
    }
    if (str.length() == 4) {
      if (str.equalsIgnoreCase("null"))
        return true;
    }
    return false;
  }

  protected abstract boolean isRightType(Object o);

  protected abstract Object convert(Object o);

  @Override
  public int hashCode() {
    int hash = dataType.hashCode();
    if (bizType != null)
      hash += bizType.hashCode();
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TypeWrapper))
      return false;
    TypeWrapper tw = (TypeWrapper) obj;
    if (dataType != tw.getDataType())
      return false;
    return Objects.equals(bizType, tw.getBizType());
  }
}
