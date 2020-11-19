package ca.ulaval.glo4003.accesspasses.exceptions;

public class UnsupportedAccessPeriodException extends AccessPassException {
  private static final String ERROR = "Unsupported access period";
  private static final String DESCRIPTION = "This access period isn't supported yet";

  public UnsupportedAccessPeriodException() {
    super(ERROR, DESCRIPTION);
  }
}
