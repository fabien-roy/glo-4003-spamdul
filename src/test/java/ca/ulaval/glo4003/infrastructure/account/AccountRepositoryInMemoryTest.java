package ca.ulaval.glo4003.infrastructure.account;

import ca.ulaval.glo4003.domain.account.Account;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountRepositoryInMemoryTest {
  @Mock private Account account; // TODO : Use AccountBuilderInstead

  private AccountRepository accountRepositoryInMemory;

  @Before
  public void setUp() {
    accountRepositoryInMemory = new AccountRepositoryInMemory();
  }

  @Test
  public void whenSavingAccount_thenAccountCanBeFound() {
    accountRepositoryInMemory.save(account);

    Account foundAccount = accountRepositoryInMemory.findById(account.getId());

    Truth.assertThat(foundAccount).isSameInstanceAs(account);
  }
}
