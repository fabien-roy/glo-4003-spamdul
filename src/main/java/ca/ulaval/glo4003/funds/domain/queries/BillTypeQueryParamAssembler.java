package ca.ulaval.glo4003.funds.domain.queries;

import ca.ulaval.glo4003.funds.domain.BillQueryBuilder;
import ca.ulaval.glo4003.funds.domain.BillType;
import java.util.List;

public class BillTypeQueryParamAssembler implements BillQueryParamAssembler {

  public static final BillQueryParam BILL_TYPE_PARAM = BillQueryParam.BILL_TYPE;

  @Override
  public BillQueryBuilder assemble(BillQueryBuilder builder, BillQueryParams billQueryParams) {
    List<String> billTypes = billQueryParams.getParam().get(BILL_TYPE_PARAM);

    return billTypes != null ? builder.withBillType(BillType.get(billTypes.get(0))) : builder;
  }
}
