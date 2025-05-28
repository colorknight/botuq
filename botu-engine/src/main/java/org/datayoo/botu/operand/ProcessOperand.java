package org.datayoo.botu.operand;

import org.datayoo.moql.EntityMap;

public interface ProcessOperand {

  Object getSource();

  ProcessOperandType getProcessOperandType();

  Object operate(EntityMap stack, StateContext stateContext);
}
