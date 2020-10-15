package ca.ulaval.glo4003.carboncredits.helpers;

import static ca.ulaval.glo4003.carboncredits.helpers.CarbonCreditMother.createCarbonCredit;

import ca.ulaval.glo4003.carboncredits.domain.CarbonCredit;

public class CarbonCreditBuilder {
  private CarbonCredit carbonCredit = createCarbonCredit();

  private CarbonCreditBuilder() {}

  public static CarbonCreditBuilder aCarbonCredit() {
    return new CarbonCreditBuilder();
  }

  public CarbonCredit build() {
    return new CarbonCredit(carbonCredit);
  }
}
