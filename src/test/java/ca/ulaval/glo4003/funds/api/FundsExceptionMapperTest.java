package ca.ulaval.glo4003.funds.api;

import ca.ulaval.glo4003.funds.exception.BillNotFound;
import ca.ulaval.glo4003.funds.exception.TooMuchMoney;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Test;

public class FundsExceptionMapperTest {
  private FundsExceptionMapper fundsExceptionMapper = new FundsExceptionMapper();

  @Test
  public void givenBillNotFound_whenResponding_thenStatusIsNotFound() {
    BillNotFound billNotFound = new BillNotFound();

    Response response = fundsExceptionMapper.toResponse(billNotFound);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
  }

  @Test
  public void givenTooMuchMoney_whenResponding_thenStatusIsBadRequest() {
    TooMuchMoney tooMuchMoney = new TooMuchMoney();

    Response response = fundsExceptionMapper.toResponse(tooMuchMoney);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
