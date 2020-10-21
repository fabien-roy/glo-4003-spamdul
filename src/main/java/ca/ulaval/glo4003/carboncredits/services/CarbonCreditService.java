package ca.ulaval.glo4003.carboncredits.services;

import ca.ulaval.glo4003.carboncredits.api.dto.CarbonCreditDto;
import ca.ulaval.glo4003.carboncredits.api.dto.CarbonCreditMonthlyPaymentStatusDto;
import ca.ulaval.glo4003.carboncredits.assemblers.CarbonCreditAssembler;
import ca.ulaval.glo4003.carboncredits.assemblers.CarbonCreditMonthlyPaymentStatusAssembler;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCreditRepository;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatus;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatusRepository;

public class CarbonCreditService {
  private final CarbonCreditRepository carbonCreditRepository;
  private final CarbonCreditAssembler carbonCreditAssembler;
  private final CarbonCreditMonthlyPaymentStatusAssembler carbonCreditMonthlyPaymentStatusAssembler;
  private final MonthlyPaymentStatusRepository monthlyPaymentStatusRepository;

  public CarbonCreditService(
      CarbonCreditRepository carbonCreditRepository,
      CarbonCreditAssembler carbonCreditAssembler,
      CarbonCreditMonthlyPaymentStatusAssembler carbonCreditMonthlyPaymentStatusAssembler,
      MonthlyPaymentStatusRepository monthlyPaymentStatusRepository) {
    this.carbonCreditRepository = carbonCreditRepository;
    this.carbonCreditAssembler = carbonCreditAssembler;
    this.carbonCreditMonthlyPaymentStatusAssembler = carbonCreditMonthlyPaymentStatusAssembler;
    this.monthlyPaymentStatusRepository = monthlyPaymentStatusRepository;
  }

  public void modifyCarbonCreditMonthlyPaymentStatus(
      CarbonCreditMonthlyPaymentStatusDto carbonCreditMonthlyPaymentStatusDto) {
    MonthlyPaymentStatus monthlyPaymentStatus =
        carbonCreditMonthlyPaymentStatusAssembler.assemble(carbonCreditMonthlyPaymentStatusDto);
    monthlyPaymentStatusRepository.save(monthlyPaymentStatus);
  }

  public CarbonCreditDto getCarbonCredits() {
    return carbonCreditAssembler.assemble(carbonCreditRepository.get());
  }

  public void listenTimeToExtractMoneyFromPiggyBanck() {
    // TODO call piggy bank function
  }
}
