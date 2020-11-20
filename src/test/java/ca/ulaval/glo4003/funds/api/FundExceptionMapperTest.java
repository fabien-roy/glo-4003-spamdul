package ca.ulaval.glo4003.funds.api;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.exception.*;
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
  public void givenBillNotFoundException_whenResponding_thenStatusIsNotFound() {
    FundException billNotFoundException = new BillNotFoundException();

    Response response = fundExceptionMapper.toResponse(billNotFoundException);

    assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
  }

  @Test
  public void givenAmountDueExceededException_whenResponding_thenStatusIsBadRequest() {
    FundException amountDueExceededException = new AmountDueExceededException();

    Response response = fundExceptionMapper.toResponse(amountDueExceededException);

    assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenInvalidMoneyException_whenResponding_thenStatusIsBadRequest() {
    FundException amountDueExceededException = new InvalidMoneyException();

    Response response = fundExceptionMapper.toResponse(amountDueExceededException);

    assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenNegativeMoneyException_whenResponding_thenStatusIsBadRequest() {
    FundException amountDueExceededException = new NegativeMoneyException();

    Response response = fundExceptionMapper.toResponse(amountDueExceededException);

    assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void
      givenSustainableMobilityProgramBankInsufficientAmountException_whenResponding_ThenStatusIsBadRequest() {
    FundException sustainableMobilityProgramBankException =
        new SustainableMobilityProgramBankInsufficientAmountException();

    Response response = fundExceptionMapper.toResponse(sustainableMobilityProgramBankException);

    assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
