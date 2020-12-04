package ca.ulaval.glo4003.accounts.helpers;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;

import ca.ulaval.glo4003.users.services.dto.AccountIdDto;

public class AccountIdDtoBuilder {
  private String accountId = createAccountId().toString();

  public static AccountIdDtoBuilder anAccountIdDto() {
    return new AccountIdDtoBuilder();
  }

  public AccountIdDto build() {
    AccountIdDto accountIdDto = new AccountIdDto();
    accountIdDto.accountId = accountId;
    return accountIdDto;
  }
}
