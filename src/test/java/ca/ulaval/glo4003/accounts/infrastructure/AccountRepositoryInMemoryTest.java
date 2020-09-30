package ca.ulaval.glo4003.accounts.infrastructure;

import static ca.ulaval.glo4003.accounts.helpers.AccountBuilder.anAccount;

import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.accounts.exceptions.NotFoundAccountException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class AccountRepositoryInMemoryTest {
  private Account account;

  private AccountRepository accountRepository;

  @Before
  public void setUp() {
    accountRepository = new AccountRepositoryInMemory();
    account = anAccount().build();
  }

  @Test
  public void whenSavingAccount_thenAccountCanBeFound() {
    accountRepository.save(account);

    Account foundAccount = accountRepository.findById(account.getId());

    Truth.assertThat(foundAccount).isSameInstanceAs(account);
  }

  @Test(expected = NotFoundAccountException.class)
  public void givenNonExistentAccount_whenGettingAccount_thenThrowNotFoundAccountException() {
    accountRepository.findById(account.getId());
  }

  @Test
  public void whenUpdatingAccount_thenAccountIsUpdated() {
    accountRepository.save(account);
    Account updatedAccount = anAccount().withId(account.getId()).build();

    accountRepository.update(updatedAccount);
    Account foundAccount = accountRepository.findById(account.getId());

    Truth.assertThat(foundAccount).isNotSameInstanceAs(account);
  }

  @Test(expected = NotFoundAccountException.class)
  public void givenNonExistentAccount_whenUpdatingAccount_thenThrowNotFoundAccountException() {
    accountRepository.update(account);
  }
}
