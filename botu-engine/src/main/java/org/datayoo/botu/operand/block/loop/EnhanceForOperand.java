package org.datayoo.botu.operand.block.loop;

import org.apache.commons.lang3.Validate;
import org.datayoo.botu.operand.JumpMode;
import org.datayoo.botu.operand.ProcessOperand;
import org.datayoo.botu.operand.StateContext;
import org.datayoo.botu.operand.block.AbstractCodeBlcokOperand;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2021/9/2 7:59 PM
 */
public class EnhanceForOperand extends AbstractCodeBlcokOperand {

  protected String varName;

  protected Operand array;

  protected List<ProcessOperand> processOperands = new LinkedList<ProcessOperand>();

  protected int loopTimes = 0;

  public EnhanceForOperand(String varName, Operand array) {
    Validate.notEmpty(varName, "varName is empty!");
    Validate.notNull(array, "array is null!");

    this.varName = varName;
    this.array = array;
  }

  @Override
  public Object operate(EntityMap stack, StateContext stateContext) {
    Object ary = array.operate(stack);
    if (ary instanceof Iterable) {
      iteratorOperate((Iterable) ary, stack, stateContext);
    } else if (ary.getClass().isArray()) {
      arrayOperate(ary, stack, stateContext);
    }
    return null;
  }

  protected void iteratorOperate(Iterable iterable, EntityMap stack,
      StateContext stateContext) {
    for (Object o : iterable) {
      stack.putEntity(varName, o);
      operate(stack, this.processOperands, stateContext);
      if (stateContext.getJumpMode() != null) {
        if (stateContext.getJumpMode() == JumpMode.BREAK) {
          stateContext.clear();
          break;
        } else if (stateContext.getJumpMode() == JumpMode.CONTINUE) {
          stateContext.clear();
          continue;
        } else {
          loopTimes = 0;
        }
      }
      if (loopTimes++ < stateContext.getMaxLoopTimes()) {
        throw new IllegalStateException(
            String.format("loopTimes exceed the max times %d!",
                stateContext.getMaxLoopTimes()));
      }
    }
    stack.removeEntity(varName);
    loopTimes = 0;
  }

  protected void arrayOperate(Object ary, EntityMap stack,
      StateContext stateContext) {
    for (int i = 0; i < Array.getLength(ary); i++) {
      Object o = Array.get(ary, i);
      stack.putEntity(varName, o);
      operate(stack, this.processOperands, stateContext);
      if (stateContext.getJumpMode() != null) {
        if (stateContext.getJumpMode() == JumpMode.BREAK) {
          stateContext.clear();
          break;
        } else if (stateContext.getJumpMode() == JumpMode.CONTINUE) {
          stateContext.clear();
        } else {
          loopTimes = 0;
        }
      }
      if (loopTimes++ >= stateContext.getMaxLoopTimes()) {
        throw new IllegalStateException(
            String.format("loopTimes exceed the max times %d!",
                stateContext.getMaxLoopTimes()));
      }
    }
    stack.removeEntity(varName);
    loopTimes = 0;
  }

  public List<ProcessOperand> getProcessOperands() {
    return processOperands;
  }

  public void setProcessOperands(List<ProcessOperand> processOperands) {
    this.processOperands = processOperands;
  }

  public String getVarName() {
    return varName;
  }

  public Operand getArray() {
    return array;
  }
}
