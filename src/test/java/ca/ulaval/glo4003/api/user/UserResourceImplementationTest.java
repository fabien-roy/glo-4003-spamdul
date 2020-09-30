package ca.ulaval.glo4003.api.user;

import static ca.ulaval.glo4003.domain.account.helpers.AccountMother.createAccountId;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4003.api.user.dto.AccountIdDto;
import ca.ulaval.glo4003.api.user.dto.UserDto;
import ca.ulaval.glo4003.domain.user.UserService;
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

  private UserResource userResource;

  private static final String ACCOUNT_ID = createAccountId().toString();

  @Before
  public void setUp() {
    userResource = new UserResourceImplementation(userService);
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
}
