package org.datayoo.botu.metadata;

import org.apache.commons.lang3.Validate;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2021/9/2 3:29 PM
 */
public class ForMetadata implements PositionAware, Statementable, Serializable {

  protected List<ExpressionMetadata> initExpressions = new LinkedList<>();

  protected ExpressionMetadata expression = null;

  protected List<ExpressionMetadata> updateExpressions = new LinkedList<>();

  protected String identifier;

  protected boolean enhanceMode = false;

  protected Statementable statement;

  protected int lineNo = 0;

  public ForMetadata(List<ExpressionMetadata> initExpressions,
      ExpressionMetadata conditionExpression,
      List<ExpressionMetadata> updateExpressions) {
    Validate.notNull(conditionExpression, "conditionExpression is null");
    this.initExpressions = initExpressions;
    this.expression = conditionExpression;
    this.updateExpressions = updateExpressions;
  }

  public ForMetadata(String identifier, ExpressionMetadata expression) {
    Validate.notEmpty(identifier, "identifier is empty!");
    Validate.notNull(expression, "expression is null!");

    this.identifier = identifier;
    this.expression = expression;
    this.enhanceMode = true;
  }

  public List<ExpressionMetadata> getInitExpressions() {
    return initExpressions;
  }

  public ExpressionMetadata getExpression() {
    return expression;
  }

  public List<ExpressionMetadata> getUpdateExpressions() {
    return updateExpressions;
  }

  public String getIdentifier() {
    return identifier;
  }

  public boolean isEnhanceMode() {
    return enhanceMode;
  }

  public Statementable getStatement() {
    return statement;
  }

  public void setStatement(Statementable statement) {
    this.statement = statement;
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
