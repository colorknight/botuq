package org.datayoo.botu.operand.function.convert;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

public class ToInt extends AbstractFunction {

  public static final String FUNCTION_NAME = "toInt";

  protected int radix = 10;

  public ToInt(List<Operand> parameters) {
    super(FUNCTION_NAME, -1, parameters);
    if (parameters.size() > 2) {
      throw new IllegalArgumentException(
          "The function format is 'toInt(field[, radix)'");
    }
    if (parameters.size() == 2) {
      radix = (int) parameters.get(1).operate((EntityMap) null);
    }
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object o = parameters.get(0).operate(entityMap);
    if (o == null)
      return null;
    if (o instanceof Number) {
      Number n = (Number) o;
      return n.intValue();
    } else {
      return Integer.valueOf(o.toString(), radix);
    }
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object o = parameters.get(0).operate(entityArray);
    if (o == null)
      return null;
    if (o instanceof Number) {
      Number n = (Number) o;
      return n.intValue();
    } else {
      return Integer.valueOf(o.toString(), radix);
    }
  }

}
