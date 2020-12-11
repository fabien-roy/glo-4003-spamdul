package ca.ulaval.glo4003.offenses.api;

import ca.ulaval.glo4003.offenses.domain.exceptions.InvalidOffenseCodeException;
import ca.ulaval.glo4003.offenses.domain.exceptions.OffenseException;
import ca.ulaval.glo4003.offenses.domain.exceptions.OffenseTypeNotFoundException;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class OffenseExceptionMapperTest {
  private OffenseExceptionMapper offenseExceptionMapper;

  @Before
  public void setUp() {
    offenseExceptionMapper = new OffenseExceptionMapper();
  }

  @Test
  public void givenInvalidOffenseCodeException_whenResponding_thenStatusIsBadRequest() {
    OffenseException offenseException = new InvalidOffenseCodeException();

    Response response = offenseExceptionMapper.toResponse(offenseException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenOffenseNotFoundException_whenResponding_thenStatusIsNotFound() {
    OffenseException offenseException = new OffenseTypeNotFoundException();

    Response response = offenseExceptionMapper.toResponse(offenseException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
  }
}
