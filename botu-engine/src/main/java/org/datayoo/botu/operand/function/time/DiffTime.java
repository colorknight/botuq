package org.datayoo.botu.operand.function.time;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.Date;
import java.util.List;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2021/6/30 8:57 AM
 */
public class DiffTime extends AbstractFunction {

  public static final String FUNCTION_NAME = "diffTime";

  protected Operand beginTime;

  protected Operand endTime;

  public DiffTime(List<Operand> parameters) {
    super(FUNCTION_NAME, 2, parameters);
    beginTime = parameters.get(0);
    endTime = parameters.get(1);
  }

  /* (non-Javadoc)
   * @see org.moql.operand.function.AbstractFunction#innerOperate(org.moql.data.EntityMap)
   */
  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Date begin = (Date) beginTime.operate(entityMap);
    if (begin == null)
      return null;
    Date end = (Date) endTime.operate(entityMap);
    if (end == null)
      return null;
    return end.getTime() - begin.getTime();
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Date begin = (Date) beginTime.operate(entityArray);
    if (begin == null)
      return null;
    Date end = (Date) endTime.operate(entityArray);
    if (end == null)
      return null;
    return end.getTime() - begin.getTime();
  }

}

