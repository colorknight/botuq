package org.datayoo.base.types;

public class StringType extends AbstractDataType {
  {
    typeName = DataTypeName.String;
  }

  @Override
  public boolean isPrimitiveDataType() {
    return true;
  }
}
