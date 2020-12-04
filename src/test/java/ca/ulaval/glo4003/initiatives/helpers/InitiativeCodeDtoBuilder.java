package ca.ulaval.glo4003.initiatives.helpers;

import static ca.ulaval.glo4003.initiatives.helpers.InitiativeMother.*;

import ca.ulaval.glo4003.initiatives.services.dto.InitiativeCodeDto;

public class InitiativeCodeDtoBuilder {
  private String initiativeCode = createInitiativeCode().toString();

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
