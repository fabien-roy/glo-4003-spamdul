package ca.ulaval.glo4003.infrastructure.account;

import static org.mockito.Mockito.mock;

import ca.ulaval.glo4003.domain.account.Account;
import com.google.common.truth.Truth;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class AccountRepositoryInMemoryTest {
  private Account account = mock(Account.class);

  private AccountRepositoryInMemory accountRepositoryInMemory;

  @Before
  public void setUp() {
    accountRepositoryInMemory = new AccountRepositoryInMemory();
  }

  @Test
  public void whenAddingAccount_thenAddAccountInMemory() {
    accountRepositoryInMemory.save(account);
    List<Account> accounts = new ArrayList<>(accountRepositoryInMemory.getUsers().values());
    Truth.assertThat(accounts).contains(account);
  }

  @Test
  public void whenFindingAccount_thenReturnsAccountFromMemory() {
    accountRepositoryInMemory.save(account);
    Account accountFound = accountRepositoryInMemory.findById(account.getAccountId());

    List<Account> accounts = new ArrayList<>(accountRepositoryInMemory.getUsers().values());
    Truth.assertThat(accounts).contains(accountFound);
  }
}
