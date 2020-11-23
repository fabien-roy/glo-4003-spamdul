package ca.ulaval.glo4003.accounts.infrastructure;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassBuilder.anAccessPass;
import static ca.ulaval.glo4003.accounts.helpers.AccountBuilder.anAccount;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.exceptions.NotFoundAccessPassException;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.accounts.exceptions.NotFoundAccountException;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;

public class AccountRepositoryInMemoryTest {
  private AccountRepository accountRepository;

  private final AccessPass accessPass = anAccessPass().build();
  private final Account account =
      anAccount().withAccessPasses(Collections.singletonList(accessPass)).build();

  @Before
  public void setUp() {
    accountRepository = new AccountRepositoryInMemory();
  }

  @Test
  public void whenSavingAccount_thenReturnId() {
    AccountId accountId = accountRepository.save(account);

    assertThat(accountId).isSameInstanceAs(account.getId());
  }

  @Test
  public void whenSavingAccount_thenAccountCanBeFound() {
    accountRepository.save(account);
    Account foundAccount = accountRepository.get(account.getId());

    assertThat(foundAccount).isSameInstanceAs(account);
  }

  @Test(expected = NotFoundAccountException.class)
  public void givenNonExistentAccount_whenGettingAccount_thenThrowNotFoundAccountException() {
    accountRepository.get(account.getId());
  }

  @Test(expected = NotFoundAccessPassException.class)
  public void
      givenNoAccountWithAccessPass_whenGettingAccessPass_thenThrowNotFoundAccessPassException() {
    accountRepository.getAccessPass(accessPass.getCode());
  }

  @Test
  public void whenGettingAccessPass_thenGetAccessPass() {
    accountRepository.save(account);

    AccessPass foundAccessPass = accountRepository.getAccessPass(accessPass.getCode());

    assertThat(foundAccessPass).isSameInstanceAs(accessPass);
  }

  @Test
  public void whenUpdatingAccount_thenAccountIsUpdated() {
    accountRepository.save(account);
    Account updatedAccount = anAccount().withId(account.getId()).build();

    accountRepository.update(updatedAccount);
    Account foundAccount = accountRepository.get(account.getId());

    assertThat(foundAccount).isNotSameInstanceAs(account);
  }

  @Test(expected = NotFoundAccountException.class)
  public void givenNonExistentAccount_whenUpdatingAccount_thenThrowNotFoundAccountException() {
    accountRepository.update(account);
  }
}
