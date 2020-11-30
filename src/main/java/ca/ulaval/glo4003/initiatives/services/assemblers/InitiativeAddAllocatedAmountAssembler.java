package ca.ulaval.glo4003.initiatives.services.assemblers;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.converters.MoneyConverter;
import ca.ulaval.glo4003.initiatives.services.dto.InitiativeAddAllocatedAmountDto;

public class InitiativeAddAllocatedAmountAssembler {

  private MoneyConverter moneyConverter;

  public InitiativeAddAllocatedAmountAssembler(MoneyConverter moneyConverter) {
    this.moneyConverter = moneyConverter;
  }

  public Money assemble(InitiativeAddAllocatedAmountDto InitiativeAddAllocatedAmount) {
    return moneyConverter.convert(InitiativeAddAllocatedAmount.amountToAdd);
  }
}
