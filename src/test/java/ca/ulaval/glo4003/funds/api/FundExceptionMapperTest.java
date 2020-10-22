package ca.ulaval.glo4003.funds.api;

import ca.ulaval.glo4003.funds.exception.AmountDueExceededException;
import ca.ulaval.glo4003.funds.exception.BillNotFoundException;
import ca.ulaval.glo4003.funds.exception.SustainableMobilityProgramBankInsufficientAmountException;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Test;

public class FundExceptionMapperTest {
  private FundExceptionMapper fundExceptionMapper = new FundExceptionMapper();

  @Test
  public void givenBillNotFound_whenResponding_thenStatusIsNotFound() {
    BillNotFoundException billNotFoundException = new BillNotFoundException();

    Response response = fundExceptionMapper.toResponse(billNotFoundException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
  }

  @Test
  public void givenTooMuchMoney_whenResponding_thenStatusIsBadRequest() {
    AmountDueExceededException amountDueExceededException = new AmountDueExceededException();

    Response response = fundExceptionMapper.toResponse(amountDueExceededException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void
      givenSustainableMobilityProgramBankInsufficientAmountException_whenResponding_ThenStatusIsBadRequest() {
    SustainableMobilityProgramBankInsufficientAmountException
        sustainableMobilityProgramBankException =
            new SustainableMobilityProgramBankInsufficientAmountException();

    Response response = fundExceptionMapper.toResponse(sustainableMobilityProgramBankException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
