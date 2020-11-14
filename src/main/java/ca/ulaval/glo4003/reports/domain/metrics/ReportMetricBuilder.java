package ca.ulaval.glo4003.reports.domain.metrics;

import java.util.List;

// TODO : Will this builder really be for many metrics or only a single one?
public interface ReportMetricBuilder {

  ReportMetricBuilder someMetrics();

  ReportMetricBuilder withTypes(List<ReportMetricType> metricTypes);

  List<ReportMetric> buildMany();
}
