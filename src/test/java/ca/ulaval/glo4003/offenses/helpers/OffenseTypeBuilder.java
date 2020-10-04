package ca.ulaval.glo4003.offenses.helpers;

import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.*;

import ca.ulaval.glo4003.offenses.domain.Offense;
import ca.ulaval.glo4003.offenses.domain.OffenseCodes;

public class OffenseTypeBuilder {
  private String description = createDescription();
  private OffenseCodes code = createOffenseCode();
  private double amount = createAmount();

  private OffenseTypeBuilder() {}

  public static OffenseTypeBuilder anOffenseType() {
    return new OffenseTypeBuilder();
  }

  public OffenseTypeBuilder withCode(OffenseCodes code) {
    this.code = code;
    return this;
  }

  public Offense build() {
    return new Offense(description, code, amount);
  }
}
