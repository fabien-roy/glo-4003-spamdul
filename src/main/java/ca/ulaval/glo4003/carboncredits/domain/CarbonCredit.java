package ca.ulaval.glo4003.carboncredits.domain;

import ca.ulaval.glo4003.funds.domain.Money;
import java.util.Objects;

public class CarbonCredit {
  private final double amount;

  public CarbonCredit(double carbonCredit) {
    this.amount = carbonCredit;
  }

  public CarbonCredit(CarbonCredit carbonCredit) {
    this.amount = carbonCredit.toDouble();
  }

  public static CarbonCredit fromMoney(Money money) {
    return new CarbonCredit(money.toDouble());
  }

  public CarbonCredit plus(CarbonCredit addedCarbonCredit) {
    return new CarbonCredit(this.amount + addedCarbonCredit.toDouble());
  }

  public double toDouble() {
    return amount;
  }

  public static CarbonCredit ZERO() {
    return new CarbonCredit(0);
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    CarbonCredit carbonCredit = (CarbonCredit) object;

    return this.amount == carbonCredit.toDouble();
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
