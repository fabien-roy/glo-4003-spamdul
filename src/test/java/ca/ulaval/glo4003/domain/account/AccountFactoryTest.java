package ca.ulaval.glo4003.domain.account;

import static ca.ulaval.glo4003.api.user.helpers.UserDtoBuilder.aUserDto;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.api.user.dto.UserDto;
import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.domain.user.UserAssembler;
import ca.ulaval.glo4003.domain.user.exception.InvalidNameException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountFactoryTest {
  @Mock private AccountIdGenerator accountIdGenerator;
  @Mock private UserAssembler userAssembler;
  @Mock private AccountId accountId;
  @Mock private User user;

  private AccountFactory accountFactory;

  private UserDto userDto;

  @Before
  public void setUp() {
    accountFactory = new AccountFactory(accountIdGenerator, userAssembler);

    userDto = aUserDto().build();

    when(userAssembler.assemble(userDto)).thenReturn(user);
    when(accountIdGenerator.generate()).thenReturn(accountId);
  }

  @Test(expected = InvalidNameException.class)
  public void whenNullUserName_thenTrowInvalidNameException() {
    userDto = aUserDto().withoutName().build();

    accountFactory.createAccount(userDto);
  }

  @Test
  public void whenCreatingAccount_thenAccountHasAccountId() {
    Account account = accountFactory.createAccount(userDto);

    Truth.assertThat(account.getId()).isSameInstanceAs(accountId);
  }

  @Test
  public void whenCreatingAccount_thenAccountHasUser() {
    Account account = accountFactory.createAccount(userDto);

    Truth.assertThat(account.getUser()).isSameInstanceAs(user);
  }
}
