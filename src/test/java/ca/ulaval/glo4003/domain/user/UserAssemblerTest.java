package ca.ulaval.glo4003.domain.user;

import static ca.ulaval.glo4003.api.user.helpers.UserDtoBuilder.aUserDto;
import static ca.ulaval.glo4003.domain.user.helpers.UserBuilder.aUser;
import static ca.ulaval.glo4003.domain.user.helpers.UserMother.createName;

import ca.ulaval.glo4003.api.user.dto.UserDto;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class UserAssemblerTest {
  private static final String NAME = createName();

  private UserAssembler userAssembler;

  private User user;
  private UserDto userDto;

  @Before
  public void setUp() {
    userAssembler = new UserAssembler();
  }

  @Test
  public void givenName_whenAssembling_thenReturnUserWithName() {
    userDto = aUserDto().withName(NAME).build();

    User user = userAssembler.assemble(userDto);

    Truth.assertThat(user.getName()).isEqualTo(NAME);
  }

  // TODO : Test domain object birth date (pass to CustomDateAssembler)

  // TODO : Test domain object invalid birth date

  // TODO : Test domain object sex

  // TODO : Test domain object invalid sex

  @Test
  public void givenName_whenAssembling_thenReturnUserDtoWithName() {
    user = aUser().withName(NAME).build();

    UserDto userDto = userAssembler.assemble(user);

    Truth.assertThat(userDto.name).isEqualTo(NAME);
  }

  // TODO : Test dto birth date (pass to CustomDateAssembler)

  // TODO : Test dto sex
}
