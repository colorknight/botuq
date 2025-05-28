/*
 * Created on 2005-9-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.datayoo.base.biztypes;

import org.apache.commons.lang3.Validate;
import org.datayoo.util.Hex;

import java.io.Serializable;

/**
 * @author Taiding Tang
 * <p>
 * Using format the Mac address from String to long<br>
 * or format from long to string.
 * The mac address formats are defined as following:
 * H or h(case insensitive) is reserved. 'H' denote the
 * hex presentation of mac address. For topsec: We can use
 * the format like HH:Hh:HH:HH:hh:hh to presentate the mac
 * address of EA:0F:CD:37:28:45;
 */
public class MacAddress implements Serializable, Comparable<Object> {
  private static final long serialVersionUID = 1L;
  public static final char UPPER_HEX_SUFFIX = 'H';
  public static final char LOWER_HEX_SUFFIX = 'h';
  public static final String MAC_5COLON_FORMAT = "HH:HH:HH:HH:HH:HH";
  public static final String MAC_5HYPHEN_FORMAT = "HH-HH-HH-HH-HH-HH";
  public static final String MAC_2DOT_FORMAT = "HHHH.HHHH.HHHH";
  public static final String MAC_2HYPHEN_FORMAT = "HHHH-HHHH-HHHH";

  private byte[] mac;

  public MacAddress(byte[] macAddress) {
    if (macAddress == null || macAddress.length != 6) {
      throw new IllegalArgumentException("The mac address format is invalid!");
    }
    this.mac = macAddress;
  }

  public MacAddress(String macAddress) {
    mac = parse(macAddress);
    if (mac == null || mac.length != 6) {
      throw new IllegalArgumentException("The mac address format is invalid!");
    }
  }

  public static long bytes2Long(byte[] macAddress) {
    Validate.notNull(macAddress, "macAddress is null!");
    long lmac = 0;
    int count = macAddress.length > 6 ? 6 : macAddress.length;
    for (int i = 0, j = 5; i < count; i++, j--) {
      lmac |= ((long) macAddress[i] & 0XFF) << 8 * j;
    }
    return lmac;
  }

  public static byte[] long2Bytes(long macAddress) {
    byte[] macBytes = new byte[6];
    long mask = 0xFF;
    for (int i = 0, j = 5; i < 6; i++, j--) {
      macBytes[i] = (byte) ((macAddress >> 8 * j) & mask);
    }
    return macBytes;
  }

  public static String bytes2String(String format, byte[] macAddress) {
    Validate.notNull(format, "format is null!");
    Validate.notNull(macAddress, "macAddress is null!");
    byte[] macChars = bytetochar(macAddress);
    StringBuilder sb = new StringBuilder(256);
    char fmt = 0;
    for (int i = 0, j = 0; i < format.length(); i++) {
      fmt = format.charAt(i);
      if (fmt == UPPER_HEX_SUFFIX || fmt == LOWER_HEX_SUFFIX) {
        sb.append((char) macChars[j++]);
      } else {
        sb.append(fmt);
      }
    }
    return sb.toString();
  }

  protected static byte[] bytetochar(byte[] macAddress) {
    byte[] macChars = new byte[12];
    for (int i = 0, j = 0; i < 6; i++) {
      macChars[j++] = Hex.inttochar((byte) ((macAddress[i] >> 4) & 0x0F));
      macChars[j++] = Hex.inttochar((byte) (macAddress[i] & 0x0F));
    }
    return macChars;
  }

  public static byte[] parse(String macAddress) {
    Validate.notEmpty(macAddress, "macAddress is empty!");
    byte[] macBytes = new byte[6];
    byte[] bytes = macAddress.getBytes();
    int bit = 0;
    for (int i = 0; i < bytes.length; i++) {
      if (bit > 12)
        throw new IllegalArgumentException("Invalid mac format!");
      if (Hex.isHex(bytes[i])) {
        if (bit % 2 == 0) {
          macBytes[bit++ / 2] |= Hex.chartoint(bytes[i]) << 4;
        } else {
          macBytes[bit++ / 2] |= Hex.chartoint(bytes[i]);
        }
      }
    }
    return macBytes;
  }

  public static byte[] getHigher3Bytes(String macAddress) {
    byte[] macBytes = parse(macAddress);
    byte[] highBytes = new byte[3];
    System.arraycopy(macBytes, 0, highBytes, 0, 3);
    return highBytes;
  }

  public static byte[] getLower3Bytes(String macAddress) {
    byte[] macBytes = parse(macAddress);
    byte[] lowBytes = new byte[3];
    System.arraycopy(macBytes, 3, lowBytes, 0, 3);
    return lowBytes;
  }

  public byte[] getMac() {
    return mac;
  }

  public static String toString(String format, long macAddress) {
    byte[] macBytes = long2Bytes(macAddress);
    return bytes2String(format, macBytes);
  }

  public String toString() {
    return bytes2String(MAC_5COLON_FORMAT, mac);
  }

  public String toString(String format) {
    return bytes2String(format, mac);
  }

  public Object clone() throws CloneNotSupportedException {
    return new MacAddress(mac);
  }

  public boolean equals(Object object) {
    if (object instanceof MacAddress) {
      MacAddress macAddress = (MacAddress) object;
      if (mac == macAddress.mac) {
        return true;
      }
    }
    return false;
  }

  public int hashCode() {
    int hashCode = 0;
    for (int i = 0, j = 3; i < 4; i++, j--) {
      hashCode |= mac[i] << 8 * j;
    }
    return hashCode;
  }

  /* (non-Javadoc)
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(Object arg0) {
    // TODO Auto-generated method stub
    if (!(arg0 instanceof MacAddress)) {
      throw new IllegalArgumentException("Invalid object type!");
    }
    MacAddress macAddress = (MacAddress) arg0;
    for (int i = 0; i < 6; i++) {
      int result = mac[i] - macAddress.mac[i];
      if (result > 0)
        return 1;
      else if (result < 0)
        return -1;
    }
    return 0;
  }

}
