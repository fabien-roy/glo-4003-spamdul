package ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.List;

public interface ReportAggregateFunctionInMemory {
  ReportPeriod aggregate(List<ReportPeriod> periods, ReportMetricType metricType);
}
