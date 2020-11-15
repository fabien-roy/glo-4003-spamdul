package ca.ulaval.glo4003.reports.domain.metrics;

public interface ReportMetricData<T> {

  // TODO : Is this used?
  ReportMetricType getType();

  T getValue();

  ReportMetricValueType getValueType();
}
