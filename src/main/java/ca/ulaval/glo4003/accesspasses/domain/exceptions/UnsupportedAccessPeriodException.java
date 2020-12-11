package ca.ulaval.glo4003.accesspasses.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class UnsupportedAccessPeriodException extends ApplicationException {
  private static final String ERROR = "Unsupported access period";
  private static final String DESCRIPTION = "This access period isn't supported yet";
  private static final ErrorCode CODE = ErrorCode.UNSUPPORTED_OPERATION;

  public UnsupportedAccessPeriodException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
