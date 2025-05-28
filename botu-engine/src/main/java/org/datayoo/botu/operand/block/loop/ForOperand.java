package org.datayoo.botu.operand.block.loop;

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
 * @date 2021/9/2 7:59 PM
 */
public class ForOperand extends AbstractCodeBlcokOperand {

  protected List<ProcessOperand> initOperands = new LinkedList();

  protected Operand condition;

  protected List<ProcessOperand> afterOperands = new LinkedList();

  protected List<ProcessOperand> processOperands = new LinkedList<ProcessOperand>();

  protected int loopTimes = 0;

  public ForOperand(List<ProcessOperand> initOperands, Operand condition,
      List<ProcessOperand> afterOperands) {
    if (initOperands != null) {
      this.initOperands = initOperands;
    }
    this.condition = condition;
    if (afterOperands != null) {
      this.afterOperands = afterOperands;
    }
  }

  @Override
  public Object operate(EntityMap stack, StateContext stateContext) {
    initForOperand(stack, stateContext);
    while (isTrue(stack)) {
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
      afterForOperand(stack, stateContext);
    }
    loopTimes = 0;
    return null;
  }

  protected void initForOperand(EntityMap stack, StateContext stateContext) {
    for (ProcessOperand operand : initOperands) {
      operand.operate(stack, stateContext);
    }
  }

  protected boolean isTrue(EntityMap stack) {
    if (condition == null)
      return true;
    return condition.booleanOperate(stack);
  }

  protected void afterForOperand(EntityMap stack, StateContext stateContext) {
    for (ProcessOperand operand : afterOperands) {
      operand.operate(stack, stateContext);
    }
  }

  public List<ProcessOperand> getInitOperands() {
    return initOperands;
  }

  public Operand getCondition() {
    return condition;
  }

  public List<ProcessOperand> getAfterOperands() {
    return afterOperands;
  }

  public List<ProcessOperand> getProcessOperands() {
    return processOperands;
  }
}
