package ca.ulaval.glo4003.carboncredits.services;

import ca.ulaval.glo4003.carboncredits.configuration.CarbonCreditConfiguration;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCredit;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCreditRepository;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatus;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatusRepository;
import ca.ulaval.glo4003.carboncredits.services.assemblers.CarbonCreditAssembler;
import ca.ulaval.glo4003.carboncredits.services.assemblers.MonthlyPaymentStatusAssembler;
import ca.ulaval.glo4003.carboncredits.services.dto.CarbonCreditDto;
import ca.ulaval.glo4003.carboncredits.services.dto.MonthlyPaymentStatusDto;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;
import ca.ulaval.glo4003.initiatives.domain.Initiative;
import ca.ulaval.glo4003.initiatives.domain.InitiativeAddedAllocatedAmountObserver;
import ca.ulaval.glo4003.initiatives.services.InitiativeService;

public class CarbonCreditService implements InitiativeAddedAllocatedAmountObserver {
  private final CarbonCreditRepository carbonCreditRepository;
  private final CarbonCreditAssembler carbonCreditAssembler;
  private final MonthlyPaymentStatusAssembler monthlyPaymentStatusAssembler;
  private final MonthlyPaymentStatusRepository monthlyPaymentStatusRepository;
  private final SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository;
  private final InitiativeService initiativeService;

  public CarbonCreditService(
      CarbonCreditRepository carbonCreditRepository,
      CarbonCreditAssembler carbonCreditAssembler,
      MonthlyPaymentStatusAssembler monthlyPaymentStatusAssembler,
      MonthlyPaymentStatusRepository monthlyPaymentStatusRepository,
      SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository,
      InitiativeService initiativeService) {
    this.carbonCreditRepository = carbonCreditRepository;
    this.carbonCreditAssembler = carbonCreditAssembler;
    this.monthlyPaymentStatusAssembler = monthlyPaymentStatusAssembler;
    this.monthlyPaymentStatusRepository = monthlyPaymentStatusRepository;
    this.sustainableMobilityProgramBankRepository = sustainableMobilityProgramBankRepository;
    this.initiativeService = initiativeService;
  }

  public CarbonCreditDto getCarbonCredits() {
    return carbonCreditAssembler.assemble(carbonCreditRepository.get());
  }

  public void modifyMonthlyPaymentStatus(MonthlyPaymentStatusDto monthlyPaymentStatusDto) {
    MonthlyPaymentStatus monthlyPaymentStatus =
        monthlyPaymentStatusAssembler.assemble(monthlyPaymentStatusDto);
    monthlyPaymentStatusRepository.save(monthlyPaymentStatus);
  }

  public void allocateRemainingFundToCarbonCreditInitiative() {
    if (monthlyPaymentStatusRepository.get().equals(MonthlyPaymentStatus.ENABLE)) {
      Money sustainableMobilityProgramBankAvailableMoney =
          sustainableMobilityProgramBankRepository.get();
      initiativeService.addAllocatedAmountToInitiative(
          CarbonCreditConfiguration.getConfiguration().getCarbonCreditInitiativeCode(),
          sustainableMobilityProgramBankAvailableMoney);
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
