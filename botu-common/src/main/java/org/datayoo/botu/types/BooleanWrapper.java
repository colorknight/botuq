package org.datayoo.botu.types;

import org.datayoo.base.types.DataTypeUtils;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2021/11/26 12:04 PM
 */
public class BooleanWrapper extends AbstractTypeWrapper {

  {
    dataType = DataTypeUtils.BOOLEAN_TYPE;
    bizType = dataType.getName().name();
  }

  @Override
  protected boolean isRightType(Object o) {
    return o instanceof Boolean;
  }

  @Override
  protected Object convert(Object o) {
    return Boolean.valueOf((String) o);
  }

}
