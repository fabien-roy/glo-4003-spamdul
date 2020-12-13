package ca.ulaval.glo4003.users.api;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassCodeDtoBuilder.anAccessPassCodeDto;
import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassDtoBuilder.anAccessPassDto;
import static ca.ulaval.glo4003.accesspasses.helpers.BicycleAccessPassDtoBuilder.aBicycleAccessPassDto;
import static ca.ulaval.glo4003.accounts.helpers.AccountIdDtoBuilder.anAccountIdDto;
import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.cars.helpers.CarDtoBuilder.aCarDto;
import static ca.ulaval.glo4003.funds.helpers.BillDtoBuilder.aBillDto;
import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillId;
import static ca.ulaval.glo4003.funds.helpers.BillPaymentDtoBuilder.aBillPaymentDto;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerCodeDtoBuilder.aParkingStickerCodeDto;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerDtoBuilder.aParkingStickerDto;
import static ca.ulaval.glo4003.users.helpers.UserDtoBuilder.aUserDto;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassDto;
import ca.ulaval.glo4003.accesspasses.services.dto.BicycleAccessPassDto;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.cars.services.dto.CarDto;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.funds.services.dto.BillDto;
import ca.ulaval.glo4003.funds.services.dto.BillPaymentDto;
import ca.ulaval.glo4003.parkings.services.ParkingStickerService;
import ca.ulaval.glo4003.parkings.services.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.parkings.services.dto.ParkingStickerDto;
import ca.ulaval.glo4003.users.services.UserService;
import ca.ulaval.glo4003.users.services.dto.AccountIdDto;
import ca.ulaval.glo4003.users.services.dto.UserDto;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceTest {
  @Mock private UserService userService;
  @Mock private AccessPassService accessPassService;
  @Mock private CarService carService;
  @Mock private AccountService accountService;
  @Mock private ParkingStickerService parkingStickerService;
  @Mock private BillService billService;

  private UserResource userResource;

  private final AccountId accountId = createAccountId();
  private final BillId billId = createBillId();
  private final BillDto billDto = aBillDto().build();
  private final BillPaymentDto billPaymentDto = aBillPaymentDto().build();
  private final CarDto carDto = aCarDto().build();
  private final UserDto userDto = aUserDto().build();
  private final AccountIdDto accountIdDto = anAccountIdDto().build();
  private final AccessPassDto accessPassDto = anAccessPassDto().build();
  private final BicycleAccessPassDto bicycleAccessPassDto = aBicycleAccessPassDto().build();
  private final AccessPassCodeDto accessPassCodeDto = anAccessPassCodeDto().build();
  private final ParkingStickerDto parkingStickerDto = aParkingStickerDto().build();
  private final ParkingStickerCodeDto parkingStickerCodeDto = aParkingStickerCodeDto().build();

  @Before
  public void setUp() {
    userResource =
        new UserResource(
            userService,
            accessPassService,
            carService,
            accountService,
            parkingStickerService,
            billService);

    when(userService.getUser(accountId.toString())).thenReturn(userDto);
    when(userService.addUser(userDto)).thenReturn(accountIdDto);
    when(accountService.getBills(accountId.toString()))
        .thenReturn(Collections.singletonList(billDto));
    when(billService.payBill(billPaymentDto, accountId.toString(), billId.toString()))
        .thenReturn(billDto);
    when(carService.getCars(accountId.toString())).thenReturn(Collections.singletonList(carDto));
    when(accessPassService.addAccessPass(accessPassDto, accountId.toString()))
        .thenReturn(accessPassCodeDto);
    when(accessPassService.addAccessPass(bicycleAccessPassDto, accountId.toString()))
        .thenReturn(accessPassCodeDto);
    when(parkingStickerService.addParkingSticker(parkingStickerDto, accountId.toString()))
        .thenReturn(parkingStickerCodeDto);
  }

  @Test
  public void whenAddingUser_thenAddUserToService() {
    when(userService.addUser(userDto)).thenReturn(accountIdDto);

    Response response = userResource.addUser(userDto);
    AccountIdDto respondedAccountIdDto = (AccountIdDto) response.getEntity();

    assertThat(respondedAccountIdDto.accountId).isEqualTo(accountIdDto.accountId);
  }

  @Test
  public void whenAddingUser_thenRespondCreatedStatus() {
    when(userService.addUser(userDto)).thenReturn(accountIdDto);

    Response response = userResource.addUser(userDto);

    assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  public void whenGettingUser_ThenGetUserFromService() {
    Response response = userResource.getUser(accountId.toString());
    UserDto respondedUserDto = (UserDto) response.getEntity();

    assertThat(respondedUserDto).isSameInstanceAs(userDto);
  }

  @Test
  public void whenGettingUser_thenRespondOkStatus() {
    Response response = userResource.getUser(accountId.toString());

    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenGettingBills_thenGetBillsFromService() {
    Response response = userResource.getBills(accountId.toString());
    List<BillDto> respondedBillDtos = (List<BillDto>) response.getEntity();

    assertThat(respondedBillDtos).hasSize(1);
    assertThat(respondedBillDtos.get(0)).isSameInstanceAs(billDto);
  }

  @Test
  public void whenGettingBills_thenRespondOkStatus() {
    Response response = userResource.getBills(accountId.toString());

    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenPayingBill_thenGetBillFromService() {
    Response response =
        userResource.payBill(accountId.toString(), billId.toString(), billPaymentDto);
    BillDto respondedBillDto = (BillDto) response.getEntity();

    assertThat(respondedBillDto).isSameInstanceAs(billDto);
  }

  @Test
  public void whenPayingBill_thenRespondOkStatus() {
    Response response =
        userResource.payBill(accountId.toString(), billId.toString(), billPaymentDto);

    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenAddingCar_thenAddCarToService() {
    userResource.addCar(accountId.toString(), carDto);

    verify(carService).addCar(carDto, accountId.toString());
  }

  @Test
  public void whenAddingCar_thenRespondCreatedStatus() {
    Response response = userResource.addCar(accountId.toString(), carDto);

    assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  public void whenGettingCars_thenGetCarsFromService() {
    Response response = userResource.getCars(accountId.toString());
    List<CarDto> respondedCarDtos = (List<CarDto>) response.getEntity();

    assertThat(respondedCarDtos).hasSize(1);
    assertThat(respondedCarDtos.get(0)).isSameInstanceAs(carDto);
  }

  @Test
  public void whenGettingCars_thenRespondOkStatus() {
    Response response = userResource.getCars(accountId.toString());

    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenAddingAccessPass_thenAddAccessPassToService() {
    Response response = userResource.addAccessPass(accountId.toString(), accessPassDto);
    AccessPassCodeDto respondedAccessPassCodeDto = (AccessPassCodeDto) response.getEntity();

    assertThat(respondedAccessPassCodeDto).isSameInstanceAs(accessPassCodeDto);
  }

  @Test
  public void whenAddingAccessPass_thenRespondCreatedStatus() {
    Response response = userResource.addAccessPass(accountId.toString(), accessPassDto);

    assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  public void whenAddingParkingSticker_thenAddParkingStickerToService() {
    Response response = userResource.addParkingSticker(accountId.toString(), parkingStickerDto);
    ParkingStickerCodeDto respondedParkingStickerCodeDto =
        (ParkingStickerCodeDto) response.getEntity();

    assertThat(respondedParkingStickerCodeDto).isSameInstanceAs(parkingStickerCodeDto);
  }

  @Test
  public void whenAddingParkingSticker_thenRespondCreatedStatus() {
    Response response = userResource.addParkingSticker(accountId.toString(), parkingStickerDto);

    assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  public void whenAddingBicycleAccessPass_thenAddBicycleAccessPassToService() {
    Response response =
        userResource.addBicycleAccessPass(accountId.toString(), bicycleAccessPassDto);
    AccessPassCodeDto respondedAccessPassCodeDto = (AccessPassCodeDto) response.getEntity();

    assertThat(respondedAccessPassCodeDto).isSameInstanceAs(accessPassCodeDto);
  }

  @Test
  public void whenAddingBicycleAccessPass_thenRespondCreatedStatus() {
    Response response =
        userResource.addBicycleAccessPass(accountId.toString(), bicycleAccessPassDto);

    assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }
}
