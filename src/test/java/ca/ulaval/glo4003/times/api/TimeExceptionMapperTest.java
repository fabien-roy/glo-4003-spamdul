package ca.ulaval.glo4003.times.api;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.times.exceptions.InvalidDateException;
import ca.ulaval.glo4003.times.exceptions.InvalidDayOfWeekException;
import ca.ulaval.glo4003.times.exceptions.TimeException;
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
  public void givenInvalidDayOfWeekException_whenResponding_thenStatusIsBadRequest() {
    TimeException timeException = new InvalidDayOfWeekException();

    Response response = timeExceptionMapper.toResponse(timeException);

    assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
