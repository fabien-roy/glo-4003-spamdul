package ca.ulaval.glo4003.initiative.helpers;

import static ca.ulaval.glo4003.initiative.helpers.InitiativeMother.*;

import ca.ulaval.glo4003.initiative.api.dto.InitiativeDto;

public class InitiativeDtoBuilder {
  private Double allocatedAmount = createAmount();
  private String initiativeName = createName();
  private String initiativeCode = createCode().toString();

  private InitiativeDtoBuilder() {}

  public static InitiativeDtoBuilder aInitiativeDto() {
    return new InitiativeDtoBuilder();
  }

  public InitiativeDtoBuilder withAllocatedAmount(Double allocatedAmount) {
    this.allocatedAmount = allocatedAmount;
    return this;
  }

  public InitiativeDtoBuilder withInitiativeName(String initiativeName) {
    this.initiativeName = initiativeName;
    return this;
  }

  public InitiativeDtoBuilder withInitiativeCode(String initiativeCode) {
    this.initiativeCode = initiativeCode;
    return this;
  }

  public InitiativeDto build() {
    InitiativeDto initiativeDto = new InitiativeDto();
    initiativeDto.allocatedAmount = allocatedAmount;
    initiativeDto.initiativeName = initiativeName;
    initiativeDto.initiativeCode = initiativeCode;
    return initiativeDto;
  }
}
