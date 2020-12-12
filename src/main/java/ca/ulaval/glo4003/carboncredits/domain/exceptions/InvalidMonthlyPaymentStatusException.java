package ca.ulaval.glo4003.carboncredits.domain.exceptions;

public class InvalidMonthlyPaymentStatusException extends CarbonCreditException {
  private static final String ERROR = "Invalid monthly payment status";
  private static final String DESCRIPTION = "Montly payment status is not valid";

  public InvalidMonthlyPaymentStatusException() {
    super(ERROR, DESCRIPTION);
  }
}
