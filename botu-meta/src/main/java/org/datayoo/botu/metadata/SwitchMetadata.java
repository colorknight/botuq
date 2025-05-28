package org.datayoo.botu.metadata;

import org.apache.commons.lang3.Validate;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class SwitchMetadata
    implements PositionAware, Statementable, Serializable {

  protected ExpressionMetadata key;

  protected List<CaseMetadata> cases = new LinkedList<CaseMetadata>();

  protected Statementable defaultCase;

  protected int lineNo;

  public SwitchMetadata(ExpressionMetadata key) {
    Validate.notNull(key, "key is null!");
    this.key = key;
  }

  public ExpressionMetadata getKey() {
    return key;
  }

  public List<CaseMetadata> getCases() {
    return cases;
  }

  public void setCases(List<CaseMetadata> cases) {
    this.cases = cases;
  }

  public Statementable getDefaultCase() {
    return defaultCase;
  }

  public void setDefaultCase(Statementable defaultCase) {
    this.defaultCase = defaultCase;
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
