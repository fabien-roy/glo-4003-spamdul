package ca.ulaval.glo4003.accounts.services.converters;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.exceptions.InvalidAccountIdException;
import java.util.UUID;

public class AccountIdConverter {
  public AccountId convert(String accountId) {
    try {
      return new AccountId(UUID.fromString(accountId));
    } catch (IllegalArgumentException | NullPointerException exception) {
      throw new InvalidAccountIdException();
    }
  }
}
