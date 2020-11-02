package ca.ulaval.glo4003.funds.domain.queryparams;

import ca.ulaval.glo4003.funds.exception.InvalidBillQueryParamException;
import java.util.HashMap;
import java.util.Map;

public enum BillQueryParam {
  YEAR("year"),
  BILL_TYPE("billType");

  String param;
  private static final Map<String, BillQueryParam> lookup = new HashMap<>();

  static {
    for (BillQueryParam param : BillQueryParam.values()) {
      lookup.put(param.toString(), param);
    }
  }

  BillQueryParam(String param) {
    this.param = param;
  }

  public static BillQueryParam get(String param) {
    if (param == null) throw new InvalidBillQueryParamException();

    BillQueryParam billQueryParam = lookup.get(param.toLowerCase());

    if (billQueryParam == null) throw new InvalidBillQueryParamException();

    return billQueryParam;
  }

  @Override
  public String toString() {
    return param;
  }
}
