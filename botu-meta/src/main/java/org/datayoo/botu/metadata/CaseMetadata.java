package org.datayoo.botu.metadata;

import org.apache.commons.lang3.Validate;

import java.io.Serializable;

public class CaseMetadata
    implements PositionAware, Statementable, Serializable {

  protected ExpressionMetadata value;

  protected Statementable statement;

  protected int lineNo;

  public CaseMetadata(ExpressionMetadata value) {
    Validate.notNull(value, "value is null!");
    this.value = value;
  }

  public ExpressionMetadata getValue() {
    return value;
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
