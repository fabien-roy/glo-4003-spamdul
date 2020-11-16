package ca.ulaval.glo4003.reports.domain.metrics;

import java.util.List;

public interface ReportMetricBuilder {

  ReportMetricBuilder someMetrics();

  ReportMetricBuilder withTypes(List<ReportMetricType> metricTypes);

  List<ReportMetric> buildMany();
}
