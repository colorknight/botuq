package org.datayoo.botu.operand;

import org.apache.commons.lang3.Validate;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;

public class ProcessFunctonOperand extends AbstractProcessOperand {

  protected Operand function;

  public ProcessFunctonOperand(Operand function) {
    Validate.notNull(function, "function is null!");
    this.function = function;
  }

  @Override
  public Object operate(EntityMap entityMap, StateContext stateContext) {
    return function.operate(entityMap);
  }

  public Operand getFunction() {
    return function;
  }
}
