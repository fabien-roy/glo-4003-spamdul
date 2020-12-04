package ca.ulaval.glo4003.initiatives.services.converters;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.converters.MoneyConverter;
import ca.ulaval.glo4003.initiatives.services.dto.InitiativeAddAllocatedAmountDto;

public class InitiativeAddAllocatedAmountConverter {

  private final MoneyConverter moneyConverter;

  public InitiativeAddAllocatedAmountConverter(MoneyConverter moneyConverter) {
    this.moneyConverter = moneyConverter;
  }

  public Money convert(InitiativeAddAllocatedAmountDto InitiativeAddAllocatedAmount) {
    return moneyConverter.convert(InitiativeAddAllocatedAmount.amountToAdd);
  }
}
