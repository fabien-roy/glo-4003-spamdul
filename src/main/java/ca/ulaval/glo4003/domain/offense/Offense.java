package ca.ulaval.glo4003.domain.offense;

import java.util.Objects;

public class Offense {
  private String reasonText;
  private String reasonCode;
  private int amount;

  public Offense(String reasonText, String reasonCode, int amount) {
    this.reasonText = reasonText;
    this.reasonCode = reasonCode;
    this.amount = amount;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    Offense offense = (Offense) object;
    return reasonCode.equals(offense.reasonCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reasonCode);
  }

  public String getReasonText() {
    return reasonText;
  }

  public String getReasonCode() {
    return reasonCode;
  }

  public int getAmount() {
    return amount;
  }
}
