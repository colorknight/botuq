package org.datayoo.botu.operand.function.parse;

import org.datayoo.botu.BotuConstants;
import org.datayoo.botu.operand.constant.CharConstant;
import org.datayoo.botu.operand.constant.StringConstant;
import org.datayoo.util.StringSpliter;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.constant.NullConstant;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.Collection;
import java.util.List;

public class SplitParse extends AbstractFunction {

  public static final String FUNCTION_NAME = "splitParse";

  protected Operand field;

  protected char separator;

  protected char beginEnclosure = 0;

  protected char endEnclosure = 0;

  protected String kvSeparator;

  public SplitParse(List<Operand> parameters) {
    super(FUNCTION_NAME, 5, parameters);
    field = parameters.get(0);
    if (!(parameters.get(1) instanceof CharConstant)) {
      throw new UnsupportedOperationException(
          "The pattern parameter should be a char constant in splitParse function!");
    }
    separator = (char) parameters.get(1).operate((EntityMap) null);
    if (!(parameters.get(2) instanceof NullConstant)) {
      if (!(parameters.get(2) instanceof CharConstant && parameters.get(
          3) instanceof CharConstant)) {
        throw new UnsupportedOperationException(
            "The beginEnclosure parameter should be a char constant in splitParse function!");
      }
      this.beginEnclosure = (char) parameters.get(2).operate((EntityMap) null);
      this.endEnclosure = (char) parameters.get(3).operate((EntityMap) null);
    }
    if (!(parameters.get(4) instanceof NullConstant)) {
      if (!(parameters.get(4) instanceof StringConstant)) {
        throw new UnsupportedOperationException(
            "The kvSeparator parameter should be a string constant in splitParse function!");
      }
      this.kvSeparator = (String) parameters.get(4).operate((EntityMap) null);
    }
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    String data = (String) field.operate(entityMap);
    if (data == null)
      return false;
    Collection<String> segments = null;
    if (beginEnclosure != 0) {
      segments = StringSpliter.enclosureSplit(data, separator, beginEnclosure,
          endEnclosure);
    } else {
      segments = StringSpliter.split(data, separator);
    }
    if (segments.size() == 0)
      return false;
    int i = 1;
    for (String segment : segments) {
      if (kvSeparator != null) {
        int index = segment.indexOf(kvSeparator);
        if (index != -1) {
          String key = segment.substring(0, index).trim();
          String value = segment.substring(index + kvSeparator.length());
          if (value.length() > 0 && value.charAt(0) == beginEnclosure) {
            value = value.substring(1, value.length() - 1);
          }
          if (value.length() > 0) {
            entityMap.putEntity(key, value);
          }
          i++;
          continue;
        }
      }
      if (segment.length() > 0) {
        entityMap.putEntity(BotuConstants.TMP_VAR_PREFIX + i, segment);
      }
      i++;
    }
    return true;
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    throw new UnsupportedOperationException("");
  }

  public char getSeparator() {
    return separator;
  }

  public char getBeginEnclosure() {
    return beginEnclosure;
  }

  public char getEndEnclosure() {
    return endEnclosure;
  }

  public String getKvSeparator() {
    return kvSeparator;
  }
}
