package ca.ulaval.glo4003.funds.services.converters;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.exceptions.InvalidMoneyException;
import ca.ulaval.glo4003.funds.domain.exceptions.NegativeMoneyException;
import org.junit.Before;
import org.junit.Test;

public class MoneyConverterTest {

  private MoneyConverter moneyConverter;

  private final Money money = createMoney();
  private final Money nullMoney = Money.fromDouble(0);
  private final Money negativeMoney = Money.fromDouble(-1);

  @Before
  public void setUp() {
    moneyConverter = new MoneyConverter();
  }

  @Test
  public void whenConverting_thenReturnMoneyWithAmount() {
    Money assembledMoney = moneyConverter.convert(money.toDouble());

    assertThat(assembledMoney).isEqualTo(money);
  }

  @Test(expected = InvalidMoneyException.class)
  public void givenNullAmountOfMoney_whenConverting_thenThrowInvalidMoneyException() {
    moneyConverter.convert(nullMoney.toDouble());
  }

  @Test(expected = NegativeMoneyException.class)
  public void givenNegativeAmountOfMoney_whenConverting_thenThrowNegativeMoneyException() {
    moneyConverter.convert(negativeMoney.toDouble());
  }
}
