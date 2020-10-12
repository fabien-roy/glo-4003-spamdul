package ca.ulaval.glo4003.users.api;

import ca.ulaval.glo4003.access.api.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.access.api.dto.AccessPassDto;
import ca.ulaval.glo4003.access.services.AccessPassService;
import ca.ulaval.glo4003.users.api.dto.AccountIdDto;
import ca.ulaval.glo4003.users.api.dto.UserDto;
import ca.ulaval.glo4003.users.services.UserService;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UserResourceImplementation implements UserResource {
  private final UserService userService;
  private final AccessPassService accessPassService;

  public UserResourceImplementation(UserService userService, AccessPassService accessPassService) {
    this.userService = userService;
    this.accessPassService = accessPassService;
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

  @Override
  public Response addAccessPass(AccessPassDto accessPassDto, String accountId) {
    AccessPassCodeDto accessPassCode = accessPassService.addAccessPass(accessPassDto, accountId);

    return Response.status(Response.Status.CREATED)
        .entity(accessPassCode)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
