package ca.ulaval.glo4003.funds.api;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.exception.InvalidBillQueryParamException;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class InvalidBillQueryParamExceptionMapperTest {
  private InvalidBillQueryParamExceptionMapper invalidBillQueryParamExceptionMapper;

  @Before
  public void setUp() {
    invalidBillQueryParamExceptionMapper = new InvalidBillQueryParamExceptionMapper();
  }

  @Test
  public void givenInvalidBillQueryParamException_whenResponding_thenStatusIsBadRequest() {
    InvalidBillQueryParamException invalidBillQueryParamException =
        new InvalidBillQueryParamException();

    Response response =
        invalidBillQueryParamExceptionMapper.toResponse(invalidBillQueryParamException);

    assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
