package ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.List;

public class AverageAggregateFunctionInMemory extends ReportAggregateFunctionInMemory {

  private static final String AVERAGE_PERIOD_NAME = "average";

  @Override
  public ReportPeriod aggregate(List<ReportPeriod> periods, ReportMetricType metricType) {
    double averageValue = getAverage(periods, metricType);

    return buildAverageReportPeriod(AVERAGE_PERIOD_NAME, metricType, averageValue);
  }

  private double getAverage(List<ReportPeriod> periods, ReportMetricType metricType) {
    double totalValue = 0d;
    int valueCount = 0;

    for (ReportPeriod period : periods) {
      for (ReportPeriodData data : period.getData()) {
        for (ReportMetricData metricData : data.getMetrics()) {
          if (metricData.getType().equals(metricType)) {
            totalValue += metricData.getValue();
            valueCount++;
          }
        }
      }
    }

    return valueCount > 0 ? totalValue / valueCount : 0;
  }
}
