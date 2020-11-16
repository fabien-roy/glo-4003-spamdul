package ca.ulaval.glo4003.reports.infrastructure.metrics;

import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportEventType;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.List;

public class InMemoryGateEntriesMetric extends ReportMetric {

  @Override
  public ReportMetricType getType() {
    return ReportMetricType.GATE_ENTRIES;
  }

  @Override
  public void calculate(ReportPeriodData data) {
    double gateEntries = calculateTotalGateEntries(data.getEvents());
    data.addMetric(toMetricData(gateEntries));
  }

  private double calculateTotalGateEntries(List<ReportEvent> events) {
    return events.stream()
        .filter(event -> event.getType().equals(ReportEventType.GATE_ENTERED))
        .count();
  }
}
