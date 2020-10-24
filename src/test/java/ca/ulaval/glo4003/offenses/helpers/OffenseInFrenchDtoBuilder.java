package ca.ulaval.glo4003.offenses.helpers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.*;

import ca.ulaval.glo4003.offenses.filesystem.dto.OffenseInFrenchDto;

public class OffenseInFrenchDtoBuilder {
  private String infraction = createDescription();
  private String code = createOffenseCode().toString();
  private int montant = (int) createMoney().toDouble();

  private OffenseInFrenchDtoBuilder() {}

  public static OffenseInFrenchDtoBuilder anOffenseInFrenchDto() {
    return new OffenseInFrenchDtoBuilder();
  }

  public OffenseInFrenchDto build() {
    OffenseInFrenchDto offenseInFrenchDto = new OffenseInFrenchDto();
    offenseInFrenchDto.infraction = infraction;
    offenseInFrenchDto.code = code;
    offenseInFrenchDto.montant = montant;
    return offenseInFrenchDto;
  }
}
