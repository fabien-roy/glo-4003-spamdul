package ca.ulaval.glo4003.reports.helpers;

import static ca.ulaval.glo4003.randomizers.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.reports.domain.ReportType;

public class ReportTypeMother {
  public static ReportType createReportType() {
    return randomEnum(ReportType.class);
  }
}
