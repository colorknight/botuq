package org.datayoo.base.biztypes;
/*
 * Created on 2005-1-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import org.datayoo.util.StringSpliter;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Taiding Tang
 *         <p>
 *         Using covert the IP address from String to long<br>
 *         or covert from long to string
 */
public class Ipv4Address implements Serializable, Comparable<Object> {
  private static final long serialVersionUID = 1L;
  public static final int IPV4_BYTES_LENGTH = 4;
  public static final int NETWORK = 1, HOST = 2;
  long ip = 0;

  public Ipv4Address(long ipAddress) {
    this.ip = ipAddress;
  }

  /**
   * @return Returns the longIp.
   */
  public long getLongIp() {
    return ip;
  }

  public Ipv4Address(String ipAddress) {
    this.ip = parseLong(ipAddress);
  }

  public static long ntoh(long ipAddress) {
    long field = 0;
    long hostIp = 0;
    for (int i = 0, j = 3; i < 4; i++, j--) {
      field = ipAddress >> 8 * i & 0xFF;
      hostIp |= field << 8 * j;
    }
    return hostIp;
  }

  public static int ntoh(int ipAddress) {
    int field = 0;
    int hostIp = 0;
    for (int i = 0, j = 3; i < 4; i++, j--) {
      field = ipAddress >> 8 * i & 0xFF;
      hostIp |= field << 8 * j;
    }
    return hostIp;
  }

  public static byte[] ntoh(byte[] ipAddress) {
    byte[] hostIpBytes = new byte[ipAddress.length];
    int j = ipAddress.length - 1;
    for (int i = 0; i < ipAddress.length; i++) {
      hostIpBytes[j--] = ipAddress[i];
    }
    return hostIpBytes;
  }

  /**
   * @param ipAddress the dot split IP address
   * @return the long represent the host byte order IP
   */
  public static long parseLong(String ipAddress) {
    //  big enddian
    long longIp = 0;
    Collection<String> collection = StringSpliter.split(ipAddress, '.');
    String[] ipTokens = new String[collection.size()];
    collection.toArray(ipTokens);
    if (ipTokens.length == IPV4_BYTES_LENGTH) {
      longIp = Long.parseLong(ipTokens[0]) << 24;
      longIp |= Long.parseLong(ipTokens[1]) << 16;
      longIp |= Long.parseLong(ipTokens[2]) << 8;
      longIp |= Long.parseLong(ipTokens[3]);
    } else {
      throw new IllegalArgumentException("Invalid ip address!");
    }
    return longIp;
  }

  /**
   * @param ipAddress Host byte order IP address
   * @return the dot split IP address
   */
  public static String parseString(long ipAddress) {
    //  big enddian
    StringBuilder sb = new StringBuilder(256);
    long mask = 0x00000000FFl;
    for (int i = 3; i >= 0; i--) {
      sb.append((ipAddress >> (8 * i)) & mask);
      if (i > 0) {
        sb.append(".");
      }
    }
    return sb.toString();
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  public boolean equals(Object arg0) {
    if (arg0 instanceof Ipv4Address) {
      Ipv4Address ipAdd = (Ipv4Address) arg0;
      if (ip == ipAdd.ip) {
        return true;
      }
    }
    return false;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  public int hashCode() {
    return (int) ip;
  }

  public String toString() {
    return parseString(ip);
  }

  /* (non-Javadoc)
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(Object arg0) {
    // TODO Auto-generated method stub
    long longIp = 0;
    if (arg0 instanceof Ipv4Address) {
      longIp = ((Ipv4Address) arg0).ip;
    } else if (arg0 instanceof String) {
      longIp = parseLong((String) arg0);
    } else if (arg0 instanceof Long) {
      longIp = ((Long) arg0).longValue();
    } else {
      throw new IllegalArgumentException("Invalid object type!");
    }
    long result = ip - longIp;
    if (result > 0)
      return 1;
    else if (result < 0)
      return -1;
    return 0;
  }

  /**
   * parse the byte array address to long
   *
   * @param ipAddress the ip address
   * @param enddian   the parameter ip's byte order
   * @return the ip in long
   */
  public static long parseBytes(byte[] ipAddress, int enddian) {
    if (ipAddress.length != IPV4_BYTES_LENGTH) {
      throw new IllegalArgumentException("Bytes length is not 4");
    }
    if (enddian == NETWORK) {
      return parseNetworkBytes(ipAddress);
    }
    return parseHostBytes(ipAddress);
  }

  protected static long parseNetworkBytes(byte[] ipAddress) {
    long ip = 0;
    long tmp = 0;
    int offset = 3;
    for (int i = 0; i < ipAddress.length; i++) {
      tmp = ipAddress[i] & 0xff;
      ip |= tmp << (8 * offset--);
    }
    return ip;
  }

  protected static long parseHostBytes(byte[] ipAddress) {
    long ip = 0;
    long tmp = 0;
    for (int i = 0; i < ipAddress.length; i++) {
      tmp = ipAddress[i] & 0xff;
      ip |= tmp << (8 * i);
    }
    return ip;
  }

  /**
   * parse the int address to long
   *
   * @param ipAddress the ip address
   * @param enddian   the parameter ip's byte order
   * @return the ip in long
   */
  public static long parseInt(int ipAddress, int enddian) {
    if (enddian == NETWORK) {
      return parseNetworkBytes(ipAddress);
    }
    return parseHostBytes(ipAddress);
  }

  protected static long parseNetworkBytes(int ipAddress) {
    long ip = 0;
    long tmp = 0;
    int offset = 3;
    for (int i = 0; i < 4; i++) {
      tmp = ipAddress >> (i * 8) & 0xff;
      ip |= tmp << (8 * offset--);
    }
    return ip;
  }

  protected static long parseHostBytes(int ipAddress) {
    long ip = 0;
    long tmp = ipAddress >> 16 & 0xffff;
    ip |= tmp << 16;
    ip |= ipAddress & 0x0000ffff;
    return ip;
  }

  /**
   * get the bytes in big-enddian or network byte order
   */
  public byte[] getBytes() {
    return getNetworkBytes(ip);
  }

  public static byte[] getBytes(long ipAddress) {
    return getNetworkBytes(ipAddress);
  }

  protected static byte[] getNetworkBytes(long ipAddress) {
    byte[] ipBytes = new byte[4];
    int offset = 3;
    for (int i = 0; i < 4; i++) {
      ipBytes[i] = (byte) (ipAddress >> (8 * offset--) & 0xFF);
    }
    return ipBytes;
  }

  public static boolean isPrivate(String ipAddress) {
    long ip = parseLong(ipAddress);
    return isPrivate(ip);
  }

  public static boolean isPrivate(long ipAddress) {
    if (ipAddress > 167772159l
        && ipAddress < 184549376l) //10.0.0.0~10.255.255.255
      return true;
    if (ipAddress > 2886729727l
        && ipAddress < 2887778304l) //172.16.0.0~172.31.255.255
      return true;
    if (ipAddress > 3232235519l
        && ipAddress < 3232301056l) //192.168.0.0~192.168.255.255
      return true;
    return false;
  }

  public boolean isPrivate() {
    return isPrivate(ip);
  }

  public static boolean isLoop(String ipAddress) {
    long ip = parseLong(ipAddress);
    return isLoop(ip);
  }

  public static boolean isLoop(long ipAddress) {
    if (ipAddress > 2130706432l
        && ipAddress < 2147483647l) //127.0.0.1~127.255.255.254
      return true;
    return false;
  }

  public boolean isLoop() {
    return isLoop(ip);
  }

  public static boolean isMulticast(String ipAddress) {
    long ip = parseLong(ipAddress);
    return isMulticast(ip);
  }

  public static boolean isMulticast(long ipAddress) {
    if (ipAddress > 3758096383l
        && ipAddress < 4026531840l) //224.0.0.0~239.255.255.255
      return true;
    return false;
  }

  public boolean isMulticast() {
    return isMulticast(ip);
  }

  public static boolean isPublic(String ipAddress) {
    long ip = parseLong(ipAddress);
    return isPublic(ip);
  }

  public static boolean isPublic(long ipAddress) {
    if (ipAddress == 0 || ipAddress == 4294967295l)
      return false;
    if (isPrivate(ipAddress))
      return false;
    if (isLoop(ipAddress))
      return false;
    if (isMulticast(ipAddress))
      return false;
    return true;
  }

  public boolean isPublic() {
    return isPublic(ip);
  }
}
