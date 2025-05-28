package org.datayoo.botu.util;

public abstract class StringUtils {

  public static String toMemString(String originalString) {
    if (originalString == null)
      return null;
    //	the minimum sql string is "''"
    if (originalString.length() < 2 || originalString.charAt(0) != '"'
        || originalString.charAt(originalString.length() - 1) != '"') {
      throw new IllegalArgumentException(
          String.format("String '%s' is invalid!", originalString));
    }
    StringBuffer sbuf = new StringBuffer(originalString.length());

    for (int i = 1; i < originalString.length() - 1; i++) {
      char ch = originalString.charAt(i);
      if (ch == '\\') {
        i++;
        if (i == originalString.length() - 1)
          break;
        ch = originalString.charAt(i);
        ch = toEscapeChar(ch);
      }
      sbuf.append(ch);
    }
    return sbuf.toString();
  }

  protected static char toEscapeChar(char ch) {
    switch (ch) {
      case 'n':
        return '\n';
      case 'b':
        return '\b';
      case 'r':
        return '\r';
      case 't':
        return '\t';
      default:
        return ch;
    }
  }

  public static char toMemChar(String originalString) {
    if (originalString == null)
      return 0;
    if (originalString.charAt(0) != '\''
        || originalString.charAt(originalString.length() - 1) != '\'') {
      throw new IllegalArgumentException(
          String.format("Char '%s' is invalid!", originalString));
    }
    //	the minimum sql string is "''"
    if (originalString.length() == 3)
      return originalString.charAt(1);
    else if (originalString.length() == 4) {
      if (originalString.charAt(1) == '\\')
        return toEscapeChar(originalString.charAt(2));
    }
    throw new IllegalArgumentException(
        String.format("Char '%s' is invalid!", originalString));
  }
}
