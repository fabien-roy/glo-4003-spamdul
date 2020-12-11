package ca.ulaval.glo4003.schedulers.api;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.schedulers.domain.exceptions.CannotBuildSchedulerException;
import ca.ulaval.glo4003.schedulers.domain.exceptions.SchedulerException;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class SchedulerExceptionMapperTest {

  private SchedulerExceptionMapper exceptionMapper;

  @Before
  public void setUp() {
    exceptionMapper = new SchedulerExceptionMapper();
  }

  @Test
  public void givenCannotBuildSchedulerException_whenResponding_thenStatusIsInternServerError() {
    SchedulerException exception = new CannotBuildSchedulerException();

    Response response = exceptionMapper.toResponse(exception);

    assertThat(response.getStatus())
        .isEqualTo(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
  }
}
