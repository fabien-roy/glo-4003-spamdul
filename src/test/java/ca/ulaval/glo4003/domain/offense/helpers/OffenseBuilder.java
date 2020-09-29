package ca.ulaval.glo4003.domain.offense.helpers;

import static ca.ulaval.glo4003.domain.offense.helpers.OffenseMother.*;

import ca.ulaval.glo4003.domain.offense.Offense;

public class OffenseBuilder {
  private String reasonText = createReasonText();
  private String reasonCode = createReasonCode();
  private int amount = createAmount();

  private OffenseBuilder() {}

  public static OffenseBuilder anOffense() {
    return new OffenseBuilder();
  }

  public OffenseBuilder withReasonText(String reasonText) {
    this.reasonText = reasonText;
    return this;
  }

  public OffenseBuilder withReasonCode(String reasonCode) {
    this.reasonCode = reasonCode;
    return this;
  }

  public OffenseBuilder withAmount(int amount) {
    this.amount = amount;
    return this;
  }

  public Offense build() {
    return new Offense(reasonText, reasonCode, amount);
  }
}
