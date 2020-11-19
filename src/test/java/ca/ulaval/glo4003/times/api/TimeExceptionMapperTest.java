package ca.ulaval.glo4003.times.api;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.times.exceptions.*;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class TimeExceptionMapperTest {

  private TimeExceptionMapper timeExceptionMapper;

  @Before
  public void setUp() {
    timeExceptionMapper = new TimeExceptionMapper();
  }

  @Test
  public void givenInvalidDateException_whenResponding_thenStatusIsBadRequest() {
    TimeException timeException = new InvalidDateException();

    Response response = timeExceptionMapper.toResponse(timeException);

    assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenInvalidDateTimeException_whenResponding_thenStatusIsBadRequest() {
    TimeException timeException = new InvalidDateTimeException();

    Response response = timeExceptionMapper.toResponse(timeException);

    assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenInvalidDayOfWeekException_whenResponding_thenStatusIsBadRequest() {
    TimeException timeException = new InvalidDayOfWeekException();

    Response response = timeExceptionMapper.toResponse(timeException);

    assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenSemesterNotFoundException_whenResponding_thenStatusIsNotFound() {
    TimeException timeException = new SemesterNotFoundException();

    Response response = timeExceptionMapper.toResponse(timeException);

    assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
  }
}
