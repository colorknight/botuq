package org.datayoo.botu.types;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2021/11/26 12:19 PM
 */
public abstract class NumberWrapper extends AbstractTypeWrapper {

  public static final int F_NUMBER = 0;
  public static final int F_STRING = 1;

  protected int convertFlag = -1;

  @Override
  protected Object convert(Object o) {
    if (convertFlag == -1) {
      convertFlag = getConvertFlag(o);
      if (convertFlag == -1)
        return null;
    }
    return convertTo(o, convertFlag);
  }

  protected abstract Object convertTo(Object o, int convertFlag);

  protected int getConvertFlag(Object o) {
    if (o instanceof Number)
      return F_NUMBER;
    if (o instanceof String) {
      if (isIgnoreString((String) o))
        return -1;
      return F_STRING;
    }
    throw new UnsupportedOperationException(
        String.format("Can not convert '%s' to '%s'!", o.getClass().getName(),
            dataType.getName()));
  }
}
