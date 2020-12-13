package ca.ulaval.glo4003.reports.domain.metrics;

public enum ReportMetricType {
  PROFITS("profits"),
  GATE_ENTRIES("gateEntries"),
  GATE_ENTRIES_FOR_CARS("gateEntriesForCars"),
  GATE_ENTRIES_FOR_BICYCLES("gateEntriesForBicycles");

  String reportMetricType;

  ReportMetricType(String reportMetricType) {
    this.reportMetricType = reportMetricType;
  }

  @Override
  public String toString() {
    return reportMetricType;
  }
}
