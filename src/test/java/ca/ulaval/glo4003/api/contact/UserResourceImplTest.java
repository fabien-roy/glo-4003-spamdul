package ca.ulaval.glo4003.api.contact;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4003.api.contact.dto.UserDto;
import ca.ulaval.glo4003.domain.user.UserService;
import org.junit.Before;
import org.junit.Test;

public class UserResourceImplTest {
  private UserDto userDto = mock(UserDto.class);
  private UserService userService = mock(UserService.class);
  private UserResource userResource;

  private final String ANY_ID = "2";

  @Before
  public void setUp() {
    userResource = new UserResourceImpl(userService);
  }

  @Test
  public void whenAddingUser_ThenServiceShouldAddUser() {
    userResource.addUser(userDto);

    verify(userService).addUser(userDto);
  }

  @Test
  public void whenGettingUser_ThenServiceShouldGetUser() {
    userResource.getUser(ANY_ID);

    verify(userService).getUser(ANY_ID);
  }
}
