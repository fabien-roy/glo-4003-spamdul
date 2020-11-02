package ca.ulaval.glo4003.funds.domain.queryparams;

import ca.ulaval.glo4003.funds.domain.BillType;
import ca.ulaval.glo4003.funds.exception.InvalidYearException;
import java.util.*;

public class BillQueryParamsAssembler {

  public BillQueryParams assemble(int year, BillType billType) {
    if (year <= 0) throw new InvalidYearException();

    BillQueryParams billQueryParams = new BillQueryParams();
    billQueryParams.add(BillQueryParam.YEAR, toList(String.valueOf(year)));
    billQueryParams.add(BillQueryParam.BILL_TYPE, toList(billType.toString()));

    return billQueryParams;
  }

  private List<String> toList(String value) {
    List<String> strings = new ArrayList<>();
    strings.add(value);

    return strings;
  }
}
