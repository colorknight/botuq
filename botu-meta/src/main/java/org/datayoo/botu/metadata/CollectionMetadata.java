package org.datayoo.botu.metadata;

import java.util.LinkedList;
import java.util.List;

public class CollectionMetadata extends ExpressionMetadata {

  protected List<ExpressionMetadata> expressionMetadatas = new LinkedList<ExpressionMetadata>();

  public CollectionMetadata() {
    super(ExpressionType.COLLECTION);
  }

  public List<ExpressionMetadata> getExpressionMetadatas() {
    return expressionMetadatas;
  }

  @Override
  public ExpressionMetadata getlOperand() {
    throw new UnsupportedOperationException();
  }

  @Override
  public ExpressionMetadata getrOperand() {
    throw new UnsupportedOperationException();
  }
}
