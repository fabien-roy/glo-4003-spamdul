package ca.ulaval.glo4003.api.bill;

import ca.ulaval.glo4003.domain.bill.exceptions.BillException;
import ca.ulaval.glo4003.domain.bill.exceptions.InvalidTimeException;
import ca.ulaval.glo4003.domain.bill.exceptions.InvalidZoneException;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class BillExceptionMapperTest {

  private BillExceptionMapper billExceptionMapper;

  @Before
  public void setUp() {
    billExceptionMapper = new BillExceptionMapper();
  }

  @Test
  public void givenInvalidTimeException_whenResponding_thenStatusIsBadRequest() {
    BillException billException = new InvalidTimeException();

    Response response = billExceptionMapper.toResponse(billException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenInvalidZoneException_whenResponding_thenStatusIsBadRequest() {
    BillException billException = new InvalidZoneException();

    Response response = billExceptionMapper.toResponse(billException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
