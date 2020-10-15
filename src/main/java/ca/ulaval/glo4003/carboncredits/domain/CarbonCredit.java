package ca.ulaval.glo4003.carboncredits.domain;

import ca.ulaval.glo4003.funds.domain.Money;
import java.util.Objects;

public class CarbonCredit {
  private final double carbonCreditAmount;

  public CarbonCredit(double carbonCredit) {
    this.carbonCreditAmount = carbonCredit;
  }

  public CarbonCredit(CarbonCredit carbonCredit) {
    this.carbonCreditAmount = carbonCredit.toDouble();
  }

  public static CarbonCredit convertMoneyToCarbonCredit(Money money) {
    return new CarbonCredit(money.toDouble());
  }

  public CarbonCredit plus(CarbonCredit addedCarbonCredit) {
    return new CarbonCredit(this.carbonCreditAmount + addedCarbonCredit.toDouble());
  }

  public CarbonCredit minus(CarbonCredit removedCarbonCredit) {
    return new CarbonCredit(this.carbonCreditAmount - removedCarbonCredit.toDouble());
  }

  public double toDouble() {
    return carbonCreditAmount;
  }

  public static CarbonCredit ZERO() {
    return new CarbonCredit(0);
  }

  public static CarbonCredit fromDouble(double carbonCreditAmount) {
    return new CarbonCredit(carbonCreditAmount);
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    CarbonCredit carbonCredit = (CarbonCredit) object;

    return this.carbonCreditAmount == carbonCredit.toDouble();
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(carbonCreditAmount);
  }

  @Override
  public String toString() {
    return String.valueOf(carbonCreditAmount);
  }
}
