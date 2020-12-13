package ca.ulaval.glo4003.accounts;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accounts.domain.AccountFactory;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import org.junit.Before;
import org.junit.Test;

public class AccountInjectorTest {

  private AccountInjector accountInjector;

  @Before
  public void setUp() {
    accountInjector = new AccountInjector();
  }

  @Test
  public void whenGettingAccountRepository_thenReturnIt() {
    AccountRepository accountRepository = accountInjector.getAccountRepository();

    assertThat(accountRepository).isNotNull();
  }

  @Test
  public void whenGettingAccountRepository_thenReturnTheSame() {
    AccountRepository firstAccountRepository = accountInjector.getAccountRepository();
    AccountRepository secondAccountRepository = accountInjector.getAccountRepository();

    assertThat(firstAccountRepository).isSameInstanceAs(secondAccountRepository);
  }

  @Test
  public void whenCreatingAccountFactory_thenReturnIt() {
    AccountFactory accountFactory = accountInjector.createAccountFactory();

    assertThat(accountFactory).isNotNull();
  }
}
