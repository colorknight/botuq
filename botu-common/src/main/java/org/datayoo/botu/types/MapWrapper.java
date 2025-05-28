package org.datayoo.botu.types;

import org.datayoo.base.biztypes.MacAddress;
import org.datayoo.base.types.DataTypeUtils;

import java.util.Map;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2021/11/26 12:04 PM
 */
public class MapWrapper extends AbstractTypeWrapper {

  {
    dataType = DataTypeUtils.OBJECT_TYPE;
    bizType = "Map";
  }

  protected int convertFlag = -1;

  @Override
  protected boolean isRightType(Object o) {
    return o instanceof Map;
  }

  @Override
  protected Object convert(Object o) {
    if (convertFlag == -1) {
      convertFlag = getConvertFlag(o);
      if (convertFlag == -1)
        return null;
    }
    throw new UnsupportedOperationException("");
  }

  protected int getConvertFlag(Object o) {
    throw new UnsupportedOperationException(
        String.format("Can not convert '%s' to '%s:'!", o.getClass().getName(),
            dataType.getName(), bizType));
  }
}
