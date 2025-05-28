package org.datayoo.base.biztypes;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpAddress extends Ipv6Address {
  private static final long serialVersionUID = 1L;
  public static final IpAddress MIN_ADDRESS = new IpAddress("::");
  public static final IpAddress MAX_ADDRESS = new IpAddress(
      "FFFF:FFFF:FFFF:FFFF:FFFF:FFFF:FFFF:FFFF");
  public static IpAddress IPV4_LOCALHOST = new IpAddress("127.0.0.1");
  public static IpAddress IPV6_LOCALHOST = new IpAddress("::1");
  public static IpAddress IPV4_ZERO = new IpAddress("0.0.0.0");

  public IpAddress(long ipAddress) {
    super(ipAddress);
    // TODO Auto-generated constructor stub
  }

  public IpAddress(InetAddress inetAddress) {
    super(inetAddress);
  }

  public IpAddress(byte[] ipAddress) {
    super(ipAddress);
    // TODO Auto-generated constructor stub
  }

  public IpAddress(String ipAddress) {
    super(ipAddress);
    // TODO Auto-generated constructor stub
  }

  /* (non-Javadoc)
   * @see com.topsec.tsm.base.type.Ipv6Address#toString()
   */
  @Override
  public String toString() {
    // TODO Auto-generated method stub
    if (isIpv4Compatible()) {
      return Ipv4Address.parseString(lowAddress);
    }
    return super.toString();
  }

  public static IpAddress getLocalIp() {
    InetAddress localInetAddress = null;
    try {
      localInetAddress = InetAddress.getLocalHost();
      return new IpAddress(localInetAddress.getHostAddress());
    } catch (UnknownHostException uhe) {
      System.err.println(
          "Could not get the local IP address using InetAddress.getLocalHost()!");
      uhe.printStackTrace();
      return IPV4_LOCALHOST;
    }
  }

}
