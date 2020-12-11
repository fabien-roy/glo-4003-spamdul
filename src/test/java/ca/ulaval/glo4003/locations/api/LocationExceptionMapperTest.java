package ca.ulaval.glo4003.locations.api;

import ca.ulaval.glo4003.locations.domain.exceptions.InvalidPostalCodeException;
import ca.ulaval.glo4003.locations.domain.exceptions.LocationException;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class LocationExceptionMapperTest {

  private LocationExceptionMapper locationExceptionMapper;

  @Before
  public void setUp() {
    locationExceptionMapper = new LocationExceptionMapper();
  }

  @Test
  public void givenInvalidPostalCodeException_whenResponding_thenStatusIsBadRequest() {
    LocationException locationException = new InvalidPostalCodeException();

    Response response = locationExceptionMapper.toResponse(locationException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
