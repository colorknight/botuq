package org.datayoo.botu.operand.function.time;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.Date;
import java.util.List;

public class Now extends AbstractFunction {

  public static final String FUNCTION_NAME = "now";

  public Now(List<Operand> parameters) {
    super(FUNCTION_NAME, 0, parameters);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    return new Date();
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    return new Date();
  }

}
