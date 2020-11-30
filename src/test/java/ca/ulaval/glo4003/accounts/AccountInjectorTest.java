package ca.ulaval.glo4003.accounts;

import ca.ulaval.glo4003.accounts.domain.AccountFactory;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.accounts.services.assemblers.AccountIdAssembler;
import com.google.common.truth.Truth;
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

    Truth.assertThat(accountRepository).isNotNull();
  }

  @Test
  public void whenGettingAccountRepository_thenReturnTheSame() {
    AccountRepository firstAccountRepository = accountInjector.getAccountRepository();
    AccountRepository secondAccountRepository = accountInjector.getAccountRepository();

    Truth.assertThat(firstAccountRepository).isSameInstanceAs(secondAccountRepository);
  }

  @Test
  public void whenCreatingAccountFactory_thenReturnIt() {
    AccountFactory accountFactory = accountInjector.createAccountFactory();

    Truth.assertThat(accountFactory).isNotNull();
  }

  @Test
  public void whenCreatingAccountIdAssembler_thenReturnIt() {
    AccountIdAssembler accountIdAssembler = accountInjector.createAccountIdAssembler();

    Truth.assertThat(accountIdAssembler).isNotNull();
  }
}
