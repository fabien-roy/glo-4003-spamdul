package ca.ulaval.glo4003.funds.assemblers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.exception.NegativeMoneyException;
import org.junit.Before;
import org.junit.Test;

public class MoneyAssemblerTest {

  private MoneyAssembler moneyAssembler;

  private final Money money = createMoney();
  private final Money negativeMoney = Money.fromDouble(-1);

  @Before
  public void setUp() {
    moneyAssembler = new MoneyAssembler();
  }

  @Test
  public void whenAssembling_thenReturnMoneyWithAmount() {
    Money assembledMoney = moneyAssembler.assemble(money.toDouble());

    assertThat(assembledMoney).isEqualTo(money);
  }

  @Test(expected = NegativeMoneyException.class)
  public void givenNegativeAmountOfMoney_whenAssembling_thenThrowNegativeMoneyException() {
    moneyAssembler.assemble(negativeMoney.toDouble());
  }
}
