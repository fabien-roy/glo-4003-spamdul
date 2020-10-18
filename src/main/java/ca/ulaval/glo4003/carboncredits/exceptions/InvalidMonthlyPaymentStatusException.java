package ca.ulaval.glo4003.carboncredits.exceptions;

public class InvalidMonthlyPaymentStatusException extends CarbonCreditsException {
  private static final String ERROR = "Invalid monthly payment status";
  private static final String DESCRIPTION = "Montly payment status is not valid";

  public InvalidMonthlyPaymentStatusException() {
    super(ERROR, DESCRIPTION);
  }
}
