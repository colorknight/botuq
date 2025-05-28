package org.datayoo.base.types;

public enum DataTypeName {
  Null,
  String,
  Integer,
  Long,
  Datetime,
  Float,
  Double,
  Boolean,
  // 多维数组
  Map,
  Object;

  public static boolean isNull(DataTypeName dataTypeName) {
    if (Null == dataTypeName)
      return true;
    return false;
  }

  public static boolean isNumberic(DataTypeName dateTypeName) {
    switch (dateTypeName) {
      case Integer:
      case Long:
      case Float:
      case Double:
        return true;
    }
    return false;
  }

  public static boolean isInteger(DataTypeName dateTypeName) {
    switch (dateTypeName) {
      case Integer:
      case Long:
        return true;
    }
    return false;
  }

  public static boolean isFloat(DataTypeName dateTypeName) {
    switch (dateTypeName) {
      case Float:
      case Double:
        return true;
    }
    return false;
  }

  public static boolean isPrimative(DataTypeName dateTypeName) {
    switch (dateTypeName) {
      case Null:
      case String:
      case Integer:
      case Long:
      case Datetime:
      case Float:
      case Double:
      case Boolean:
        return true;
    }
    return false;
  }

  public static boolean isCollection(DataTypeName dateTypeName) {
    switch (dateTypeName) {
      case Map:
        return true;
    }
    return false;
  }

}
