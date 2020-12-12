package ca.ulaval.glo4003.accounts.api;

import ca.ulaval.glo4003.accounts.domain.exceptions.AccountException;
import ca.ulaval.glo4003.accounts.domain.exceptions.InvalidAccountIdException;
import ca.ulaval.glo4003.accounts.domain.exceptions.NotFoundAccountException;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class AccountExceptionMapperTest {

  private AccountExceptionMapper accountExceptionMapper;

  @Before
  public void setUp() {
    accountExceptionMapper = new AccountExceptionMapper();
  }

  @Test
  public void givenInvalidAccountIdException_whenResponding_thenStatusIsBadRequest() {
    AccountException accountException = new InvalidAccountIdException();

    Response response = accountExceptionMapper.toResponse(accountException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenNotFoundAccountIdException_whenResponding_thenStatusIsNotFound() {
    AccountException accountException = new NotFoundAccountException();

    Response response = accountExceptionMapper.toResponse(accountException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
  }
}
