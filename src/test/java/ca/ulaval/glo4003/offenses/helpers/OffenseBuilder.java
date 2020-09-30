package ca.ulaval.glo4003.domain.offense.helpers;

import static ca.ulaval.glo4003.domain.offense.helpers.OffenseMother.*;

import ca.ulaval.glo4003.offenses.domain.Offense;
import ca.ulaval.glo4003.offenses.domain.OffenseCodes;

public class OffenseBuilder {
  private String description = createReasonText();
  private OffenseCodes code = createReasonCode();
  private double amount = createAmount();

  private OffenseBuilder() {}

  public static OffenseBuilder anOffense() {
    return new OffenseBuilder();
  }

  public OffenseBuilder withReasonText(String reasonText) {
    this.description = reasonText;
    return this;
  }

  public OffenseBuilder withReasonCode(OffenseCodes reasonCode) {
    this.code = reasonCode;
    return this;
  }

  public OffenseBuilder withAmount(double amount) {
    this.amount = amount;
    return this;
  }

  public Offense build() {
    return new Offense(description, code, amount);
  }
}
