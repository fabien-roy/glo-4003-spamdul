package ca.ulaval.glo4003.funds.infrastructure.filters;

import ca.ulaval.glo4003.funds.domain.Bill;
import java.util.List;

public interface BillFilterInMemory {
  List<Bill> filter(List<Bill> filteredBills);
}
