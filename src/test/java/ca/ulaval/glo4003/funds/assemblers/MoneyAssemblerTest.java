package ca.ulaval.glo4003.funds.assemblers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;

import ca.ulaval.glo4003.funds.domain.Money;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class MoneyAssemblerTest {

  private MoneyAssembler moneyAssembler;

  private final Money money = createMoney();

  @Before
  public void setUp() {
    moneyAssembler = new MoneyAssembler();
  }

  @Test
  public void whenAssembling_thenReturnMoneyWithAmount() {
    Money assembledMoney = moneyAssembler.assemble(money.toDouble());

    Truth.assertThat(assembledMoney).isEqualTo(money);
  }
}
