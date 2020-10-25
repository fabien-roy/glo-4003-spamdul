package ca.ulaval.glo4003.funds.infrastructure.filters;

import ca.ulaval.glo4003.funds.domain.Bill;
import java.util.List;
import java.util.stream.Collectors;

public class YearFilterInMemory implements BillFilterInMemory {

  private final int year;

  public YearFilterInMemory(int year) {
    this.year = year;
  }

  @Override
  public List<Bill> filter(List<Bill> filteredBills) {
    return filteredBills.stream()
        .filter(bill -> bill.isYearEqual(year))
        .collect(Collectors.toList());
  }
}
