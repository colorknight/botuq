package org.datayoo.botu.operand.function.generator;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;
import org.datayoo.util.UUIDUtils;

import java.util.List;

public class UUIDGenerator extends AbstractFunction {

  public static final String FUNCTION_NAME = "uuid";

  public UUIDGenerator(List<Operand> parameters) {
    super(FUNCTION_NAME, 0, null);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    return UUIDUtils.compressedUUID();
  }

  @Override
  protected Object innerOperate(Object[] objects) {
    return UUIDUtils.compressedUUID();
  }

}
