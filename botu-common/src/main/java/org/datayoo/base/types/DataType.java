package org.datayoo.base.types;

import java.io.Serializable;

public interface DataType extends Serializable {
  // 范型参数
  static final char BEGIN_P_ENCLOSURE = '<';

  static final char END_P_ENCLOSURE = '>';

  static final char BEGIN_N_ENCLOSURE = '[';

  static final char END_N_ENCLOSURE = ']';
  //
  static final char COMMA_SEPARATOR = ',';

  DataTypeName getName();

  DataType[] getParameterizedTypes();

  String getType();

  boolean isPrimitiveDataType();

}