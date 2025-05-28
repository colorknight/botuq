package org.datayoo.base.types;

public class BooleanType extends AbstractDataType {
  {
    typeName = DataTypeName.Boolean;
  }

  @Override
  public boolean isPrimitiveDataType() {
    return true;
  }
}
