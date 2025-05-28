package org.datayoo.botu.operand.collection;

import org.apache.commons.lang3.Validate;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.SelectorConstants;
import org.datayoo.moql.operand.expression.AbstractExpression;
import org.datayoo.moql.operand.expression.ExpressionType;

import java.util.List;

public class CollectionOperand extends AbstractExpression {

  protected List<Operand> operands;

  {
    expressionType = ExpressionType.OPERANDS;
  }

  public CollectionOperand(List<Operand> operands) {
    Validate.notEmpty(operands, "operands is empty!");

    this.operands = operands;
    name = buildNameString();
  }

  protected String buildNameString() {
    StringBuffer sbuf = new StringBuffer();
    sbuf.append(SelectorConstants.LPAREN);
    int i = 0;
    for (Operand operand : operands) {
      if (i++ != 0)
        sbuf.append(SelectorConstants.COMMA);
      sbuf.append(operand.toString());
    }
    sbuf.append(SelectorConstants.RPAREN);
    return sbuf.toString();
  }

  @Override
  public Object operate(EntityMap entityMap) {
    Object[] ary = new Object[operands.size()];
    int i = 0;
    for (Operand operand : operands) {
      ary[i] = operand.operate(entityMap);
    }
    return ary;
  }

  @Override
  public void bind(String[] entityNames) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object operate(Object[] entityArray) {
    throw new UnsupportedOperationException();
  }

  public List<Operand> getOperands() {
    return operands;
  }
}
