package ca.ulaval.glo4003.funds.assemblers;

import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillId;

import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.exception.BillNotFoundException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class BillIdAssemblerTest {

  private final BillId billId = createBillId();
  private BillIdAssembler billIdAssembler;

  @Before
  public void setUp() {
    billIdAssembler = new BillIdAssembler();
  }

  @Test(expected = BillNotFoundException.class)
  public void givenNullAccountId_whenAssembling_thenThrowInvalidAccountIdException() {
    billIdAssembler.assemble(null);
  }

  @Test
  public void whenAssembling_thenReturnBillId() {
    BillId id = billIdAssembler.assemble(billId.toString());

    Truth.assertThat(id).isEqualTo(billId);
  }
}
