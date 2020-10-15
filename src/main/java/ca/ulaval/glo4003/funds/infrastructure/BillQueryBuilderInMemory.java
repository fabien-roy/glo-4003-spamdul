package ca.ulaval.glo4003.funds.infrastructure;

import ca.ulaval.glo4003.funds.domain.BillQueryBuilder;
import ca.ulaval.glo4003.funds.domain.BillType;
import ca.ulaval.glo4003.funds.infrastructure.filters.BillFilterInMemory;
import ca.ulaval.glo4003.funds.infrastructure.filters.BillTypeFilterInMemory;
import ca.ulaval.glo4003.funds.infrastructure.filters.YearFilterInMemory;
import java.util.ArrayList;
import java.util.List;

public class BillQueryBuilderInMemory implements BillQueryBuilder<BillQueryInMemory> {

  private List<BillFilterInMemory> filters = new ArrayList<>();

  @Override
  public BillQueryBuilder<BillQueryInMemory> emptyBillQuery() {
    return new BillQueryBuilderInMemory();
  }

  @Override
  public BillQueryBuilder<BillQueryInMemory> withBillType(BillType billType) {
    this.filters.add(new BillTypeFilterInMemory());
    return this;
  }

  @Override
  public BillQueryBuilder<BillQueryInMemory> withYear(int Year) {
    this.filters.add(new YearFilterInMemory());
    return this;
  }

  @Override
  public BillQueryInMemory build() {
    return new BillQueryInMemory(filters);
  }

  public List<BillFilterInMemory> getFilters() {
    return this.filters;
  }
}
