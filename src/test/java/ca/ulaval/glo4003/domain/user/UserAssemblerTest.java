package ca.ulaval.glo4003.domain.user;

import static ca.ulaval.glo4003.api.user.helpers.UserDtoBuilder.aUserDto;
import static ca.ulaval.glo4003.domain.time.helpers.CustomDateMother.createPastDate;
import static ca.ulaval.glo4003.domain.user.helpers.UserBuilder.aUser;
import static ca.ulaval.glo4003.domain.user.helpers.UserMother.createName;
import static ca.ulaval.glo4003.domain.user.helpers.UserMother.createSex;

import ca.ulaval.glo4003.api.user.dto.UserDto;
import ca.ulaval.glo4003.domain.time.CustomDate;
import ca.ulaval.glo4003.domain.time.CustomDateAssembler;
import ca.ulaval.glo4003.domain.time.exception.InvalidDateException;
import ca.ulaval.glo4003.domain.user.exception.InvalidBirthDateException;
import ca.ulaval.glo4003.domain.user.exception.InvalidSexException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserAssemblerTest {
  private static final String NAME = createName();
  private static final CustomDate BIRTH_DATE = createPastDate();
  private static final Sex SEX = createSex();

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

    BDDMockito.when(customDateAssembler.assemble(BIRTH_DATE.toString())).thenReturn(BIRTH_DATE);
  }

  @Test
  public void whenAssembling_thenReturnUserWithName() {
    User user = userAssembler.assemble(userDto);

    Truth.assertThat(user.getName()).isEqualTo(NAME);
  }

  @Test
  public void whenAssembling_thenReturnUserWithBirthDate() {
    User user = userAssembler.assemble(userDto);

    Truth.assertThat(user.getBirthDate()).isEqualTo(BIRTH_DATE);
  }

  @Test(expected = InvalidBirthDateException.class)
  public void givenInvalidBirthDate_whenAssembling_thenThrowInvalidBirthDateException() {
    BDDMockito.when(customDateAssembler.assemble(BIRTH_DATE.toString()))
        .thenThrow(new InvalidDateException());

    userAssembler.assemble(userDto);
  }

  @Test
  public void whenAssembling_thenReturnUserWithSex() {
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
