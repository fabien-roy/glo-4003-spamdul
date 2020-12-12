package ca.ulaval.glo4003.accesspasses.domain.exceptions;

import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidAccessPeriodException extends ApplicationException {
  private static final String ERROR = "Invalid access period";
  private static final String DESCRIPTION = "Access period should be one of %s";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidAccessPeriodException() {
    super(ERROR, DESCRIPTION, CODE);
  }

  @Override
  public String getDescription() {
    return String.format(DESCRIPTION, enumerateValues(AccessPeriod.class));
  }
}
