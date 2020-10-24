package ca.ulaval.glo4003.initiatives.helpers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;

import ca.ulaval.glo4003.initiatives.api.dto.InitiativeAddAllocatedAmountDto;

public class InitiativeAddAllocatedAmountDtoBuilder {
  private double amountToAdd = createMoney().toDouble();

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
