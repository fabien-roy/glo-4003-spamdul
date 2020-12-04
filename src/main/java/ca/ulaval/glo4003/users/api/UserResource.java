package ca.ulaval.glo4003.users.api;

import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassDto;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.cars.services.dto.CarDto;
import ca.ulaval.glo4003.funds.services.dto.BillDto;
import ca.ulaval.glo4003.funds.services.dto.BillPaymentDto;
import ca.ulaval.glo4003.parkings.services.ParkingStickerService;
import ca.ulaval.glo4003.parkings.services.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.parkings.services.dto.ParkingStickerDto;
import ca.ulaval.glo4003.users.services.UserService;
import ca.ulaval.glo4003.users.services.dto.AccountIdDto;
import ca.ulaval.glo4003.users.services.dto.UserDto;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {
  private final AccountService accountService;
  private final AccessPassService accessPassService;
  private final CarService carService;
  private final UserService userService;
  private final ParkingStickerService parkingStickerService;

  public UserResource(
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

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response addUser(UserDto userDto) {
    AccountIdDto accountIdDto = userService.addUser(userDto);
    return Response.status(Response.Status.CREATED)
        .entity(accountIdDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{accountId}/parkingStickers")
  public Response addParkingSticker(
      @PathParam("accountId") String accountId, ParkingStickerDto parkingStickerDto) {
    parkingStickerDto.accountId = accountId;
    ParkingStickerCodeDto parkingStickerCodeDto =
        parkingStickerService.addParkingSticker(parkingStickerDto);
    return Response.status(Response.Status.CREATED)
        .entity(parkingStickerCodeDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{accountId}")
  public Response getUser(@PathParam("accountId") String accountId) {
    UserDto userDto = userService.getUser(accountId);
    return Response.status(Response.Status.OK)
        .entity(userDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{accountId}/accessPasses")
  public Response addAccessPass(
      @PathParam("accountId") String accountId, AccessPassDto accessPassDto) {
    AccessPassCodeDto accessPassCode = accessPassService.addAccessPass(accessPassDto, accountId);

    return Response.status(Response.Status.CREATED)
        .entity(accessPassCode)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{accountId}/cars")
  public Response addCar(@PathParam("accountId") String accountId, CarDto carDto) {
    carService.addCar(carDto, accountId);
    return Response.status(Response.Status.CREATED).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{accountId}/cars")
  public Response getCars(@PathParam("accountId") String accountId) {
    List<CarDto> carsDto = carService.getCars(accountId);
    GenericEntity<List<CarDto>> entities = new GenericEntity<List<CarDto>>(carsDto) {};

    return Response.status(Response.Status.OK)
        .entity(entities)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{accountId}/bills")
  public Response getBills(@PathParam("accountId") String accountId) {
    List<BillDto> billsDto = accountService.getBills(accountId);
    GenericEntity<List<BillDto>> entities = new GenericEntity<List<BillDto>>(billsDto) {};

    return Response.status(Response.Status.OK)
        .entity(entities)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{accountId}/bills/{billId}")
  public Response payBill(
      @PathParam("accountId") String accountId,
      @PathParam("billId") String billId,
      BillPaymentDto billPaymentDto) {
    BillDto billDto = accountService.payBill(billPaymentDto, accountId, billId);

    return Response.status(Response.Status.OK)
        .entity(billDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
