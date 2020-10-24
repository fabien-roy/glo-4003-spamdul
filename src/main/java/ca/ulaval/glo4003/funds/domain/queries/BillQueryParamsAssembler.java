package ca.ulaval.glo4003.funds.domain.queries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillQueryParamsAssembler {

  // TODO : Naming?

  private Map<BillQueryParam, List<String>> billQueryParams;

  public Map<BillQueryParam, List<String>> assemble(
      BillQueryParam billQueryParam, List<String> params) {
    billQueryParams = new HashMap<>();
    return billQueryParams.put(billQueryParam, params);
  }
}
