package ca.ulaval.glo4003.gateentries.api;

import static org.junit.Assert.*;

import ca.ulaval.glo4003.gateentries.exceptions.GateEntryException;
import ca.ulaval.glo4003.gateentries.exceptions.InvalidDayOfWeekException;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class GateEntryExceptionMapperTest {

  private GateEntryExceptionMapper gateEntryExceptionMapper;

  @Before
  public void setUp() {
    gateEntryExceptionMapper = new GateEntryExceptionMapper();
  }

  @Test
  public void givenInvalidDayOfWeekException_whenResponding_thenStatusIsBadRequest() {
    GateEntryException gateEntryException = new InvalidDayOfWeekException();

    Response response = gateEntryExceptionMapper.toResponse(gateEntryException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
