package org.datayoo.botu.operand;

import org.datayoo.moql.operand.OperandSourceAware;

public abstract class AbstractProcessOperand
    implements ProcessOperand, OperandSourceAware {

  protected Object source;

  protected ProcessOperandType processOperandType;

  {
    processOperandType = ProcessOperandType.UNKNOW;
  }

  @Override
  public Object getSource() {
    return source;
  }

  @Override
  public void setSource(Object source) {
    this.source = source;
  }

  @Override
  public ProcessOperandType getProcessOperandType() {
    return processOperandType;
  }
}
