package ca.ulaval.glo4003.carboncredits.services;

import ca.ulaval.glo4003.carboncredits.api.dto.CarbonCreditDto;
import ca.ulaval.glo4003.carboncredits.api.dto.MonthlyPaymentStatusDto;
import ca.ulaval.glo4003.carboncredits.assemblers.CarbonCreditAssembler;
import ca.ulaval.glo4003.carboncredits.assemblers.MonthlyPaymentStatusAssembler;
import ca.ulaval.glo4003.carboncredits.configuration.CarbonCreditConfiguration;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCredit;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCreditRepository;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatus;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatusRepository;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.SustainableMobilityProgramBankService;
import ca.ulaval.glo4003.initiatives.domain.Initiative;
import ca.ulaval.glo4003.initiatives.domain.InitiativeAddedAllocatedAmountObserver;

public class CarbonCreditService implements InitiativeAddedAllocatedAmountObserver {
  private final CarbonCreditRepository carbonCreditRepository;
  private final CarbonCreditAssembler carbonCreditAssembler;
  private final MonthlyPaymentStatusAssembler monthlyPaymentStatusAssembler;
  private final MonthlyPaymentStatusRepository monthlyPaymentStatusRepository;
  private final SustainableMobilityProgramBankService sustainableMobilityProgramBankService;

  public CarbonCreditService(
      CarbonCreditRepository carbonCreditRepository,
      CarbonCreditAssembler carbonCreditAssembler,
      MonthlyPaymentStatusAssembler monthlyPaymentStatusAssembler,
      MonthlyPaymentStatusRepository monthlyPaymentStatusRepository,
      SustainableMobilityProgramBankService sustainableMobilityProgramBankService) {
    this.carbonCreditRepository = carbonCreditRepository;
    this.carbonCreditAssembler = carbonCreditAssembler;
    this.monthlyPaymentStatusAssembler = monthlyPaymentStatusAssembler;
    this.monthlyPaymentStatusRepository = monthlyPaymentStatusRepository;
    this.sustainableMobilityProgramBankService = sustainableMobilityProgramBankService;
  }

  public CarbonCreditDto getCarbonCredits() {
    return carbonCreditAssembler.assemble(carbonCreditRepository.get());
  }

  public void modifyMonthlyPaymentStatus(MonthlyPaymentStatusDto monthlyPaymentStatusDto) {
    MonthlyPaymentStatus monthlyPaymentStatus =
        monthlyPaymentStatusAssembler.assemble(monthlyPaymentStatusDto);
    monthlyPaymentStatusRepository.save(monthlyPaymentStatus);
  }

  public void extractMoneyFromSustainableMobilityProgramBank() {
    if (monthlyPaymentStatusRepository.get().equals(MonthlyPaymentStatus.ENABLE)) {
      Money sustainableMobilityProgramBankAvailableMoney =
          sustainableMobilityProgramBankService
              .extractSustainableMobilityProgramBankAvailableMoney();
      // TODO don't extract just get amountAvailable
    }
  }

  @Override
  public void listenInitiativeAddedAllocatedAmount(
      Initiative initiative, Money addedAllocatedAmount) {
    if (initiative
        .getCode()
        .equals(CarbonCreditConfiguration.getConfiguration().getCarbonCreditInitiativeCode())) {
      purchaseCarbonCredit(addedAllocatedAmount);
    }
  }

  private void purchaseCarbonCredit(Money amountInMoney) {
    CarbonCredit carbonCredit = CarbonCredit.fromMoney(amountInMoney);
    carbonCreditRepository.add(carbonCredit);
  }
}
