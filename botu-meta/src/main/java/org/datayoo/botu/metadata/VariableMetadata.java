package org.datayoo.botu.metadata;

public class VariableMetadata extends ExpressionMetadata {

  protected String name;

  public VariableMetadata(String name) {
    super(ExpressionType.IDENTIFIER);
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
