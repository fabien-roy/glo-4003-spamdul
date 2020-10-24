package ca.ulaval.glo4003.funds.domain.queries;

import ca.ulaval.glo4003.funds.domain.BillQueryBuilder;
import ca.ulaval.glo4003.funds.domain.BillType;
import java.util.List;
import java.util.Map;

public class BillTypeQueryParamAssembler implements BillQueryParamAssembler {

  public static final BillQueryParam BILL_TYPE_PARAM = BillQueryParam.BILL_TYPE;

  @Override
  public BillQueryBuilder assemble(
      BillQueryBuilder builder, Map<BillQueryParam, List<String>> params) {
    List<String> billTypes = params.get(BILL_TYPE_PARAM);

    // TODO : Could be more than one in the future
    return billTypes != null ? builder.withBillType(BillType.get(billTypes.get(0))) : builder;
  }
}
