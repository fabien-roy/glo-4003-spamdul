package ca.ulaval.glo4003.times.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.times.domain.SemesterSymbol;

public class InvalidSemesterSymbolException extends ApplicationException {
  private static final String ERROR = "Invalid semester code";
  private static final String DESCRIPTION = "Semester symbol must be one of %s";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidSemesterSymbolException() {
    super(ERROR, DESCRIPTION, CODE);
  }

  @Override
  public String getDescription() {
    return String.format(DESCRIPTION, enumerateValues(SemesterSymbol.class));
  }
}
