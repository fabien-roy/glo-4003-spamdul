package ca.ulaval.glo4003.reports.helpers;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;

public class ReportMetricMother {
  public static ReportMetricType createReportMetricType() {
    return randomEnum(ReportMetricType.class);
  }
}
