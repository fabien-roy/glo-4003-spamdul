package ca.ulaval.glo4003.funds.infrastructure;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.PiggyBankRepository;
import ca.ulaval.glo4003.funds.exception.PiggyBankInsufficientAmountException;

public class PiggyBankRepositoryInMemory implements PiggyBankRepository {
  private Money piggyBankAmount = Money.ZERO();

  @Override
  public void add(Money money) {
    this.piggyBankAmount = piggyBankAmount.plus(money);
  }

  @Override
  public void remove(Money money) {
    if (piggyBankAmount.isLessThan(money)) throw new PiggyBankInsufficientAmountException();

    this.piggyBankAmount = piggyBankAmount.minus(money);
  }

  @Override
  public Money takeAll() {
    Money remainingAmount = piggyBankAmount;
    piggyBankAmount = Money.ZERO();
    return remainingAmount;
  }

  @Override
  public Money get() {
    return piggyBankAmount;
  }
}
