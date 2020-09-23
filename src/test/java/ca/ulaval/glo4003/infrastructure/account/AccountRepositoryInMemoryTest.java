package ca.ulaval.glo4003.infrastructure.account;

import static ca.ulaval.glo4003.domain.account.helpers.AccountBuilder.anAccount;

import ca.ulaval.glo4003.domain.account.Account;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import ca.ulaval.glo4003.domain.account.NotFoundAccountException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class AccountRepositoryInMemoryTest {
  private Account account;

  private AccountRepository accountRepositoryInMemory;

  @Before
  public void setUp() {
    accountRepositoryInMemory = new AccountRepositoryInMemory();
    account = anAccount().build();
  }

  @Test
  public void whenSavingAccount_thenAccountCanBeFound() {
    accountRepositoryInMemory.save(account);

    Account foundAccount = accountRepositoryInMemory.findById(account.getId());

    Truth.assertThat(foundAccount).isSameInstanceAs(account);
  }

  @Test
  public void whenUpdatingAccount_thenAccountIsUpdated() {
    accountRepositoryInMemory.save(account);
    Account updatedAccount = anAccount().withId(account.getId()).build();

    accountRepositoryInMemory.update(updatedAccount);
    Account foundAccount = accountRepositoryInMemory.findById(account.getId());

    Truth.assertThat(foundAccount).isNotSameInstanceAs(account);
  }

  @Test(expected = NotFoundAccountException.class)
  public void givenNonExistentAccount_whenUpdatingAccount_thenThrowNotFoundAccountException() {
    accountRepositoryInMemory.update(account);
  }
}
