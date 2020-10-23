package ca.ulaval.glo4003.carboncredits.domain;

import com.google.common.truth.Truth;
import org.junit.Test;

public class CarbonCreditTest {
  @Test
  public void whenAddingCarbonCredit_thenReturnAddedCarbonCredit() {
    CarbonCredit carbonCredit = CarbonCredit.fromDouble(5);
    CarbonCredit addedCarbonCredit = CarbonCredit.fromDouble(10);
    CarbonCredit expectedCarbonCredit = CarbonCredit.fromDouble(15);

    CarbonCredit actualCarbonCredit = carbonCredit.plus(addedCarbonCredit);

    Truth.assertThat(actualCarbonCredit).isEqualTo(expectedCarbonCredit);
  }
}
