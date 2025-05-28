package org.datayoo.botu.operand.function.time;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.Date;
import java.util.List;

public class ToTime extends AbstractFunction {

  public static final String FUNCTION_NAME = "toTime";

  public ToTime(List<Operand> parameters) {
    super(FUNCTION_NAME, 1, parameters);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object o = parameters.get(0).operate(entityMap);
    if (o == null)
      return null;
    if (o instanceof Integer) {
      Integer sec = (Integer) o;
      return new Date(sec * 1000l);
    } else if (o instanceof Long) {
      return new Date((Long) o);
    } else {
      String t = (String) o;
      long l = 0;
      if (t.length() == 10) {
        l = Integer.valueOf(t) * 1000l;
      } else {
        l = Long.valueOf(t);
      }
      return new Date(l);
    }
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object o = parameters.get(0).operate(entityArray);
    if (o == null)
      return null;
    if (o instanceof Integer) {
      Integer sec = (Integer) o;
      return new Date(sec * 1000l);
    } else if (o instanceof Long) {
      return new Date((Long) o);
    } else {
      String t = (String) o;
      long l = 0;
      if (t.length() == 10) {
        l = Integer.valueOf(t) * 1000l;
      } else {
        l = Long.valueOf(t);
      }
      return new Date(l);
    }
  }

}
