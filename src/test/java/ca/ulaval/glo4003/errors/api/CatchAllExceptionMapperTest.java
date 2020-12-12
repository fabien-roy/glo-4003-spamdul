package ca.ulaval.glo4003.errors.api;

import static com.google.common.truth.Truth.assertThat;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class CatchAllExceptionMapperTest {

  private CatchAllExceptionMapper exceptionMapper;

  @Before
  public void setUp() {
    exceptionMapper = new CatchAllExceptionMapper();
  }

  @Test
  public void givenWebApplicationException_whenResponding_thenStatusIsBadRequest() {
    Exception exception = new WebApplicationException();

    Response response = exceptionMapper.toResponse(exception);

    assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenException_whenResponding_thenStatusIsInternalServerError() {
    Exception exception = new Exception();

    Response response = exceptionMapper.toResponse(exception);

    assertThat(response.getStatus())
        .isEqualTo(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
  }
}
