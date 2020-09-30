package ca.ulaval.glo4003.interfaces.api;

import com.google.common.truth.Truth;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class CatchAllExceptionMapperTest {

  private CatchAllExceptionMapper catchAllExceptionMapper;

  @Before
  public void setUp() {
    catchAllExceptionMapper = new CatchAllExceptionMapper();
  }

  @Test
  public void givenWebApplicationException_whenResponding_thenStatusIsBadRequest() {
    Exception exception = new WebApplicationException();

    Response response = catchAllExceptionMapper.toResponse(exception);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenException_whenResponding_thenStatusIsInternalServerError() {
    Exception exception = new Exception();

    Response response = catchAllExceptionMapper.toResponse(exception);

    Truth.assertThat(response.getStatus())
        .isEqualTo(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
  }
}
