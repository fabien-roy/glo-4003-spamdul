package ca.ulaval.glo4003.accounts.services.converters;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.domain.exceptions.InvalidAccountIdException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountIdConverterTest {
  private static final AccountId ACCOUNT_ID = createAccountId();

  private AccountIdConverter accountIdConverter;

  @Before
  public void setUp() {
    accountIdConverter = new AccountIdConverter();
  }

  @Test(expected = InvalidAccountIdException.class)
  public void givenInvalidAccountId_whenConverting_thenThrowInvalidAccountIdException() {
    accountIdConverter.convert("invalidAccountId");
  }

  @Test(expected = InvalidAccountIdException.class)
  public void givenNullAccountId_whenConverting_thenThrowInvalidAccountIdException() {
    accountIdConverter.convert(null);
  }

  @Test
  public void whenConverting_thenReturnAccountId() {
    AccountId accountId = accountIdConverter.convert(ACCOUNT_ID.toString());

    assertThat(accountId).isEqualTo(ACCOUNT_ID);
  }
}
