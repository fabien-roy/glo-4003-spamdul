package ca.ulaval.glo4003.carboncredits;

import ca.ulaval.glo4003.carboncredits.api.CarbonCreditResource;
import ca.ulaval.glo4003.carboncredits.assemblers.CarbonCreditAssembler;
import ca.ulaval.glo4003.carboncredits.assemblers.MonthlyPaymentStatusAssembler;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCreditRepository;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatusRepository;
import ca.ulaval.glo4003.carboncredits.infrastructure.CarbonCreditRepositoryInMemory;
import ca.ulaval.glo4003.carboncredits.infrastructure.MonthlyPaymentStatusRepositoryInMemory;
import ca.ulaval.glo4003.carboncredits.services.CarbonCreditService;
import ca.ulaval.glo4003.carboncredits.systemtime.ConvertCarbonCreditHandler;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;
import ca.ulaval.glo4003.initiatives.services.InitiativeService;

public class CarbonCreditInjector {
  private final CarbonCreditRepository carbonCreditRepository =
      new CarbonCreditRepositoryInMemory();
  private final MonthlyPaymentStatusRepository monthlyPaymentStatusRepository =
      new MonthlyPaymentStatusRepositoryInMemory();

  public CarbonCreditRepository getCarbonCreditRepository() {
    return carbonCreditRepository;
  }

  public CarbonCreditResource createCarbonCreditResource(
      InitiativeService initiativeService,
      SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository) {
    CarbonCreditService carbonCreditService =
        createCarbonCreditService(initiativeService, sustainableMobilityProgramBankRepository);
    return new CarbonCreditResource(carbonCreditService);
  }

  public ConvertCarbonCreditHandler createConvertCarbonCreditHandler(
      InitiativeService initiativeService,
      SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository) {
    CarbonCreditService carbonCreditService =
        createCarbonCreditService(initiativeService, sustainableMobilityProgramBankRepository);
    return new ConvertCarbonCreditHandler(carbonCreditService);
  }

  public CarbonCreditService createCarbonCreditService(
      InitiativeService initiativeService,
      SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository) {
    CarbonCreditAssembler carbonCreditAssembler = new CarbonCreditAssembler();
    MonthlyPaymentStatusAssembler monthlyPaymentStatusAssembler =
        new MonthlyPaymentStatusAssembler();

    return new CarbonCreditService(
        carbonCreditRepository,
        carbonCreditAssembler,
        monthlyPaymentStatusAssembler,
        monthlyPaymentStatusRepository,
        sustainableMobilityProgramBankRepository,
        initiativeService);
  }
}
