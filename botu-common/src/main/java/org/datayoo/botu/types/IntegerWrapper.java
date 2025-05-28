package org.datayoo.botu.types;

import org.datayoo.base.types.DataTypeUtils;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2021/11/26 12:04 PM
 */
public class IntegerWrapper extends NumberWrapper {

  {
    dataType = DataTypeUtils.INTEGER_TYPE;
    bizType = dataType.getName().name();
  }

  @Override
  protected boolean isRightType(Object o) {
    return o instanceof Integer;
  }

  @Override
  protected Object convertTo(Object o, int convertFlag) {
    if (convertFlag == F_NUMBER) {
      return ((Number) o).intValue();
    } else {
      return Integer.valueOf((String) o);
    }
  }
}
