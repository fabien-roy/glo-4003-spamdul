package ca.ulaval.glo4003.reports.domain.aggregatefunctions;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.List;

// TODO #238 : Should AggregateFunction interface be moved to infrastructure?
public interface AggregateFunction {
  ReportPeriod aggregate(List<ReportPeriod> periods, ReportMetricType metricType);
}
