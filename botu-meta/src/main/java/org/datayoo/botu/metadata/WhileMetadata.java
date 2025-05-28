package org.datayoo.botu.metadata;

import java.io.Serializable;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2021/9/2 3:30 PM
 */
public class WhileMetadata
    implements PositionAware, Statementable, Serializable {

  protected ExpressionMetadata condition;

  protected Statementable statement;

  protected int lineNo;

  public WhileMetadata(ExpressionMetadata condition) {
    this.condition = condition;
  }

  public ExpressionMetadata getCondition() {
    return condition;
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
