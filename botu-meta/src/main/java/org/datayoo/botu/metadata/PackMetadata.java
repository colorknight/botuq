package org.datayoo.botu.metadata;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class PackMetadata
    implements PositionAware, Statementable, Serializable {

  protected String schemaName;

  protected boolean mapMode = true;

  protected List<ExpressionMetadata> expressions = new LinkedList<ExpressionMetadata>();

  protected int lineNo;

  public PackMetadata(String schemaName, boolean mapMode) {
    this.schemaName = schemaName;
    this.mapMode = mapMode;
  }

  public String getSchemaName() {
    return schemaName;
  }

  public boolean isMapMode() {
    return mapMode;
  }

  public List<ExpressionMetadata> getExpressions() {
    return expressions;
  }

  public void setExpressions(List<ExpressionMetadata> expressions) {
    this.expressions = expressions;
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
