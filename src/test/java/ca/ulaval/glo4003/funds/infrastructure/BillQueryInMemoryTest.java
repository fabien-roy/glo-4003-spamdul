package ca.ulaval.glo4003.funds.infrastructure;

import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.infrastructure.filters.BillFilterInMemory;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BillQueryInMemoryTest {

  @Mock private BillFilterInMemory billFilterInMemory;
  private BillQueryInMemory billQueryInMemory;
  private List<Bill> bills;
  private Map<BillId, Bill> billMap = new HashMap<>();
  private List<Bill> filteredBills;
  private Bill filteredBill;

  @Before
  public void setUp() {
    Bill bill = aBill().build();
    filteredBill = aBill().build();
    bills = Arrays.asList(bill);
    billMap.put(bill.getId(), bill);

    filteredBills = Collections.singletonList(filteredBill);
    when(billFilterInMemory.filter(bills)).thenReturn(filteredBills);

    List<BillFilterInMemory> filters = new ArrayList<>();
    filters.add(billFilterInMemory);
    billQueryInMemory = new BillQueryInMemory(filters);
    billQueryInMemory.setBills(billMap);
  }

  @Test
  public void whenExecutingQuery_thenShouldUseFilter() {
    List<Bill> bills = billQueryInMemory.execute();

    assertThat(bills.size()).isEqualTo(1);
    assertThat(bills.get(0)).isSameInstanceAs(filteredBill);
  }
}