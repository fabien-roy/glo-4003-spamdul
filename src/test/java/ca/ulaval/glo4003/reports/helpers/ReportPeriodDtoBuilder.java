package ca.ulaval.glo4003.reports.helpers;

import static ca.ulaval.glo4003.reports.helpers.ReportPeriodMother.createReportPeriodName;

import ca.ulaval.glo4003.reports.services.dto.ReportPeriodDto;

public class ReportPeriodDtoBuilder {
  private final String period = createReportPeriodName();

  public static ReportPeriodDtoBuilder aReportPeriodDto() {
    return new ReportPeriodDtoBuilder();
  }

  public ReportPeriodDto build() {
    ReportPeriodDto reportPeriodDto = new ReportPeriodDto();
    reportPeriodDto.period = period;
    return reportPeriodDto;
  }
}
