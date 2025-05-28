package org.datayoo.botu.metadata;

import org.apache.commons.lang3.Validate;

import java.util.LinkedList;
import java.util.List;

public class MethodMetadata extends ExpressionMetadata {

  protected String name;

  protected List<ExpressionMetadata> parameters = new LinkedList<ExpressionMetadata>();

  public MethodMetadata(String name) {
    super(ExpressionType.METHOD);
    Validate.notEmpty(name, "name is empty!");
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public List<ExpressionMetadata> getParameters() {
    return parameters;
  }

  public void setParameters(List<ExpressionMetadata> parameters) {
    this.parameters = parameters;
  }

  public void toProcessFuncType() {
    this.expressionType = ExpressionType.PROCESS_METHOD;
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
