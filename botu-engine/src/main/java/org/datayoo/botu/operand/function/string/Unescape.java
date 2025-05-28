package org.datayoo.botu.operand.function.string;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

/**
 * 去除value中的转义符
 */
public class Unescape extends AbstractFunction {

  public static final String FUNCTION_NAME = "unescape";

  protected Operand field;

  public static char[] escapeChars = new char[] { '\r', '\n', '\t' };
  public static char[] escapedChars = new char[] { 'r', 'n', 't' };

  public Unescape(List<Operand> parameters) {
    super(FUNCTION_NAME, 1, parameters);
    field = parameters.get(0);
    this.constantReturn = false;
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    String value = (String) field.operate(entityMap);
    return unescape(value);
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    String value = (String) field.operate(entityArray);
    return unescape(value);
  }

  protected static String unescape(String str) {
    StringBuffer sbuf = new StringBuffer();
    boolean esChar = false;
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if (ch == '\\') {
        if (!esChar) {
          esChar = true;
          continue;
        }
        ch = unescape(ch);
      }
      sbuf.append(ch);
      esChar = false;
    }
    return sbuf.toString();
  }

  protected static char unescape(char ch) {
    for (int i = 0; i < escapedChars.length; i++) {
      if (ch == escapedChars[i])
        return escapeChars[i];
    }
    return ch;
  }

}
