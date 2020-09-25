package ca.ulaval.glo4003.domain.account;

import ca.ulaval.glo4003.api.user.dto.UserDto;
import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.domain.user.UserAssembler;
import ca.ulaval.glo4003.domain.user.exception.InvalidNameException;

import javax.inject.Inject;

public class AccountFactory {
  private AccountIdGenerator accountIdGenerator;
  private UserAssembler userAssembler;

  @Inject
  public AccountFactory(AccountIdGenerator accountIdGenerator, UserAssembler userAssembler) {
    this.accountIdGenerator = accountIdGenerator;
    this.userAssembler = userAssembler;
  }

  public Account createAccount(UserDto userDto) {
    this.validate(userDto);

    AccountId accountId = this.accountIdGenerator.generate();
    User user = this.userAssembler.assemble(userDto);

    return new Account(accountId, user);
  }

  private void validate(UserDto userDto) {
    this.validateNull(userDto);
  }

  private void validateNull(UserDto userDto) {
    if (userDto.name == null) {
      throw new InvalidNameException();
    }
  }
}
