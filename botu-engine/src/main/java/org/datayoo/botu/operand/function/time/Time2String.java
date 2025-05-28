package org.datayoo.botu.operand.function.time;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.constant.StringConstant;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Tang Tadin
 */
public class Time2String extends AbstractFunction {

  public static final String FUNCTION_NAME = "time2String";

  protected Operand field;

  protected SimpleDateFormat formater;

  public Time2String(List<Operand> parameters) {
    super(FUNCTION_NAME, 2, parameters);
    field = parameters.get(0);
    if (!(parameters.get(1) instanceof StringConstant)) {
      throw new IllegalArgumentException(
          "The second parameter should be a string constant in trim function!");
    }
    String format = (String) parameters.get(1).operate((EntityMap) null);
    formater = new SimpleDateFormat(format);
  }

  /* (non-Javadoc)
   * @see org.moql.operand.function.AbstractFunction#innerOperate(org.moql.data.EntityMap)
   */
  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Date date = (Date) field.operate(entityMap);
    if (date == null)
      return null;
    return formater.format(date);
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Date date = (Date) field.operate(entityArray);
    if (date == null)
      return null;
    return formater.format(date);
  }

}
