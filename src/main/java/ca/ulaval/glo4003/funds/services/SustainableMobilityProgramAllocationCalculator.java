package ca.ulaval.glo4003.funds.services;

import ca.ulaval.glo4003.funds.domain.Money;

public class SustainableMobilityProgramAllocationCalculator {

  public Money calculate(Money money) {
    return Money.multiply(money, 0.4);
  }
}
