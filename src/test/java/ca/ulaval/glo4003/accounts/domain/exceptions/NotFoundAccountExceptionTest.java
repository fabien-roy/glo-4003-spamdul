package ca.ulaval.glo4003.accounts.domain.exceptions;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import org.junit.Before;
import org.junit.Test;

public class NotFoundAccountExceptionTest {

  private ApplicationException exception;

  private final AccountId accountId = createAccountId();

  @Before
  public void setUp() {
    exception = new NotFoundAccountException(accountId);
  }

  @Test
  public void whenGettingDescription_thenWriteAccountId() {
    String expectedDescription =
        String.format("Account with id %s was not found", accountId.toString());

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
