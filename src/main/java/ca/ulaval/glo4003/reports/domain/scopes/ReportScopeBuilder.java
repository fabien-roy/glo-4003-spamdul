package ca.ulaval.glo4003.reports.domain.scopes;

import ca.ulaval.glo4003.times.domain.TimePeriod;

public class ReportScopeBuilder {

  private ReportScopeType scopeType = ReportScopeType.YEARLY; // TODO : Should yearly be default?
  private TimePeriod period; // TODO : Should there be a default period? The whole year?

  public ReportScopeBuilder aScope() {
    return new ReportScopeBuilder();
  }

  public ReportScopeBuilder withType(ReportScopeType reportScopeType) {
    this.scopeType = reportScopeType;
    return this;
  }

  public ReportScopeBuilder withPeriod(TimePeriod timePeriod) {
    this.period = timePeriod;
    return this;
  }

  public ReportScope build() {
    switch (scopeType) {
      default:
      case YEARLY:
        return new YearlyScope(period);
      case MONTHLY:
        return new MonthlyScope(period);
      case DAILY:
        return new DailyScope(period);
    }
  }
}
