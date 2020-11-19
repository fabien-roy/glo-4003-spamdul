package ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.Collections;
import java.util.List;

public abstract class ReportAggregateFunctionInMemory {
  public abstract ReportPeriod aggregate(List<ReportPeriod> periods, ReportMetricType metricType);

  protected ReportPeriod buildAverageReportPeriod(
      String periodName, ReportMetricType metricType, double averageValue) {
    ReportPeriod averagePeriod = new ReportPeriod(periodName, null);
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
