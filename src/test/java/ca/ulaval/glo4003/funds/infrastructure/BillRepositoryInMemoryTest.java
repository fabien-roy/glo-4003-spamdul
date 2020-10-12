package ca.ulaval.glo4003.funds.infrastructure;

import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.BillRepository;
import com.google.common.truth.Truth;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class BillRepositoryInMemoryTest {
  private BillRepository billRepository;

  private final Bill bill = aBill().build();
  private final Bill bill2 = aBill().build();

  @Before
  public void setUp() {
    billRepository = new BillRepositoryInMemory();
  }

  @Test
  public void whenSavingBill_thenReturnId() {
    BillId billId = billRepository.save(bill);

    Truth.assertThat(billId).isSameInstanceAs(bill.getId());
  }

  @Test
  public void givenBillIds_whenGettingBills_thenReturnBills() {
    billRepository.save(bill);
    billRepository.save(bill2);

    List<BillId> billIds = new ArrayList<>();
    billIds.add(bill.getId());
    billIds.add(bill2.getId());

    List<Bill> bills = billRepository.getBills(billIds);

    Truth.assertThat(bills).contains(bill);
    Truth.assertThat(bills).contains(bill2);
  }
}
