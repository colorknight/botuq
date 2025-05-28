package org.datayoo.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public abstract class UUIDUtils {

  public static String UUID() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }

  public static String compressedUUID() {
    UUID uuid = UUID.randomUUID();
    return compressedUUID(uuid);
  }

  public static String compactUUID() {
    UUID uuid = UUID.randomUUID();
    String result = uuid.toString();
    return StringUtils.remove(result, '-');
  }

  protected static String compressedUUID(UUID uuid) {
    byte[] byUUID = new byte[16];
    long least = uuid.getLeastSignificantBits();
    long most = uuid.getMostSignificantBits();
    long2bytes(most, byUUID, 0);
    long2bytes(least, byUUID, 8);
    String compressUUID = Base64.encodeBase64URLSafeString(byUUID);
    return compressUUID;
  }

  protected static void long2bytes(long value, byte[] bytes, int offset) {
    for (int i = 7; i > -1; --i) {
      bytes[offset++] = (byte) ((int) (value >> 8 * i & 255L));
    }
  }

}
