package ca.ulaval.glo4003.api.contact;

import ca.ulaval.glo4003.api.contact.dto.UserDto;
import ca.ulaval.glo4003.domain.user.UserService;

public class UserResourceImpl implements UserResource {
  private final UserService userService;

  public UserResourceImpl(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void addUser(UserDto userDto) {
    userService.addUser(userDto);
  }

  @Override
  public UserDto getUser(String id) {
    return userService.getUser(id);
  }
}
