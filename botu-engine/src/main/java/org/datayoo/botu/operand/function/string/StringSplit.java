package org.datayoo.botu.operand.function.string;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

public class StringSplit extends AbstractFunction {

  public static final String FUNCTION_NAME = "strSplit";

  protected Operand field;

  protected Operand pattern;

  public StringSplit(List<Operand> parameters) {
    super(FUNCTION_NAME, 2, parameters);
    field = parameters.get(0);
    pattern = parameters.get(1);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object o = field.operate(entityMap);
    if (o == null)
      return null;
    String regex = (String) pattern.operate(entityMap);
    return o.toString().split(regex);
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object o = field.operate(entityArray);
    if (o == null)
      return null;
    String regex = (String) pattern.operate(entityArray);
    return o.toString().split(regex);
  }

}
