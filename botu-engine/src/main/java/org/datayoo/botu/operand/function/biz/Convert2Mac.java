package org.datayoo.botu.operand.function.biz;

import org.datayoo.base.biztypes.MacAddress;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

public class Convert2Mac extends AbstractFunction {

  public static final String FUNCTION_NAME = "convert2Mac";

  protected Operand macField;

  public Convert2Mac(List<Operand> parameters) {
    super(FUNCTION_NAME, 1, parameters);
    macField = parameters.get(0);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object o = macField.operate(entityMap);
    if (o == null)
      return null;
    return new MacAddress(o.toString());
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object o = macField.operate(entityArray);
    if (o == null)
      return null;
    return new MacAddress(o.toString());
  }

}
