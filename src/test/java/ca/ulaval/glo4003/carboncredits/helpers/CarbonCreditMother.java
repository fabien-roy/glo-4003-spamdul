package ca.ulaval.glo4003.carboncredits.helpers;

import ca.ulaval.glo4003.carboncredits.domain.CarbonCredit;
import com.github.javafaker.Faker;

public class CarbonCreditMother {
  public static CarbonCredit createCarbonCredit() {
    double carbonCredit = Faker.instance().number().numberBetween(1, 200);
    return new CarbonCredit(carbonCredit);
  }
}
