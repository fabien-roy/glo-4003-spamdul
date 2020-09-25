package ca.ulaval.glo4003.api.user;

import ca.ulaval.glo4003.api.user.dto.AccountIdDto;
import ca.ulaval.glo4003.api.user.dto.UserDto;
import ca.ulaval.glo4003.domain.user.UserService;

public class UserResourceImplementation implements UserResource {
  private final UserService userService;

  public UserResourceImplementation(UserService userService) {
    this.userService = userService;
  }

  @Override
  public AccountIdDto addUser(UserDto userDto) {
    return userService.addUser(userDto);
  }

  @Override
  public UserDto getUser(String id) {
    return userService.getUser(id);
  }
}
