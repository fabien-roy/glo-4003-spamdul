package ca.ulaval.glo4003.reports.helpers;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.reports.domain.aggregatefunctions.ReportAggregateFunctionType;

public class ReportAggregateFunctionMother {
  public static ReportAggregateFunctionType createReportAggregateFunctionType() {
    return randomEnum(ReportAggregateFunctionType.class);
  }
}
