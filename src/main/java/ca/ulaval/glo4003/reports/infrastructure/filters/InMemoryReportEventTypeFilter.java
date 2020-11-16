package ca.ulaval.glo4003.reports.infrastructure.filters;

import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportEventType;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryReportEventTypeFilter implements InMemoryReportFilter {

  private final ReportEventType reportEventType;

  public InMemoryReportEventTypeFilter(ReportEventType reportEventType) {
    this.reportEventType = reportEventType;
  }

  @Override
  public List<ReportEvent> filter(List<ReportEvent> reportEvents) {
    return reportEvents.stream()
        .filter(reportEvent -> reportEvent.getType().equals(reportEventType))
        .collect(Collectors.toList());
  }
}
