package ca.ulaval.glo4003.reports.exceptions;

public class InvalidReportScopeException extends ReportException {
  private static final String ERROR = "Invalid report scope";
  private static final String DESCRIPTION = "Report scope must be yearly or monthly";

  public InvalidReportScopeException() {
    super(ERROR, DESCRIPTION);
  }
}
