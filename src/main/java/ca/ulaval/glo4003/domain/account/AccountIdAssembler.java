package ca.ulaval.glo4003.domain.account;

import ca.ulaval.glo4003.domain.account.exception.InvalidAccountIdException;
import java.util.UUID;

public class AccountIdAssembler {
  public AccountId assemble(String accountId) {
    UUID assembledId;

    try {
      assembledId = UUID.fromString(accountId);
    } catch (IllegalArgumentException exception) {
      throw new InvalidAccountIdException();
    }

    return new AccountId(assembledId);
  }
}
