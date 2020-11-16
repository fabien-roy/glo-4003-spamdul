package ca.ulaval.glo4003.reports.helpers;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeType;

public class ReportScopeMother {
  public static ReportScopeType createReportScopeType() {
    return randomEnum(ReportScopeType.class);
  }
}
