package ca.ulaval.glo4003.api.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4003.api.user.dto.UserDto;
import ca.ulaval.glo4003.domain.account.AccountValidationError;
import ca.ulaval.glo4003.domain.user.UserService;
import org.junit.Before;
import org.junit.Test;

public class UserResourceImplementationTest {
  private UserDto userDto = mock(UserDto.class);
  private UserService userService = mock(UserService.class);
  private UserResource userResource;

  private final String ANY_ID = "2";

  @Before
  public void setUp() {
    userResource = new UserResourceImplementation(userService);
  }

  @Test
  public void whenAddingUser_ThenServiceShouldAddUser() throws AccountValidationError {
    userResource.addUser(userDto);

    verify(userService).addUser(userDto);
  }

  @Test
  public void whenGettingUser_ThenServiceShouldGetUser() {
    userResource.getUser(ANY_ID);

    verify(userService).getUser(ANY_ID);
  }
}
