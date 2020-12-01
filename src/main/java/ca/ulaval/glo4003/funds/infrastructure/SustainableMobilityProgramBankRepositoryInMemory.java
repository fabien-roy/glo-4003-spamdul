package ca.ulaval.glo4003.funds.infrastructure;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;
import ca.ulaval.glo4003.funds.domain.exceptions.InsufficientAvailableMoneyException;

public class SustainableMobilityProgramBankRepositoryInMemory
    implements SustainableMobilityProgramBankRepository {
  private Money sustainableMobilityProgramBankAmount = Money.zero();

  @Override
  public void add(Money money) {
    this.sustainableMobilityProgramBankAmount = sustainableMobilityProgramBankAmount.plus(money);
  }

  @Override
  public void remove(Money money) {
    if (sustainableMobilityProgramBankAmount.isLessThan(money))
      throw new InsufficientAvailableMoneyException();

    this.sustainableMobilityProgramBankAmount = sustainableMobilityProgramBankAmount.minus(money);
  }

  @Override
  public Money takeAll() {
    Money remainingAmount = sustainableMobilityProgramBankAmount;
    sustainableMobilityProgramBankAmount = Money.zero();
    return remainingAmount;
  }

  @Override
  public Money get() {
    return sustainableMobilityProgramBankAmount;
  }
}
