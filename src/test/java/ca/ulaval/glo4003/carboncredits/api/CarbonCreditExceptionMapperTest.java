package ca.ulaval.glo4003.carboncredits.api;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.carboncredits.exceptions.CarbonCreditException;
import ca.ulaval.glo4003.carboncredits.exceptions.InvalidMonthlyPaymentStatusException;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class CarbonCreditExceptionMapperTest {
  private CarbonCreditExceptionMapper carbonCreditExceptionMapper;

  @Before
  public void setUp() {
    carbonCreditExceptionMapper = new CarbonCreditExceptionMapper();
  }

  @Test
  public void givenInvalidMonthlyPaymentStatusException_whenResponding_thenStatusIsBadRequest() {
    CarbonCreditException invalidMonthlyPaymentStatusException =
        new InvalidMonthlyPaymentStatusException();

    Response response =
        carbonCreditExceptionMapper.toResponse(invalidMonthlyPaymentStatusException);

    assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
