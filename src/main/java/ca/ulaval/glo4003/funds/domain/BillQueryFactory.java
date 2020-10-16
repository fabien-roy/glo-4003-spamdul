package ca.ulaval.glo4003.funds.domain;

import ca.ulaval.glo4003.funds.domain.queries.BillQueryParamAssembler;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BillQueryFactory {

  private final BillQueryBuilder billQueryBuilder;
  private final Set<BillQueryParamAssembler> queryParamAssemblers;

  public BillQueryFactory(
      BillQueryBuilder billQueryBuilder, Set<BillQueryParamAssembler> queryParamAssemblers) {
    this.billQueryBuilder = billQueryBuilder;
    this.queryParamAssemblers = queryParamAssemblers;
  }

  public BillQuery create(Map<String, List<String>> params) {
    BillQueryBuilder builder = billQueryBuilder.aBillQuery();

    for (BillQueryParamAssembler queryParamAssembler : queryParamAssemblers)
      builder = queryParamAssembler.assemble(builder, params);

    return builder.build();
  }
}
