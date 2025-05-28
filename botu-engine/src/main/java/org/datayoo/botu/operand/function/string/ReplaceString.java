package org.datayoo.botu.operand.function.string;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

public class ReplaceString extends AbstractFunction {

  public static final String FUNCTION_NAME = "replaceString";

  protected Operand field;

  protected Operand regex;

  protected Operand replacement;

  public ReplaceString(List<Operand> parameters) {
    super(FUNCTION_NAME, 3, parameters);
    field = parameters.get(0);
    regex = parameters.get(1);
    replacement = parameters.get(2);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object obj = field.operate(entityMap);
    if (obj == null)
      return null;
    String src = (String) obj;
    return src.replaceAll((String) regex.operate(entityMap),
        (String) replacement.operate(entityMap));
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object obj = field.operate(entityArray);
    if (obj == null)
      return null;
    String src = (String) obj;
    return src.replaceAll((String) regex.operate(entityArray),
        (String) replacement.operate(entityArray));
  }

}
