package org.datayoo.botu.types;

import org.datayoo.base.biztypes.MacAddress;
import org.datayoo.base.types.DataTypeUtils;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2021/11/26 12:04 PM
 */
public class MacWrapper extends AbstractTypeWrapper {

  {
    dataType = DataTypeUtils.OBJECT_TYPE;
    bizType = "Mac";
  }

  public static final int F_STRING = 1;
  public static final int F_BYTES = 2;

  protected int convertFlag = -1;

  @Override
  protected boolean isRightType(Object o) {
    return o instanceof MacAddress;
  }

  @Override
  protected Object convert(Object o) {
    if (convertFlag == -1) {
      convertFlag = getConvertFlag(o);
      if (convertFlag == -1)
        return null;
    }
    if (convertFlag == F_STRING) {
      return new MacAddress((String) o);
    } else {
      return new MacAddress((byte[]) o);
    }
  }

  protected int getConvertFlag(Object o) {
    if (o instanceof String) {
      if (isIgnoreString((String) o))
        return -1;
      return F_STRING;
    }
    if (o instanceof byte[])
      return F_BYTES;
    throw new UnsupportedOperationException(
        String.format("Can not convert '%s' to '%s:'!", o.getClass().getName(),
            dataType.getName(), bizType));
  }
}
