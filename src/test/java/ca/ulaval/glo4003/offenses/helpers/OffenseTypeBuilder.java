package ca.ulaval.glo4003.offenses.helpers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.*;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.offenses.domain.OffenseType;

public class OffenseTypeBuilder {
  private String description = createDescription();
  private OffenseCode code = createOffenseCode();
  private Money amount = createMoney();

  private OffenseTypeBuilder() {}

  public static OffenseTypeBuilder anOffenseType() {
    return new OffenseTypeBuilder();
  }

  public OffenseType build() {
    return new OffenseType(description, code, amount);
  }
}
