package ca.ulaval.glo4003.accounts.services.assemblers;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.exceptions.InvalidAccountIdException;
import ca.ulaval.glo4003.users.services.dto.AccountIdDto;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountIdAssemblerTest {
  private static final AccountId ACCOUNT_ID = createAccountId();

  private AccountIdAssembler accountIdAssembler;

  @Before
  public void setUp() {
    accountIdAssembler = new AccountIdAssembler();
  }

  @Test(expected = InvalidAccountIdException.class)
  public void givenInvalidAccountId_whenAssembling_thenThrowInvalidAccountIdException() {
    accountIdAssembler.assemble("invalidAccountId");
  }

  @Test(expected = InvalidAccountIdException.class)
  public void givenNullAccountId_whenAssembling_thenThrowInvalidAccountIdException() {
    accountIdAssembler.assemble((String) null);
  }

  @Test
  public void whenAssembling_thenReturnAccountId() {
    AccountId accountId = accountIdAssembler.assemble(ACCOUNT_ID.toString());

    Truth.assertThat(accountId).isEqualTo(ACCOUNT_ID);
  }

  @Test
  public void whenAssemblingDto_thenReturnAccountId() {
    AccountIdDto accountIdDto = accountIdAssembler.assemble(ACCOUNT_ID);

    Truth.assertThat(accountIdDto.accountId).isEqualTo(ACCOUNT_ID.toString());
  }
}
