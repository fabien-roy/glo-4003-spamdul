package ca.ulaval.glo4003.reports.infrastructure;

import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.ReportQuery;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScope;
import ca.ulaval.glo4003.reports.infrastructure.filters.InMemoryReportFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryReportQuery implements ReportQuery {

  private final ReportScope scope;
  private final List<ReportMetric> metrics;
  private final List<ReportDimension> dimensions;
  private final List<InMemoryReportFilter> filters;
  private List<ReportEvent> events;

  public InMemoryReportQuery(
      ReportScope scope,
      List<ReportMetric> metrics,
      List<ReportDimension> dimensions,
      List<InMemoryReportFilter> filters) {
    this.scope = scope;
    this.metrics = metrics;
    this.dimensions = dimensions;
    this.filters = filters;
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

  public List<InMemoryReportFilter> getFilters() {
    return filters;
  }

  public List<ReportPeriod> execute() {
    List<ReportPeriod> queriedPeriods = new ArrayList<>();
    List<ReportEvent> filteredEvents = getFilteredEvents();

    for (ReportPeriod period : scope.getReportPeriods()) {
      List<ReportEvent> periodEvents = getEventsForPeriod(filteredEvents, period);
      period.setSingleData(periodEvents);

      List<ReportPeriodData> data = splitDataInDimensions(period.getData());
      calculateMetrics(data);

      period.setData(data);
      queriedPeriods.add(period);
    }

    return queriedPeriods;
  }

  private List<ReportEvent> getFilteredEvents() {
    List<ReportEvent> filteredEvents = events;

    for (InMemoryReportFilter filter : filters) filteredEvents = filter.filter(filteredEvents);

    return filteredEvents;
  }

  private List<ReportEvent> getEventsForPeriod(List<ReportEvent> events, ReportPeriod period) {
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
