package ca.ulaval.glo4003.domain.user;

import static ca.ulaval.glo4003.domain.account.helpers.AccountMother.createAccountId;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4003.api.user.dto.UserDto;
import ca.ulaval.glo4003.domain.account.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

// TODO : Remove any() usage in this class, control what mocks return instead
// TODO : Use Builders instead of mocks for domain objects and DTOs
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
  @Mock private Account account;
  @Mock private UserDto userDto;
  @Mock private AccountRepository accountRepository;
  @Mock private AccountFactory accountFactory;
  @Mock private AccountIdAssembler accountIdAssembler;
  @Mock private UserAssembler userAssembler;

  private UserService userService;

  private static final AccountId ACCOUNT_ID = createAccountId();

  @Before
  public void setUp() {
    userService =
        new UserService(accountRepository, accountFactory, accountIdAssembler, userAssembler);
  }

  @Test
  public void whenAddingToRepository_thenAccountFactoryAndRepositoryIsCalled() {
    doReturn(ACCOUNT_ID).when(accountRepository).save(any());

    userService.addUser(userDto);

    verify(accountFactory).createAccount(userDto);
    verify(accountRepository).save(any()); // TODO : Remove any() usage
  }

  @Test
  public void whenGettingUserToRepository_thenAssemblerAndRepositoryIsCalled() {
    doReturn(account).when(accountRepository).findById(any());

    userService.getUser(ACCOUNT_ID.toString());

    verify(accountRepository).findById(any());
    verify(userAssembler).create(account.getUser());
  }
}
