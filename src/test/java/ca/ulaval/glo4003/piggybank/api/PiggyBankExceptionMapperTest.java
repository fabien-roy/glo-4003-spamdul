package ca.ulaval.glo4003.piggybank.api;

import ca.ulaval.glo4003.piggybank.exceptions.PiggyBankException;
import ca.ulaval.glo4003.piggybank.exceptions.PiggyBankInsufficientAmountException;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class PiggyBankExceptionMapperTest {

  private PiggyBankExceptionMapper piggyBankExceptionMapper;

  @Before
  public void setUp() {
    piggyBankExceptionMapper = new PiggyBankExceptionMapper();
  }

  @Test
  public void givenPiggyBankInsufficientAmountException_whenResponding_ThenStatusIsBadRequest() {
    PiggyBankException piggyBankException = new PiggyBankInsufficientAmountException();

    Response response = piggyBankExceptionMapper.toResponse(piggyBankException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
