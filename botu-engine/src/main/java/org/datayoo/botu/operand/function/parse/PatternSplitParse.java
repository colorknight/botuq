package org.datayoo.botu.operand.function.parse;

import org.datayoo.botu.BotuConstants;
import org.datayoo.botu.operand.constant.StringConstant;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.constant.NullConstant;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

public class PatternSplitParse extends AbstractFunction {

  public static final String FUNCTION_NAME = "patternSplitParse";

  protected Operand field;

  protected String separator;

  protected String kvSeparator;

  public PatternSplitParse(List<Operand> parameters) {
    super(FUNCTION_NAME, 3, parameters);
    field = parameters.get(0);
    if (!(parameters.get(1) instanceof StringConstant)) {
      throw new UnsupportedOperationException(
          "The pattern parameter should be a string constant in patternSplitParse function!");
    }
    separator = (String) parameters.get(1).operate((EntityMap) null);
    if (!(parameters.get(2) instanceof NullConstant)) {
      if (!(parameters.get(2) instanceof StringConstant)) {
        throw new UnsupportedOperationException(
            "The kvSeparator parameter should be a string constant in patternSplitParse function!");
      }
      this.kvSeparator = (String) parameters.get(2).operate((EntityMap) null);
    }
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    String data = (String) field.operate(entityMap);
    if (data == null)
      return false;
    String[] segments = data.split(separator);
    if (segments.length == 0)
      return false;
    int i = 1;
    for (String segment : segments) {
      if (kvSeparator != null) {
        int index = segment.indexOf(kvSeparator);
        if (index != -1) {
          String key = segment.substring(0, index).trim();
          String value = segment.substring(index + kvSeparator.length());
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
    throw new UnsupportedOperationException();
  }

  public String getSeparator() {
    return separator;
  }

  public String getKvSeparator() {
    return kvSeparator;
  }
}
