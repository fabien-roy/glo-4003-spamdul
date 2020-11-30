package ca.ulaval.glo4003.funds;

import ca.ulaval.glo4003.funds.services.converters.MoneyConverter;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class FundInjectorTest {

  private FundInjector fundInjector;

  @Before
  public void setUp() {
    fundInjector = new FundInjector();
  }

  @Test
  public void whenCreatingMoneyAssembler_thenReturnIt() {
    MoneyConverter moneyConverter = fundInjector.createMoneyAssembler();

    Truth.assertThat(moneyConverter).isNotNull();
  }
}
