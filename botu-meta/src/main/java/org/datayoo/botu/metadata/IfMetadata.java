package org.datayoo.botu.metadata;

import org.apache.commons.lang3.Validate;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class IfMetadata implements PositionAware, Statementable, Serializable {

  protected ExpressionMetadata condition;

  protected boolean optimized = false;

  protected Statementable statement;

  protected List<IfMetadata> branches = new LinkedList();

  protected int lineNo;

  public IfMetadata(ExpressionMetadata condition, boolean optimized) {
    Validate.notNull(condition, "condition is null!");
    this.condition = condition;
    this.optimized = optimized;
  }

  public IfMetadata() {
  }

  public ExpressionMetadata getCondition() {
    return condition;
  }

  public boolean isOptimized() {
    return optimized;
  }

  public Statementable getStatement() {
    return statement;
  }

  public void setStatement(Statementable statement) {
    this.statement = statement;
  }

  public List<IfMetadata> getBranches() {
    return branches;
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
