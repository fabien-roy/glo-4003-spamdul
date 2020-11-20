package ca.ulaval.glo4003.reports.helpers;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeType;
import com.github.javafaker.Faker;

public class ReportScopeMother {
  public static ReportScopeType createReportScopeType() {
    return randomEnum(ReportScopeType.class);
  }

  public static int createYear() {
    return Faker.instance().number().numberBetween(2018, 2022);
  }
}
