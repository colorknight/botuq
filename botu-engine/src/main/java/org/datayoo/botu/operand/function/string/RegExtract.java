package org.datayoo.botu.operand.function.string;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExtract extends AbstractFunction {

  public static final String FUNCTION_NAME = "regExtract";

  protected Operand field;

  protected Operand pattern;

  public RegExtract(List<Operand> parameters) {
    super(FUNCTION_NAME, 2, parameters);
    field = parameters.get(0);
    pattern = parameters.get(1);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object obj = field.operate(entityMap);
    if (obj == null)
      return null;
    String src = obj.toString();
    Pattern r = Pattern.compile((String) pattern.operate(entityMap));
    Matcher m = r.matcher(src);
    return m.find() ? String.format("%s", m.group(1)) : null;
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object obj = field.operate(entityArray);
    if (obj == null)
      return null;
    String src = obj.toString();
    Pattern r = Pattern.compile((String) pattern.operate(entityArray));
    Matcher m = r.matcher(src);
    return m.find() ? String.format("%s", m.group(1)) : null;
  }

}
