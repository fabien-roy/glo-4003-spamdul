package ca.ulaval.glo4003.reports.domain.scopes;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.times.domain.TimeCalendar;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ReportScope {

  protected final TimePeriod period;

  public ReportScope(TimePeriod period) {
    this.period = period;
  }

  public TimePeriod getPeriod() {
    return period;
  }

  protected abstract List<TimeCalendar> getCalendars();

  public List<ReportPeriod> getReportPeriods() {
    return getCalendars().stream().map(this::calendarToPeriod).collect(Collectors.toList());
  }

  private ReportPeriod calendarToPeriod(TimeCalendar calendar) {
    return new ReportPeriod(calendar.toString(), calendar.toPeriod());
  }
}
