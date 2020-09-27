package ca.ulaval.glo4003.domain.account;

import ca.ulaval.glo4003.api.user.dto.UserDto;
import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.domain.user.UserAssembler;
import javax.inject.Inject;

public class AccountFactory {
  private final AccountIdGenerator accountIdGenerator;
  private final UserAssembler userAssembler;

  @Inject
  public AccountFactory(AccountIdGenerator accountIdGenerator, UserAssembler userAssembler) {
    this.accountIdGenerator = accountIdGenerator;
    this.userAssembler = userAssembler;
  }

  public Account createAccount(UserDto userDto) {
    User user = userAssembler.assemble(userDto);
    AccountId accountId = accountIdGenerator.generate();

    return new Account(accountId, user);
  }
}
