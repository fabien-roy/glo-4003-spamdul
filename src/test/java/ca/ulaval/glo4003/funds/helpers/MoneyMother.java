package ca.ulaval.glo4003.funds.helpers;

import ca.ulaval.glo4003.funds.domain.Money;
import com.github.javafaker.Faker;

public class MoneyMother {
  public static Money createMoney() {
    double amount = Faker.instance().number().numberBetween(1, 200);
    return Money.fromDouble(amount);
  }

  public static Money createMoneyBelowAmount(Money maxAmount) {
    double amount = Faker.instance().number().numberBetween(1, (int) maxAmount.toDouble());
    return Money.fromDouble(amount);
  }

  public static double createRatio() {
    return Faker.instance().number().randomDouble(2, 0, 1);
  }
}
