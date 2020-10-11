package ca.ulaval.glo4003.users.api;

import ca.ulaval.glo4003.access.api.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.access.api.dto.AccessPassDto;
import ca.ulaval.glo4003.access.services.AccessService;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.api.dto.CarDto;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.funds.api.dto.BillDto;
import ca.ulaval.glo4003.funds.api.dto.BillsDto;
import ca.ulaval.glo4003.funds.api.dto.PayBillDto;
import ca.ulaval.glo4003.users.api.dto.AccountIdDto;
import ca.ulaval.glo4003.users.api.dto.UserDto;
import ca.ulaval.glo4003.users.services.UserService;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UserResourceImplementation implements UserResource {
  private final UserService userService;
  private final AccessService accessService;
  private final CarService carService;
  private final AccountService accountService;

  public UserResourceImplementation(
      UserService userService,
      AccessService accessService,
      CarService carService,
      AccountService accountService) {
    this.userService = userService;
    this.accessService = accessService;
    this.carService = carService;
    this.accountService = accountService;
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
    AccessPassCodeDto accessPassCode = accessService.addAccessPass(accessPassDto, accountId);

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
  public Response getBills(String accountId) {
    BillsDto billsDto = accountService.getBills(accountId);

    return Response.status(Response.Status.OK)
        .entity(billsDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @Override
  public Response payBill(PayBillDto payBillDto, String accountId, String billId) {
    BillDto billDto = accountService.payBill(payBillDto, accountId, billId);

    return Response.status(Response.Status.OK)
        .entity(billDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
