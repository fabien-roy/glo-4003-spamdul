package ca.ulaval.glo4003.reports.domain.scopes;

import ca.ulaval.glo4003.times.domain.TimeMonth;
import ca.ulaval.glo4003.times.domain.TimeYear;

public class ReportScopeFactory {
  public ReportScope createYearlyScope(int year) {
    return new YearlyScope(new TimeYear(year).toPeriod());
  }

  public ReportScope createMonthlyScope() {
    return new MonthlyScope(TimeYear.now().toPeriod());
  }

  public ReportScope createDailyScope(String month) {
    return new DailyScope(new TimeMonth(month).toPeriod());
  }
}
