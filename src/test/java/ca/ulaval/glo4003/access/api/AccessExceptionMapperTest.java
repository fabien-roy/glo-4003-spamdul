package ca.ulaval.glo4003.access.api;

import ca.ulaval.glo4003.access.exceptions.AccessException;
import ca.ulaval.glo4003.access.exceptions.InvalidAccessPassCodeException;
import ca.ulaval.glo4003.access.exceptions.InvalidAccessPeriodException;
import ca.ulaval.glo4003.access.exceptions.NotFoundAccessPassException;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class AccessExceptionMapperTest {
  private AccessExceptionMapper accessExceptionMapper;

  @Before
  public void setUp() {
    accessExceptionMapper = new AccessExceptionMapper();
  }

  @Test
  public void givenInvalidAccessPeriodException_whenResponding_thenStatusIsBadRequest() {
    AccessException invalidAccessPeriodException = new InvalidAccessPeriodException();

    Response response = accessExceptionMapper.toResponse(invalidAccessPeriodException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenNotFoundAccessPassException_whenResponding_thenStatusIsNotFound() {
    AccessException invalidAccessPeriodException = new NotFoundAccessPassException();

    Response response = accessExceptionMapper.toResponse(invalidAccessPeriodException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
  }

  @Test
  public void givenInvalidAccessCodePassException_whenResponding_thenStatusIsBadRequest() {
    AccessException invalidAccessPeriodException = new InvalidAccessPassCodeException();

    Response response = accessExceptionMapper.toResponse(invalidAccessPeriodException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
