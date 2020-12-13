package ca.ulaval.glo4003.funds.services;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramAllocationCalculator;
import org.junit.Before;
import org.junit.Test;

public class SustainableMobilityProgramAllocationCalculatorTest {

  private SustainableMobilityProgramAllocationCalculator
      sustainableMobilityProgramAllocationCalculator;

  private final Money amountCalculated = createMoney();

  @Before
  public void setUp() {
    sustainableMobilityProgramAllocationCalculator =
        new SustainableMobilityProgramAllocationCalculator();
  }

  @Test
  public void givenRatio_whenMoneyIsCalculated_thenReturnCalculatedMoney() {
    double ratio =
        SustainableMobilityProgramAllocationCalculator.RATIO_KEPT_FOR_SUSTAINABLE_MOBILITY_PROGRAM;
    Money expectedMoney = Money.fromDouble(ratio * amountCalculated.toDouble());

    Money calculatedMoney =
        sustainableMobilityProgramAllocationCalculator.calculate(amountCalculated);

    assertThat(calculatedMoney).isEqualTo(expectedMoney);
  }
}
