package org.datayoo.base.types;

public class DatetimeType extends AbstractDataType {
  {
    typeName = DataTypeName.Datetime;
  }

  @Override
  public boolean isPrimitiveDataType() {
    return true;
  }
}
