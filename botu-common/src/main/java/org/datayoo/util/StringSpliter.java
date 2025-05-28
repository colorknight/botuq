package org.datayoo.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class StringSpliter {

  public static final Collection<String> split(String data, char separator) {
    List<String> tokens = new LinkedList<>();
    if ("".equals(data)) {
      tokens.add(data);
    }
    int startIndex = 0;
    for (int i = 0; i < data.length(); i++) {
      if (data.charAt(i) == separator) {
        tokens.add(data.substring(startIndex, i));
        startIndex = i + 1;
      }
    }
    if (startIndex != data.length())
      tokens.add(data.substring(startIndex));
    return tokens;
  }

  public static final Collection<String> enclosureSplit(String data,
      char separator, char beginEnclosure, char endEnclosure) {
    int match = 0;
    int startOffset = 0;
    boolean beginEQend = (beginEnclosure == endEnclosure);
    List<String> tokens = new LinkedList<String>();
    if ("".equals(data)) {
      tokens.add("");
      return tokens;
    } else if (data == null) {
      return tokens;
    }

    for (int i = 0; i < data.length(); i++) {
      if (data.charAt(i) == beginEnclosure) {
        if (beginEQend && match != 0)
          match--;
        else
          match++;
      } else if (data.charAt(i) == endEnclosure) {
        match--;
      } else if (data.charAt(i) == separator) {
        if (match == 0) {
          tokens.add(
              decodeEnclosure(data.substring(startOffset, i), beginEnclosure,
                  endEnclosure));
          startOffset = i + 1;
        }
      }
    }
    //处理分割符后的数据
    if (startOffset != data.length()) {
      tokens.add(decodeEnclosure(data.substring(startOffset), beginEnclosure,
          endEnclosure));
    } else if (data.charAt(data.length() - 1) == separator) {
      tokens.add("");
    }
    return tokens;
  }

  public static final Collection<String> purlyEnclosureSplit(String data,
      char separator, char beginEnclosure, char endEnclosure) {
    Collection<String> segs = enclosureSplit(data, separator, beginEnclosure,
        endEnclosure);
    Collection<String> out = new ArrayList<>(segs.size());
    for (String seg : segs) {
      if (seg.length() > 1 && seg.charAt(0) == beginEnclosure) {
        int end = seg.length() - 1;
        if (seg.charAt(seg.length() - 1) == endEnclosure) {
          out.add(seg.substring(1, end));
          continue;
        }
      }
      out.add(seg);
    }
    return out;
  }

  protected static final String decodeEnclosure(String data,
      char beginEnclosure, char endEnclosure) {
    if (data == null || data.length() == 0)
      return data;
    if (data.charAt(0) != beginEnclosure)
      return data;
    StringBuffer sbuf = new StringBuffer();
    for (int i = 0; i < data.length(); i++) {
      char c = data.charAt(i);
      if (c == beginEnclosure) {
        if (i + 1 < data.length()) {
          if (data.charAt(i + 1) == beginEnclosure)
            i++;
        }
      } else if (c == endEnclosure) {
        if (i + 1 < data.length()) {
          if (data.charAt(i + 1) == endEnclosure)
            i++;
        }
      }
      sbuf.append(c);
    }
    return sbuf.toString();
  }

}
