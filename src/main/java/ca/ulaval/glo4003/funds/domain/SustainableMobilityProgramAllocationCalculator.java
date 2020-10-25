package ca.ulaval.glo4003.funds.domain;

public class SustainableMobilityProgramAllocationCalculator {
  public static final double RATIO_KEPT_FOR_SUSTAINABLE_MOBILITY_PROGRAM = 0.4;

  public Money calculate(Money money) {
    return money.multiply(RATIO_KEPT_FOR_SUSTAINABLE_MOBILITY_PROGRAM);
  }
}
