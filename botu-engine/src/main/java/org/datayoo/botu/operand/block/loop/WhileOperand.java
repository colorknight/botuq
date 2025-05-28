package org.datayoo.botu.operand.block.loop;

import org.apache.commons.lang3.Validate;
import org.datayoo.botu.operand.JumpMode;
import org.datayoo.botu.operand.ProcessOperand;
import org.datayoo.botu.operand.StateContext;
import org.datayoo.botu.operand.block.AbstractCodeBlcokOperand;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;

import java.util.LinkedList;
import java.util.List;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2021/9/2 8:00 PM
 */
public class WhileOperand extends AbstractCodeBlcokOperand {

  protected Operand condition;

  protected List<ProcessOperand> processOperands = new LinkedList<ProcessOperand>();

  protected int loopTimes = 0;

  public WhileOperand(Operand condition) {
    Validate.notNull(condition, "condition is null!");
    this.condition = condition;
  }

  @Override
  public Object operate(EntityMap stack, StateContext stateContext) {
    while (condition.booleanOperate(stack)) {
      operate(stack, this.processOperands, stateContext);
      if (stateContext.getJumpMode() != null) {
        if (stateContext.getJumpMode() == JumpMode.BREAK) {
          stateContext.clear();
          break;
        } else if (stateContext.getJumpMode() == JumpMode.CONTINUE) {
          stateContext.clear();
        } else {
          loopTimes = 0;
          return null;
        }
      }
      if (loopTimes++ >= stateContext.getMaxLoopTimes()) {
        throw new IllegalStateException(
            String.format("loopTimes exceed the max times %d!",
                stateContext.getMaxLoopTimes()));
      }
    }
    loopTimes = 0;
    return null;
  }

  public Operand getCondition() {
    return condition;
  }

  public List<ProcessOperand> getProcessOperands() {
    return processOperands;
  }

  public void setProcessOperands(List<ProcessOperand> processOperands) {
    this.processOperands = processOperands;
  }
}
