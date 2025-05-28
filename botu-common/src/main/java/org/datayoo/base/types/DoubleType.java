package org.datayoo.base.types;

public class DoubleType extends AbstractDataType {
  {
    typeName = DataTypeName.Double;
  }

  @Override
  public boolean isPrimitiveDataType() {
    return true;
  }

}
