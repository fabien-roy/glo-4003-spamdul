package ca.ulaval.glo4003.reports.helpers;

import static ca.ulaval.glo4003.reports.helpers.ReportPeriodMother.createReportPeriodName;
import static ca.ulaval.glo4003.times.helpers.TimePeriodBuilder.aTimePeriod;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.times.domain.TimePeriod;

public class ReportPeriodBuilder {
  private final String name = createReportPeriodName();
  private final TimePeriod period = aTimePeriod().build();

  public static ReportPeriodBuilder aReportPeriod() {
    return new ReportPeriodBuilder();
  }

  public ReportPeriod build() {
    return new ReportPeriod(name, period);
  }
}
