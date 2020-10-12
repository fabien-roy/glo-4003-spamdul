package ca.ulaval.glo4003.users.api;

import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerDto;
import ca.ulaval.glo4003.parkings.services.ParkingService;
import ca.ulaval.glo4003.users.api.dto.AccountIdDto;
import ca.ulaval.glo4003.users.api.dto.UserDto;
import ca.ulaval.glo4003.users.services.UserService;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UserResourceImplementation implements UserResource {
  private final UserService userService;
  private final ParkingService parkingService;

  public UserResourceImplementation(UserService userService, ParkingService parkingService) {
    this.userService = userService;
    this.parkingService = parkingService;
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
  public Response addParkingSticker(String accountId, ParkingStickerDto parkingStickerDto) {
    parkingStickerDto.accountId = accountId;
    ParkingStickerCodeDto parkingStickerCodeDto =
        parkingService.addParkingSticker(parkingStickerDto);
    return Response.status(Response.Status.CREATED)
        .entity(parkingStickerCodeDto)
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
