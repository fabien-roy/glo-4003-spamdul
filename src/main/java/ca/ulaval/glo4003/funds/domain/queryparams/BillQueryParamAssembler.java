package ca.ulaval.glo4003.funds.domain.queryparams;

import ca.ulaval.glo4003.funds.domain.BillQueryBuilder;

public interface BillQueryParamAssembler {

  BillQueryBuilder assemble(BillQueryBuilder builder, BillQueryParams params);
}
