package ca.ulaval.glo4003.reports.domain.metrics;

public interface ReportMetricData<T> {

  ReportMetricType getType();

  T getValue();
}
