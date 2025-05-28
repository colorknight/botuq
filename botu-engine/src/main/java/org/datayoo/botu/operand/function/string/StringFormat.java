package org.datayoo.botu.operand.function.string;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

public class StringFormat extends AbstractFunction {

  public static final String FUNCTION_NAME = "strFormat";

  protected Operand pattern;

  public StringFormat(List<Operand> parameters) {
    super(FUNCTION_NAME, -1, parameters);
    pattern = parameters.get(0);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object[] objs = new Object[parameters.size() - 1];
    int i = 0;
    for (Operand param : parameters) {
      if (i != 0) {
        objs[i - 1] = param.operate(entityMap);
      }
      i++;
    }
    return String.format((String) pattern.operate(entityMap), objs);
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object[] objs = new Object[parameters.size() - 1];
    int i = 0;
    for (Operand param : parameters) {
      if (i != 0) {
        objs[i - 1] = param.operate(entityArray);
      }
      i++;
    }
    return String.format((String) pattern.operate(entityArray), objs);
  }

}
