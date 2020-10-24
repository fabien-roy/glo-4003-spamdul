package ca.ulaval.glo4003.funds.domain;

import java.util.Objects;

public class Money {
  private final double amount;

  public Money(double amount) {
    this.amount = amount;
  }

  public Money(Money money) {
    this.amount = money.toDouble();
  }

  public Money plus(Money addedMoney) {
    return new Money(amount + addedMoney.toDouble());
  }

  public Money minus(Money money) {
    return new Money(amount - money.toDouble());
  }

  public double toDouble() {
    return amount;
  }

  public static Money zero() {
    return new Money(0);
  }

  public static Money fromDouble(double amount) {
    return new Money(amount);
  }

  public boolean isLessThan(Money money) {
    return this.amount < money.toDouble();
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

  @Override
  public String toString() {
    return String.valueOf(amount);
  }
}
