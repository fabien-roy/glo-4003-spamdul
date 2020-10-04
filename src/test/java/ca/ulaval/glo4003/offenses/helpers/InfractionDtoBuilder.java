package ca.ulaval.glo4003.offenses.helpers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.*;

import ca.ulaval.glo4003.offenses.filesystem.dto.InfractionDto;

public class InfractionDtoBuilder {
  private String infraction = createDescription();
  private String code = createOffenseCode().toString();
  private int montant = (int) createMoney().toDouble();

  private InfractionDtoBuilder() {}

  public static InfractionDtoBuilder anInfractionDto() {
    return new InfractionDtoBuilder();
  }

  public InfractionDtoBuilder withCode(String code) {
    this.code = code;
    return this;
  }

  public InfractionDto build() {
    InfractionDto infractionDto = new InfractionDto();
    infractionDto.infraction = infraction;
    infractionDto.code = code;
    infractionDto.montant = montant;
    return infractionDto;
  }
}
