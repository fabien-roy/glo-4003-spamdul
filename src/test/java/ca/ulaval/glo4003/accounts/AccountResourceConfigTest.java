package ca.ulaval.glo4003.accounts;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountFactory;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.accounts.services.AccountService;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class AccountResourceConfigTest {

  private AccountResourceConfig accountResourceConfig;

  @Before
  public void setUp() {
    accountResourceConfig = new AccountResourceConfig();
  }

  @Test
  public void whenGettingAccountRepository_thenReturnIt() {
    AccountRepository accountRepository = accountResourceConfig.getAccountRepository();

    Truth.assertThat(accountRepository).isNotNull();
  }

  @Test
  public void whenGettingAccountRepository_thenReturnTheSame() {
    AccountRepository firstAccountRepository = accountResourceConfig.getAccountRepository();
    AccountRepository secondAccountRepository = accountResourceConfig.getAccountRepository();

    Truth.assertThat(firstAccountRepository).isSameInstanceAs(secondAccountRepository);
  }

  @Test
  public void whenCreatingAccountFactory_thenReturnIt() {
    AccountFactory accountFactory = accountResourceConfig.createAccountFactory();

    Truth.assertThat(accountFactory).isNotNull();
  }

  @Test
  public void whenCreatingAccountIdAssembler_thenReturnIt() {
    AccountIdAssembler accountIdAssembler = accountResourceConfig.createAccountIdAssembler();

    Truth.assertThat(accountIdAssembler).isNotNull();
  }

  @Test
  public void whenCreatingAccountService_thenReturnIt() {
    AccountService accountService = accountResourceConfig.createAccountService();

    Truth.assertThat(accountService).isNotNull();
  }
}
