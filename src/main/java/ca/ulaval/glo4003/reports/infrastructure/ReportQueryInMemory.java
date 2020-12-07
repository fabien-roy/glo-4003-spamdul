package ca.ulaval.glo4003.reports.infrastructure;

import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.ReportQuery;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScope;
import ca.ulaval.glo4003.reports.infrastructure.filters.ReportFilterInMemory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReportQueryInMemory implements ReportQuery {

  private ReportScope scope;
  private List<ReportMetric> metrics;
  private List<ReportDimension> dimensions;
  private List<ReportFilterInMemory> filters;
  private List<ReportEvent> events;

  // TODO #326 : Delete this if only constructor
  public ReportQueryInMemory() {}

  // TODO #326 : Delete this
  public ReportQueryInMemory(
      ReportScope scope,
      List<ReportMetric> metrics,
      List<ReportDimension> dimensions,
      List<ReportFilterInMemory> filters) {
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

  public void setScope(ReportScope scope) {
    this.scope = scope;
  }

  public List<ReportMetric> getMetrics() {
    return metrics;
  }

  public void addMetric(ReportMetric metric) {
    metrics.add(metric);
  }

  public List<ReportDimension> getDimensions() {
    return dimensions;
  }

  public void addDimension(ReportDimension dimension) {
    dimensions.add(dimension);
  }

  public List<ReportFilterInMemory> getFilters() {
    return filters;
  }

  public void addFilter(ReportFilterInMemory filter) {
    filters.add(filter);
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

    for (ReportFilterInMemory filter : filters) filteredEvents = filter.filter(filteredEvents);

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
