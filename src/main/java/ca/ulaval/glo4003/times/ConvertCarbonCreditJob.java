package ca.ulaval.glo4003.times;

import ca.ulaval.glo4003.carboncredits.domain.CarbonCredit;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCreditRepository;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;

public class ConvertCarbonCreditJob implements JobHandler {

  private CarbonCreditRepository carbonCreditRepository;
  private SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository;

  public ConvertCarbonCreditJob(
      CarbonCreditRepository carbonCreditRepository,
      SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository) {
    this.carbonCreditRepository = carbonCreditRepository;
    this.sustainableMobilityProgramBankRepository = sustainableMobilityProgramBankRepository;
  }

  @Override
  public void invoke() {
    Money removedMoney = sustainableMobilityProgramBankRepository.takeAll();
    CarbonCredit convertedMoney = CarbonCredit.fromMoney(removedMoney);
    carbonCreditRepository.add(convertedMoney);
  }
}
