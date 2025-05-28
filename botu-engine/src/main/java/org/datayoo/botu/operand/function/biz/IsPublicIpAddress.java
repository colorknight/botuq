package org.datayoo.botu.operand.function.biz;

import org.datayoo.base.biztypes.Ipv4Address;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

public class IsPublicIpAddress extends AbstractFunction {

  public static final String FUNCTION_NAME = "isPublicIpAddress";

  public IsPublicIpAddress(List<Operand> parameters) {
    super(FUNCTION_NAME, 1, parameters);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object o = parameters.get(0).operate(entityMap);
    if (o == null)
      return null;
    return Ipv4Address.isPublic(o.toString());
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object o = parameters.get(0).operate(entityArray);
    if (o == null)
      return null;
    return Ipv4Address.isPublic(o.toString());
  }

}
