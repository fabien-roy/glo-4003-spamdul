package ca.ulaval.glo4003.funds.infrastructure.filters;

import ca.ulaval.glo4003.funds.domain.Bill;
import java.util.List;

public class YearFilterInMemory implements BillFilterInMemory {

  private final int year;

  public YearFilterInMemory(int year) {
    this.year = year;
  }

  @Override
  public List<Bill> filter(List<Bill> filteredBills) {
    // TODO : Bill curently has no notion of the time of billing
    return null;
  }
}
