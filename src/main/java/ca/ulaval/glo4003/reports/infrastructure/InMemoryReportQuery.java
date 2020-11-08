package ca.ulaval.glo4003.reports.infrastructure;

import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.ReportQuery;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScope;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryReportQuery implements ReportQuery {

  private final ReportScope scope;
  private final List<ReportMetric> metrics;
  private final List<ReportDimension> dimensions;
  private List<ReportEvent> events;

  public InMemoryReportQuery(
      ReportScope scope, List<ReportMetric> metrics, List<ReportDimension> dimensions) {
    this.scope = scope;
    this.metrics = metrics;
    this.dimensions = dimensions;
  }

  public void setEvents(List<ReportEvent> events) {
    this.events = events;
  }

  public ReportScope getScope() {
    return scope;
  }

  public List<ReportMetric> getMetrics() {
    return metrics;
  }

  public List<ReportDimension> getDimensions() {
    return dimensions;
  }

  public List<ReportPeriod> execute() {
    List<ReportPeriod> queriedPeriods = new ArrayList<>();

    for (ReportPeriod period : scope.getReportPeriods()) {
      List<ReportEvent> periodEvents = getEventsForPeriod(period);
      period.setSingleData(periodEvents);

      List<ReportPeriodData> data = splitDataInDimensions(period.getData());
      calculateMetrics(data);

      period.setData(data);
      queriedPeriods.add(period);
    }

    return queriedPeriods;
  }

  private List<ReportEvent> getEventsForPeriod(ReportPeriod period) {
    return events.stream()
        .filter(event -> period.contains(event.getDateTime()))
        .collect(Collectors.toList());
  }

  private List<ReportPeriodData> splitDataInDimensions(List<ReportPeriodData> data) {
    List<ReportPeriodData> splitData = new ArrayList<>(data);

    for (ReportDimension dimension : dimensions) {
      splitData = dimension.splitAll(splitData);
    }

    return splitData;
  }

  private void calculateMetrics(List<ReportPeriodData> data) {
    data.forEach(datum -> metrics.forEach(metric -> metric.calculate(datum)));
  }
}
