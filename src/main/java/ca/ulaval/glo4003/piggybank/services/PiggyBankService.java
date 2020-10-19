package ca.ulaval.glo4003.piggybank.services;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.piggybank.domain.PiggyBankRepository;

public class PiggyBankService {
  private PiggyBankRepository piggyBankRepository;

  public PiggyBankService(PiggyBankRepository piggyBankRepository) {
    this.piggyBankRepository = piggyBankRepository;
  }

  public Money extractPiggyBankAvailableMoney() {
    Money piggyBankAvailableAmount = piggyBankRepository.get();

    piggyBankRepository.remove(piggyBankAvailableAmount);

    return piggyBankAvailableAmount;
  }

  public void extractPiggyBankSpecificAmount(Money money) {
    piggyBankRepository.remove(money);
  }
}
