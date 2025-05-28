package org.datayoo.botu.operand.function.time;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.constant.StringConstant;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * @author Tang Tadin
 */
public class Format2Time extends AbstractFunction {

  public static final String FUNCTION_NAME = "format2Time";

  protected Operand field;

  protected SimpleDateFormat formater;

  protected Locale locale = Locale.getDefault();

  public Format2Time(List<Operand> parameters) {
    super(FUNCTION_NAME, -1, parameters);
    if (parameters.size() > 3)
      throw new IllegalArgumentException(
          "Function format:format2Time(field,format[,lang])");
    field = parameters.get(0);
    if (!(parameters.get(1) instanceof StringConstant)) {
      throw new IllegalArgumentException(
          "The second parameter should be a string constant in trim function!");
    }
    String format = (String) parameters.get(1).operate((EntityMap) null);
    if (parameters.size() == 3) {
      if (!(parameters.get(2) instanceof StringConstant)) {
        throw new IllegalArgumentException(
            "The second parameter should be a string constant in trim function!");
      }
      String lang = (String) parameters.get(2).operate((EntityMap) null);
      locale = new Locale(lang);
    }
    formater = new SimpleDateFormat(format, locale);
  }

  /* (non-Javadoc)
   * @see org.moql.operand.function.AbstractFunction#innerOperate(org.moql.data.EntityMap)
   */
  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object obj = field.operate(entityMap);
    if (obj == null)
      return null;
    try {
      return formater.parse(obj.toString());
    } catch (ParseException e) {
      throw new RuntimeException(String.format("parse time failed!"), e);
    }
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object obj = field.operate(entityArray);
    if (obj == null)
      return null;
    try {
      return formater.parse(obj.toString());
    } catch (ParseException e) {
      throw new RuntimeException(String.format("parse time failed!"), e);
    }
  }

}
