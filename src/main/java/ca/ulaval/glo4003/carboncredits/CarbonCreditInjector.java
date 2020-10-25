package ca.ulaval.glo4003.carboncredits;

import ca.ulaval.glo4003.carboncredits.api.CarbonCreditResource;
import ca.ulaval.glo4003.carboncredits.api.CarbonCreditResourceImplementation;
import ca.ulaval.glo4003.carboncredits.assemblers.CarbonCreditAssembler;
import ca.ulaval.glo4003.carboncredits.assemblers.CarbonCreditMonthlyPaymentStatusAssembler;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCreditRepository;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatusRepository;
import ca.ulaval.glo4003.carboncredits.infrastructure.CarbonCreditRepositoryInMemory;
import ca.ulaval.glo4003.carboncredits.infrastructure.MonthlyPaymentStatusRepositoryInMemory;
import ca.ulaval.glo4003.carboncredits.services.CarbonCreditService;
import ca.ulaval.glo4003.carboncredits.systemtime.ConvertCarbonCreditHandler;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;
import ca.ulaval.glo4003.funds.infrastructure.SustainableMobilityProgramBankRepositoryInMemory;
import ca.ulaval.glo4003.funds.services.SustainableMobilityProgramBankService;

public class CarbonCreditInjector {
  private final CarbonCreditRepository carbonCreditRepository =
      new CarbonCreditRepositoryInMemory();
  private final MonthlyPaymentStatusRepository monthlyPaymentStatusRepository =
      new MonthlyPaymentStatusRepositoryInMemory();
  private final SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository =
      new SustainableMobilityProgramBankRepositoryInMemory();
  private final SustainableMobilityProgramBankService sustainableMobilityProgramBankService =
      new SustainableMobilityProgramBankService(sustainableMobilityProgramBankRepository);

  public CarbonCreditRepository getCarbonCreditRepository() {
    return carbonCreditRepository;
  }

  public CarbonCreditResource createCarbonCreditResource() {
    CarbonCreditService carbonCreditService = createCarbonCreditService();
    return new CarbonCreditResourceImplementation(carbonCreditService);
  }

  public ConvertCarbonCreditHandler createConvertCarbonCreditHandler() {
    CarbonCreditService carbonCreditService = createCarbonCreditService();
    return new ConvertCarbonCreditHandler(carbonCreditService);
  }

  private CarbonCreditService createCarbonCreditService() {
    CarbonCreditAssembler carbonCreditAssembler = new CarbonCreditAssembler();
    CarbonCreditMonthlyPaymentStatusAssembler carbonCreditMonthlyPaymentStatusAssembler =
        new CarbonCreditMonthlyPaymentStatusAssembler();

    return new CarbonCreditService(
        carbonCreditRepository,
        carbonCreditAssembler,
        carbonCreditMonthlyPaymentStatusAssembler,
        monthlyPaymentStatusRepository,
        sustainableMobilityProgramBankService);
  }
}
