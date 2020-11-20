package ca.ulaval.glo4003.reports.helpers;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;

public class ReportDimensionMother {
  public static ReportDimensionType createReportDimensionType() {
    return randomEnum(ReportDimensionType.class);
  }
}
