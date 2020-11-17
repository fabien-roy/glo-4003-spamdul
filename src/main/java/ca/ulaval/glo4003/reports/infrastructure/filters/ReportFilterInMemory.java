package ca.ulaval.glo4003.reports.infrastructure.filters;

import ca.ulaval.glo4003.reports.domain.ReportEvent;
import java.util.List;

public interface ReportFilterInMemory {

  List<ReportEvent> filter(List<ReportEvent> reportEvents);
}
