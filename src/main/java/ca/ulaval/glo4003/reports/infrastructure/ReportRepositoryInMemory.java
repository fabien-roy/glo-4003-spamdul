package ca.ulaval.glo4003.reports.infrastructure;

import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportRepository;
import java.util.ArrayList;
import java.util.List;

public class ReportRepositoryInMemory implements ReportRepository<ReportQueryInMemory> {
  private final List<ReportEvent> events = new ArrayList<>();

  @Override
  public void addEvent(ReportEvent event) {
    events.add(event);
  }

  @Override
  public List<ReportPeriod> getPeriods(ReportQueryInMemory query) {
    query.setEvents(events);
    return query.execute();
  }
}
