package ca.ulaval.glo4003.funds.infrastructure;

import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.BillRepository;
import ca.ulaval.glo4003.funds.exception.BillNotFound;
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

    List<Bill> bills = billRepository.getBillsByIds(billIds);

    Truth.assertThat(bills).contains(bill);
    Truth.assertThat(bills).contains(bill2);
  }

  @Test(expected = BillNotFound.class)
  public void givenBillIds_whenGettingBillsButNotFound_thenThrowBillNotFound() {
    List<BillId> billIds = new ArrayList<>();
    billIds.add(bill.getId());
    billIds.add(bill2.getId());

    billRepository.getBillsByIds(billIds);
  }

  @Test
  public void givenBillId_whenGettingBill_thenReturnBill() {
    billRepository.save(bill);
    Bill billFromRepository = billRepository.getBill(bill.getId());

    Truth.assertThat(billFromRepository).isEqualTo(bill);
  }

  @Test(expected = BillNotFound.class)
  public void givenBillId_whenGettingBillNotFound_thenThrowBillNotFound() {
    billRepository.getBill(bill.getId());
  }
}
