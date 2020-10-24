package ca.ulaval.glo4003.initiatives.assembler;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.initiatives.api.dto.InitiativeAvailableAmountDto;

public class InitiativeAvailableAmountAssembler {
  public InitiativeAvailableAmountDto assemble(Money availableAmount) {
    InitiativeAvailableAmountDto initiativeAvailableAmountDto = new InitiativeAvailableAmountDto();
    initiativeAvailableAmountDto.availableAmount = availableAmount.toDouble();
    return initiativeAvailableAmountDto;
  }
}
