package org.datayoo.botu.metadata;

import org.apache.commons.lang3.Validate;

import java.io.Serializable;

public class ExpressionMetadata
    implements PositionAware, Statementable, Serializable {

  protected ExpressionType expressionType;

  protected String operator;

  protected ExpressionMetadata lOperand;

  protected ExpressionMetadata rOperand;

  protected int lineNo;

  protected ExpressionMetadata(ExpressionType expressionType) {
    Validate.notNull(expressionType, "expressionType is null!");
    this.expressionType = expressionType;
  }

  public ExpressionMetadata(ExpressionType expressionType,
      ExpressionMetadata expression) {
    Validate.notNull(expressionType, "expressionType is null!");
    Validate.notNull(expression, "expression is null!");

    this.expressionType = expressionType;
    this.lOperand = expression;
  }

  public ExpressionMetadata(ExpressionType expressionType, String operator,
      ExpressionMetadata expression) {
    Validate.notNull(expressionType, "expressionType is null!");
    Validate.notNull(operator, "operator is empty!");
    Validate.notNull(expression, "expression is null!");

    this.expressionType = expressionType;
    this.operator = operator;
    this.lOperand = expression;
  }

  public ExpressionMetadata(ExpressionType expressionType, String operator,
      ExpressionMetadata lOperand, ExpressionMetadata rOperand) {
    Validate.notNull(expressionType, "expressionType is null!");
    Validate.notNull(operator, "operator is empty!");
    Validate.notNull(lOperand, "lOperand is null!");
    Validate.notNull(rOperand, "rOperand is null!");

    this.expressionType = expressionType;
    this.operator = operator;
    this.lOperand = lOperand;
    this.rOperand = rOperand;
  }

  public ExpressionType getExpressionType() {
    return expressionType;
  }

  public String getOperator() {
    return operator;
  }

  public ExpressionMetadata getlOperand() {
    return lOperand;
  }

  public ExpressionMetadata getrOperand() {
    return rOperand;
  }

  @Override
  public int getLineNo() {
    return lineNo;
  }

  @Override
  public void setLineNo(int lineNo) {
    this.lineNo = lineNo;
  }
}
