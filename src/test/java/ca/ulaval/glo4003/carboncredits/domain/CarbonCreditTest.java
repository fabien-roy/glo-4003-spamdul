package ca.ulaval.glo4003.carboncredits.domain;

import ca.ulaval.glo4003.funds.domain.Money;
import com.google.common.truth.Truth;
import org.junit.Test;

public class CarbonCreditTest {
  private final CarbonCredit CARBON_CREDIT = new CarbonCredit(20);

  @Test
  public void whenAddingCarbonCredit_thenReturnAddedCarbonCredit() {
    CarbonCredit carbonCredit = new CarbonCredit(5);
    CarbonCredit addedCarbonCredit = new CarbonCredit(10);
    CarbonCredit expectedCarbonCredit = new CarbonCredit(15);

    CarbonCredit actualCarbonCredit = carbonCredit.plus(addedCarbonCredit);

    Truth.assertThat(actualCarbonCredit).isEqualTo(expectedCarbonCredit);
  }

  @Test
  public void whenConvertingMoneyToCarbonCredit_thenReturnCarbonCredit() {
    Money money = new Money(20);

    CarbonCredit actualCarbonCredit = CarbonCredit.fromMoney(money);

    Truth.assertThat(actualCarbonCredit).isEqualTo(CARBON_CREDIT);
  }
}
