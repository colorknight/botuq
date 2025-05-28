package org.datayoo.botu.types;

import org.datayoo.base.types.DataTypeUtils;

import java.util.Date;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2021/11/26 12:04 PM
 */
public class DatetimeWrapper extends AbstractTypeWrapper {

  {
    dataType = DataTypeUtils.TIMESTAMP_TYPE;
    bizType = dataType.getName().name();
  }

  public static final int F_LONG = 0;
  public static final int F_STRING = 1;

  protected int convertFlag = -1;

  @Override
  protected boolean isRightType(Object o) {
    return o instanceof Date;
  }

  @Override
  protected Object convert(Object o) {
    if (convertFlag == -1) {
      convertFlag = getConvertFlag(o);
      if (convertFlag == -1)
        return null;
    }
    if (convertFlag == F_LONG) {
      return new Date((Long) o);
    } else {
      return DataTypeUtils.createDatetime((String) o, null);
    }
  }

  protected int getConvertFlag(Object o) {
    if (o instanceof Long)
      return F_LONG;
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
