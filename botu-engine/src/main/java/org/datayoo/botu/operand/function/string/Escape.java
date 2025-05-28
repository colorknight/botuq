package org.datayoo.botu.operand.function.string;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

/**
 * 对字符串转义
 */
public class Escape extends AbstractFunction {

  public static char[] escapeChars = new char[] { '\r', '\n', '\t', '\\', '\"'
  };
  public static char[] escapedChars = new char[] { 'r', 'n', 't', '\\', '\"' };

  public static final String FUNCTION_NAME = "escape";

  protected Operand field;

  public Escape(List<Operand> parameters) {
    super(FUNCTION_NAME, 1, parameters);
    field = parameters.get(0);
    this.constantReturn = false;
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object value = field.operate(entityMap);
    if (value == null) {
      return null;
    }
    if (value instanceof String) {
      return escapeString((String) value);
    } else {
      throw new UnsupportedOperationException("not a String value");
    }
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object value = this.field.operate(entityArray);
    if (value == null) {
      return null;
    }
    if (value instanceof String) {
      return escapeString((String) value);
    } else {
      throw new UnsupportedOperationException("not a String value");
    }
  }

  protected static String escapeString(String v) {
    StringBuffer sbuf = new StringBuffer();
    for (int i = 0; i < v.length(); i++) {
      char ch = v.charAt(i);
      char c = transEscapeChar(ch);
      if (c != 0) {
        sbuf.append('\\');
        ch = c;
      }
      sbuf.append(ch);
    }
    return sbuf.toString();
  }

  protected static char transEscapeChar(char ch) {
    for (int i = 0; i < escapeChars.length; i++) {
      if (ch == escapeChars[i])
        return escapedChars[i];
    }
    return 0;
  }

}
