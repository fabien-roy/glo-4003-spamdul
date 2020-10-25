package ca.ulaval.glo4003.initiatives.helpers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.initiatives.helpers.InitiativeMother.*;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.initiatives.domain.Initiative;
import ca.ulaval.glo4003.initiatives.domain.InitiativeCode;

public class InitiativeBuilder {
  private String name = createInitiativeName();
  private InitiativeCode code = createInitiativeCode();
  private Money allocatedAmount = createMoney();

  private InitiativeBuilder() {}

  public static InitiativeBuilder anInitiative() {
    return new InitiativeBuilder();
  }

  public InitiativeBuilder withAllocatedAmount(Money allocatedAmount) {
    this.allocatedAmount = allocatedAmount;
    return this;
  }

  public InitiativeBuilder withInitiativeName(String initiativeName) {
    this.name = initiativeName;
    return this;
  }

  public InitiativeBuilder withInitiativeCode(InitiativeCode initiativeCode) {
    this.code = initiativeCode;
    return this;
  }

  public Initiative build() {
    Initiative initiative = new Initiative(name, allocatedAmount);
    initiative.setCode(code);
    return initiative;
  }
}
