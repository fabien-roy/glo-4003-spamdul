package ca.ulaval.glo4003.accounts.services.converters;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.exceptions.InvalidAccountIdException;
import ca.ulaval.glo4003.users.services.dto.AccountIdDto;
import java.util.UUID;

public class AccountIdConverter {
  public AccountId convert(String accountId) {
    try {
      return new AccountId(UUID.fromString(accountId));
    } catch (IllegalArgumentException | NullPointerException exception) {
      throw new InvalidAccountIdException();
    }
  }

  public AccountIdDto convert(AccountId accountId) {
    AccountIdDto accountIdDto = new AccountIdDto();
    accountIdDto.accountId = accountId.toString();
    return accountIdDto;
  }
}
