package ca.ulaval.glo4003.api.user;

import static ca.ulaval.glo4003.domain.account.helpers.AccountMother.createAccountId;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4003.api.user.dto.AccountIdDto;
import ca.ulaval.glo4003.api.user.dto.UserDto;
import ca.ulaval.glo4003.domain.user.UserService;
import com.google.common.truth.Truth;
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
  public void whenAddingUser_ThenAddUserToService() {
    when(userService.addUser(userDto)).thenReturn(accountIdDto);

    AccountIdDto receivedAccountIdDto = userResource.addUser(userDto);

    Truth.assertThat(receivedAccountIdDto).isSameInstanceAs(accountIdDto);
  }

  @Test
  public void whenGettingUser_ThenGetUserFromService() {
    when(userService.getUser(ACCOUNT_ID)).thenReturn(userDto);

    UserDto receivedUserDto = userResource.getUser(ACCOUNT_ID);

    Truth.assertThat(receivedUserDto).isSameInstanceAs(userDto);
  }
}
