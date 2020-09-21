package ca.ulaval.glo4003.domain.account;

import ca.ulaval.glo4003.api.contact.dto.UserDto;
import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.domain.user.UserAssembler;
import ca.ulaval.glo4003.domain.user.userEnum.CommunicationMethod;
import ca.ulaval.glo4003.domain.user.userEnum.Sex;

public class AccountFactory {
  private AccountNumberGenerator accountNumberGenerator;
  private UserAssembler userAssembler;

  public AccountFactory(
      AccountNumberGenerator accountNumberGenerator, UserAssembler userAssembler) {
    this.accountNumberGenerator = accountNumberGenerator;
    this.userAssembler = userAssembler;
  }

  public Account createAccount(UserDto userDto) throws AccountValidationError {
    AccountId accountId = this.accountNumberGenerator.getUserNextNumber();

    this.validate(userDto);
    User user = this.userAssembler.create(userDto);

    return new Account(accountId, user);
  }

  private void validate(UserDto userDto) throws AccountValidationError {
    this.validateNull(userDto);
    this.validatePreferredCommunicationValue(userDto);
    this.validateSexValue(userDto);
    this.validatePostalCodeIfNeeded(userDto);
  }

  private void validateNull(UserDto userDto) throws AccountValidationError {
    if (userDto.name == null || userDto.age == null) {
      throw new AccountValidationError();
    }
  }

  private void validatePreferredCommunicationValue(UserDto userDto) throws AccountValidationError {
    if (!userDto.preferredCommunicationMethod.equals(CommunicationMethod.EMAIL.toString())
        && !userDto.preferredCommunicationMethod.equals(CommunicationMethod.POSTAL.toString())) {
      throw new AccountValidationError();
    }
  }

  private void validateSexValue(UserDto userDto) throws AccountValidationError {
    if (!userDto.sex.equals(Sex.M.toString()) && !userDto.sex.equals(Sex.MME.toString())) {
      throw new AccountValidationError();
    }
  }

  private void validatePostalCodeIfNeeded(UserDto userDto) throws AccountValidationError {
    if (userDto
        .preferredCommunicationMethod
        .toUpperCase()
        .equals(CommunicationMethod.POSTAL.toString())) {
      if (userDto.postalCode == null) {
        throw new AccountValidationError();
      }
    }
  }
}
