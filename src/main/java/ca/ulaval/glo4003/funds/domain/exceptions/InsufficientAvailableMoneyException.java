package ca.ulaval.glo4003.funds.domain.exceptions;

// TODO : Implement ApplicationException
public class InsufficientAvailableMoneyException extends FundException {
  private static final String ERROR = "Insufficient available money";
  private static final String DESCRIPTION =
      "Not enough available money to remove the requested amount";

  public InsufficientAvailableMoneyException() {
    super(ERROR, DESCRIPTION);
  }
}
