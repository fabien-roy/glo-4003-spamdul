package ca.ulaval.glo4003.offenses.domain;

import ca.ulaval.glo4003.funds.domain.Money;
import java.util.Objects;

public class OffenseType {
  private final String description;
  private final OffenseCode code;
  private final Money amount;

  public OffenseType(String description, OffenseCode code, Money amount) {
    this.description = description;
    this.code = code;
    this.amount = amount;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    OffenseType offenseType = (OffenseType) object;
    return code.equals(offenseType.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }

  public String getDescription() {
    return description;
  }

  public OffenseCode getCode() {
    return code;
  }

  public Money getAmount() {
    return amount;
  }
}
