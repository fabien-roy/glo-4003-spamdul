package ca.ulaval.glo4003.funds.infrastructure;

import ca.ulaval.glo4003.funds.domain.BillQueryBuilder;
import ca.ulaval.glo4003.funds.domain.BillType;

public class BillQueryBuilderInMemory implements BillQueryBuilder<BillQueryInMemory> {
  @Override
  public BillQueryBuilder<BillQueryInMemory> aBillQuery() {
    return null;
  }

  @Override
  public BillQueryBuilder<BillQueryInMemory> withBillType(BillType billType) {
    return null;
  }

  @Override
  public BillQueryBuilder<BillQueryInMemory> withYear(int Year) {
    return null;
  }

  @Override
  public BillQueryInMemory build() {
    return null;
  }
}
