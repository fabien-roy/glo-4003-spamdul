package ca.ulaval.glo4003.domain.user;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4003.api.contact.dto.UserDto;
import ca.ulaval.glo4003.domain.account.*;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {

  private Account account = mock(Account.class);
  private UserDto userDto = mock(UserDto.class);
  private AccountRepository accountRepository = mock(AccountRepository.class);
  private AccountFactory accountFactory = mock(AccountFactory.class);
  private UserAssembler userAssembler = mock(UserAssembler.class);
  private UserService userService;

  private final String ANY_ID = "2";

  @Before
  public void setUp() {
    userService = new UserService(accountRepository, accountFactory, userAssembler);
  }

  @Test
  public void whenAddingToRepository_thenAccountFactoryAndRepositoryIsCalled()
      throws AccountValidationError {
    userService.addUser(userDto);

    verify(accountFactory).createAccount(userDto);
    verify(accountRepository).save(any());
  }

  @Test
  public void whenGettingUserToRepository_thenAssemblerAndRepositoryIsCalled() {
    doReturn(account).when(accountRepository).findById(any());

    userService.getUser(ANY_ID);

    verify(accountRepository).findById(any());
    verify(userAssembler).create(account);
  }
}
