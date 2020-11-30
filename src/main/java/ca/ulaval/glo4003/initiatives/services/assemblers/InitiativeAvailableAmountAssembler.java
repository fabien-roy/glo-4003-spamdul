package ca.ulaval.glo4003.initiatives.services.assemblers;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.initiatives.services.dto.InitiativeAvailableAmountDto;

public class InitiativeAvailableAmountAssembler {
  public InitiativeAvailableAmountDto assemble(Money availableAmount) {
    InitiativeAvailableAmountDto initiativeAvailableAmountDto = new InitiativeAvailableAmountDto();
    initiativeAvailableAmountDto.availableAmount = availableAmount.toDouble();
    return initiativeAvailableAmountDto;
  }
}
