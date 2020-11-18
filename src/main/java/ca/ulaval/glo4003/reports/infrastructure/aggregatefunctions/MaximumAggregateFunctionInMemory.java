package ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.aggregatefunctions.AggregateFunction;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.List;

public class MaximumAggregateFunctionInMemory implements AggregateFunction {

  // TODO #238 : Legend says the best devs can make this in a single line using .stream()
  @Override
  public ReportPeriod aggregate(List<ReportPeriod> periods, ReportMetricType metricType) {
    ReportPeriod highestPeriod = null;
    Double highestValue = null;

    for (ReportPeriod period : periods) {
      for (ReportPeriodData data : period.getData()) {
        for (ReportMetricData metricData : data.getMetrics()) {
          if (metricData.getType().equals(metricType)) {
            if (highestValue == null) {
              highestValue = metricData.getValue();
              highestPeriod = period;
            } else if (highestValue < metricData.getValue()) {
              highestValue = metricData.getValue();
              highestPeriod = period;
            }
          }
        }
      }
    }

    return highestPeriod;
  }
}
