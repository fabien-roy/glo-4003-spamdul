package ca.ulaval.glo4003.funds.domain.exceptions;

import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillId;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.funds.domain.BillId;
import org.junit.Before;
import org.junit.Test;

public class NotFoundBillExceptionTest {
  private ApplicationException exception;

  private final BillId billId = createBillId();

  @Before
  public void setUp() {
    exception = new NotFoundBillException(billId);
  }

  @Test
  public void whenGettingDescription_thenWriteBillId() {
    String expectedDescription = String.format("Bill with id %s was not found", billId.toString());

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
