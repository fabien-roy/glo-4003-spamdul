package ca.ulaval.glo4003.funds.services;

import ca.ulaval.glo4003.funds.domain.Money;

public class SustainableMobilityProgramAllocationCalculator {

  public Money calculate(Money money, double multiplicationFactor) {
    return Money.multiply(money, multiplicationFactor);
  }
}
