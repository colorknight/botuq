package org.datayoo.botu.operand.block.condition;

import org.datayoo.botu.operand.ProcessOperand;
import org.datayoo.botu.operand.StateContext;
import org.datayoo.botu.operand.block.AbstractCodeBlcokOperand;
import org.datayoo.moql.EntityMap;

import java.util.LinkedList;
import java.util.List;

public class IfOperand extends AbstractCodeBlcokOperand {

  protected static final int RESORT_TRIGGER = 1000;

  protected int count = 0;

  protected List<ProcessOperand> conditionOperands = new LinkedList<>();

  protected ProcessOperand elseOperand;

  public IfOperand(ConditionOperand conditionOperand) {
    addConditionOperand(conditionOperand);
    this.source = conditionOperand.getSource();
  }

  protected IfOperand() {
  }

  @Override
  public Object operate(EntityMap stack, StateContext stateContext) {
    synchronized (conditionOperands) {
      for (ProcessOperand operand : conditionOperands) {
        boolean ret = (boolean) operand.operate(stack, stateContext);
        if (ret) {
          return null;
        }
      }
    }
    if (elseOperand != null) {
      elseOperand.operate(stack, stateContext);
    }
    return null;
  }

  public void addConditionOperand(ConditionOperand conditionOperand) {
    if (conditionOperand.getCondition() != null) {
      conditionOperands.add(conditionOperand);
    } else {
      elseOperand = conditionOperand;
    }
  }

}
