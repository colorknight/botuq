package org.datayoo.botu.metadata;

import java.io.Serializable;

public class BreakMetadata
    implements PositionAware, Statementable, Serializable {

  protected int lineNo;

  @Override
  public int getLineNo() {
    return lineNo;
  }

  @Override
  public void setLineNo(int lineNo) {
    this.lineNo = lineNo;
  }
}
