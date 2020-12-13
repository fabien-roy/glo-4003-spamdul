package ca.ulaval.glo4003.reports.domain;

import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricData;
import java.util.ArrayList;
import java.util.List;

public class ReportPeriodData {

  private final List<ReportEvent> events;
  private final List<ReportMetricData> metrics = new ArrayList<>();
  private List<ReportDimensionData> dimensions = new ArrayList<>();

  public ReportPeriodData(List<ReportEvent> events) {
    this.events = events;
  }

  public List<ReportEvent> getEvents() {
    return events;
  }

  public List<ReportMetricData> getMetrics() {
    return metrics;
  }

  public List<ReportDimensionData> getDimensions() {
    return dimensions;
  }

  public void setDimensions(List<ReportDimensionData> dimensions) {
    this.dimensions = dimensions;
  }

  public void addMetric(ReportMetricData metric) {
    metrics.add(metric);
  }

  public void addDimension(ReportDimensionData dimension) {
    dimensions.add(dimension);
  }
}
