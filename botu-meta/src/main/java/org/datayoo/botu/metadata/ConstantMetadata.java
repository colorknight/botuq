package org.datayoo.botu.metadata;

public class ConstantMetadata extends ExpressionMetadata {

  protected String value;

  public ConstantMetadata(ExpressionType expressionType, String value) {
    super(expressionType);
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
