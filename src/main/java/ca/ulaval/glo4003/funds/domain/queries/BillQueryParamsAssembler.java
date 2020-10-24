package ca.ulaval.glo4003.funds.domain.queries;

import ca.ulaval.glo4003.funds.domain.BillType;
import java.util.*;

public class BillQueryParamsAssembler {

  public BillQueryParams assembleWithYear(int year, BillType billType) {
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
