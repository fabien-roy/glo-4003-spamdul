package ca.ulaval.glo4003.access.api;

import ca.ulaval.glo4003.access.exceptions.AccessExceptionPeriodsException;
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
  public void givenAccessExceptionPeriodsException_whenResponding_thenStatusIsBadRequest() {
    AccessExceptionPeriodsException accessExceptionPeriodsException =
        new AccessExceptionPeriodsException();

    Response response = accessExceptionMapper.toResponse(accessExceptionPeriodsException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
