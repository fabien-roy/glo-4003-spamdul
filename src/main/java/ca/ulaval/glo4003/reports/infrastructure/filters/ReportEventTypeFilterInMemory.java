package ca.ulaval.glo4003.reports.infrastructure.filters;

import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportEventType;
import java.util.List;
import java.util.stream.Collectors;

public class ReportEventTypeFilterInMemory implements ReportFilterInMemory {

  private final ReportEventType reportEventType;

  public ReportEventTypeFilterInMemory(ReportEventType reportEventType) {
    this.reportEventType = reportEventType;
  }

  public ReportEventType getReportEventType() {
    return reportEventType;
  }

  @Override
  public List<ReportEvent> filter(List<ReportEvent> reportEvents) {
    return reportEvents.stream()
        .filter(reportEvent -> reportEvent.getType().equals(reportEventType))
        .collect(Collectors.toList());
  }
}
