package ca.ulaval.glo4003.users.services.assemblers;

import static ca.ulaval.glo4003.times.helpers.CustomDateMother.createPastDate;
import static ca.ulaval.glo4003.users.helpers.UserBuilder.aUser;
import static ca.ulaval.glo4003.users.helpers.UserDtoBuilder.aUserDto;
import static ca.ulaval.glo4003.users.helpers.UserMother.createName;
import static ca.ulaval.glo4003.users.helpers.UserMother.createSex;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.times.domain.CustomDate;
import ca.ulaval.glo4003.times.exceptions.InvalidDateException;
import ca.ulaval.glo4003.times.services.assemblers.CustomDateAssembler;
import ca.ulaval.glo4003.users.domain.Sex;
import ca.ulaval.glo4003.users.domain.User;
import ca.ulaval.glo4003.users.exceptions.InvalidBirthDateException;
import ca.ulaval.glo4003.users.exceptions.InvalidNameException;
import ca.ulaval.glo4003.users.exceptions.InvalidSexException;
import ca.ulaval.glo4003.users.services.dto.UserDto;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserAssemblerTest {
  private static final String NAME = createName();
  private static final CustomDate BIRTH_DATE = createPastDate();
  private static final Sex SEX = createSex();

  @Mock private CustomDate futureBirthDate;
  @Mock private CustomDateAssembler customDateAssembler;

  private UserAssembler userAssembler;

  private User user;
  private UserDto userDto;

  @Before
  public void setUp() {
    userAssembler = new UserAssembler(customDateAssembler);

    user = aUser().withName(NAME).withBirthDate(BIRTH_DATE).withSex(SEX).build();
    userDto =
        aUserDto()
            .withName(NAME)
            .withBirthDate(BIRTH_DATE.toString())
            .withSex(SEX.toString())
            .build();

    when(futureBirthDate.isFuture()).thenReturn(true);
    when(customDateAssembler.assemble(BIRTH_DATE.toString())).thenReturn(BIRTH_DATE);
  }

  @Test
  public void whenAssembling_thenReturnUserWithName() {
    User user = userAssembler.assemble(userDto);

    Truth.assertThat(user.getName()).isEqualTo(NAME);
  }

  @Test(expected = InvalidNameException.class)
  public void givenNullName_whenAssembling_thenThrowInvalidNameException() {
    userDto = aUserDto().withName(null).build();

    userAssembler.assemble(userDto);
  }

  @Test
  public void whenAssembling_thenReturnUserWithBirthDate() {
    User user = userAssembler.assemble(userDto);

    Truth.assertThat(user.getBirthDate()).isEqualTo(BIRTH_DATE);
  }

  @Test(expected = InvalidBirthDateException.class)
  public void givenInvalidBirthDate_whenAssembling_thenThrowInvalidBirthDateException() {
    when(customDateAssembler.assemble(BIRTH_DATE.toString())).thenThrow(new InvalidDateException());

    userAssembler.assemble(userDto);
  }

  @Test(expected = InvalidBirthDateException.class)
  public void givenNullBirthDate_whenAssembling_thenThrowInvalidBirthDateException() {
    userDto = aUserDto().withBirthDate(null).build();

    userAssembler.assemble(userDto);
  }

  @Test(expected = InvalidBirthDateException.class)
  public void givenFutureBirthDate_whenAssembling_thenThrowInvalidBirthDateException() {
    when(customDateAssembler.assemble(BIRTH_DATE.toString())).thenReturn(futureBirthDate);

    userAssembler.assemble(userDto);
  }

  @Test
  public void whenAssembling_thenReturnUserWithSex() {
    User user = userAssembler.assemble(userDto);

    Truth.assertThat(user.getSex()).isEqualTo(SEX);
  }

  @Test
  public void givenUpperCaseSex_whenAssembling_thenReturnUserWithSex() {
    userDto.sex = SEX.toString().toUpperCase();

    User user = userAssembler.assemble(userDto);

    Truth.assertThat(user.getSex()).isEqualTo(SEX);
  }

  @Test(expected = InvalidSexException.class)
  public void givenInvalidSex_whenAssembling_thenThrowInvalidSexException() {
    userDto.sex = "invalidSex";

    userAssembler.assemble(userDto);
  }

  @Test
  public void whenAssembling_thenReturnUserDtoWithName() {
    UserDto userDto = userAssembler.assemble(user);

    Truth.assertThat(userDto.name).isEqualTo(NAME);
  }

  @Test
  public void whenAssembling_thenReturnUserDtoWithBirthDate() {
    UserDto userDto = userAssembler.assemble(user);

    Truth.assertThat(userDto.birthDate).isEqualTo(BIRTH_DATE.toString());
  }

  @Test
  public void whenAssembling_thenReturnUserDtoWithSex() {
    UserDto userDto = userAssembler.assemble(user);

    Truth.assertThat(userDto.sex).isEqualTo(SEX.toString());
  }
}
