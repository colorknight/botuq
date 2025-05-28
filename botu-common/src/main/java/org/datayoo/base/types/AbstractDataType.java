package org.datayoo.base.types;

import org.apache.commons.lang3.Validate;

import java.util.Objects;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/9 11:26 AM
 */
public class AbstractDataType implements DataType {

  protected DataTypeName typeName;

  protected DataType[] parameterizedTypes;

  protected transient String typeString;

  public AbstractDataType() {
  }

  public AbstractDataType(DataType[] parameterizedTypes) {
    Validate.notEmpty(parameterizedTypes, "parameterizedTypes is empty!");
    this.parameterizedTypes = parameterizedTypes;
  }

  @Override
  public DataTypeName getName() {
    return typeName;
  }

  @Override
  public DataType[] getParameterizedTypes() {
    return parameterizedTypes;
  }

  @Override
  public String getType() {
    if (typeString == null) {
      typeString = type2String();
    }
    return typeString;
  }

  @Override
  public boolean isPrimitiveDataType() {
    return false;
  }

  @Override
  public int hashCode() {
    if (typeString == null) {
      typeString = type2String();
    }
    return typeString.hashCode();
  }

  @Override
  public String toString() {
    if (typeString == null) {
      typeString = type2String();
    }
    return typeString;
  }

  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (obj instanceof DataType) {
      DataType objType = (DataType) obj;
      if (!objType.getName().equals(typeName))
        return false;
      if (!Objects.deepEquals(objType.getParameterizedTypes(),
          parameterizedTypes))
        return false;
    }
    return true;
  }

  protected String type2String() {
    StringBuilder sbud = new StringBuilder();
    sbud.append(typeName.name());
    if (parameterizedTypes != null) {
      parameterizedTypes2String(sbud);
    }
    return sbud.toString();
  }

  protected void parameterizedTypes2String(StringBuilder sbud) {
    sbud.append(BEGIN_P_ENCLOSURE);
    for (int i = 0; i < parameterizedTypes.length; i++) {
      if (i != 0) {
        sbud.append(COMMA_SEPARATOR);
      }
      sbud.append(parameterizedTypes[i].getType());
    }
    sbud.append(END_P_ENCLOSURE);
  }
}
