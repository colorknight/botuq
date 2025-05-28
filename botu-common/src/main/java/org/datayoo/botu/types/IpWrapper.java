package org.datayoo.botu.types;

import org.datayoo.base.biztypes.IpAddress;
import org.datayoo.base.types.DataTypeUtils;

import java.net.InetAddress;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2021/11/26 12:04 PM
 */
public class IpWrapper extends AbstractTypeWrapper {

  {
    dataType = DataTypeUtils.OBJECT_TYPE;
    bizType = "Ip";
  }

  public static final int F_LONG = 0;
  public static final int F_STRING = 1;
  public static final int F_BYTES = 2;
  public static final int F_INET_ADDR = 3;

  protected int convertFlag = -1;

  @Override
  protected boolean isRightType(Object o) {
    return o instanceof IpAddress;
  }

  @Override
  protected Object convert(Object o) {
    if (convertFlag == -1) {
      convertFlag = getConvertFlag(o);
      if (convertFlag == -1)
        return null;
    }
    if (convertFlag == F_LONG) {
      return new IpAddress((Long) o);
    } else if (convertFlag == F_STRING) {
      return new IpAddress((String) o);
    } else if (convertFlag == F_BYTES) {
      return new IpAddress((byte[]) o);
    } else {
      return new IpAddress((InetAddress) o);
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
    if (o instanceof byte[])
      return F_STRING;
    if (o instanceof InetAddress)
      return F_STRING;
    throw new UnsupportedOperationException(
        String.format("Can not convert '%s' to '%s:'!", o.getClass().getName(),
            dataType.getName(), bizType));
  }
}
