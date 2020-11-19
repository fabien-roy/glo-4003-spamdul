package ca.ulaval.glo4003.reports.domain;

import ca.ulaval.glo4003.reports.domain.aggregatefunctions.ReportAggregateFunctionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.List;

public interface ReportSummaryBuilder {

  ReportSummaryBuilder aReportSummary();

  ReportSummaryBuilder withPeriods(List<ReportPeriod> periods);

  ReportSummaryBuilder withAggregateFunctions(
      List<ReportAggregateFunctionType> aggregateFunctionTypes);

  ReportSummaryBuilder withMetric(ReportMetricType metric);

  List<ReportPeriod> build();
}
