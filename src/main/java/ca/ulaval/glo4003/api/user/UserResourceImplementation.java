package ca.ulaval.glo4003.api.user;

import ca.ulaval.glo4003.api.user.dto.AccountIdDto;
import ca.ulaval.glo4003.api.user.dto.UserDto;
import ca.ulaval.glo4003.domain.user.UserService;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UserResourceImplementation implements UserResource {
  private final UserService userService;

  @Inject
  public UserResourceImplementation(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Response addUser(UserDto userDto) {
    AccountIdDto accountIdDto = userService.addUser(userDto);
    return Response.status(Response.Status.CREATED)
        .entity(accountIdDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @Override
  public Response getUser(String accountId) {
    UserDto userDto = userService.getUser(accountId);
    return Response.status(Response.Status.OK)
        .entity(userDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
