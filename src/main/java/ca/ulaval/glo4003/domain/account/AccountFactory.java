package ca.ulaval.glo4003.domain.account;

import ca.ulaval.glo4003.api.user.dto.UserDto;
import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.domain.user.UserAssembler;
import ca.ulaval.glo4003.domain.user.exception.InvalidNameException;
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
    this.validate(userDto);

    User user = userAssembler.assemble(userDto);
    AccountId accountId = accountIdGenerator.generate();

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
