package ca.ulaval.glo4003.accesspasses.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidAccessPassEntryException extends ApplicationException {
  private static final String ERROR = "Invalid access pass entry";
  private static final String DESCRIPTION = "This access pass has already been admitted";

  public InvalidAccessPassEntryException() {
    super(ERROR, DESCRIPTION);
  }

  @Override
  public ErrorCode getErrorCode() {
    return ErrorCode.INVALID_REQUEST;
  }
}
