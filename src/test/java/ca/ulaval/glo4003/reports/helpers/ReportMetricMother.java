package ca.ulaval.glo4003.reports.helpers;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import com.github.javafaker.Faker;

public class ReportMetricMother {
  public static ReportMetricType createReportMetricType() {
    return randomEnum(ReportMetricType.class);
  }

  public static double createReportMetricValue() {
    return Faker.instance().number().numberBetween(10, 400);
  }
}
