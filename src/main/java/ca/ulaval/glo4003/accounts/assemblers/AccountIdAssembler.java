package ca.ulaval.glo4003.accounts.assemblers;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.exceptions.InvalidAccountIdException;
import ca.ulaval.glo4003.api.user.dto.AccountIdDto;
import java.util.UUID;

public class AccountIdAssembler {
  public AccountId assemble(String accountId) {
    if (accountId == null) throw new InvalidAccountIdException();

    UUID assembledId;

    try {
      assembledId = UUID.fromString(accountId);
    } catch (IllegalArgumentException exception) {
      throw new InvalidAccountIdException();
    }

    return new AccountId(assembledId);
  }

  public AccountIdDto assemble(AccountId accountId) {
    AccountIdDto accountIdDto = new AccountIdDto();
    accountIdDto.accountId = accountId.toString();
    return accountIdDto;
  }
}
