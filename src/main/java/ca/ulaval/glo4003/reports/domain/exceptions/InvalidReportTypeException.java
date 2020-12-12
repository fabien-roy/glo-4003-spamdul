package ca.ulaval.glo4003.reports.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.reports.domain.ReportType;

public class InvalidReportTypeException extends ApplicationException {
  private static final String ERROR = "Invalid report type";
  private static final String DESCRIPTION = "Report type should be one of %s";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidReportTypeException() {
    super(ERROR, DESCRIPTION, CODE);
  }

  @Override
  public String getDescription() {
    return String.format(DESCRIPTION, enumerateValues(ReportType.class));
  }
}
