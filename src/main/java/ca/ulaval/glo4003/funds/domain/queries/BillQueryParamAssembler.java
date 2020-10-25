package ca.ulaval.glo4003.funds.domain.queries;

import ca.ulaval.glo4003.funds.domain.BillQueryBuilder;

public interface BillQueryParamAssembler {

  BillQueryBuilder assemble(BillQueryBuilder builder, BillQueryParams params);
}
