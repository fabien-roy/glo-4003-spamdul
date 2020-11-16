package ca.ulaval.glo4003.reports.infrastructure.metrics;

import ca.ulaval.glo4003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricBuilder;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryReportMetricBuilder implements ReportMetricBuilder {

  private List<ReportMetricType> metricTypes = new ArrayList<>();

  public InMemoryReportMetricBuilder someMetrics() {
    return new InMemoryReportMetricBuilder();
  }

  public InMemoryReportMetricBuilder withTypes(List<ReportMetricType> metricTypes) {
    this.metricTypes = metricTypes;
    return this;
  }

  public List<ReportMetric> buildMany() {
    return metricTypes.stream().map(this::buildOne).collect(Collectors.toList());
  }

  private ReportMetric buildOne(ReportMetricType metricType) {
    switch (metricType) {
      default:
      case PROFITS:
        return new InMemoryProfitsMetric();
      case GATE_ENTRIES:
        return null; // TODO #249
    }
  }
}
