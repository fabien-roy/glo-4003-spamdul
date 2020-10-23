package ca.ulaval.glo4003.users.api;

import ca.ulaval.glo4003.accesspasses.api.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.accesspasses.api.dto.AccessPassDto;
import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.api.dto.CarDto;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.funds.api.dto.BillDto;
import ca.ulaval.glo4003.funds.api.dto.BillPaymentDto;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerDto;
import ca.ulaval.glo4003.parkings.services.ParkingStickerService;
import ca.ulaval.glo4003.users.api.dto.AccountIdDto;
import ca.ulaval.glo4003.users.api.dto.UserDto;
import ca.ulaval.glo4003.users.services.UserService;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UserResourceImplementation implements UserResource {
  private final AccountService accountService;
  private final AccessPassService accessPassService;
  private final CarService carService;
  private final UserService userService;
  private final ParkingStickerService parkingStickerService;

  public UserResourceImplementation(
      UserService userService,
      AccessPassService accessPassService,
      CarService carService,
      AccountService accountService,
      ParkingStickerService parkingStickerService) {
    this.userService = userService;
    this.accessPassService = accessPassService;
    this.carService = carService;
    this.accountService = accountService;
    this.parkingStickerService = parkingStickerService;
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
        parkingStickerService.addParkingSticker(parkingStickerDto);
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

  @Override
  public Response addAccessPass(AccessPassDto accessPassDto, String accountId) {
    AccessPassCodeDto accessPassCode = accessPassService.addAccessPass(accessPassDto, accountId);

    return Response.status(Response.Status.CREATED)
        .entity(accessPassCode)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @Override
  public Response addCar(CarDto carDto, String accountId) {
    carService.addCar(carDto, accountId);
    return Response.status(Response.Status.CREATED).build();
  }

  @Override
  public Response getCars(String accountId) {
    List<CarDto> carsDto = accountService.getCars(accountId);
    GenericEntity<List<CarDto>> entities = new GenericEntity<List<CarDto>>(carsDto) {};

    return Response.status(Response.Status.OK)
        .entity(entities)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @Override
  public Response getBills(String accountId) {
    List<BillDto> billsDto = accountService.getBills(accountId);
    GenericEntity<List<BillDto>> entities = new GenericEntity<List<BillDto>>(billsDto) {};

    return Response.status(Response.Status.OK)
        .entity(entities)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @Override
  public Response payBill(BillPaymentDto billPaymentDto, String accountId, String billId) {
    BillDto billDto = accountService.payBill(billPaymentDto, accountId, billId);

    return Response.status(Response.Status.OK)
        .entity(billDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
