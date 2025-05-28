package org.datayoo.botu.operand.function.biz;

import org.datayoo.base.types.DataTypeUtils;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

public class ParseSyslogFacility extends AbstractFunction {

  public static final String FUNCTION_NAME = "parseSyslogFacility";

  protected Operand var;

  public ParseSyslogFacility(List<Operand> parameters) {
    super(FUNCTION_NAME, 1, parameters);
    var = parameters.get(0);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object o = var.operate(entityMap);
    if (o == null)
      return null;
    int l = DataTypeUtils.intValue(o.toString());
    return l / 8;
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object o = var.operate(entityArray);
    if (o == null)
      return null;
    int l = DataTypeUtils.intValue(o.toString());
    return l / 8;
  }

}
