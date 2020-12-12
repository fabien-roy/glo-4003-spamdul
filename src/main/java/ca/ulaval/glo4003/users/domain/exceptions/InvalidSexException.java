package ca.ulaval.glo4003.users.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.users.domain.Sex;

public class InvalidSexException extends ApplicationException {
  private static final String ERROR = "Invalid sex";
  private static final String DESCRIPTION = "Sex should be one of %s";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidSexException() {
    super(ERROR, DESCRIPTION, CODE);
  }

  @Override
  public String getDescription() {
    return String.format(DESCRIPTION, enumerateValues(Sex.class));
  }
}
