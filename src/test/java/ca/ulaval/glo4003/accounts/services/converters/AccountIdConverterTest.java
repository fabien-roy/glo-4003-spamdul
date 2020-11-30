package ca.ulaval.glo4003.accounts.services.converters;

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
    accountIdConverter.convert((String) null);
  }

  @Test
  public void whenConverting_thenReturnAccountId() {
    AccountId accountId = accountIdConverter.convert(ACCOUNT_ID.toString());

    Truth.assertThat(accountId).isEqualTo(ACCOUNT_ID);
  }

  @Test
  public void whenConvertingDto_thenReturnAccountId() {
    AccountIdDto accountIdDto = accountIdConverter.convert(ACCOUNT_ID);

    Truth.assertThat(accountIdDto.accountId).isEqualTo(ACCOUNT_ID.toString());
  }
}
