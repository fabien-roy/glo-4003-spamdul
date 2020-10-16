package ca.ulaval.glo4003.funds.infrastructure.filters;

import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillId;
import static ca.ulaval.glo4003.funds.helpers.BillMother.createDescription;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillType;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class BillTypeFilterInMemoryTest {

  private BillTypeFilterInMemory billTypeFilterInMemory;

  private static final BillType A_BILL_TYPE = BillType.PARKING_STICKER;
  private static final BillType ANOTHER_BILL_TYPE = BillType.OFFENSE;
  private List<Bill> bills = new ArrayList<>();

  @Before
  public void setUp() {
    billTypeFilterInMemory = new BillTypeFilterInMemory(A_BILL_TYPE);
  }

  @Test
  public void whenFiltering_thenShouldOnlyReturnBillsWithMatchingBillType() {
    Bill firstBill = new Bill(createBillId(), A_BILL_TYPE, createDescription(), createMoney());
    Bill secondBill =
        new Bill(createBillId(), ANOTHER_BILL_TYPE, createDescription(), createMoney());
    bills.add(firstBill);
    bills.add(secondBill);

    List<Bill> filteredBills = billTypeFilterInMemory.filter(bills);

    assertThat(filteredBills.get(0)).isEqualTo(firstBill);
    assertThat(filteredBills.size()).isEqualTo(bills.size() - 1);
  }
}
