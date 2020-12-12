package ca.ulaval.glo4003.carboncredits;

import ca.ulaval.glo4003.carboncredits.api.CarbonCreditResource;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCreditRepository;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatusRepository;
import ca.ulaval.glo4003.carboncredits.infrastructure.CarbonCreditRepositoryInMemory;
import ca.ulaval.glo4003.carboncredits.infrastructure.MonthlyPaymentStatusRepositoryInMemory;
import ca.ulaval.glo4003.carboncredits.services.CarbonCreditService;
import ca.ulaval.glo4003.carboncredits.systemtime.ConvertCarbonCreditHandler;
import ca.ulaval.glo4003.initiatives.domain.InitiativeRepository;
import ca.ulaval.glo4003.initiatives.services.InitiativeService;

public class CarbonCreditInjector {
  private final CarbonCreditRepository carbonCreditRepository =
      new CarbonCreditRepositoryInMemory();
  private final MonthlyPaymentStatusRepository monthlyPaymentStatusRepository =
      new MonthlyPaymentStatusRepositoryInMemory();

  public CarbonCreditResource createCarbonCreditResource(
      InitiativeService initiativeService, InitiativeRepository initiativeRepository) {
    CarbonCreditService carbonCreditService =
        createCarbonCreditService(initiativeService, initiativeRepository);
    return new CarbonCreditResource(carbonCreditService);
  }

  public ConvertCarbonCreditHandler createConvertCarbonCreditHandler(
      InitiativeService initiativeService, InitiativeRepository initiativeRepository) {
    CarbonCreditService carbonCreditService =
        createCarbonCreditService(initiativeService, initiativeRepository);
    return new ConvertCarbonCreditHandler(carbonCreditService);
  }

  public CarbonCreditService createCarbonCreditService(
      InitiativeService initiativeService, InitiativeRepository initiativeRepository) {
    return new CarbonCreditService(
        carbonCreditRepository,
        monthlyPaymentStatusRepository,
        initiativeRepository,
        initiativeService);
  }
}
