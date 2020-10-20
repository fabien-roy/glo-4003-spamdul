package ca.ulaval.glo4003.piggybank.services;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.piggybank.domain.PiggyBankRepository;

public class PiggyBankService {
  private PiggyBankRepository piggyBankRepository;

  public PiggyBankService(PiggyBankRepository piggyBankRepository) {
    this.piggyBankRepository = piggyBankRepository;
  }

  public Money extractPiggyBankAvailableMoney() {
    return piggyBankRepository.takeAll();
  }

  public void extractPiggyBankSpecificAmount(Money money) {
    piggyBankRepository.remove(money);
  }
}
