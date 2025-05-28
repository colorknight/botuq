package org.datayoo.botu.operand.function.biz;

import org.datayoo.base.biztypes.IpAddress;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

public class Convert2Ip extends AbstractFunction {

  public static final String FUNCTION_NAME = "convert2Ip";

  protected Operand ipField;

  public Convert2Ip(List<Operand> parameters) {
    super(FUNCTION_NAME, 1, parameters);
    ipField = parameters.get(0);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object o = ipField.operate(entityMap);
    if (o == null)
      return null;
    if (o instanceof String) {
      return new IpAddress((String) o);
    } else if (o instanceof Long) {
      return new IpAddress((Long) o);
    }
    throw new IllegalArgumentException(
        String.format("Invalid ipAddress type %s!", o.getClass().getName()));
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object o = ipField.operate(entityArray);
    if (o == null)
      return null;
    if (o instanceof String) {
      return new IpAddress((String) o);
    } else if (o instanceof Long) {
      return new IpAddress((Long) o);
    }
    throw new IllegalArgumentException(
        String.format("Invalid ipAddress type %s!", o.getClass().getName()));
  }

}
