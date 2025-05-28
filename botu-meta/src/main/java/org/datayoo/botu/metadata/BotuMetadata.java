package org.datayoo.botu.metadata;

import org.apache.commons.lang3.Validate;

import java.io.Serializable;

public class BotuMetadata
    implements PositionAware, Statementable, Serializable {

  protected String name;

  protected Statementable statement;

  protected int lineNo;

  public BotuMetadata(String name) {
    Validate.notEmpty(name, "name is empty!");
    this.name = name;
  }

  public String getName() {
    return name;
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
