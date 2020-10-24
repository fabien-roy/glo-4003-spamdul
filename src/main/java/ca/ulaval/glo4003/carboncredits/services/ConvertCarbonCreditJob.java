package ca.ulaval.glo4003.carboncredits.services;

import ca.ulaval.glo4003.carboncredits.domain.CarbonCredit;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCreditRepository;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;
import ca.ulaval.glo4003.times.systemtime.JobHandler;

public class ConvertCarbonCreditJob implements JobHandler {

  private CarbonCreditRepository carbonCreditRepository;
  private SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository;

  public ConvertCarbonCreditJob(
      CarbonCreditRepository carbonCreditRepository,
      SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository) {
    this.carbonCreditRepository = carbonCreditRepository;
    this.sustainableMobilityProgramBankRepository = sustainableMobilityProgramBankRepository;
  }

  public void invoketest() {
    Money removedMoney = sustainableMobilityProgramBankRepository.takeAll();
    CarbonCredit convertedMoney = CarbonCredit.fromMoney(removedMoney);
    carbonCreditRepository.add(convertedMoney);
  }

  @Override
  public void invoke() {
    sustainableMobilityProgramBankRepository.add(Money.fromDouble(50));
    System.out.println(sustainableMobilityProgramBankRepository.get());
  }
}
