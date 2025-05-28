package org.datayoo.botu.operand.function;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Length extends AbstractFunction {

  public static final String FUNCTION_NAME = "len";

  protected Operand field;

  public Length(List<Operand> parameters) {
    super(FUNCTION_NAME, 1, parameters);
    field = parameters.get(0);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object o = field.operate(entityMap);
    if (o == null)
      return null;
    if (o instanceof String) {
      return ((String) o).length();
    } else if (o instanceof Collection) {
      return ((Collection) o).size();
    } else if (o.getClass().isArray()) {
      return Array.getLength(o);
    } else if (o instanceof Map) {
      return ((Map) o).size();
    }
    throw new UnsupportedOperationException(
        String.format("Unsupport get length from class %s",
            o.getClass().getName()));
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object o = field.operate(entityArray);
    if (o == null)
      return null;
    if (o instanceof String) {
      return ((String) o).length();
    } else if (o instanceof Collection) {
      return ((Collection) o).size();
    } else if (o.getClass().isArray()) {
      return Array.getLength(o);
    } else if (o instanceof Map) {
      return ((Map) o).size();
    }
    throw new UnsupportedOperationException(
        String.format("Unsupport get length from class %s",
            o.getClass().getName()));
  }

}
