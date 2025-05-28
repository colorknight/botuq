package org.datayoo.base.biztypes;

import org.datayoo.util.Hex;

import java.io.Serializable;
import java.net.InetAddress;

/**
 * @author Taiding Tang
 */
public class Ipv6Address implements Serializable, Comparable<Object> {
  private static final long serialVersionUID = 1L;
  public static final int IPV4_BYTES_LENGTH = 4, IPV6_BYTES_LENGTH = 16;
  public static final long MAX_IPV4_RANGE = 0x100000000l;
  public static final char IPV4_DOT_SEPARATOR = '.';
  public static final String IPV6_COLON_SEPARATOR = ":";
  public static final String IPV6_DUAL_COLON_SEPARATOR = "::";
  protected long highAddress = 0;
  protected long lowAddress = 0;

  public Ipv6Address(byte[] ipAddress) {
    if (ipAddress.length != IPV4_BYTES_LENGTH
        && ipAddress.length != IPV6_BYTES_LENGTH) {
      throw new IllegalArgumentException("Bytes length is not 4 and 16!");
    }
    if (ipAddress.length == IPV4_BYTES_LENGTH) {
      lowAddress = Ipv4Address.parseBytes(ipAddress, Ipv4Address.NETWORK);
    } else {
      highAddress = parseLong(ipAddress, 0, 8);
      lowAddress = parseLong(ipAddress, 8, 8);
      adjustIpv4Address();
    }
  }

  public Ipv6Address(InetAddress inetAddress) {
    byte[] addrs = inetAddress.getAddress();
    if (addrs.length == IPV4_BYTES_LENGTH) {
      lowAddress = Ipv4Address.parseBytes(addrs, Ipv4Address.NETWORK);
    } else {
      highAddress = parseLong(addrs, 0, 8);
      lowAddress = parseLong(addrs, 8, 8);
      adjustIpv4Address();
    }
  }

  public Ipv6Address(String ipAddress) {
    byte[] addrs = parseBytes(ipAddress);
    highAddress = parseLong(addrs, 0, 8);
    lowAddress = parseLong(addrs, 8, 8);
    adjustIpv4Address();
  }

  public Ipv6Address(long ipAddress) {
    lowAddress = ipAddress;
  }

  protected long parseLong(byte[] addrs, int start, int length) {
    if (start + length > addrs.length)
      length = addrs.length - start;
    long value = 0;
    int shift = 56;
    for (int i = 0; i < length; i++) {
      value += (addrs[start++] & 0xFFl) << shift;
      shift -= 8;
    }
    return value;
  }

  protected void adjustIpv4Address() {
    if (highAddress != 0)
      return;
    if ((lowAddress >> 32 & 0xFFFFFFFF) == 0xFFFF) { // ::FFFF:127.0.0.1
      lowAddress &= 0x0000FFFFFFFFl;
    }
  }

  public static boolean isIpv4Compatible(byte[] addrs) {
    if (addrs.length == IPV4_BYTES_LENGTH)
      return true;
    if (addrs.length != IPV6_BYTES_LENGTH)
      return false;
    for (int i = 0; i < addrs.length - 4; i++) { //::127.0.0.1
      if (addrs[i] != 0) {
        if ((i == 10 || i == 11)
            && (addrs[i] & 0xFF) == 255) { // ::FFFF:127.0.0.1

        } else
          return false;
      }
    }
    return true;
  }

  public static boolean isIpv4Compatible(String ipAddress) {
    if (ipAddress.indexOf(IPV4_DOT_SEPARATOR) != -1) {
      try {
        parseBytes(ipAddress);
        return true;
      } catch (Exception e) {
        return false;
      }
    }
    return false;
  }

  public boolean isIpv4Compatible() {
    return highAddress == 0 && lowAddress < MAX_IPV4_RANGE;
  }

  public static byte[] parseBytes(String ipAddress) {
    String[] secs = ipAddress.split(IPV6_COLON_SEPARATOR);
    if (secs.length > 8)
      throw new IllegalArgumentException("Invalid ip address!");
    if (secs.length == 1) { // ipv4
      return paseIpv4Address(ipAddress);
    }
    byte[] addrs = new byte[IPV6_BYTES_LENGTH];
    int i = 0;
    for (int j = 0; i < secs.length; i++) {
      if (secs[i].length() > 0) {
        byte[] byAry = str2bytes(secs[i]);
        addrs[j++] = byAry[0];
        addrs[j++] = byAry[1];
      } else
        // 出现缩略格式，退出处理
        break;
    }
    if (i != secs.length) { //在地址的头部或中部出现缩略格式
      i = secs.length - 1;
      for (int j = IPV6_BYTES_LENGTH - 1; i >= 0; i--) { //从地址尾部开始处理
        byte[] byAry = null;
        //是否是ipv4兼容格式
        if (i == secs.length - 1) {
          if (secs[i].indexOf(IPV4_DOT_SEPARATOR) != -1) {
            long ip = Ipv4Address.parseLong(secs[i]);
            byAry = Ipv4Address.getBytes(ip);
          }
        }
        if (byAry == null) {
          if (secs[i].length() > 0) {
            byAry = str2bytes(secs[i]);
          } else {
            break;
          }
        }
        for (int m = byAry.length - 1; m > -1; m--) {
          addrs[j--] = byAry[m];
        }
      }
    }
    return addrs;
  }

  protected static byte[] paseIpv4Address(String ipAddress) {
    byte[] addrs = new byte[IPV6_BYTES_LENGTH];
    long ip = Ipv4Address.parseLong(ipAddress);
    byte[] byAry = Ipv4Address.getBytes(ip);
    for (int i = IPV6_BYTES_LENGTH - IPV4_BYTES_LENGTH, j = 0;
         j < byAry.length; i++, j++) {
      addrs[i] = byAry[j];
    }
    return addrs;
  }

  protected static byte[] str2bytes(String sec) {
    byte[] byArray = new byte[2];
    int value = Integer.valueOf(sec, 16).intValue();
    byArray[0] = (byte) (value >> 8 & 0xFF);
    byArray[1] = (byte) (value & 0xFF);
    return byArray;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    // TODO Auto-generated method stub
    return (int) (highAddress + lowAddress);
  }

  public byte[] toBytes() {
    byte[] addrs;
    if (isIpv4Compatible()) {
      addrs = new byte[IPV4_BYTES_LENGTH];
      int shift = 24;
      for (int i = 0; i < IPV4_BYTES_LENGTH; i++) {
        addrs[i] = (byte) ((lowAddress >> shift) & 0xFF);
        shift -= 8;
      }
    } else {
      addrs = new byte[IPV6_BYTES_LENGTH];
      toIpv6Bytes(addrs, 0, highAddress);
      toIpv6Bytes(addrs, 8, lowAddress);
    }
    return addrs;
  }

  protected void toIpv6Bytes(byte[] addrs, int offset, long address) {
    int shift = 56;
    for (int i = offset; i < 8; i++) {
      addrs[i] = (byte) ((address >> shift) & 0xFF);
      shift -= 8;
    }
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    // TODO Auto-generated method stub
    if (isIpv4Compatible())
      return ipv4IntegrityFormat();
    return toIntegrityFormat();
  }

  protected String ipv4IntegrityFormat() {
    StringBuffer sbuf = new StringBuffer();
    sbuf.append("0000:0000:0000:0000:0000:FFFF:");
    sbuf.append(Ipv4Address.parseString(lowAddress));
    return sbuf.toString();
  }

  public String toIntegrityFormat() {
    StringBuffer sbuf = new StringBuffer();
    toIntegrityFormat(sbuf, highAddress);
    sbuf.append(IPV6_COLON_SEPARATOR);
    toIntegrityFormat(sbuf, lowAddress);
    return sbuf.toString();
  }

  protected void toIntegrityFormat(StringBuffer sbuf, long address) {
    int shift = 60;
    for (int i = 0; i < 16; i++) {
      if (i != 0 && i % 4 == 0)
        sbuf.append(IPV6_COLON_SEPARATOR);
      byte hex = (byte) (address >> shift & 0xF);
      sbuf.append((char) Hex.inttochar(hex));

      shift -= 4;
    }
  }

  protected short[] toShort() {
    short[] shAry = new short[8];
    toShort(shAry, 0, highAddress);
    toShort(shAry, 4, lowAddress);
    return shAry;
  }

  protected void toShort(short[] addrs, int offset, long address) {
    int shift = 48;
    for (int i = offset; i < 4; i++) {
      addrs[i] = (short) ((address >> shift) & 0xFFFF);
      shift -= 16;
    }
  }

  public String toAbbreviationFormat() {
    if (isIpv4Compatible())
      return ipv4AbbreviationFormat();
    return criterionAbbreviationFormat();
  }

  protected String ipv4AbbreviationFormat() {
    StringBuffer sbuf = new StringBuffer();
    if (lowAddress != 0) {
      sbuf.append("::FFFF:");
      sbuf.append(Ipv4Address.parseString(lowAddress));
    } else {
      sbuf.append("::");
    }
    return sbuf.toString();
  }

  protected String criterionAbbreviationFormat() {
    StringBuffer sbuf = new StringBuffer();
    long address = highAddress;
    int shortShift = 48;

    int zeroCount = 0;
    boolean bAbbr = false;
    for (int a = 0; a < 2; a++) {
      if (a == 1) {
        address = lowAddress;
        shortShift = 48;
      }
      for (int i = 0; i < 4; i++) {
        short sh = (short) (address >> shortShift & 0xFFFF);
        if (sh == 0 && !bAbbr) {
          zeroCount++;
        } else {
          if (zeroCount > 0) {
            sbuf.append(IPV6_DUAL_COLON_SEPARATOR);
            zeroCount = 0;
            bAbbr = true;
          } else {
            if (sbuf.length() > 0) {
              sbuf.append(IPV6_COLON_SEPARATOR);
            }
          }
          int hexShift = 12;
          boolean hasNonZero = false;
          for (int j = 0; j < 4; j++) {
            byte hex = (byte) (sh >> hexShift & 0xF);
            if (hex != 0) {
              hasNonZero = true;
              sbuf.append((char) Hex.inttochar(hex));
            } else {
              if (hasNonZero) {
                sbuf.append(0);
              }
            }
            hexShift -= 4;
          }
        }
        shortShift -= 16;
      }

    }

    // 特殊处理全0的地址"::"
    if (zeroCount != 0) {
      sbuf.append(IPV6_DUAL_COLON_SEPARATOR);
    }
    return sbuf.toString();
  }

  public int compareTo(Object o) {
    // TODO Auto-generated method stub

    if (o == this)
      return 0;
    if (o instanceof String) {
      o = new Ipv6Address((String) o);
    } else if (o instanceof Ipv6Address) {
      o = (Ipv6Address) o;
    } else if (o instanceof Long) {
      o = new Ipv6Address((Long) o);
    }

    long ret = highAddress - ((Ipv6Address) o).highAddress;
    if (ret == 0) {
      ret = lowAddress - ((Ipv6Address) o).lowAddress;
      if (ret == 0)
        return 0;
    }
    return ret > 0 ? 1 : -1;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object arg0) {
    // TODO Auto-generated method stub
    if (arg0 instanceof Ipv6Address) {
      Ipv6Address o = (Ipv6Address) arg0;
      if (highAddress == o.highAddress)
        if (lowAddress == o.lowAddress)
          return true;
    }
    return false;
  }

  public long getHighAddress() {
    return highAddress;
  }

  public long getLowAddress() {
    return lowAddress;
  }

}
