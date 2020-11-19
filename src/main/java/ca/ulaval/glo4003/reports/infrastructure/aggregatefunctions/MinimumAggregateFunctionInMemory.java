package ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.List;

public class MinimumAggregateFunctionInMemory extends ReportAggregateFunctionInMemory {

  @Override
  public ReportPeriod aggregate(List<ReportPeriod> periods, ReportMetricType metricType) {
    ReportPeriod lowestPeriod = null;
    Double lowestValue = null;

    for (ReportPeriod period : periods) {
      for (ReportPeriodData data : period.getData()) {
        for (ReportMetricData metricData : data.getMetrics()) {
          if (metricData.getType().equals(metricType)) {
            if (lowestValue == null) {
              lowestValue = metricData.getValue();
              lowestPeriod = period;
            } else if (lowestValue > metricData.getValue()) {
              lowestValue = metricData.getValue();
              lowestPeriod = period;
            }
          }
        }
      }
    }

    return lowestPeriod;
  }
}
