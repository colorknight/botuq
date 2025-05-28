package org.datayoo.botu.operand.function.parse;

import org.datayoo.botu.operand.constant.CharConstant;
import org.datayoo.botu.operand.constant.StringConstant;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

public class KeyPositionSplitParse extends AbstractFunction {

  public static final String FUNCTION_NAME = "keyPositionSplitParse";

  protected Operand field;

  protected char separator;

  protected String kvSeparator;

  public KeyPositionSplitParse(List<Operand> parameters) {
    super(FUNCTION_NAME, 3, parameters);
    field = parameters.get(0);

    if (!(parameters.get(1) instanceof CharConstant)) {
      throw new UnsupportedOperationException(
          "The pattern parameter should be a char constant in splitParse function!");
    }
    separator = (char) parameters.get(1).operate((EntityMap) null);

    if (!(parameters.get(2) instanceof StringConstant)) {
      throw new UnsupportedOperationException(
          "The kvSeparator parameter should be a string constant in splitParse function!");
    }
    this.kvSeparator = (String) parameters.get(2).operate((EntityMap) null);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    String data = (String) field.operate(entityMap);
    if (data == null)
      return false;
    String[] segments = data.split(kvSeparator);
    if (segments.length == 0)
      return false;
    String[][] pairs = loadPairs(segments);
    for (int i = 0; i < pairs.length - 1; i++) {
      if(pairs[i][0] == null && pairs[i][1] == null){
        continue;
      }
      if (i == 0) {
        entityMap.putEntity(pairs[0][1], pairs[1][0]);
        continue;
      }
      entityMap.putEntity(pairs[i][1], pairs[i + 1][0]);
    }
    return true;
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    throw new UnsupportedOperationException("");
  }

  protected String[][] loadPairs(String[] segments) {
    String[][] pairs = new String[segments.length][2];
    StringBuilder sb = new StringBuilder();
    boolean hasPrefix = false;
    int specialPairs = 0;
    for (int i = 0; i < segments.length; i++) {
      String segment = segments[i];
      if (i == 0) {
        pairs[i][1] = segment.trim();
        continue;
      }
      if (i == segments.length - 1) {
        pairs[i][0] = segment.trim();
        continue;
      }
      int index = segment.lastIndexOf(separator);
      if (index != -1) {
        if (hasPrefix) {
          pairs[i-specialPairs][0] = sb.append(segment.substring(0, index).trim())
              .toString();
          hasPrefix = false;
        }else{
          pairs[i-specialPairs][0] = segment.substring(0, index).trim();
        }
        pairs[i-specialPairs][1] = segment.substring(index + 1).trim();
      } else {
        //开头结尾处理过后，正文中间如果再出现无法切割的，就表示源数据存在问题
        sb.append(segment).append(kvSeparator);
        hasPrefix = true;
        specialPairs++;
      }
    }
    return pairs;
  }

}
