package ca.ulaval.glo4003.accounts.services.assemblers;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.users.services.dto.AccountIdDto;

public class AccountIdAssembler {
  public AccountIdDto assemble(AccountId accountId) {
    AccountIdDto accountIdDto = new AccountIdDto();
    accountIdDto.accountId = accountId.toString();
    return accountIdDto;
  }
}
