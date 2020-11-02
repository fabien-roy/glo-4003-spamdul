package ca.ulaval.glo4003.reports.domain.queryparams;

import ca.ulaval.glo4003.reports.domain.ReportQueryBuilder;
import java.util.List;
import java.util.Map;

public interface ReportQueryParamAssembler {

  ReportQueryBuilder assemble(ReportQueryBuilder builder, Map<String, List<String>> params);
}
