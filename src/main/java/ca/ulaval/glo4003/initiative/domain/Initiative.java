package ca.ulaval.glo4003.initiative.domain;

import ca.ulaval.glo4003.funds.domain.Money;

public class Initiative {
  private InitiativeCode initiativeCode;
  private String initiativeName;
  private Money allocatedAmount;

  public Initiative(InitiativeCode initiativeCode, String initiativeName) {
    this.initiativeCode = initiativeCode;
    this.initiativeName = initiativeName;
    this.allocatedAmount = Money.ZERO();
  }

  public void addAllocatedAmount(Money addedAmount) {
    allocatedAmount = allocatedAmount.plus(addedAmount);
  }

  public InitiativeCode getInitiativeCode() {
    return initiativeCode;
  }

  public Money getAllocatedAmount() {
    return allocatedAmount;
  }

  public String getInitiativeName() {
    return initiativeName;
  }
}
