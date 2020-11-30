package ca.ulaval.glo4003.accounts.services.assemblers;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.exceptions.InvalidAccountIdException;
import ca.ulaval.glo4003.users.services.dto.AccountIdDto;
import java.util.UUID;

public class AccountIdAssembler {
  public AccountId assemble(String accountId) {
    try {
      return new AccountId(UUID.fromString(accountId));
    } catch (IllegalArgumentException | NullPointerException exception) {
      throw new InvalidAccountIdException();
    }
  }

  public AccountIdDto assemble(AccountId accountId) {
    AccountIdDto accountIdDto = new AccountIdDto();
    accountIdDto.accountId = accountId.toString();
    return accountIdDto;
  }
}
