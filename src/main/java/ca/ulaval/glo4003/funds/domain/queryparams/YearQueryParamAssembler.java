package ca.ulaval.glo4003.funds.domain.queryparams;

import ca.ulaval.glo4003.funds.domain.BillQueryBuilder;
import java.util.List;

public class YearQueryParamAssembler implements BillQueryParamAssembler {

  public static final BillQueryParam YEAR_PARAM = BillQueryParam.YEAR;

  @Override
  public BillQueryBuilder assemble(BillQueryBuilder builder, BillQueryParams params) {
    List<String> years = params.getParam().get(YEAR_PARAM);

    return years != null ? builder.withYear(parseValue(years.get(0))) : builder;
  }

  private Integer parseValue(String string) {
    return Integer.parseInt(string);
  }
}
