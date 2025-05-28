package org.datayoo.botu.operand.block.condition;

import org.apache.commons.lang3.Validate;
import org.datayoo.botu.operand.ProcessOperand;
import org.datayoo.botu.operand.StateContext;
import org.datayoo.botu.operand.block.AbstractCodeBlcokOperand;
import org.datayoo.moql.EntityMap;

import java.util.LinkedList;
import java.util.List;

public class CaseOperand extends AbstractCodeBlcokOperand {

  protected Object value;

  protected List<ProcessOperand> operands = new LinkedList<ProcessOperand>();

  public CaseOperand(Object value) {
    Validate.notNull(value, "value is null!");
    this.value = value;
  }

  @Override
  public Object operate(EntityMap entityMap, StateContext stateContext) {
    operate(entityMap, operands, stateContext);
    return null;
  }

  public Object getValue() {
    return value;
  }

  public List<ProcessOperand> getOperands() {
    return operands;
  }

  public void setOperands(List<ProcessOperand> operands) {
    if (operands != null)
      this.operands = operands;
  }

}
