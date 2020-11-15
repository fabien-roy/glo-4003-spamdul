package ca.ulaval.glo4003.reports.infrastructure.metrics;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.List;

public class InMemoryProfitsMetric extends ReportMetric<Double> {

  @Override
  public ReportMetricType getType() {
    return ReportMetricType.PROFITS;
  }

  @Override
  public void calculate(ReportPeriodData data) {
    Money profits = calculateTotalProfits(data.getEvents());
    data.addMetric(toMetricData(profits.toDouble()));
  }

  private Money calculateTotalProfits(List<ReportEvent> events) {
    Money profits = Money.zero();

    for (ReportEvent event : events) profits = profits.plus(event.getProfits());

    return profits;
  }
}
