package ca.ulaval.glo4003.offenses.domain;

import java.util.Objects;

public class Offense {
  private String description;
  private OffenseCodes code;
  private double amount;

  public Offense(String description, OffenseCodes code, double amount) {
    this.description = description;
    this.code = code;
    this.amount = amount;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    Offense offense = (Offense) object;
    return code.equals(offense.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }

  public String getDescription() {
    return description;
  }

  public OffenseCodes getCode() {
    return code;
  }

  public double getAmount() {
    return amount;
  }
}
