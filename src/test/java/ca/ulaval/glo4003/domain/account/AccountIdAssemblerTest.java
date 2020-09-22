package ca.ulaval.glo4003.domain.account;

import static ca.ulaval.glo4003.domain.account.helpers.AccountMother.createAccountId;

import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountIdAssemblerTest {
  private static final String INVALID_ACCOUNT_ID = "InvalidAccountId";
  private static final String ACCOUNT_ID =
      createAccountId()
          .toString(); // TODO : If it exists, change for a method in AccountDtoObjectMother

  private AccountIdAssembler accountIdAssembler;

  @Before
  public void setUp() {
    accountIdAssembler = new AccountIdAssembler();
  }

  @Test(expected = InvalidAccountIdException.class)
  public void givenInvalidAccountId_whenAssembling_thenThrowInvalidAccountIdException() {
    accountIdAssembler.assemble(INVALID_ACCOUNT_ID);
  }

  @Test
  public void whenAssembling_thenReturnAccountId() {
    AccountId accountId = accountIdAssembler.assemble(ACCOUNT_ID);

    Truth.assertThat(accountId.toString()).isEqualTo(ACCOUNT_ID);
  }
}
