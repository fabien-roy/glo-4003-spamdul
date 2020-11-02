package ca.ulaval.glo4003.funds.domain;

import ca.ulaval.glo4003.funds.domain.queryparams.BillQueryParamAssembler;
import ca.ulaval.glo4003.funds.domain.queryparams.BillQueryParams;
import java.util.Set;

public class BillQueryFactory {

  private final BillQueryBuilder billQueryBuilder;
  private final Set<BillQueryParamAssembler> queryParamAssemblers;

  public BillQueryFactory(
      BillQueryBuilder billQueryBuilder, Set<BillQueryParamAssembler> queryParamAssemblers) {
    this.billQueryBuilder = billQueryBuilder;
    this.queryParamAssemblers = queryParamAssemblers;
  }

  public BillQuery create(BillQueryParams billQueryParams) {
    BillQueryBuilder builder = billQueryBuilder.aBillQuery();

    for (BillQueryParamAssembler queryParamAssembler : queryParamAssemblers)
      builder = queryParamAssembler.assemble(builder, billQueryParams);

    return builder.build();
  }
}
