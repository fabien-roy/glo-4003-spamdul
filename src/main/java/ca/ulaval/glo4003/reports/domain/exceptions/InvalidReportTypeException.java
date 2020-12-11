package ca.ulaval.glo4003.reports.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidReportTypeException extends ApplicationException {
  private static final String ERROR = "Invalid report type";
  private static final String DESCRIPTION = "Report type must be monthly, summary or dayOfMonth";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidReportTypeException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
