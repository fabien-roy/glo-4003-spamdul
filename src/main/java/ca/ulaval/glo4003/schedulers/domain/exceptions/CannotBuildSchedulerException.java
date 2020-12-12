package ca.ulaval.glo4003.schedulers.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class CannotBuildSchedulerException extends ApplicationException {
  private static final String ERROR = "Cannot build scheduler";
  private static final String DESCRIPTION = "Something went wrong when building scheduler";
  private static final ErrorCode CODE = ErrorCode.APPLICATION_FAILURE;

  public CannotBuildSchedulerException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
