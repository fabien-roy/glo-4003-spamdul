package ca.ulaval.glo4003.initiative.helpers;

import static ca.ulaval.glo4003.initiative.helpers.InitiativeMother.*;

import ca.ulaval.glo4003.initiative.api.dto.InitiativeCodeDto;

public class InitiativeCodeDtoBuilder {
  private String initiativeCode = createCode().toString();

  private InitiativeCodeDtoBuilder() {}

  public static InitiativeCodeDtoBuilder aInitiativeCodeDto() {
    return new InitiativeCodeDtoBuilder();
  }

  public InitiativeCodeDtoBuilder withCode(String initiativeCode) {
    this.initiativeCode = initiativeCode;
    return this;
  }

  public InitiativeCodeDto build() {
    InitiativeCodeDto initiativeCodeDto = new InitiativeCodeDto();
    initiativeCodeDto.initiativeCode = initiativeCode;
    return initiativeCodeDto;
  }
}
