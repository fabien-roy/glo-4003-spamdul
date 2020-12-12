package ca.ulaval.glo4003.funds.services.converters;

import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillId;

import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.exceptions.NotFoundBillException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class BillIdConverterTest {

  private final BillId billId = createBillId();
  private BillIdConverter billIdConverter;

  @Before
  public void setUp() {
    billIdConverter = new BillIdConverter();
  }

  @Test(expected = NotFoundBillException.class)
  public void givenNullAccountId_whenConverting_thenThrowInvalidAccountIdException() {
    billIdConverter.convert(null);
  }

  @Test
  public void whenConverting_thenReturnBillId() {
    BillId id = billIdConverter.convert(billId.toString());

    Truth.assertThat(id).isEqualTo(billId);
  }
}
