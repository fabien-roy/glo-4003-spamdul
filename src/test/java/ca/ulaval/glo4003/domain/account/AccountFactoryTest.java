package ca.ulaval.glo4003.domain.account;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4003.api.contact.dto.UserDto;
import ca.ulaval.glo4003.domain.user.UserAssembler;
import ca.ulaval.glo4003.domain.user.exception.InvalidAgeException;
import ca.ulaval.glo4003.domain.user.exception.InvalidNameException;
import ca.ulaval.glo4003.domain.user.exception.InvalidPostalCodeException;
import com.google.common.truth.Truth;
import org.junit.Test;

public class AccountFactoryTest {
  AccountNumberGenerator accountNumberGenerator = mock(AccountNumberGenerator.class);
  UserAssembler userAssembler = mock(UserAssembler.class);

  private final String invalid = "invalid";

  AccountFactory accountFactory = new AccountFactory(accountNumberGenerator, userAssembler);

  private UserDto createValidUserDto() {
    UserDto userDto = new UserDto();
    userDto.sex = "m";
    userDto.postalCode = "feffesfs";
    userDto.preferredCommunicationMethod = "postal";
    userDto.name = "Paul";
    userDto.birthDate = "02-02-1990";
    userDto.age = 20;

    return userDto;
  }

  private void testAccountValidationError(UserDto userDto) {}

  @Test
  public void whenNullUserName_thenTrowInvalidNameException() {
    UserDto userDto = this.createValidUserDto();
    userDto.name = null;

    try {
      this.accountFactory.createAccount(userDto);
      Truth.assertThat(false);
    } catch (Exception exception) {
      if (exception instanceof InvalidNameException) {
        Truth.assertThat(true);
      }
    }
  }

  @Test
  public void whenInvalidAge_thenTrowInvalidAgeException() {
    UserDto userDto = this.createValidUserDto();
    userDto.age = 2;

    try {
      this.accountFactory.createAccount(userDto);
      Truth.assertThat(false);
    } catch (Exception exception) {
      if (exception instanceof InvalidAgeException) {
        Truth.assertThat(true);
      }
    }
  }

  @Test
  public void givenNullPostalCode_whenCommunicationMethodIsPostal_thenInvalidPostalCodeException() {
    UserDto userDto = this.createValidUserDto();
    userDto.postalCode = null;

    try {
      this.accountFactory.createAccount(userDto);
      Truth.assertThat(false);
    } catch (Exception exception) {
      if (exception instanceof InvalidPostalCodeException) {
        Truth.assertThat(true);
      }
    }
  }

  @Test
  public void whenCreatingAccount_thenUserAssemblerIsCalled() throws AccountValidationError {
    UserDto userDto = this.createValidUserDto();

    this.accountFactory.createAccount(userDto);

    verify(userAssembler).create(userDto);
  }
}
