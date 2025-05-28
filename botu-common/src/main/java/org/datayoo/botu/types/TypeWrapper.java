package org.datayoo.botu.types;

import org.datayoo.base.types.DataType;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2021/11/26 10:54 AM
 */
public interface TypeWrapper {

  DataType getDataType();

  String getBizType();

  Object wrapper(Object o);
}
