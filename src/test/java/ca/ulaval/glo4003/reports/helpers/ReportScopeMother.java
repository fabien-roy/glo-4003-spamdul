package ca.ulaval.glo4003.reports.helpers;

import com.github.javafaker.Faker;

public class ReportScopeMother {
  public static int createYear() {
    return Faker.instance().number().numberBetween(2018, 2022);
  }
}
