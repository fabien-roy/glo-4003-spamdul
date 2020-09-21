package ca.ulaval.glo4003.api.contact;

import ca.ulaval.glo4003.api.contact.dto.PostUserDto;
import ca.ulaval.glo4003.api.contact.dto.UserDto;
import ca.ulaval.glo4003.domain.account.AccountValidationError;
import ca.ulaval.glo4003.domain.user.UserService;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;

public class UserResourceImpl implements UserResource {
  private final UserService userService;

  public UserResourceImpl(UserService userService) {
    this.userService = userService;
  }

  @Override
  public PostUserDto addUser(UserDto userDto) {
    try {
      return userService.addUser(userDto);
    } catch (AccountValidationError e) {
      throw new WebApplicationException(
          Response.status(HttpStatus.BAD_REQUEST_400).entity(e.getMessage()).build());
    }
  }

  @Override
  public UserDto getUser(String id) {
    return userService.getUser(id);
  }
}
