package ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.List;

public class MinimumAggregateFunctionInMemory extends ReportAggregateFunctionInMemory {

  @Override
  public ReportPeriod aggregate(List<ReportPeriod> periods, ReportMetricType metricType) {
    ReportPeriod lowestPeriod = null;
    double lowestValue = Double.MAX_VALUE;

    for (ReportPeriod period : periods) {
      if (hasMetricsOfType(period, metricType)) {
        double totalValueForPeriod = getTotalValueForPeriod(period, metricType);

        if (lowestValue == Double.MAX_VALUE || lowestValue > totalValueForPeriod) {
          lowestValue = totalValueForPeriod;
          lowestPeriod = period;
        }
      }
    }

    if (lowestValue == Double.MAX_VALUE) lowestValue = 0d;

    return buildReportPeriod(formatMaximumPeriodName(lowestPeriod), metricType, lowestValue);
  }

  private String formatMaximumPeriodName(ReportPeriod period) {
    return String.format("%s (minimum)", period == null ? "no period" : period.getName());
  }
}
