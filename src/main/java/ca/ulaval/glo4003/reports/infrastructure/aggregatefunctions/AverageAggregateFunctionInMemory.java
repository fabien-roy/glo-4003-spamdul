package ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.Collections;
import java.util.List;

public class AverageAggregateFunctionInMemory implements ReportAggregateFunctionInMemory {

  private static final String AVERAGE_PERIOD_NAME = "average";

  @Override
  public ReportPeriod aggregate(List<ReportPeriod> periods, ReportMetricType metricType) {
    double averageValue = getAverage(periods, metricType);

    return buildAverageReportPeriod(metricType, averageValue);
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

  private ReportPeriod buildAverageReportPeriod(ReportMetricType metricType, double averageValue) {
    ReportPeriod averagePeriod = new ReportPeriod(AVERAGE_PERIOD_NAME, null);
    ReportPeriodData averagePeriodData = new ReportPeriodData(Collections.emptyList());

    averagePeriodData.addMetric(
        new ReportMetricData() {
          @Override
          public ReportMetricType getType() {
            return metricType;
          }

          @Override
          public double getValue() {
            return averageValue;
          }
        });
    averagePeriodData.setDimensions(Collections.emptyList());

    averagePeriod.setData(Collections.singletonList(averagePeriodData));

    return averagePeriod;
  }
}
