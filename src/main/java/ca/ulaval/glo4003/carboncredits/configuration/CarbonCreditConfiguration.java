package ca.ulaval.glo4003.carboncredits.configuration;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.initiatives.domain.InitiativeCode;

// TODO= : Doesn't this belong to the domain?
public class CarbonCreditConfiguration {
  private static CarbonCreditConfiguration carbonCreditConfiguration = null;

  private static final String CARBON_CREDIT_INITIATIVE_NAME = "Marché du carbone";
  private static final String CARBON_CREDIT_INITIATIVE_CODE = "MCARB";
  private static final Double CARBON_CREDIT_PRICE = 21.81;

  private String carbonCreditInitiativeName;
  private InitiativeCode carbonCreditInitiativeCode;
  private Money carbonCreditPrice;

  private CarbonCreditConfiguration() {
    this.carbonCreditInitiativeName = CARBON_CREDIT_INITIATIVE_NAME;
    this.carbonCreditInitiativeCode = new InitiativeCode(CARBON_CREDIT_INITIATIVE_CODE);
    this.carbonCreditPrice = Money.fromDouble(CARBON_CREDIT_PRICE);
  }

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
