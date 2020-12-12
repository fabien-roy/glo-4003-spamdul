package ca.ulaval.glo4003.reports.domain;

import ca.ulaval.glo4003.reports.domain.aggregatefunctions.ReportAggregateFunctionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.List;

public interface ReportSummaryFactory {

  List<ReportPeriod> create(
      List<ReportAggregateFunctionType> aggregateFunctionTypes,
      List<ReportPeriod> periods,
      List<ReportMetricType> metrics);
}
