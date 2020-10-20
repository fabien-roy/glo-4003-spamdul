package ca.ulaval.glo4003.initiative.helpers;

import static ca.ulaval.glo4003.initiative.helpers.InitiativeMother.createAmount;

import ca.ulaval.glo4003.initiative.api.dto.InitiativeAddAllocatedAmountDto;

public class InitiativeAddAllocatedAmountDtoBuilder {
  private Double amountToAdd = createAmount();

  private InitiativeAddAllocatedAmountDtoBuilder() {}

  public static InitiativeAddAllocatedAmountDtoBuilder aInitiativeAddAllocatedAmountDTO() {
    return new InitiativeAddAllocatedAmountDtoBuilder();
  }

  public InitiativeAddAllocatedAmountDto build() {
    InitiativeAddAllocatedAmountDto initiativeAddAllocatedAmountDto =
        new InitiativeAddAllocatedAmountDto();
    initiativeAddAllocatedAmountDto.amountToAdd = amountToAdd;
    return initiativeAddAllocatedAmountDto;
  }
}
