package org.datayoo.botu.operand.arithmetic;

import org.apache.commons.lang3.Validate;
import org.datayoo.botu.metadata.ExpressionMetadata;
import org.datayoo.botu.operand.ProcessOperand;
import org.datayoo.botu.operand.ProcessOperandType;
import org.datayoo.botu.operand.StateContext;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.operand.expression.AbstractExpression;
import org.datayoo.moql.operand.expression.ExpressionType;
import org.datayoo.moql.operand.variable.Variable;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2021/9/2 10:27 PM
 */
public class PlusPlusOperand extends AbstractExpression
    implements ProcessOperand {

  protected Variable operand;

  protected boolean plusFirst = false;

  public PlusPlusOperand(Variable operand, boolean plusFirst) {
    Validate.notNull(operand, "operand is null");
    this.operand = operand;
    this.plusFirst = plusFirst;
    this.expressionType = ExpressionType.ARITHMETIC;
    name = buildNameString();
  }

  protected String buildNameString() {
    StringBuffer sbuf = new StringBuffer();
    if (plusFirst)
      sbuf.append("++");
    sbuf.append(operand.toString());
    if (!plusFirst)
      sbuf.append("++");
    return sbuf.toString();
  }

  @Override
  public Object operate(EntityMap entityMap) {
    Object o = operand.operate(entityMap);
    if (o instanceof Long || o instanceof Integer || o instanceof Short
        || o instanceof Byte) {
      Number n = (Number) o;
      long l = n.longValue() + 1;
      entityMap.putEntity(operand.getName(), l);
      if (plusFirst)
        o = l;
    } else {
      ExpressionMetadata expressionMetadata = (ExpressionMetadata) source;
      throw new IllegalStateException(
          String.format("%s is not a number! At line %d!", operand.getName(),
              expressionMetadata.getLineNo()));
    }
    return o;
  }

  @Override
  public void bind(String[] entityNames) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object operate(Object[] entityArray) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ProcessOperandType getProcessOperandType() {
    return ProcessOperandType.ASSIGNMENT;
  }

  @Override
  public Object operate(EntityMap stack, StateContext stateContext) {
    return operate(stack);
  }

  public Variable getOperand() {
    return operand;
  }

  public boolean isPlusFirst() {
    return plusFirst;
  }
}
