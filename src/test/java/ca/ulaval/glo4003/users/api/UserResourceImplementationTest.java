package ca.ulaval.glo4003.users.api;

import static ca.ulaval.glo4003.access.helper.AccessPassCodeDtoBuilder.anAccessPassCodeDtoBuilder;
import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.cars.helpers.CarBuilderDtoBuilder.aCarDto;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4003.access.api.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.access.api.dto.AccessPassDto;
import ca.ulaval.glo4003.access.services.AccessService;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.api.dto.CarDto;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.users.api.dto.AccountIdDto;
import ca.ulaval.glo4003.users.api.dto.UserDto;
import ca.ulaval.glo4003.users.services.UserService;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceImplementationTest {
  @Mock private UserDto userDto;
  @Mock private AccountIdDto accountIdDto;
  @Mock private UserService userService;
  @Mock private AccessService accessService;
  @Mock private CarService carService;
  @Mock private AccessPassDto accessPassDto;

  private AccountId accountId = createAccountId();
  private AccessPassCodeDto accessPassCodeDto = anAccessPassCodeDtoBuilder().build();
  private CarDto carDto = aCarDto().build();
  private UserResource userResource;

  private static final String ACCOUNT_ID = createAccountId().toString();

  @Before
  public void setUp() {
    userResource = new UserResourceImplementation(userService, accessService, carService);
  }

  @Test
  public void whenAddingUser_thenAddUserToService() {
    when(userService.addUser(userDto)).thenReturn(accountIdDto);

    Response response = userResource.addUser(userDto);
    AccountIdDto respondedAccountIdDto = (AccountIdDto) response.getEntity();

    Truth.assertThat(respondedAccountIdDto.accountId).isEqualTo(accountIdDto.accountId);
  }

  @Test
  public void whenAddingUser_thenResponseCreatedStatus() {
    when(userService.addUser(userDto)).thenReturn(accountIdDto);

    Response response = userResource.addUser(userDto);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  public void whenGettingUser_ThenGetUserFromService() {
    when(userService.getUser(ACCOUNT_ID)).thenReturn(userDto);

    Response response = userResource.getUser(ACCOUNT_ID);
    UserDto respondedUserDto = (UserDto) response.getEntity();

    Truth.assertThat(respondedUserDto.name).isEqualTo(userDto.name);
    Truth.assertThat(respondedUserDto.birthDate).isEqualTo(userDto.birthDate);
    Truth.assertThat(respondedUserDto.sex).isEqualTo(userDto.sex);
  }

  @Test
  public void whenGettingUser_thenResponseOkStatus() {
    when(userService.addUser(userDto)).thenReturn(accountIdDto);

    Response response = userResource.getUser(ACCOUNT_ID);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenAddingAccessPass_thenAddAccessPassToService() {
    when(accessService.addAccessPass(accessPassDto, accountId.toString()))
        .thenReturn(accessPassCodeDto);

    Response response = userResource.addAccessPass(accessPassDto, accountId.toString());
    AccessPassCodeDto respondedAccessPassCodeDto = (AccessPassCodeDto) response.getEntity();

    Truth.assertThat(respondedAccessPassCodeDto.accessPassCode)
        .isEqualTo(accessPassCodeDto.accessPassCode);
  }

  @Test
  public void whenAddingAccessPass_thenResponseCreatedStatus() {
    when(accessService.addAccessPass(accessPassDto, accountId.toString()))
        .thenReturn(accessPassCodeDto);

    Response response = userResource.addAccessPass(accessPassDto, accountId.toString());

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  public void whenAddingCar_thenAddCar() {
    userResource.addCar(carDto, accountId.toString());

    verify(carService).addCar(carDto, accountId.toString());
  }

  @Test
  public void whenAddingCar_thenRespondWithCreatedStatus() {
    Response response = userResource.addCar(carDto, accountId.toString());

    assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }
}
