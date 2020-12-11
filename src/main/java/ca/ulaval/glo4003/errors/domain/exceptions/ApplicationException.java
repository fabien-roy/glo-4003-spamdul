package ca.ulaval.glo4003.errors.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;

public abstract class ApplicationException extends RuntimeException {
  private final String error;
  private final String description;
  private final ErrorCode code;

  protected ApplicationException(String error, String description, ErrorCode errorCode) {
    super(error);
    this.error = error;
    this.description = description;
    this.code = errorCode;
  }

  public String getError() {
    return error;
  }

  public String getDescription() {
    return description;
  }

  public ErrorCode getCode() {
    return code;
  }
}
