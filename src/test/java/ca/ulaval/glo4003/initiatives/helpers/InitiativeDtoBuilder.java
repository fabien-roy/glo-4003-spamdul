package ca.ulaval.glo4003.initiatives.helpers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.initiatives.helpers.InitiativeMother.*;

import ca.ulaval.glo4003.initiatives.api.dto.InitiativeDto;

public class InitiativeDtoBuilder {
  private String code = createCode().toString();
  private String name = createName();
  private double allocatedAmount = createMoney().toDouble();

  private InitiativeDtoBuilder() {}

  public static InitiativeDtoBuilder anInitiativeDto() {
    return new InitiativeDtoBuilder();
  }

  public InitiativeDtoBuilder withAllocatedAmount(Double allocatedAmount) {
    this.allocatedAmount = allocatedAmount;
    return this;
  }

  public InitiativeDtoBuilder withInitiativeName(String initiativeName) {
    this.name = initiativeName;
    return this;
  }

  public InitiativeDtoBuilder withInitiativeCode(String initiativeCode) {
    this.code = initiativeCode;
    return this;
  }

  public InitiativeDto build() {
    InitiativeDto initiativeDto = new InitiativeDto();
    initiativeDto.allocatedAmount = allocatedAmount;
    initiativeDto.name = name;
    initiativeDto.code = code;
    return initiativeDto;
  }
}
