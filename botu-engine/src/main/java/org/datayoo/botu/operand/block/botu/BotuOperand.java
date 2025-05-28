package org.datayoo.botu.operand.block.botu;

import org.apache.commons.lang3.Validate;
import org.datayoo.botu.BotuConstants;
import org.datayoo.botu.operand.JumpMode;
import org.datayoo.botu.operand.ProcessOperand;
import org.datayoo.botu.operand.StateContext;
import org.datayoo.botu.operand.block.AbstractCodeBlcokOperand;
import org.datayoo.moql.EntityMap;

import java.util.LinkedList;
import java.util.List;

public class BotuOperand extends AbstractCodeBlcokOperand {

  protected String name;

  protected int maxLoopTimes = BotuConstants.MAX_LOOP_TIMES;

  protected List<ProcessOperand> operands = new LinkedList<ProcessOperand>();

  public BotuOperand(String name) {
    Validate.notEmpty(name, "name is empty!");
    this.name = name;
  }

  @Override
  public Object operate(EntityMap stack, StateContext stateContext) {
    throw new UnsupportedOperationException("");
  }

  public boolean parse(EntityMap stack) {
    StateContext stateContext = new StateContext(maxLoopTimes);
    for (ProcessOperand operand : operands) {
      operand.operate(stack, stateContext);
      if (stateContext.getJumpMode() != null
          && stateContext.getJumpMode() == JumpMode.RETURN) {
        return true;
      }
    }
    return false;
  }

  public String getName() {
    return name;
  }

  public List<ProcessOperand> getOperands() {
    return operands;
  }

  public int getMaxLoopTimes() {
    return maxLoopTimes;
  }

  public void setMaxLoopTimes(int maxLoopTimes) {
    this.maxLoopTimes = maxLoopTimes;
  }
}
