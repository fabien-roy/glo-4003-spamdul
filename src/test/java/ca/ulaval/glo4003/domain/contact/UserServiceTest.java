package ca.ulaval.glo4003.domain.contact;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4003.api.contact.dto.UserDto;
import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.domain.user.UserAssembler;
import ca.ulaval.glo4003.domain.user.UserRepository;
import ca.ulaval.glo4003.domain.user.UserService;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {

  private User user = mock(User.class);
  private UserDto userDto = mock(UserDto.class);
  private UserRepository userRepository = mock(UserRepository.class);
  private UserAssembler userAssembler = mock(UserAssembler.class);
  private UserService userService;

  private final String ANY_ID = "2";

  @Before
  public void setUp() {
    userService = new UserService(userRepository, userAssembler);
  }

  @Test
  public void whenAddingToRepository_thenAssemblerAndRepositoryIsCalled() {
    userService.addUser(userDto);

    verify(userRepository).save(any());
    verify(userAssembler).create(userDto);
  }

  @Test
  public void whenGettingUserToRepository_thenAssemblerAndRepositoryIsCalled() {
    userService.getUser(ANY_ID);

    verify(userRepository).findById(ANY_ID);
    verify(userAssembler).create((User) any());
  }
}
