package ca.ulaval.glo4003.funds.domain.queries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillQueryParams {
  private Map<BillQueryParam, List<String>> param;

  public BillQueryParams() {
    param = new HashMap<>();
  }

  public Map<BillQueryParam, List<String>> getParam() {
    return param;
  }

  public void add(BillQueryParam queryParam, List<String> strings) {
    param.put(queryParam, strings);
  }
}
