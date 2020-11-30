package ca.ulaval.glo4003.users.api;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassCodeDtoBuilder.anAccessPassCodeDto;
import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassDtoBuilder.anAccessPassDto;
import static ca.ulaval.glo4003.accounts.helpers.AccountIdDtoBuilder.anAccountIdDto;
import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.cars.helpers.CarDtoBuilder.aCarDto;
import static ca.ulaval.glo4003.users.helpers.UserDtoBuilder.aUserDto;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassDto;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.api.dto.CarDto;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.parkings.services.ParkingStickerService;
import ca.ulaval.glo4003.users.api.dto.AccountIdDto;
import ca.ulaval.glo4003.users.api.dto.UserDto;
import ca.ulaval.glo4003.users.services.UserService;
import java.util.ArrayList;
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

  private UserResource userResource;

  private static final String ACCOUNT_ID = createAccountId().toString();
  private final AccountId accountId = createAccountId();
  private final AccessPassCodeDto accessPassCodeDto = anAccessPassCodeDto().build();
  private final CarDto carDto = aCarDto().build();
  private final UserDto userDto = aUserDto().build();
  private final AccountIdDto accountIdDto = anAccountIdDto().build();
  private final AccessPassDto accessPassDto = anAccessPassDto().build();

  @Before
  public void setUp() {
    userResource =
        new UserResource(
            userService, accessPassService, carService, accountService, parkingStickerService);
  }

  @Test
  public void whenAddingUser_thenAddUserToService() {
    when(userService.addUser(userDto)).thenReturn(accountIdDto);

    Response response = userResource.addUser(userDto);
    AccountIdDto respondedAccountIdDto = (AccountIdDto) response.getEntity();

    assertThat(respondedAccountIdDto.accountId).isEqualTo(accountIdDto.accountId);
  }

  @Test
  public void whenAddingUser_thenResponseCreatedStatus() {
    when(userService.addUser(userDto)).thenReturn(accountIdDto);

    Response response = userResource.addUser(userDto);

    assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  public void whenGettingUser_ThenGetUserFromService() {
    when(userService.getUser(ACCOUNT_ID)).thenReturn(userDto);

    Response response = userResource.getUser(ACCOUNT_ID);
    UserDto respondedUserDto = (UserDto) response.getEntity();

    assertThat(respondedUserDto.name).isEqualTo(userDto.name);
    assertThat(respondedUserDto.birthDate).isEqualTo(userDto.birthDate);
    assertThat(respondedUserDto.sex).isEqualTo(userDto.sex);
  }

  @Test
  public void whenGettingUser_thenResponseOkStatus() {
    when(userService.addUser(userDto)).thenReturn(accountIdDto);

    Response response = userResource.getUser(ACCOUNT_ID);

    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenAddingAccessPass_thenAddAccessPassToService() {
    when(accessPassService.addAccessPass(accessPassDto, accountId.toString()))
        .thenReturn(accessPassCodeDto);

    Response response = userResource.addAccessPass(accountId.toString(), accessPassDto);
    AccessPassCodeDto respondedAccessPassCodeDto = (AccessPassCodeDto) response.getEntity();

    assertThat(respondedAccessPassCodeDto.accessPassCode)
        .isEqualTo(accessPassCodeDto.accessPassCode);
  }

  @Test
  public void whenAddingAccessPass_thenResponseCreatedStatus() {
    when(accessPassService.addAccessPass(accessPassDto, accountId.toString()))
        .thenReturn(accessPassCodeDto);

    Response response = userResource.addAccessPass(accountId.toString(), accessPassDto);

    assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  public void whenAddingCar_thenAddCar() {
    userResource.addCar(accountId.toString(), carDto);

    verify(carService).addCar(carDto, accountId.toString());
  }

  @Test
  public void whenAddingCar_thenRespondWithCreatedStatus() {
    Response response = userResource.addCar(accountId.toString(), carDto);

    assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  public void whenGettingBills_thenGetBills() {
    userResource.getBills(accountId.toString());

    verify(accountService).getBills(accountId.toString());
  }

  @Test
  public void whenGettingBills_thenRespondWithOkStatus() {
    Response response = userResource.getBills(accountId.toString());

    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenGettingCars_thenGetCars() {
    List<CarDto> carsDto = new ArrayList<>();
    carsDto.add(carDto);
    carsDto.add(carDto);
    when(carService.getCars(accountId.toString())).thenReturn(carsDto);

    Response response = userResource.getCars(accountId.toString());
    Object respondedCarsDto = response.getEntity();

    assertThat(respondedCarsDto).isEqualTo(carsDto);
  }

  @Test
  public void whenGettingCars_thenRespondWithOkStatus() {
    Response response = userResource.getCars(accountId.toString());

    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }
}
