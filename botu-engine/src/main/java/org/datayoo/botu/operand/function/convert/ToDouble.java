package org.datayoo.botu.operand.function.convert;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

public class ToDouble extends AbstractFunction {

  public static final String FUNCTION_NAME = "toDouble";

  public ToDouble(List<Operand> parameters) {
    super(FUNCTION_NAME, 1, parameters);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object o = parameters.get(0).operate(entityMap);
    if (o == null)
      return null;
    if (o instanceof Number) {
      Number n = (Number) o;
      return n.doubleValue();
    } else {
      return Double.valueOf(o.toString());
    }
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object o = parameters.get(0).operate(entityArray);
    if (o == null)
      return null;
    if (o instanceof Number) {
      Number n = (Number) o;
      return n.doubleValue();
    } else {
      return Double.valueOf(o.toString());
    }
  }

}
