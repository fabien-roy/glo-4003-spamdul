package ca.ulaval.glo4003.accounts.services.assemblers;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.users.services.dto.AccountIdDto;
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

  @Test
  public void whenAssembling_thenReturnAccountId() {
    AccountIdDto accountIdDto = accountIdAssembler.assemble(ACCOUNT_ID);

    assertThat(accountIdDto.accountId).isEqualTo(ACCOUNT_ID.toString());
  }
}
