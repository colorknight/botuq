package org.datayoo.base.types;

public class IntegerType extends AbstractDataType {
  {
    typeName = DataTypeName.Integer;
  }

  @Override
  public boolean isPrimitiveDataType() {
    return true;
  }
}
