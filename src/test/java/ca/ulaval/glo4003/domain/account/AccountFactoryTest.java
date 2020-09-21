package ca.ulaval.glo4003.domain.account;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4003.api.contact.dto.UserDto;
import ca.ulaval.glo4003.domain.user.UserAssembler;
import com.google.common.truth.Truth;
import org.junit.Test;

public class AccountFactoryTest {
  AccountNumberGenerator accountNumberGenerator = mock(AccountNumberGenerator.class);
  UserAssembler userAssembler = mock(UserAssembler.class);

  private final String invalid = "invalid";

  AccountFactory accountFactory = new AccountFactory(accountNumberGenerator, userAssembler);

  private UserDto createValidUserDto() {
    UserDto userDto = new UserDto();
    userDto.sex = "M";
    userDto.postalCode = "feffesfs";
    userDto.preferredCommunicationMethod = "postal";
    userDto.name = "Paul";
    userDto.birthDate = "02-02-1990";
    userDto.age = 20;

    return userDto;
  }

  private void testAccountValidationError(UserDto userDto) {
    try {
      this.accountFactory.createAccount(userDto);
      Truth.assertThat(false);
    } catch (Exception exception) {
      if (exception instanceof AccountValidationError) {
        Truth.assertThat(true);
      }
    }
  }

  @Test
  public void whenNullUserName_thenTrowAccountValidationError() {
    UserDto userDto = this.createValidUserDto();
    userDto.name = null;

    this.testAccountValidationError(userDto);
  }

  @Test
  public void whenInvalidCommunicationMethod_thenTrowAccountValidationError() {
    UserDto userDto = this.createValidUserDto();
    userDto.preferredCommunicationMethod = invalid;

    this.testAccountValidationError(userDto);
  }

  @Test
  public void whenInvalidSex_thenTrowAccountValidationError() {
    UserDto userDto = this.createValidUserDto();
    userDto.sex = invalid;

    this.testAccountValidationError(userDto);
  }

  @Test
  public void
      givenCommunicationMethodPostalCode_whenPostalCodeNotThere_thenTrowAccountValidationError() {
    UserDto userDto = this.createValidUserDto();
    userDto.postalCode = null;

    this.testAccountValidationError(userDto);
  }

  @Test
  public void whenCreatingAccount_thenUserAssemblerIsCalled() throws AccountValidationError {
    UserDto userDto = this.createValidUserDto();

    this.accountFactory.createAccount(userDto);

    verify(userAssembler).create(userDto);
  }
}
