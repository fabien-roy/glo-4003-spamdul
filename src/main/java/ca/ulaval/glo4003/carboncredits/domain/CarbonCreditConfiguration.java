package ca.ulaval.glo4003.carboncredits.domain;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.initiatives.domain.InitiativeCode;

public class CarbonCreditConfiguration {
  private static CarbonCreditConfiguration carbonCreditConfiguration = null;

  private final String carbonCreditInitiativeName = "Marché du carbon";
  private final InitiativeCode carbonCreditInitiativeCode = new InitiativeCode("MCARB");
  private final Money carbonCreditPrice = Money.fromDouble(21.82);

  private CarbonCreditConfiguration() {}

  public static CarbonCreditConfiguration getConfiguration() {
    if (carbonCreditConfiguration == null) {
      carbonCreditConfiguration = new CarbonCreditConfiguration();
    }
    return carbonCreditConfiguration;
  }

  public InitiativeCode getCarbonCreditInitiativeCode() {
    return carbonCreditInitiativeCode;
  }

  public String getCarbonCreditInitiativeName() {
    return carbonCreditInitiativeName;
  }

  public Money getCarbonCreditPrice() {
    return carbonCreditPrice;
  }
}
