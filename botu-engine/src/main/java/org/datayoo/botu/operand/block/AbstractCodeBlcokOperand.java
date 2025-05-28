package org.datayoo.botu.operand.block;

import org.datayoo.botu.operand.AbstractProcessOperand;
import org.datayoo.botu.operand.ProcessOperand;
import org.datayoo.botu.operand.ProcessOperandType;
import org.datayoo.botu.operand.StateContext;
import org.datayoo.moql.EntityMap;

import java.util.List;

public abstract class AbstractCodeBlcokOperand extends AbstractProcessOperand {

  protected CodeBlockType codeBlockType;

  {
    processOperandType = ProcessOperandType.BLOCK;
  }

  protected void operate(EntityMap entityMap, List<ProcessOperand> operands,
      StateContext stateContext) {
    for (ProcessOperand operand : operands) {
      operand.operate(entityMap, stateContext);
      if (stateContext.getJumpMode() != null)
        return;
    }
  }

  public CodeBlockType getCodeBlockType() {
    return codeBlockType;
  }

}
