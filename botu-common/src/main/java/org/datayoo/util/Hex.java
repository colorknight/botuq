package org.datayoo.util;

/*
 * Created on 2005-9-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Taiding Tang
 * <p>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class Hex {
  public static boolean isHex(byte hex) {
    if (hex >= '0' && hex <= '9') {
      return true;
    } else if (hex >= 'a' && hex <= 'f' || hex >= 'A' && hex <= 'F') {
      return true;
    }
    return false;
  }

  public static byte chartoint(byte hex) {
    if (hex >= '0' && hex <= '9') {
      return (byte) (hex - '0');
    } else if (hex >= 'a' && hex <= 'f') {
      return (byte) (hex - 'a' + 10);
    } else if (hex >= 'A' && hex <= 'F') {
      return (byte) (hex - 'A' + 10);
    }
    return (byte) 0xFF;
  }

  public static byte inttochar(byte hex) {
    return inttochar(hex, true);
  }

  public static byte inttochar(byte hex, boolean upperCase) {
    byte base = 'A';
    if (!upperCase)
      base = 'a';
    if (hex >= 0 && hex < 10) {
      return (byte) (hex + '0');
    } else if (hex > 9 && hex < 16) {
      return (byte) (hex - 10 + base);
    }
    return (byte) 0xFF;
  }

  public static String toHexString(byte[] bytes, boolean uppercase) {
    StringBuilder sbud = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      byte by = bytes[i];
      byte b = (byte) ((by & 0xF0) >> 4);
      sbud.append((char) inttochar(b, uppercase));
      b = (byte) (by & 0x0F);
      sbud.append((char) inttochar(b, uppercase));
    }
    return sbud.toString();
  }

  public static byte[] toBytes(String hexString) {
    byte[] data = hexString.getBytes();
    byte[] bytes = new byte[hexString.getBytes().length / 2];
    for (int i = 0, j = 0; i < data.length; ) {
      byte b = 0;
      b |= chartoint(data[i++]) << 4;
      b |= chartoint(data[i++]);
      bytes[j++] = b;
    }
    return bytes;
  }
}
