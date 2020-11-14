package ca.ulaval.glo4003.reports.helpers;

import com.github.javafaker.Faker;

public class ReportPeriodMother {
  public static String createReportPeriodName() {
    return Faker.instance().beer().name();
  }
}
