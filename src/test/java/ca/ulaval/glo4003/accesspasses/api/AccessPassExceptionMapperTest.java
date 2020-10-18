package ca.ulaval.glo4003.accesspasses.api;

import ca.ulaval.glo4003.accesspasses.exceptions.AccessPassException;
import ca.ulaval.glo4003.accesspasses.exceptions.InvalidAccessPassCodeException;
import ca.ulaval.glo4003.accesspasses.exceptions.InvalidAccessPeriodException;
import ca.ulaval.glo4003.accesspasses.exceptions.NotFoundAccessPassException;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class AccessPassExceptionMapperTest {
  private AccessPassExceptionMapper accessPassExceptionMapper;

  @Before
  public void setUp() {
    accessPassExceptionMapper = new AccessPassExceptionMapper();
  }

  @Test
  public void givenInvalidAccessPeriodException_whenResponding_thenStatusIsBadRequest() {
    AccessPassException invalidAccessPeriodException = new InvalidAccessPeriodException();

    Response response = accessPassExceptionMapper.toResponse(invalidAccessPeriodException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenNotFoundAccessPassException_whenResponding_thenStatusIsNotFound() {
    AccessPassException invalidAccessPeriodException = new NotFoundAccessPassException();

    Response response = accessPassExceptionMapper.toResponse(invalidAccessPeriodException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
  }

  @Test
  public void givenInvalidAccessCodePassException_whenResponding_thenStatusIsBadRequest() {
    AccessPassException invalidAccessPeriodException = new InvalidAccessPassCodeException();

    Response response = accessPassExceptionMapper.toResponse(invalidAccessPeriodException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
