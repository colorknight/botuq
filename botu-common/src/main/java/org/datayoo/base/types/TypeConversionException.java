package org.datayoo.base.types;

/**
 * @author tangtaiding
 */
public class TypeConversionException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  /**
   * @param message
   * @param cause
   */
  public TypeConversionException(String message, Throwable cause) {
    super(message, cause);
    // TODO Auto-generated constructor stub
  }

  /**
   * @param message
   */
  public TypeConversionException(String message) {
    super(message);
    // TODO Auto-generated constructor stub
  }

  /**
   * @param cause
   */
  public TypeConversionException(Throwable cause) {
    super(cause);
    // TODO Auto-generated constructor stub
  }

}
