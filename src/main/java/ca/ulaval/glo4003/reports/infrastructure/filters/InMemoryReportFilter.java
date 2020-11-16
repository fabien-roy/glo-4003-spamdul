package ca.ulaval.glo4003.reports.infrastructure.filters;

import ca.ulaval.glo4003.reports.domain.ReportEvent;
import java.util.List;

public interface InMemoryReportFilter {

  List<ReportEvent> filter(List<ReportEvent> reportEvents);
}
