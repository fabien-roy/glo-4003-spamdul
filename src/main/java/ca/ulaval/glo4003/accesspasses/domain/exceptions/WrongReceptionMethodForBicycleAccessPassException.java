package ca.ulaval.glo4003.accesspasses.domain.exceptions;

public class WrongReceptionMethodForBicycleAccessPassException extends AccessPassException {
  private static final String ERROR = "Wrong reception method for bicycle access pass";
  private static final String DESCRIPTION =
      "The reception method provided doesn't match any reception method";

  public WrongReceptionMethodForBicycleAccessPassException() {
    super(ERROR, DESCRIPTION);
  }
}
