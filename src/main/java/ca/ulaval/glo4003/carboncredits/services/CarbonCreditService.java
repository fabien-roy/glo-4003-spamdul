package ca.ulaval.glo4003.carboncredits.services;

import ca.ulaval.glo4003.carboncredits.api.dto.CarbonCreditDto;
import ca.ulaval.glo4003.carboncredits.api.dto.CarbonCreditMonthlyPaymentStatusDto;
import ca.ulaval.glo4003.carboncredits.assemblers.CarbonCreditAssembler;
import ca.ulaval.glo4003.carboncredits.assemblers.CarbonCreditMonthlyPaymentStatusAssembler;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCredit;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCreditRepository;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatus;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatusRepository;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.SustainableMobilityProgramBankService;

public class CarbonCreditService {
  private final CarbonCreditRepository carbonCreditRepository;
  private final CarbonCreditAssembler carbonCreditAssembler;
  private final CarbonCreditMonthlyPaymentStatusAssembler carbonCreditMonthlyPaymentStatusAssembler;
  private final MonthlyPaymentStatusRepository monthlyPaymentStatusRepository;
  private final SustainableMobilityProgramBankService sustainableMobilityProgramBankService;

  public CarbonCreditService(
      CarbonCreditRepository carbonCreditRepository,
      CarbonCreditAssembler carbonCreditAssembler,
      CarbonCreditMonthlyPaymentStatusAssembler carbonCreditMonthlyPaymentStatusAssembler,
      MonthlyPaymentStatusRepository monthlyPaymentStatusRepository,
      SustainableMobilityProgramBankService sustainableMobilityProgramBankService) {
    this.carbonCreditRepository = carbonCreditRepository;
    this.carbonCreditAssembler = carbonCreditAssembler;
    this.carbonCreditMonthlyPaymentStatusAssembler = carbonCreditMonthlyPaymentStatusAssembler;
    this.monthlyPaymentStatusRepository = monthlyPaymentStatusRepository;
    this.sustainableMobilityProgramBankService = sustainableMobilityProgramBankService;
  }

  // TODO : Test
  public void modifyCarbonCreditMonthlyPaymentStatus(
      CarbonCreditMonthlyPaymentStatusDto carbonCreditMonthlyPaymentStatusDto) {
    MonthlyPaymentStatus monthlyPaymentStatus =
        carbonCreditMonthlyPaymentStatusAssembler.assemble(carbonCreditMonthlyPaymentStatusDto);
    monthlyPaymentStatusRepository.save(monthlyPaymentStatus);
  }

  public CarbonCreditDto getCarbonCredits() {
    return carbonCreditAssembler.assemble(carbonCreditRepository.get());
  }

  // TODO : Test
  public void extractMoneyFromSustainableMobilityProgramBank() {
    Money sustainableMobilityProgramBankAvailableMoney =
        sustainableMobilityProgramBankService.extractSustainableMobilityProgramBankAvailableMoney();
    CarbonCredit carbonCredit =
        CarbonCredit.fromMoney(sustainableMobilityProgramBankAvailableMoney);
    carbonCreditRepository.add(carbonCredit);
  }
}
