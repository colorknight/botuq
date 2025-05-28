package org.datayoo.base.types;

public class NullType extends AbstractDataType {
  {
    typeName = DataTypeName.Null;
  }

  @Override
  public boolean isPrimitiveDataType() {
    return true;
  }
}
