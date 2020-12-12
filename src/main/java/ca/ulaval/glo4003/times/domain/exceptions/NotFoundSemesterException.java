package ca.ulaval.glo4003.times.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class NotFoundSemesterException extends ApplicationException {
  private static final String ERROR = "Semester not found";
  private static final String DESCRIPTION =
      "The semester code used does not belong to an existing semester";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;

  public NotFoundSemesterException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
