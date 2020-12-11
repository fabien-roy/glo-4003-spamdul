package ca.ulaval.glo4003.accesspasses.api;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accesspasses.domain.exceptions.*;
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

    assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenNotFoundAccessPassException_whenResponding_thenStatusIsNotFound() {
    AccessPassException invalidAccessPeriodException = new NotFoundAccessPassException();

    Response response = accessPassExceptionMapper.toResponse(invalidAccessPeriodException);

    assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
  }

  @Test
  public void givenInvalidAccessCodePassException_whenResponding_thenStatusIsBadRequest() {
    AccessPassException invalidAccessPeriodException = new InvalidAccessPassCodeException();

    Response response = accessPassExceptionMapper.toResponse(invalidAccessPeriodException);

    assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenUnsupportedAccessPeriodException_whenResponding_thenStatusIsNotImplemented() {
    AccessPassException unsupportedAccessPeriod = new UnsupportedAccessPeriodException();

    Response response = accessPassExceptionMapper.toResponse(unsupportedAccessPeriod);

    assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_IMPLEMENTED.getStatusCode());
  }

  @Test
  public void givenWrongAmountSemestersException_whenResponding_thenStatusIsBadRequest() {
    AccessPassException wrongAmountOfSemestersException =
        new WrongAmountOfSemestersForPeriodException();

    Response response = accessPassExceptionMapper.toResponse(wrongAmountOfSemestersException);

    assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
