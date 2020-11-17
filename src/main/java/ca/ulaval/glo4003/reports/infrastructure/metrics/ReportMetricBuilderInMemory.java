package ca.ulaval.glo4003.reports.infrastructure.metrics;

import ca.ulaval.glo4003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricBuilder;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReportMetricBuilderInMemory implements ReportMetricBuilder {

  private List<ReportMetricType> metricTypes = new ArrayList<>();

  public ReportMetricBuilderInMemory someMetrics() {
    return new ReportMetricBuilderInMemory();
  }

  public ReportMetricBuilderInMemory withTypes(List<ReportMetricType> metricTypes) {
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
        return new ProfitsMetricInMemory();
      case GATE_ENTRIES:
        return null; // TODO #249
    }
  }
}
