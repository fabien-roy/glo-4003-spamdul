package ca.ulaval.glo4003.domain.account;

import ca.ulaval.glo4003.api.contact.dto.UserDto;
import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.domain.user.UserAssembler;
import ca.ulaval.glo4003.domain.user.exception.InvalidNameException;

public class AccountFactory {
  private AccountNumberGenerator accountNumberGenerator;
  private UserAssembler userAssembler;

  public AccountFactory(
      AccountNumberGenerator accountNumberGenerator, UserAssembler userAssembler) {
    this.accountNumberGenerator = accountNumberGenerator;
    this.userAssembler = userAssembler;
  }

  public Account createAccount(UserDto userDto) throws AccountValidationError {
    this.validate(userDto);

    AccountId accountId = this.accountNumberGenerator.getUserNextNumber();
    User user = this.userAssembler.create(userDto);

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
