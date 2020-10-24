package ca.ulaval.glo4003.initiative.domain;

import ca.ulaval.glo4003.funds.domain.Money;

public class Initiative {
  private InitiativeCode code;
  private final String name;
  private Money allocatedAmount;

  public Initiative(String name, Money allocatedAmount) {
    this.name = name;
    this.allocatedAmount = allocatedAmount;
  }

  public InitiativeCode getCode() {
    return code;
  }

  public void setCode(InitiativeCode code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public Money getAllocatedAmount() {
    return allocatedAmount;
  }

  public void addAllocatedAmount(Money addedAmount) {
    allocatedAmount = allocatedAmount.plus(addedAmount);
  }
}
