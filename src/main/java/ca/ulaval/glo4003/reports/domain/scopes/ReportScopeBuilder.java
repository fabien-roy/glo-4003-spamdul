package ca.ulaval.glo4003.reports.domain.scopes;

import ca.ulaval.glo4003.times.domain.TimePeriod;

public class ReportScopeBuilder {

  private ReportScopeType scopeType;
  private TimePeriod period;

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
    // TODO : ReportScopeBuilder.build (switch-case for each ReportScopeType)
    return null;
  }
}
