package ca.ulaval.glo4003.reports.domain.scopes;

import ca.ulaval.glo4003.times.domain.TimeCalendar;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.util.List;

public class DailyScope extends ReportScope {

  public DailyScope(TimePeriod period) {
    super(period);
  }

  @Override
  protected List<TimeCalendar> getCalendars() {
    return period.getDays();
  }
}
