package ca.ulaval.glo4003.funds.infrastructure.filters;

import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillId;
import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillType;
import static ca.ulaval.glo4003.funds.helpers.BillMother.createDescription;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.times.helpers.CustomDateTimeMother.createDateTime;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.times.domain.CustomDateTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class YearFilterInMemoryTest {

  private YearFilterInMemory yearFilterInMemory;

  private static final CustomDateTime A_YEAR = createDateTime();
  private static final CustomDateTime ANOTHER_YEAR = new CustomDateTime(LocalDateTime.MIN);
  private List<Bill> bills = new ArrayList<>();

  @Before
  public void setUp() {
    yearFilterInMemory = new YearFilterInMemory(A_YEAR.getIntYear());
  }

  @Test
  public void whenFiltering_thenShouldOnlyReturnBillsWithMatchingYear() {
    Bill firstBill =
        new Bill(createBillId(), createBillType(), createDescription(), createMoney(), A_YEAR);
    Bill secondBill =
        new Bill(
            createBillId(), createBillType(), createDescription(), createMoney(), ANOTHER_YEAR);
    bills.add(firstBill);
    bills.add(secondBill);

    List<Bill> filteredBills = yearFilterInMemory.filter(bills);

    assertThat(filteredBills.get(0)).isEqualTo(firstBill);
    assertThat(filteredBills.size()).isEqualTo(bills.size() - 1);
  }
}
