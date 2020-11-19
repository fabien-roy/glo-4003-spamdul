package ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.List;

public class MaximumAggregateFunctionInMemory extends ReportAggregateFunctionInMemory {

  @Override
  public ReportPeriod aggregate(List<ReportPeriod> periods, ReportMetricType metricType) {
    ReportPeriod highestPeriod = null;
    double highestValue = 0d;

    for (ReportPeriod period : periods) {
      double totalValueForPeriod = getTotalValueForPeriod(period, metricType);

      if (highestValue < totalValueForPeriod) {
        highestValue = totalValueForPeriod;
        highestPeriod = period;
      }
    }

    return buildReportPeriod(formatMaximumPeriodName(highestPeriod), metricType, highestValue);
  }

  private String formatMaximumPeriodName(ReportPeriod period) {
    return String.format("%s (maximum)", period == null ? "no period" : period.getName());
  }

  private double getTotalValueForPeriod(ReportPeriod period, ReportMetricType metricType) {
    return period.getData().stream()
        .flatMap(data -> data.getMetrics().stream())
        .filter(metric -> metric.getType().equals(metricType))
        .mapToDouble(ReportMetricData::getValue)
        .sum();
  }
}
