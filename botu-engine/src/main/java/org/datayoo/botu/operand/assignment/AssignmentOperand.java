package org.datayoo.botu.operand.assignment;

import org.apache.commons.lang3.Validate;
import org.datayoo.botu.metadata.ExpressionMetadata;
import org.datayoo.botu.operand.AbstractProcessOperand;
import org.datayoo.botu.operand.ProcessOperandType;
import org.datayoo.botu.operand.StateContext;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;

public class AssignmentOperand extends AbstractProcessOperand {

  protected String varName;

  protected String operator;

  protected Operand operand;

  {
    processOperandType = ProcessOperandType.ASSIGNMENT;
  }

  public AssignmentOperand(String varName, String operator, Operand operand) {
    Validate.notEmpty(varName, "varName is empty!");
    Validate.notEmpty(operator, "operator is empty!");
    Validate.notNull(operand, "operand is null");
    this.varName = varName;
    this.operator = operator;
    this.operand = operand;
  }

  @Override
  public Object operate(EntityMap stack, StateContext stateContext) {
    try {
      Object value = operand.operate(stack);
      stack.putEntity(varName, value);
    } catch (Throwable t) {
      ExpressionMetadata expressionMetadata = (ExpressionMetadata) source;
      throw new RuntimeException(
          String.format("%s assignment failed! At line %d!", varName,
              expressionMetadata.getLineNo()), t);
    }
    return null;
  }

  public String getVarName() {
    return varName;
  }

  public String getOperator() {
    return operator;
  }

  public Operand getOperand() {
    return operand;
  }
}
