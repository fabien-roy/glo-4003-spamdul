package ca.ulaval.glo4003.offenses.helpers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.*;

import ca.ulaval.glo4003.offenses.api.dto.OffenseTypeDto;

public class OffenseTypeDtoBuilder {
  private String description = createDescription();
  private String code = createOffenseCode().toString();
  private int amount = (int) createMoney().toDouble();

  private OffenseTypeDtoBuilder() {}

  public static OffenseTypeDtoBuilder anOffenseTypeDto() {
    return new OffenseTypeDtoBuilder();
  }

  public OffenseTypeDto build() {
    OffenseTypeDto offenseTypeDto = new OffenseTypeDto();
    offenseTypeDto.description = description;
    offenseTypeDto.code = code;
    offenseTypeDto.amount = amount;

    return offenseTypeDto;
  }
}
