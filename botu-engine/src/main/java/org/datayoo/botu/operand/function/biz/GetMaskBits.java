package org.datayoo.botu.operand.function.biz;

import org.datayoo.base.biztypes.Ipv4Address;
import org.datayoo.base.types.DataType;
import org.datayoo.base.types.DataTypeUtils;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

public class GetMaskBits extends AbstractFunction {

  public static final String FUNCTION_NAME = "getMaskBits";

  public GetMaskBits(List<Operand> parameters) {
    super(FUNCTION_NAME, 1, parameters);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object o = parameters.get(0).operate(entityMap);
    if (o == null)
      return null;
    long ip = Ipv4Address.parseLong(o.toString());
    return getBits(ip);
  }

  protected int getBits(long ip) {
    int bits = 0;
    for (int i = 31; i >= 0; i--) {
      if ((ip >> i & 0x01) != 1)
        break;
      bits++;
    }
    return bits;
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object o = parameters.get(0).operate(entityArray);
    if (o == null)
      return null;
    long ip = Ipv4Address.parseLong(o.toString());
    return getBits(ip);
  }

}
