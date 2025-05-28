package org.datayoo.botu.operand.function.inside;

import org.datayoo.botu.operand.constant.StringConstant;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackMap extends AbstractFunction {

  public static final String FUNCTION_NAME = "packMap";

  public PackMap(List<Operand> parameters) {
    super(FUNCTION_NAME, -1, parameters);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Map<String, Object> packedMap = new HashMap<>();
    for (Operand operand : parameters) {
      Object v = operand.operate(entityMap);
      if (v != null)
        packedMap.put(operand.getName(), v);
    }
    return packedMap;
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    throw new UnsupportedOperationException("");
  }
}
