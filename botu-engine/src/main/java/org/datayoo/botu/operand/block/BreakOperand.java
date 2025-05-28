package org.datayoo.botu.operand.block;

import org.datayoo.botu.operand.AbstractProcessOperand;
import org.datayoo.botu.operand.JumpMode;
import org.datayoo.botu.operand.ProcessOperandType;
import org.datayoo.botu.operand.StateContext;
import org.datayoo.moql.EntityMap;

public class BreakOperand extends AbstractProcessOperand {
  {
    processOperandType = ProcessOperandType.CONTROL;
  }

  public BreakOperand() {
  }

  @Override
  public Object operate(EntityMap entityMap, StateContext stateContext) {
    stateContext.setJumpMode(JumpMode.BREAK);
    return null;
  }

}
