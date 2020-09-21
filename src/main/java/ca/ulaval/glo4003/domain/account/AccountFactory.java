package ca.ulaval.glo4003.domain.account;

import ca.ulaval.glo4003.api.contact.dto.UserDto;
import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.domain.user.UserAssembler;
import ca.ulaval.glo4003.domain.user.exception.InvalidAgeException;
import ca.ulaval.glo4003.domain.user.exception.InvalidNameException;
import ca.ulaval.glo4003.domain.user.exception.InvalidPostalCodeException;
import ca.ulaval.glo4003.domain.user.userEnum.CommunicationMethod;

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

  private void validate(UserDto userDto) throws AccountValidationError {
    this.validateNull(userDto);
    this.validatePostalCodeIfNeeded(userDto);
  }

  private void validateNull(UserDto userDto) throws AccountValidationError {
    if (userDto.name == null) {
      throw new InvalidNameException();
    }

    if (userDto.age == null) {
      throw new InvalidAgeException();
    }
  }

  private void validatePostalCodeIfNeeded(UserDto userDto) throws AccountValidationError {
    if (CommunicationMethod.get(userDto.preferredCommunicationMethod)
        .equals(CommunicationMethod.POSTAL)) {
      if (userDto.postalCode == null) {
        throw new InvalidPostalCodeException();
      }
    }
  }
}
