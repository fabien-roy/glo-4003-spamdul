package ca.ulaval.glo4003.initiatives.assembler;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.initiatives.api.dto.InitiativeAddAllocatedAmountDto;

public class InitiativeAddAllocatedAmountAssembler {

  private MoneyAssembler moneyAssembler;

  public InitiativeAddAllocatedAmountAssembler(MoneyAssembler moneyAssembler) {
    this.moneyAssembler = moneyAssembler;
  }

  public Money assemble(InitiativeAddAllocatedAmountDto InitiativeAddAllocatedAmount) {
    return moneyAssembler.assemble(InitiativeAddAllocatedAmount.amountToAdd);
  }
}
