package org.datayoo.botu.types;

import org.datayoo.base.types.DataTypeUtils;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2021/11/26 12:04 PM
 */
public class StringWrapper extends AbstractTypeWrapper {

  {
    dataType = DataTypeUtils.STRING_TYPE;
    bizType = dataType.getName().name();
  }

  @Override
  protected boolean isRightType(Object o) {
    return o instanceof String;
  }

  @Override
  protected Object convert(Object o) {
    return o.toString();
  }

}
