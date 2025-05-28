package org.datayoo.base.types;

public class LongType extends AbstractDataType {
  {
    typeName = DataTypeName.Long;
  }

  @Override
  public boolean isPrimitiveDataType() {
    return true;
  }
}
