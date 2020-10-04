package ca.ulaval.glo4003.offenses.helpers;

import static ca.ulaval.glo4003.offenses.helpers.OffenseMother.*;

import ca.ulaval.glo4003.offenses.domain.Offense;
import ca.ulaval.glo4003.offenses.domain.OffenseCodes;

public class OffenseBuilder {
  private String description = createDescription();
  private OffenseCodes code = createOffenseCode();
  private double amount = createAmount();

  private OffenseBuilder() {}

  public static OffenseBuilder anOffense() {
    return new OffenseBuilder();
  }

  public Offense build() {
    return new Offense(description, code, amount);
  }
}
