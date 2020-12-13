package ca.ulaval.glo4003.accounts.domain;

import static ca.ulaval.glo4003.users.helpers.UserBuilder.aUser;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.users.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountFactoryTest {
  @Mock private AccountIdGenerator accountIdGenerator;
  @Mock private AccountId accountId;

  private AccountFactory accountFactory;

  private User user;

  @Before
  public void setUp() {
    accountFactory = new AccountFactory(accountIdGenerator);

    user = aUser().build();

    when(accountIdGenerator.generate()).thenReturn(accountId);
  }

  @Test
  public void whenCreatingAccount_thenAccountHasAccountId() {
    Account account = accountFactory.createAccount(user);

    assertThat(account.getId()).isSameInstanceAs(accountId);
  }

  @Test
  public void whenCreatingAccount_thenAccountHasUser() {
    Account account = accountFactory.createAccount(user);

    assertThat(account.getUser()).isSameInstanceAs(user);
  }
}
