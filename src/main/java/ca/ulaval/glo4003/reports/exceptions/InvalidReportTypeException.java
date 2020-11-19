package ca.ulaval.glo4003.reports.exceptions;

public class InvalidReportTypeException extends ReportException {
  private static final String ERROR = "Invalid report type";
  private static final String DESCRIPTION = "Report type must be monthly, summary or dayOfMonth";

  public InvalidReportTypeException() {
    super(ERROR, DESCRIPTION);
  }
}
