package ca.ulaval.glo4003.funds.services;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;

public class SustainableMobilityProgramBankService {
  private SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository;

  public SustainableMobilityProgramBankService(
      SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository) {
    this.sustainableMobilityProgramBankRepository = sustainableMobilityProgramBankRepository;
  }

  public Money extractSustainableMobilityProgramBankAvailableMoney() {
    return sustainableMobilityProgramBankRepository.takeAll();
  }

  public void extractSustainableMobilityProgramBankSpecificAmount(Money money) {
    sustainableMobilityProgramBankRepository.remove(money);
  }
}
