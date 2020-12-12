package ca.ulaval.glo4003.funds.domain.exceptions;

public class InvalidMoneyException extends FundException {
  private static final String ERROR = "Invalid amount of money";
  private static final String DESCRIPTION = "Amount of money is invalid";

  public InvalidMoneyException() {
    super(ERROR, DESCRIPTION);
  }
}
