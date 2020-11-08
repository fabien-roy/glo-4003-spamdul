package ca.ulaval.glo4003.reports.helpers;

import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricData;
import java.util.ArrayList;
import java.util.List;

public class ReportPeriodDataBuilder {

  private List<ReportEvent> events = new ArrayList<>();
  private List<ReportMetricData> metrics = new ArrayList<>();
  private List<ReportDimensionData> dimensions = new ArrayList<>();

  public static ReportPeriodDataBuilder aReportPeriodData() {
    return new ReportPeriodDataBuilder();
  }

  public ReportPeriodData build() {
    ReportPeriodData data = new ReportPeriodData(events);
    metrics.forEach(data::addMetric);
    dimensions.forEach(data::addDimension);
    return data;
  }
}
