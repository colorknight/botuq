package org.datayoo.botu.exception;

public class BotuRuntimeException extends RuntimeException {

  public BotuRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public BotuRuntimeException(String message) {
    super(message);
  }

  public BotuRuntimeException(Throwable cause) {
    super(cause);
  }
}
