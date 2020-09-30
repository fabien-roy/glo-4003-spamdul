package ca.ulaval.glo4003.times.api;

import ca.ulaval.glo4003.times.exceptions.InvalidDateException;
import ca.ulaval.glo4003.times.exceptions.InvalidDayException;
import ca.ulaval.glo4003.times.exceptions.TimeException;
import com.google.common.truth.Truth;
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

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenInvalidDayException_whenResponding_thenStatusIsBadRequest() {
    TimeException timeException = new InvalidDayException();

    Response response = timeExceptionMapper.toResponse(timeException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
