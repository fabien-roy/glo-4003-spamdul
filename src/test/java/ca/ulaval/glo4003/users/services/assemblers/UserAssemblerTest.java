package ca.ulaval.glo4003.users.services.assemblers;

import static ca.ulaval.glo4003.times.helpers.CustomDateMother.createPastDate;
import static ca.ulaval.glo4003.users.helpers.UserBuilder.aUser;
import static ca.ulaval.glo4003.users.helpers.UserMother.createName;
import static ca.ulaval.glo4003.users.helpers.UserMother.createSex;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.times.domain.CustomDate;
import ca.ulaval.glo4003.users.domain.Sex;
import ca.ulaval.glo4003.users.domain.User;
import ca.ulaval.glo4003.users.services.dto.UserDto;
import org.junit.Before;
import org.junit.Test;

public class UserAssemblerTest {
  private UserAssembler userAssembler;

  private final String name = createName();
  private final CustomDate birthDate = createPastDate();
  private final Sex sex = createSex();
  private final User user = aUser().withName(name).withBirthDate(birthDate).withSex(sex).build();

  @Before
  public void setUp() {
    userAssembler = new UserAssembler();
  }

  @Test
  public void whenAssembling_thenReturnUserDtoWithName() {
    UserDto userDto = userAssembler.assemble(user);

    assertThat(userDto.name).isEqualTo(name);
  }

  @Test
  public void whenAssembling_thenReturnUserDtoWithBirthDate() {
    UserDto userDto = userAssembler.assemble(user);

    assertThat(userDto.birthDate).isEqualTo(birthDate.toString());
  }

  @Test
  public void whenAssembling_thenReturnUserDtoWithSex() {
    UserDto userDto = userAssembler.assemble(user);

    assertThat(userDto.sex).isEqualTo(sex.toString());
  }
}
