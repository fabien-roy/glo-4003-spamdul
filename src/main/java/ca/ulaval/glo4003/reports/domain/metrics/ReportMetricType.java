package ca.ulaval.glo4003.reports.domain.metrics;

public enum ReportMetricType {
  PROFITS("profits"),
  GATE_ENTRIES("gateEntries");

  String reportMetricType;

  ReportMetricType(String reportMetricType) {
    this.reportMetricType = reportMetricType;
  }

  @Override
  public String toString() {
    return reportMetricType;
  }
}
