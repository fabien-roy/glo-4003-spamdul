package ca.ulaval.glo4003.funds.services.converters;

import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillId;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.exceptions.InvalidBillIdException;
import org.junit.Before;
import org.junit.Test;

public class BillIdConverterTest {

  private final BillId billId = createBillId();
  private BillIdConverter billIdConverter;

  @Before
  public void setUp() {
    billIdConverter = new BillIdConverter();
  }

  @Test(expected = InvalidBillIdException.class)
  public void givenInvalidBillId_whenConverting_thenThrowInvalidBillIdException() {
    billIdConverter.convert("invalidBillId");
  }

  @Test(expected = InvalidBillIdException.class)
  public void givenNullBillId_whenConverting_thenThrowInvalidBillIdException() {
    billIdConverter.convert(null);
  }

  @Test
  public void whenConverting_thenReturnBillId() {
    BillId id = billIdConverter.convert(billId.toString());

    assertThat(id).isEqualTo(billId);
  }
}
