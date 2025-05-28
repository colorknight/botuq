package org.datayoo.botu.operand.block.condition;

import org.apache.commons.lang3.Validate;
import org.datayoo.botu.operand.ProcessOperand;
import org.datayoo.botu.operand.StateContext;
import org.datayoo.botu.operand.block.AbstractCodeBlcokOperand;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;

import java.util.LinkedList;
import java.util.List;

public class ConditionOperand extends AbstractCodeBlcokOperand {

  protected Operand condition;

  protected List<ProcessOperand> processOperands = new LinkedList<ProcessOperand>();

  public ConditionOperand() {
  }

  public ConditionOperand(Operand condition) {
    Validate.notNull(condition, "condition is null!");
    this.condition = condition;
  }

  @Override
  public Object operate(EntityMap entityMap, StateContext stateContext) {
    if (condition == null || condition.booleanOperate(entityMap)) {
      operate(entityMap, processOperands, stateContext);
      return true;
    }
    return false;
  }

  public List<ProcessOperand> getProcessOperands() {
    return processOperands;
  }

  public Operand getCondition() {
    return condition;
  }
}
