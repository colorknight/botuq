package org.datayoo.botu.operand.block;

import org.datayoo.botu.operand.ProcessOperand;
import org.datayoo.botu.operand.StateContext;
import org.datayoo.moql.EntityMap;

import java.util.LinkedList;
import java.util.List;

public class BlockOperand extends AbstractCodeBlcokOperand {

  protected List<ProcessOperand> operands = new LinkedList<ProcessOperand>();

  @Override
  public Object operate(EntityMap stack, StateContext stateContext) {
    throw new UnsupportedOperationException();
  }

  public List<ProcessOperand> getOperands() {
    return operands;
  }

  public void setOperands(List<ProcessOperand> operands) {
    this.operands = operands;
  }
}
