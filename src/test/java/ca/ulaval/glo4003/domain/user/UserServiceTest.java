package ca.ulaval.glo4003.domain.user;

import static ca.ulaval.glo4003.accounts.helpers.AccountBuilder.anAccount;
import static ca.ulaval.glo4003.api.user.helpers.UserDtoBuilder.aUserDto;
import static ca.ulaval.glo4003.domain.user.helpers.UserBuilder.aUser;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountFactory;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.api.user.dto.AccountIdDto;
import ca.ulaval.glo4003.api.user.dto.UserDto;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
  @Mock private AccountRepository accountRepository;
  @Mock private AccountFactory accountFactory;
  @Mock private AccountIdAssembler accountIdAssembler;
  @Mock private UserAssembler userAssembler;
  @Mock private AccountIdDto accountIdDto;

  private Account account;
  private User user;
  private UserDto userDto;

  private UserService userService;

  @Before
  public void setUp() {
    userService =
        new UserService(accountRepository, accountFactory, accountIdAssembler, userAssembler);

    account = anAccount().build();
    user = aUser().build();
    userDto = aUserDto().build();

    when(userAssembler.assemble(userDto)).thenReturn(user);
    when(accountFactory.createAccount(user)).thenReturn(account);
    when(accountRepository.save(account)).thenReturn(account.getId());
    when(accountIdAssembler.assemble(account.getId())).thenReturn(accountIdDto);

    when(accountIdAssembler.assemble(account.getId().toString())).thenReturn(account.getId());
    when(accountRepository.findById(account.getId())).thenReturn(account);
    when(userAssembler.assemble(account.getUser())).thenReturn(userDto);
  }

  @Test
  public void whenAddingUser_thenReturnAccountIdDto() {
    AccountIdDto receivedAccountIdDto = userService.addUser(userDto);

    Truth.assertThat(receivedAccountIdDto).isSameInstanceAs(accountIdDto);
  }

  @Test
  public void whenGettingUser_thenReturnUserDto() {
    UserDto receivedUserDto = userService.getUser(account.getId().toString());

    Truth.assertThat(receivedUserDto).isSameInstanceAs(userDto);
  }
}
