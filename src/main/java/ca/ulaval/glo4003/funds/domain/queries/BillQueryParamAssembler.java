package ca.ulaval.glo4003.funds.domain.queries;

import ca.ulaval.glo4003.funds.domain.BillQueryBuilder;
import java.util.List;
import java.util.Map;

public interface BillQueryParamAssembler {

  BillQueryBuilder assemble(BillQueryBuilder builder, Map<BillQueryParam, List<String>> params);
}
