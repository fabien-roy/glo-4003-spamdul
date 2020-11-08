package ca.ulaval.glo4003.reports.helpers;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.reports.domain.ReportEventType;

public class ReportEventMother {
  public static ReportEventType createReportEventType() {
    return randomEnum(ReportEventType.class);
  }
}
