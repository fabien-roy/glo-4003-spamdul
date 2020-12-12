package ca.ulaval.glo4003.users.services.converters;

import static ca.ulaval.glo4003.times.helpers.CustomDateMother.createPastDate;
import static ca.ulaval.glo4003.users.helpers.UserDtoBuilder.aUserDto;
import static ca.ulaval.glo4003.users.helpers.UserMother.createName;
import static ca.ulaval.glo4003.users.helpers.UserMother.createSex;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.times.domain.CustomDate;
import ca.ulaval.glo4003.times.domain.exceptions.InvalidDateException;
import ca.ulaval.glo4003.times.services.converters.CustomDateConverter;
import ca.ulaval.glo4003.users.domain.Sex;
import ca.ulaval.glo4003.users.domain.User;
import ca.ulaval.glo4003.users.domain.exceptions.InvalidBirthDateException;
import ca.ulaval.glo4003.users.domain.exceptions.InvalidNameException;
import ca.ulaval.glo4003.users.domain.exceptions.InvalidSexException;
import ca.ulaval.glo4003.users.services.dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserConverterTest {
  @Mock private CustomDate futureBirthDate;
  @Mock private CustomDateConverter customDateConverter;

  private UserConverter userConverter;

  private final String name = createName();
  private final CustomDate birthDate = createPastDate();
  private final Sex sex = createSex();
  private final UserDto userDto =
      aUserDto().withName(name).withBirthDate(birthDate.toString()).withSex(sex.toString()).build();

  @Before
  public void setUp() {
    userConverter = new UserConverter(customDateConverter);

    when(futureBirthDate.isFuture()).thenReturn(true);
    when(customDateConverter.convert(birthDate.toString())).thenReturn(birthDate);
  }

  @Test
  public void whenConverting_thenReturnUserWithName() {
    User user = userConverter.convert(userDto);

    assertThat(user.getName()).isEqualTo(name);
  }

  @Test(expected = InvalidNameException.class)
  public void givenNullName_whenConverting_thenThrowInvalidNameException() {
    UserDto userDto = aUserDto().withName(null).build();

    userConverter.convert(userDto);
  }

  @Test
  public void whenConverting_thenReturnUserWithBirthDate() {
    User user = userConverter.convert(userDto);

    assertThat(user.getBirthDate()).isEqualTo(birthDate);
  }

  @Test(expected = InvalidBirthDateException.class)
  public void givenInvalidBirthDate_whenConverting_thenThrowInvalidBirthDateException() {
    when(customDateConverter.convert(birthDate.toString())).thenThrow(new InvalidDateException());

    userConverter.convert(userDto);
  }

  @Test(expected = InvalidBirthDateException.class)
  public void givenNullBirthDate_whenConverting_thenThrowInvalidBirthDateException() {
    UserDto userDto = aUserDto().withBirthDate(null).build();

    userConverter.convert(userDto);
  }

  @Test(expected = InvalidBirthDateException.class)
  public void givenFutureBirthDate_whenConverting_thenThrowInvalidBirthDateException() {
    when(customDateConverter.convert(birthDate.toString())).thenReturn(futureBirthDate);

    userConverter.convert(userDto);
  }

  @Test
  public void whenConverting_thenReturnUserWithSex() {
    User user = userConverter.convert(userDto);

    assertThat(user.getSex()).isEqualTo(sex);
  }

  @Test
  public void givenUpperCaseSex_whenConverting_thenReturnUserWithSex() {
    userDto.sex = sex.toString().toUpperCase();

    User user = userConverter.convert(userDto);

    assertThat(user.getSex()).isEqualTo(sex);
  }

  @Test(expected = InvalidSexException.class)
  public void givenInvalidSex_whenConverting_thenThrowInvalidSexException() {
    userDto.sex = "invalidSex";

    userConverter.convert(userDto);
  }
}
