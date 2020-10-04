package ca.ulaval.glo4003.offenses.helpers;

import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.*;

import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.offenses.domain.OffenseType;

public class OffenseTypeBuilder {
  private String description = createDescription();
  private OffenseCode code = createOffenseCode();
  private double amount = createAmount();

  private OffenseTypeBuilder() {}

  public static OffenseTypeBuilder anOffenseType() {
    return new OffenseTypeBuilder();
  }

  public OffenseTypeBuilder withCode(OffenseCode code) {
    this.code = code;
    return this;
  }

  public OffenseType build() {
    return new OffenseType(description, code, amount);
  }
}
