package ca.ulaval.glo4003.carboncredits;

import ca.ulaval.glo4003.carboncredits.api.CarbonCreditResource;
import ca.ulaval.glo4003.carboncredits.api.CarbonCreditResourceImplementation;
import ca.ulaval.glo4003.carboncredits.assemblers.CarbonCreditAssembler;
import ca.ulaval.glo4003.carboncredits.assemblers.CarbonCreditMonthlyPaymentStatusAssembler;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCreditRepository;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatusRepository;
import ca.ulaval.glo4003.carboncredits.insfrastructure.CarbonCreditRepositoryInMemory;
import ca.ulaval.glo4003.carboncredits.insfrastructure.MonthlyPaymentStatusRepositoryInMemory;
import ca.ulaval.glo4003.carboncredits.services.CarbonCreditService;

public class CarbonCreditInjector {
  private final CarbonCreditRepository carbonCreditRepository =
      new CarbonCreditRepositoryInMemory();
  private final MonthlyPaymentStatusRepository monthlyPaymentStatusRepository =
      new MonthlyPaymentStatusRepositoryInMemory();

  public CarbonCreditResource createCarbonCreditResource() {
    CarbonCreditAssembler carbonCreditAssembler = new CarbonCreditAssembler();
    CarbonCreditMonthlyPaymentStatusAssembler carbonCreditMonthlyPaymentStatusAssembler =
        new CarbonCreditMonthlyPaymentStatusAssembler();
    CarbonCreditService carbonCreditService =
        new CarbonCreditService(
            carbonCreditRepository,
            carbonCreditAssembler,
            carbonCreditMonthlyPaymentStatusAssembler,
            monthlyPaymentStatusRepository);

    return new CarbonCreditResourceImplementation(carbonCreditService);
  }
}
