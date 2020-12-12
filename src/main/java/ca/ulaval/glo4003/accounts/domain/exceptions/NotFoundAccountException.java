package ca.ulaval.glo4003.accounts.domain.exceptions;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class NotFoundAccountException extends ApplicationException {
  private static final String ERROR = "Account not found";
  private static final String DESCRIPTION = "Account with id %s was not found";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;
  private final AccountId accountId;

  public NotFoundAccountException(AccountId accountId) {
    super(ERROR, DESCRIPTION, CODE);
    this.accountId = accountId;
  }

  @Override
  public String getDescription() {
    return String.format(DESCRIPTION, accountId.toString());
  }
}
