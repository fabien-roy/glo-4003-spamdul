package ca.ulaval.glo4003.accesspasses.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class WrongAmountOfSemestersForPeriodException extends ApplicationException {
  private static final String ERROR = "Wrong amount of semesters";
  private static final String DESCRIPTION =
      "The amount of semesters provided doesn't match the period selected";

  public WrongAmountOfSemestersForPeriodException() {
    super(ERROR, DESCRIPTION);
  }

  @Override
  public ErrorCode getErrorCode() {
    return ErrorCode.INVALID_REQUEST;
  }
}
