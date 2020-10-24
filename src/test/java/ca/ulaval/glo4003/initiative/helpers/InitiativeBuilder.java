package ca.ulaval.glo4003.initiative.helpers;

import static ca.ulaval.glo4003.initiative.helpers.InitiativeMother.*;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.initiative.domain.Initiative;
import ca.ulaval.glo4003.initiative.domain.InitiativeCode;

public class InitiativeBuilder {
  private Money allocatedAmount = Money.fromDouble(createAmount());
  private String initiativeName = createName();
  private InitiativeCode initiativeCode = createCode();

  private InitiativeBuilder() {}

  public static InitiativeBuilder aInitiative() {
    return new InitiativeBuilder();
  }

  public InitiativeBuilder withAllocatedAmount(Money allocatedAmount) {
    this.allocatedAmount = allocatedAmount;
    return this;
  }

  public InitiativeBuilder withInitiativeName(String initiativeName) {
    this.initiativeName = initiativeName;
    return this;
  }

  public InitiativeBuilder withInitiativeCode(InitiativeCode initiativeCode) {
    this.initiativeCode = initiativeCode;
    return this;
  }

  public Initiative build() {
    Initiative initiative = new Initiative(initiativeName, allocatedAmount);
    initiative.setInitiativeCode(initiativeCode);
    return initiative;
  }
}
