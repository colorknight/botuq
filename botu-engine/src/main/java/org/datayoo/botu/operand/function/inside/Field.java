package org.datayoo.botu.operand.function.inside;

import org.datayoo.botu.operand.constant.StringConstant;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

public class Field extends AbstractFunction {

  public static final String FUNCTION_NAME = "field";

  protected String field;

  public Field(List<Operand> parameters) {
    super(FUNCTION_NAME, 1, parameters);
    if (!(parameters.get(0) instanceof StringConstant)) {
      throw new IllegalArgumentException(
          "The field parameter should be a string constant in field function!");
    }
    field = (String) parameters.get(0).operate((EntityMap) null);
    this.constantReturn = false;
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    return entityMap.getEntity(field);
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    throw new UnsupportedOperationException("");
  }
}
