package ca.ulaval.glo4003.funds.services;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramAllocationCalculator;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class SustainableMobilityProgramAllocationCalculatorTest {

  private SustainableMobilityProgramAllocationCalculator
      sustainableMobilityProgramAllocationCalculator;
  private Money amountCalculated = createMoney();
  private double ratio =
      SustainableMobilityProgramAllocationCalculator.RATIO_KEPT_FOR_SUSTAINABLE_MOBILITY_PROGRAM;

  @Before
  public void setUp() {
    sustainableMobilityProgramAllocationCalculator =
        new SustainableMobilityProgramAllocationCalculator();
  }

  @Test
  public void givenRatio_whenMoneyIsCalculated_thenReturnCalculatedMoney() {
    Money calculatedMoney =
        sustainableMobilityProgramAllocationCalculator.calculate(amountCalculated);

    Money expectedMoney = Money.fromDouble(ratio * amountCalculated.toDouble());

    Truth.assertThat(calculatedMoney).isEqualTo(expectedMoney);
  }
}
