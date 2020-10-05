package ca.ulaval.glo4003.funds.api;

import ca.ulaval.glo4003.funds.exceptions.FundException;
import ca.ulaval.glo4003.funds.exceptions.InvalidTimeException;
import ca.ulaval.glo4003.funds.exceptions.InvalidZoneException;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class FundExceptionMapperTest {

  private FundExceptionMapper fundExceptionMapper;

  @Before
  public void setUp() {
    fundExceptionMapper = new FundExceptionMapper();
  }

  @Test
  public void givenInvalidTimeException_whenResponding_thenStatusIsBadRequest() {
    FundException fundException = new InvalidTimeException();

    Response response = fundExceptionMapper.toResponse(fundException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenInvalidZoneException_whenResponding_thenStatusIsBadRequest() {
    FundException fundException = new InvalidZoneException();

    Response response = fundExceptionMapper.toResponse(fundException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
