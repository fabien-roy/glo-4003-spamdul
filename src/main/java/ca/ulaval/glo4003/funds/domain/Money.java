package ca.ulaval.glo4003.funds.domain;

import java.util.Objects;

public class Money {
  private final double amount;

  public Money(double amount) {
    this.amount = amount;
  }

  public double toDouble() {
    return amount;
  }

  public static Money ZERO() {
    return new Money(0);
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    Money money = (Money) object;

    return this.amount == money.toDouble();
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(amount);
  }
}
