package ca.ulaval.glo4003.errors.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;

public abstract class ApplicationException extends RuntimeException {
  public final String error;
  public final String description;

  protected ApplicationException(String error, String description) {
    super(error);
    this.error = error;
    this.description = description;
  }

  public abstract ErrorCode getErrorCode();
}
