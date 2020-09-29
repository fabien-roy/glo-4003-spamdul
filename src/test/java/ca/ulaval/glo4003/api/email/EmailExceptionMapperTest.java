package ca.ulaval.glo4003.api.email;

import ca.ulaval.glo4003.domain.Email.exception.EmailException;
import ca.ulaval.glo4003.domain.Email.exception.EmailSendingFailedException;
import ca.ulaval.glo4003.domain.Email.exception.InvalidEmailAddressException;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class EmailExceptionMapperTest {

  private EmailExceptionMapper emailExceptionMapper;

  @Before
  public void setUp() {
    emailExceptionMapper = new EmailExceptionMapper();
  }

  @Test
  public void givenInvalidEmailAddressException_whenResponding_thenStatusIsBadRequest() {
    EmailException emailException = new InvalidEmailAddressException();

    Response response = emailExceptionMapper.toResponse(emailException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenEmailSendingFailedException_whenResponding_thenStatusIsInternalServerError() {
    EmailException emailException = new EmailSendingFailedException();

    Response response = emailExceptionMapper.toResponse(emailException);

    Truth.assertThat(response.getStatus())
        .isEqualTo(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
  }
}
