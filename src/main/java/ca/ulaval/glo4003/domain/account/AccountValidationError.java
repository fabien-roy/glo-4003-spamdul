package ca.ulaval.glo4003.domain.account;

public class AccountValidationError extends Exception {
  private static final String ERROR_MESSAGE =
      "The json is not Valid. Please check for null,"
          + " check if your preferred communication is email or postal and check if you have"
          + " a postal code if your preferred communication is postal";

  public AccountValidationError() {
    super(ERROR_MESSAGE);
  }
}
