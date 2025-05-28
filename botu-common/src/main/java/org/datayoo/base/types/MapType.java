package org.datayoo.base.types;

//  value is object
public class MapType extends AbstractDataType {

  {
    typeName = DataTypeName.Map;
  }

  public MapType(DataType[] parameterizedTypes) {
    super(parameterizedTypes);
    if (parameterizedTypes.length != 2)
      throw new IllegalArgumentException("Invalid parameterizedTypes!");
  }

}
